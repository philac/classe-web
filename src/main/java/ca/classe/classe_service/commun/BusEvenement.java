package ca.classe.classe_service.commun;

public interface BusEvenement {

    /**
    *  Lève un évènement.
    * 
    *  @param O type de l'observateur
    *  @param evenement L'évènement
    */ 
    <O> void notifier(Evenement<O> evenement);

    /**
    *  Enregistre un observateur de type DIRECT {@link TypeObservateur.DIRECT} et synchrone sur un type d'évènement.
    *
    *  @param O type de l'observateur.
    *  @param typeEvenement type d'évènement
    *  @param observateur L'observateur
    */ 
    <O> void observer(TypeEvenement<O> typeEvenement, O observateur);

    /**
    *  Enregistre un observateur sur un type d'évènement.
    * 
    *  @param O type de l'observateur.
    *  @param typeEvenement type d'évènement
    *  @param observateur L'observateur
    *  @param type À quel moment est-ce que l'observateur doit être appelé
    *  @param synchrone Est-ce que l'observateur est appelé de manière synchrone ou asynchrone
    */ 
    <O> void observer(TypeEvenement<O> typeEvenement, O observateur, TypeObservateur type, boolean synchrone);

    /**
    *  Enlever un observateur.
    * 
    *  @param O type de l'observateur
    *  @param observateur L'observateur
    */ 
    <O> void enleverObservateur(O observateur);

}
