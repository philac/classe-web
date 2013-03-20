package ca.classe.classe_web.page.subject;

import ca.classe.classe_modele.Subject;
import ca.classe.classe_modele.Subject_;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_web.page.commons.AddingView;
import ca.classe.classe_web.page.subject.evenement.EvenementAddSubject;
import ca.classe.classe_web.page.subject.evenement.EvenementCancel;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.TextField;

public class ViewAddSubject extends AddingView<Subject> {

	private static final long serialVersionUID = -6900994575760293584L;
	private TextField nameField = new TextField();
	
	public ViewAddSubject(BusEvenement busEvenement) {
		super(busEvenement);
		init();
	}
	
	@Override
	protected void init() {
		if (!initialized) {
			nameField.setNullRepresentation("");
			addField("Nom :", nameField);
			super.init();
			initialized = true;
		}
	}

	@Override
	protected Evenement<EvenementAddSubject.Observer> getAddEvent() {
		return new EvenementAddSubject(beanItem);
	}

	@Override
	protected Evenement<EvenementCancel.Observer> getCancelEvent() {
		return new EvenementCancel();
	}


	@Override
	protected void bindData() {
		beanItem = new BeanItem<Subject>(new Subject());
		nameField.setPropertyDataSource(beanItem.getItemProperty(Subject_.name.getName()));
	}



	@Override
	public void reinitFields() {
		nameField.setValue(null);
	}

}
