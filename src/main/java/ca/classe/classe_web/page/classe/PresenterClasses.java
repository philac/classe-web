package ca.classe.classe_web.page.classe;

import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.PresenterBase;
import ca.classe.classe_web.page.classe.events.EventSelectSubject;

import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class PresenterClasses extends PresenterBase<ModelClass, ViewClassSelection>
	implements EventSelectSubject.Observer {
	private ViewManageClassMarks viewManageClassMark;
	
	public PresenterClasses(ModelClass model, ViewClassSelection view,
			ViewManageClassMarks viewManageClassMarks, BusEvenement busEvenement) {
		super(model, view, busEvenement);
		this.viewManageClassMark = viewManageClassMarks;
	}

	@Override
	protected void observer() {
		busEvenement.observer(EventSelectSubject.TYPE, this);
		
	}

	@Override
	public Layout getComponent() {
		VerticalLayout layout = new VerticalLayout();
		view.setSubjects(model.loadAllSubjects());
		layout.addComponent(view.getLayout());
		return null;
	}

	@Override
	public void onSelect(Subject subject) {
		Subject currentSubject = model.loadByIdWithClasses(subject.getId());
		view.setClasses(currentSubject.getClasses());
	}

}
