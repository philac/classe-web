package ca.classe.classe_service.commun;


public interface Evenement<O>
{
    public TypeEvenement<O> getType();
    public void notifierObservateur(O observateur);
}