package ca.classe.classe_web.page.classe;

import ca.classe.classe_service.entite.ServiceClass;
import ca.classe.classe_web.mvp.ModelBaseImpl;

public class ModelClass extends ModelBaseImpl<ServiceClass> {

	private static ModelClass modelClass;
	
	private ModelClass() {
		
	}
	
	public static ModelClass getInstance() {
		if (modelClass == null) {
			modelClass = new ModelClass();
		}
		return modelClass;
	}

}
