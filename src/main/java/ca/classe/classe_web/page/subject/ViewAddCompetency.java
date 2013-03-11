package ca.classe.classe_web.page.subject;

import ca.classe.classe_modele.Competency;
import ca.classe.classe_modele.Competency_;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_web.page.commons.AddingView;
import ca.classe.classe_web.page.subject.evenement.EvenementCancel;
import ca.classe.classe_web.page.subject.evenement.EvenementCancel.Observer;
import ca.classe.classe_web.page.subject.evenement.EvenementModifySubject;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.TextField;

public class ViewAddCompetency extends AddingView<Competency> {

	private TextField nameField = new TextField();
	private Subject subject;
	
	public ViewAddCompetency(BusEvenement busEvenement) {
		super(busEvenement);
		init();
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	@Override
	protected void init() {
		if (!initialized) {
			nameField.setNullRepresentation("");
			addField("Nom", nameField);
			super.init();
			initialized = true;
		}
	}

	@Override
	protected void bindData() {
		beanItem = new BeanItem<Competency>(new Competency());
		nameField.setPropertyDataSource(beanItem.getItemProperty(Competency_.name.getName()));

	}

	@Override
	protected Evenement<?> getAddEvent() {
		subject.getCompetencies().add(beanItem.getBean());
		beanItem.getBean().setSubject(subject);
		return new EvenementModifySubject(subject);
	}

	@Override
	public void reinitFields() {
		nameField.setValue(null);
	}

	@Override
	protected Evenement<Observer> getCancelEvent() {
		return new EvenementCancel();
	}

}
