package ca.classe.classe_web.page.subject;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.PresenterBase;
import ca.classe.classe_web.page.subject.evenement.EvenementAddSubject;
import ca.classe.classe_web.page.subject.evenement.EvenementCancel;
import ca.classe.classe_web.page.subject.evenement.EvenementDeleteSubject;
import ca.classe.classe_web.page.subject.evenement.EvenementModifySubject;

import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class PresenterSubjects extends PresenterBase<ModelSubject, ViewSubjectTable>
implements EvenementAddSubject.Observer, EvenementDeleteSubject.Observer, EvenementCancel.Observer,
EvenementModifySubject.Observer
{

	private VerticalLayout mainLayout;
	private ViewAddSubject viewAddSubject;
	
	public PresenterSubjects(ModelSubject model, ViewSubjectTable view, BusEvenement busEvenement) {
		super(model, view, busEvenement);
	}
	
	private void initLayout() {
		mainLayout = new VerticalLayout();
		viewAddSubject = new ViewAddSubject(busEvenement);
		mainLayout.addComponent(new Label("<h1>Les Matières</h1>", ContentMode.HTML));
		mainLayout.addComponent(view.getLayout());
		mainLayout.addComponent(viewAddSubject.getLayout());
		view.setEntities(model.loadSubjects());
		view.init();
	}

	@Override
	public Layout getComponent() {
		initLayout();
		return mainLayout;
	}

	@Override
	public void onAjouter(BeanItem<Subject> beanItem) {
		model.add(beanItem.getBean());
		view.setEntities(model.loadSubjects());
		viewAddSubject.reinitFields();
	}

	@Override
	protected void observer() {
		busEvenement.observer(EvenementAddSubject.TYPE, this);
		busEvenement.observer(EvenementDeleteSubject.TYPE, this);
		busEvenement.observer(EvenementCancel.TYPE, this);
		busEvenement.observer(EvenementModifySubject.TYPE, this);
	}

	@Override
	public void onDelete(Subject subject) {
		model.delete(subject);
		view.setEntities(model.loadSubjects());
	}

	@Override
	public void onCancel() {
		viewAddSubject.reinitFields();
	}

	@Override
	public void onModify(Object source, Subject subject) {
		if (source.equals(view)) {
			model.modify(subject);
		}
	}
}
