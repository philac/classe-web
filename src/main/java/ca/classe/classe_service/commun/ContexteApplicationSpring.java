package ca.classe.classe_service.commun;

import org.apache.commons.lang.Validate;
import org.springframework.context.ApplicationContext;

/**
 * Impl�mentation du contexte application avec un applicationContext Spring.
 * 
 * @author frpol9
 * @since 0.1
 */
public class ContexteApplicationSpring implements ContexteApplication {

    private final ApplicationContext contexteSpring;
    private BusEvenement bus;

    public ContexteApplicationSpring(ApplicationContext contexteSpring) {
        Validate.notNull(contexteSpring);
        this.contexteSpring = contexteSpring;
    }

    public ContexteApplicationSpring(ApplicationContext contexteSpring, String nomServiceBusEvenement,
            String nomServiceAutorisation, String nomServiceEnvironnement) {
        this(contexteSpring);

        if (nomServiceBusEvenement != null) {
            this.bus = getService(BusEvenement.class, nomServiceBusEvenement);
        }
    }

    public ContexteApplicationSpring(ApplicationContext contexteSpring, BusEvenement busEvenement) {
        Validate.notNull(contexteSpring);
        this.contexteSpring = contexteSpring;

        this.bus = busEvenement;
    }

    @Override
    public <T> T getService(Class<T> clazz) {
        Validate.notNull(clazz);
        return contexteSpring.getBean(clazz);
    }

    @Override
    public <T> T getService(Class<T> clazz, String nom) {
        Validate.notNull(clazz);
        Validate.notNull(nom);
        return contexteSpring.getBean(nom, clazz);
    }

    public ApplicationContext getContexteSpring() {
        return contexteSpring;
    }

    @Override
    public BusEvenement getBusEvenement() {
        if (bus == null) {
            bus = getService(BusEvenement.class);
        }

        return bus;
    }
}
