package ca.classe.classe_modele;

import java.io.Serializable;

public abstract class BaseEntite<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1367897799011823066L;
	
	private T id;
	
	public T getId() {
		return id;
	}
	
	public void setId(T id) {
		this.id = id;
	}

}
