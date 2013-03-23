package ca.classe.classe_web.page.subject;

import ca.classe.classe_modele.Competency;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.PresenterBase;
import ca.classe.classe_web.page.enums.PageNames;
import ca.classe.classe_web.page.subject.evenement.EvenementCancel;
import ca.classe.classe_web.page.subject.evenement.EvenementDeleteCompetency;
import ca.classe.classe_web.page.subject.evenement.EvenementModifyCompetency;
import ca.classe.classe_web.page.subject.evenement.EvenementModifySubject;

import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class PresenterSubject extends
		PresenterBase<ModelSubject, ViewCompetencyTable> 
implements EvenementModifyCompetency.Observer, EvenementDeleteCompetency.Observer, EvenementModifySubject.Observer, EvenementCancel.Observer {
	
	private Subject subject;
	private ViewModifySubject viewModifySubject;
	private ViewAddCompetency viewAddCompetency;

	public PresenterSubject(ModelSubject model, ViewCompetencyTable view, ViewModifySubject viewModifySubject, ViewAddCompetency viewAddCompetency,
			BusEvenement busEvenement) {
		super(model, view, busEvenement);
		this.viewModifySubject = viewModifySubject;
		this.viewAddCompetency = viewAddCompetency;
	}

	@Override
	protected void observer() {
		busEvenement.observer(EvenementModifyCompetency.TYPE, this);
		busEvenement.observer(EvenementDeleteCompetency.TYPE, this);
		busEvenement.observer(EvenementModifySubject.TYPE, this);
		busEvenement.observer(EvenementCancel.TYPE, this);
	}

	@Override
	public Layout getComponent() {
		VerticalLayout mainLayout = new VerticalLayout();
		Label title = new Label("Modification de la matière");
		view.setEntities(subject.getCompetencies());
		viewModifySubject.setEntity(subject);
		viewAddCompetency.setSubject(subject);
		mainLayout.addComponent(title);
		mainLayout.addComponent(viewModifySubject.getLayout());
		mainLayout.addComponent(viewAddCompetency.getLayout());
		mainLayout.addComponent(view.getLayout());
		return mainLayout;
	}

	public void setSubject(Integer subjectId, com.vaadin.server.Page parentPage) {
		subject = model.loadSubject(subjectId);
		parentPage.setTitle(PageNames.MODIFY.getTitle() + " de la matière " + subject.getName());		
	}

	@Override
	public void onModifyCompetency(Competency competency) {
		model.modify(competency);
		view.displayTotal();
	}

	
	
	@Override
	public void onDelete(Competency competency) {
		model.delete(competency);
		view.setEntities(model.loadSubject(subject.getId()).getCompetencies());
		viewAddCompetency.setSubject(model.loadSubject(subject.getId()));
	}

	@Override
	public void onModify(Object source, Subject subject) {
		if (source.equals(viewModifySubject)) {
			subject.setCompetencies(model.loadSubject(subject.getId()).getCompetencies());
			model.modify(subject);
			viewModifySubject.setEntity(model.loadSubject(subject.getId()));
		} else if (source.equals(viewAddCompetency)) {
			model.modify(subject);
			view.setEntities(model.loadSubject(subject.getId()).getCompetencies());
			viewAddCompetency.reinitFields();
		}
	}

	@Override
	public void onCancel(Object source) {
		if (source == viewAddCompetency) {
			viewAddCompetency.reinitFields();
		}
	}

}
