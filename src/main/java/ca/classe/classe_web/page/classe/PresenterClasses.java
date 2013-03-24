package ca.classe.classe_web.page.classe;

import java.util.HashSet;
import java.util.Set;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.PresenterBase;
import ca.classe.classe_web.page.classe.events.EventRequestAddClass;
import ca.classe.classe_web.page.classe.events.EventRequestClassModification;
import ca.classe.classe_web.page.classe.events.EventSelectClass;
import ca.classe.classe_web.page.classe.events.EventSelectSubject;
import ca.classe.classe_web.page.subject.evenement.EvenementCancel;

import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class PresenterClasses extends PresenterBase<ModelClass, ViewClassSelection>
	implements EventSelectSubject.Observer, EventSelectClass.Observer,
	EventAddClass.Observer, EvenementCancel.Observer, EventRequestAddClass.Observer,
	EventRequestClassModification.Ovserver {
	
	private ViewManageClassMarks viewManageClassMark;
	private ViewModifyClass viewModifyClass;
	private ViewAddClass viewAddClass;	
	private VerticalLayout layout = new VerticalLayout();
	
	public PresenterClasses(ModelClass model, ViewClassSelection view,
			ViewManageClassMarks viewManageClassMarks, ViewAddClass viewAddClass,
			ViewModifyClass viewModifyClass, BusEvenement busEvenement) {
		super(model, view, busEvenement);
		this.viewManageClassMark = viewManageClassMarks;
		this.viewAddClass = viewAddClass;
		this.viewModifyClass = viewModifyClass;
	}

	@Override
	protected void observer() {
		busEvenement.observer(EventSelectSubject.TYPE, this);
		busEvenement.observer(EventSelectClass.TYPE, this);
		busEvenement.observer(EventRequestAddClass.TYPE, this);
		busEvenement.observer(EvenementCancel.TYPE, this);
		busEvenement.observer(EventAddClass.TYPE, this);
		busEvenement.observer(EventRequestClassModification.TYPE, this);
	}

	@Override
	public Layout getComponent() {
		view.setSubjects(model.loadAllSubjects());
		layout.addComponent(view.getLayout());
		layout.addComponent(viewAddClass.getLayout());
		layout.addComponent(viewModifyClass.getLayout());
		viewModifyClass.getLayout().setVisible(false);
		viewAddClass.setSubjects(model.loadAllSubjects());
		viewAddClass.init();
		viewAddClass.getLayout().setVisible(false);
		return layout;
	}

	@Override
	public void onSelect(Subject subject) {
		Set<Classe> classes = new HashSet<Classe>();
		if (subject != null) {
			Subject currentSubject = model.loadByIdWithClasses(subject.getId());
			classes = currentSubject.getClasses();
		}
		view.setClasses(classes);
	}

	@Override
	public void onSelectClass(Classe classe) {
		view.showModifyLink(classe);
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

	@Override
	public void onRequestClassModification(Classe classe) {
		viewModifyClass.setClasse(classe);
		viewModifyClass.getLayout().setVisible(classe != null);
	}

}
