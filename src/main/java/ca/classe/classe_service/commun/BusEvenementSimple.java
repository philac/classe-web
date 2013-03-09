package ca.classe.classe_service.commun;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Implémentation d'un bus d'évènement ne supportant pas les transactions ni les observateurs asynchrones.
 *
 * @author frpol9
 * @since 0.1
 */
public class BusEvenementSimple implements BusEvenement {

    private Map<TypeEvenement<?>, List<Object>> enregistrements = new ConcurrentHashMap<TypeEvenement<?>, List<Object>>();

    public synchronized void reinitialiser() {
        enregistrements.clear();
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized <O> void notifier(Evenement<O> evenement) {
        List<Object> observateurs = enregistrements.get(evenement.getType());

        if (observateurs == null) {
            return;
        }

        for (Object observateur : observateurs) {
            evenement.notifierObservateur((O) observateur);
        }
    }

    @Override
    public <O> void observer(TypeEvenement<O> typeEvenement, O observateur) {
        observer(typeEvenement, observateur, TypeObservateur.DIRECT, true);

    }

    @Override
    public synchronized <O> void observer(TypeEvenement<O> typeEvenement, O observateur, TypeObservateur type,
            boolean synchrone) {
        List<Object> observateurs = enregistrements.get(typeEvenement);

        if (observateurs == null) {
            observateurs = new ArrayList<Object>();
            enregistrements.put(typeEvenement, observateurs);
        }

        observateurs.add(observateur);
    }

    @Override
    public synchronized <O> void enleverObservateur(O observateur) {

        for (TypeEvenement<?> typeEvenement : this.enregistrements.keySet()) {

            List<Object> observateurs = enregistrements.get(typeEvenement);

            if (observateurs == null) {
                return;
            }

            for (Iterator<Object> iter = observateurs.iterator(); iter.hasNext();) {
                Object observateurEnregistre = iter.next();
                if (observateur.equals(observateurEnregistre)) {
                    iter.remove();
                }
            }
        }

    }
}
