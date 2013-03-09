package ca.classe.classe_service.commun;

import org.apache.commons.lang.Validate;

/**
 * Classe principale impl�mentant les patterns de - service locator - s�curit� - observateur
 *
 * La classe utilise un singleton de type ContexteApplication pour d�l�guer la majorit� des appels. La classe doit avoir
 * �t� initialis�e en appelant setInstance avant de pouvoir l'utiliser. Dans le cas contraire, des NullPointerException
 * vont �tre lanc�s.
 *
 * @author frpol9
 * @since 0.1
 */
public class ContexteApplicationUtils {
    /**
     * D�termine si le thread courant est ex�cuter en tant que syst�me.
     */
    private static final ThreadLocal<Boolean> executerEnTantQueSysteme = new ThreadLocal<Boolean>();

    /**
     * Le singleton du contexte application
     */
    private static ContexteApplication contexteApplication;

    /**
     * Obtient un service impl�mentant l'interface demand�e.
     *
     * @param <T> type de l'interface demand�e
     * @param clazz classe de l'interface demand�e
     * @return L'impl�mentation du service
     * @throws RuntimeException
     *             Si le service n'existe pas ou il existe plus d'un service impl�mentant l'interface
     */
    public static <T> T getService(Class<T> clazz) {
        return contexteApplication.getService(clazz);
    }

    /**
     * Obtient un service impl�mentant l'interface demand�e et ayant un nom d�termin�.
     *
     * @param <T> type de l'interface demand�e
     * @param clazz classe de l'interface demand�e
     * @param nom nom du service � retourner
     * @return L'impl�mentation du service
     * @throws RuntimeException Si le service n'existe pas
     */
    public static <T> T getService(Class<T> clazz, String nom) {
        return contexteApplication.getService(clazz, nom);
    }

    /**
     * Permet d'ex�cuter une t�che avec un contexte privil�gi� qui poss�de temporairement tous les droits. L'ex�cution
     * se fait de mani�re synchrone.
     *
     * @param tache
     *            La t�che � ex�cuter
     */
    public static void executerEnTantQueSysteme(Runnable tache) {
        Validate.notNull(tache);

        Boolean valeurAvantExecution = executerEnTantQueSysteme.get();

        executerEnTantQueSysteme.set(Boolean.TRUE);

        try {
            tache.run();
        } finally {
            executerEnTantQueSysteme.set(valeurAvantExecution);
        }
    }

    public static boolean isExecuteEnTantQueSysteme() {
        return executerEnTantQueSysteme.get() == Boolean.TRUE;
    }

    /**
     * L�ve un �v�nement dans le bus d'�v�nement par d�faut.
     *
     * @param <O> type de l'observateur associ� � l'�v�nement
     * @param evenement L'�v�nement
     */
    public static <O> void notifier(Evenement<O> evenement) {
        if (contexteApplication != null) {
            contexteApplication.getBusEvenement().notifier(evenement);
        }
    }

    /**
     * Enregistre un observateur sur un type d'�v�nement
     *
     * @param <O> type d'observateur
     * @param typeEvenement type de l'�v�nement � observer (g�n�ralement une constante)
     * @param observateur L'observateur
     */
    public static <O> void observer(TypeEvenement<O> typeEvenement, O observateur) {
        contexteApplication.getBusEvenement().observer(typeEvenement, observateur, TypeObservateur.DIRECT, true);
    }

    /**
     * Enregistre un observateur sur un type d'�v�nement
     *
     * @param <O> type d'observateur
     * @param typeEvenement type de l'�v�nement � observer (g�n�ralement une constante)
     * @param observateur L'observateur
     * @param type D�fini � quel moment invoquer l'observateur
     * @param synchrone Ex�cuter l'observateur de mani�re synchrone ou asynchrone
     */
//    public static <O> void observer(TypeEvenement<O> typeEvenement, O observateur, TypeObservateur type,
//            boolean synchrone) {
//        contexteApplication.getBusEvenement().observer(typeEvenement, observateur, type, synchrone);
//    }

    /**
     * @return Retourne l'instance courante du contexte d'application
     */
    public synchronized static ContexteApplication getInstance() {
        return contexteApplication;
    }

    /**
     * Assigne le contexte application courant
     *
     * @param contexte Le contexte application
     */
    public synchronized static void setInstance(ContexteApplication contexte) {
        ContexteApplicationUtils.contexteApplication = contexte;

        if (contexte != null) {
            notifier(new EvenementContexteInitialise(contexte));
        }
    }
}