package ca.classe.classe_service.commun;


public class EvenementContexteInitialise implements Evenement<EvenementContexteInitialise.Observer> {

    public static interface Observer {

        void onContexteInitialise(EvenementContexteInitialise evenement);

    }

    public static final TypeEvenement<Observer> TYPE = new TypeEvenement<Observer>();

    private final ContexteApplication contexte;

    public EvenementContexteInitialise(ContexteApplication contexte) {
        this.contexte = contexte;
    }

    public ContexteApplication getContexteApplication() {
        return contexte;
    }

    @Override
    public TypeEvenement<Observer> getType() {
        return TYPE;
    }

    @Override
    public void notifierObservateur(Observer observateur) {
        observateur.onContexteInitialise(this);
    }
}