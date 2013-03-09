package ca.classe.classe_web.mvp;

import ca.classe.classe_service.commun.BusEvenement;

import com.vaadin.ui.Layout;


public abstract class PresenterBase<M extends ModelBase, V extends ViewBase> {

	protected M model;
	
	protected V view;
	
	protected BusEvenement busEvenement;
	
	public PresenterBase(M model, V view, BusEvenement busEvenement) {
		this.model = model;
		this.view = view;
		this.busEvenement = busEvenement;
		observer();
	}
	
	protected abstract void observer();
		

	public abstract Layout getComponent();
	
}
