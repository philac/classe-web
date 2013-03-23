package ca.classe.classe_web.page.classe;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.PresenterBase;

import com.vaadin.ui.Layout;

public class PresenterClasses extends PresenterBase<ModelClass, ViewClassSelection>{

	private ViewManageClassMarks viewManageClassMark;
	
	public PresenterClasses(ModelClass model, ViewClassSelection view,
			ViewManageClassMarks viewManageClassMarks, BusEvenement busEvenement) {
		super(model, view, busEvenement);
		this.viewManageClassMark = viewManageClassMarks;
	}

	@Override
	protected void observer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Layout getComponent() {
		// TODO Auto-generated method stub
		return null;
	}

}
