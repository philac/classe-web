package ca.classe.classe_service.commun;

public enum TypeObservateur {
    /**
     * Indique que l'observateur doit �tre invoqu� imm�diatement.
     */
    DIRECT,

    /**
     * Indique que l'observateur est invoqu� juste avant le commit de la transaction en cours.
     * 
     * Si l'�v�nement ne se produit pas dans un contexte transactionnel, le comportement est le m�me que
     * <code>DIRECT<code>.
     */
    AVANT_COMMIT,

    /**
     * Indique que l'observateur est invoqu� imm�diatement apr�s le commit de la transaction en cours.
     * 
     * Si l'�v�nement ne se produit pas dans un contexte transactionnel, le comportement est le m�me que
     * <code>DIRECT<code>.
     */
    APRES_COMMIT,

    /**
     * Indique que l'observateur est invoqu� imm�diatement apr�s le rollback de la transaction en cours.
     * 
     * Si l'�v�nement ne se produit pas dans un contexte transactionnel, le comportement est le m�me que
     * <code>DIRECT<code>.
     */
    APRES_ROLLBACK
};