package ca.classe.classe_web.page.classe;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.PresenterBase;
import ca.classe.classe_web.page.classe.events.EventRequestAddClass;
import ca.classe.classe_web.page.classe.events.EventSelectClass;
import ca.classe.classe_web.page.classe.events.EventSelectSubject;
import ca.classe.classe_web.page.subject.evenement.EvenementCancel;

import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class PresenterClasses extends PresenterBase<ModelClass, ViewClassSelection>
	implements EventSelectSubject.Observer, EventSelectClass.Observer,
	EventAddClass.Observer, EvenementCancel.Observer, EventRequestAddClass.Observer {
	
	private ViewManageClassMarks viewManageClassMark;
	private ViewAddClass viewAddClass;	
	private VerticalLayout layout = new VerticalLayout();
	
	public PresenterClasses(ModelClass model, ViewClassSelection view,
			ViewManageClassMarks viewManageClassMarks, ViewAddClass viewAddClass, BusEvenement busEvenement) {
		super(model, view, busEvenement);
		this.viewManageClassMark = viewManageClassMarks;
		this.viewAddClass = viewAddClass;
	}

	@Override
	protected void observer() {
		busEvenement.observer(EventSelectSubject.TYPE, this);
		busEvenement.observer(EventSelectClass.TYPE, this);
		busEvenement.observer(EventRequestAddClass.TYPE, this);
		busEvenement.observer(EvenementCancel.TYPE, this);
		busEvenement.observer(EventAddClass.TYPE, this);
	}

	@Override
	public Layout getComponent() {
		view.setSubjects(model.loadAllSubjects());
		layout.addComponent(view.getLayout());
		layout.addComponent(viewAddClass.getLayout());
		viewAddClass.setSubjects(model.loadAllSubjects());
		viewAddClass.init();
		viewAddClass.getLayout().setVisible(false);
		return layout;
	}

	@Override
	public void onSelect(Subject subject) {
		Subject currentSubject = model.loadByIdWithClasses(subject.getId());
		view.setClasses(currentSubject.getClasses());
	}

	@Override
	public void onSelectClass(Classe classe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCancel(Object source) {
		if (source == viewAddClass) {
			viewAddClass.reinitFields();
			viewAddClass.getLayout().setVisible(false);
		}
	}

	@Override
	public void onAddClass(Object source, Classe bean) {
		if (source == viewAddClass) {
			model.addClass(bean);
			onCancel(source);
			onSelect(bean.getSubject());
			view.selectClass(bean.getSubject(), bean);
		}
	}

	@Override
	public void onRequestAddClass() {
		viewAddClass.getLayout().setVisible(true);
	}

}
