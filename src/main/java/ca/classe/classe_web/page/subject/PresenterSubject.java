package ca.classe.classe_web.page.subject;

import ca.classe.classe_modele.Competency;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.PresenterBase;
import ca.classe.classe_web.page.enums.PageNames;
import ca.classe.classe_web.page.subject.evenement.EvenementModifyCompetency;

import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class PresenterSubject extends
		PresenterBase<ModelSubject, ViewCompetencyTable> 
implements EvenementModifyCompetency.Observer {
	
	private Subject subject;
	private ViewModifySubject viewModifySubject;

	public PresenterSubject(ModelSubject model, ViewCompetencyTable view, ViewModifySubject viewModifySubject,
			BusEvenement busEvenement) {
		super(model, view, busEvenement);
		this.viewModifySubject = viewModifySubject;
	}

	@Override
	protected void observer() {
		busEvenement.observer(EvenementModifyCompetency.TYPE, this);
	}

	@Override
	public Layout getComponent() {
		VerticalLayout mainLayout = new VerticalLayout();
		Label title = new Label("Modification de la matière");
		view.setEntities(subject.getCompetencies());
		viewModifySubject.setEntity(subject);
		mainLayout.addComponent(title);
		mainLayout.addComponent(viewModifySubject.getLayout());
		mainLayout.addComponent(view.getLayout());
		return mainLayout;
	}

	public void setSubject(Integer subjectId, com.vaadin.server.Page parentPage) {
		subject = model.loadSubject(subjectId);
		parentPage.setTitle(PageNames.MODIFY.getTitle() + " de la matière " + subject.getName());		
	}

	@Override
	public void onModifyCompetency(Competency competency) {
		// TODO Faire une méthode dans le modèle pour modifer la compétence. 
	}

}
