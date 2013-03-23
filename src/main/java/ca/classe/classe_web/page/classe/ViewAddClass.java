package ca.classe.classe_web.page.classe;

import java.util.List;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_modele.Subject_;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_web.page.commons.AddingView;
import ca.classe.classe_web.page.subject.evenement.EvenementCancel.Observer;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;

public class ViewAddClass extends AddingView<Classe> {
	
	private TextField nameField = new TextField();
	private TextField levelField = new TextField();
	private ComboBox subjectsCombobox = new ComboBox();
	private BeanItemContainer<Subject> bicSubject = new BeanItemContainer<Subject>(Subject.class);

	public ViewAddClass(BusEvenement busEvenement, List<Subject> subjects) {
		super(busEvenement);
		bicSubject.addAll(subjects);
	}
	
	@Override
	protected void init() {
		if (!initialized) {
			nameField.setNullRepresentation("");
			addField("Nom", nameField);
			levelField.setNullRepresentation("");
			addField("Niveau", levelField);
			initSubjectsCombobox();
			addField("Matière", subjectsCombobox);
			super.init();
			initialized = true;
		}
	}

	private void initSubjectsCombobox() {
		subjectsCombobox.setContainerDataSource(bicSubject);
		subjectsCombobox.setItemCaptionPropertyId(Subject_.name.getName());
	}

	@Override
	protected void bindData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Evenement<?> getAddEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reinitFields() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Evenement<Observer> getCancelEvent() {
		// TODO Auto-generated method stub
		return null;
	}

}
