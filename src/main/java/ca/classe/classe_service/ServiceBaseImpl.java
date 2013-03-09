package ca.classe.classe_service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ServiceBaseImpl implements ServiceBase {

    /**
     * Méthode privée qui valide les annotations des attributs d'un entité
     * 
     * @param entite
     *            l'attribut de publication saisi à valider
     * @return un Set<ConstraintViolation<ENTITE>>
     */
    protected <ENTITE> Set<ConstraintViolation<ENTITE>> valider(ENTITE entite) {
        ValidatorFactory fabrique = Validation.byDefaultProvider().configure().traversableResolver(
                new CustomTraversableResolver()).buildValidatorFactory();

        Validator validateur = fabrique.getValidator();
        Set<ConstraintViolation<ENTITE>> violations = validateur.validate(entite);
        return violations;
    }
}
