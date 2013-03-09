package ca.classe.classe_service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.metamodel.Attribute;

import ca.classe.classe_modele.BaseEntite;

public interface DaoBase <E extends BaseEntite<PK>, PK extends Serializable> {

    /**
     * Charger toutes les entités.
     * 
     * @return la liste de l'ensemble des entités.
     */
    List<E> loadAll();

    /**
     * Charger toutes les entités avec leurs associations de premier niveau.
     * 
     * @return la liste de l'ensemble des entités.
     */
    List<E> chargerToutAvecAssociations();

    /**
     * Charger toutes les entités avec les associations passées en paramètres.
     * 
     * @param associations
     *            la liste des associations voulues.
     * @return la liste de l'ensemble des entités avec les associations voulues chargées.
     */
    List<E> chargerToutAvecAssociations(Attribute<? super E, ?>... associations);

    /**
     * Charger une entité sans aucunes associations de chargées.
     * 
     * @param id
     *            l'identifiant de l'entité.
     * @return l'entité.
     */
    E chargerParId(PK id);

    /**
     * Charger une entité avec toutes les associations de premier niveau.
     * 
     * @param id
     *            l'identifiant de l'entité.
     * @return l'entité.
     */
    E chargerParIdAvecAssociations(PK id);

    /**
     * Charger une entité avec les associations passées en paramètres.
     * 
     * @param id
     *            l'identifiant de l'entité.
     * @param associations
     *            la liste des associations voulues.
     * @return l'entités avec les associations voulues chargées.
     */
    E chargerParIdAvecAssociations(PK id, Attribute<? super E, ?>... associations);

    /**
     * Ajouter une entité.
     * 
     * @param entite
     *            l'entité à ajouter.
     */
    void ajouter(final E entite);

    /**
     * Modifier une entité.
     * 
     * @param entite
     *            l'entité à modifier.
     * @return l'entité modifié.
     */
    E modifier(final E entite);

    /**
     * Supprimer une entié
     * 
     * @param entite
     */
    void supprimer(E entite);

    /**
     * Compter le nombre d'élément dans la base de données.
     */
    Long compter();
}
