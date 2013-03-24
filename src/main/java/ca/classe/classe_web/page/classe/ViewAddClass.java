package ca.classe.classe_web.page.classe;

import java.util.List;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_modele.Classe_;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_modele.Subject_;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_web.page.commons.AddingView;
import ca.classe.classe_web.page.subject.evenement.EvenementCancel;
import ca.classe.classe_web.page.subject.evenement.EvenementCancel.Observer;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;

public class ViewAddClass extends AddingView<Classe> {
	
	private TextField nameField = new TextField();
	private TextField levelField = new TextField();
	private ComboBox subjectsCombobox = new ComboBox();
	private BeanItemContainer<Subject> bicSubject = new BeanItemContainer<Subject>(Subject.class);

	public ViewAddClass(BusEvenement busEvenement) {
		super(busEvenement);
	}
	
	public void setSubjects(List<Subject> subjects) {
		bicSubject.removeAllItems();
		bicSubject.addAll(subjects);
	}
	
	@Override
	protected void init() {
		if (!initialized) {
			nameField.setNullRepresentation("");
			addField("Nom", nameField);
			levelField.setNullRepresentation("");
			addField("Niveau", levelField);
			addField("Matière", subjectsCombobox);
			super.init();
			initSubjectsCombobox();
			initialized = true;
		}
	}

	private void initSubjectsCombobox() {
		subjectsCombobox.setContainerDataSource(bicSubject);
		subjectsCombobox.setItemCaptionPropertyId(Subject_.name.getName());
		subjectsCombobox.addValueChangeListener(new ValueChangeListener(){

			private static final long serialVersionUID = 5200419609035430773L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Subject subject = (Subject) subjectsCombobox.getValue();
				if (subject != null) {
					subject.getClasses().add(beanItem.getBean());
					beanItem.getBean().setSubject(subject);
				}
			} 
		});
	}

	@Override
	protected void bindData() {
		beanItem = new BeanItem<Classe>(new Classe());
		nameField.setPropertyDataSource(beanItem.getItemProperty(Classe_.name.getName()));
		levelField.setPropertyDataSource(beanItem.getItemProperty(Classe_.level.getName()));
	}

	@Override
	protected Evenement<?> getAddEvent() {
		return new EventAddClass(this, beanItem.getBean());
	}

	@Override
	public void reinitFields() {
		nameField.setValue(null);
		levelField.setValue(null);
		subjectsCombobox.setValue(null);
		bindData();
	}

	@Override
	protected Evenement<Observer> getCancelEvent() {
		return new EvenementCancel(this);
	}

}
