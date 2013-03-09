package ca.classe.classe_web.mvp;

import ca.classe.classe_service.commun.BusEvenement;

import com.vaadin.ui.Layout;

public abstract class ViewBaseImpl implements ViewBase {
	
	protected BusEvenement busEvenement;
	
	public ViewBaseImpl(BusEvenement busEvenement) {
		this.busEvenement = busEvenement;
	}
	
	public abstract Layout getLayout();
}
