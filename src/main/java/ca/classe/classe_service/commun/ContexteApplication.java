package ca.classe.classe_service.commun;


/**
 * Interface d�crivant les responsabilit�s d'un contexte d'application. Le contexte d'application sert � encapsuler le
 * pattern du service locator, la s�curit� et un bus d'�v�nements.
 *
 * @author frpol9
 * @since 0.1
 */
public interface ContexteApplication {

    <T> T getService(Class<T> clazz);

    <T> T getService(Class<T> clazz, String nom);

    BusEvenement getBusEvenement();

}
