package ca.classe.classe_web.page.classe;

import java.util.List;
import java.util.Set;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_modele.Classe_;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_modele.Subject_;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.ViewBaseImpl;
import ca.classe.classe_web.page.classe.events.EventRequestAddClass;
import ca.classe.classe_web.page.classe.events.EventSelectClass;
import ca.classe.classe_web.page.classe.events.EventSelectSubject;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

public class ViewClassSelection extends ViewBaseImpl {

	private HorizontalLayout layout = new HorizontalLayout();
	private ComboBox cmbSubject = new ComboBox();
	private ComboBox cmbClass = new ComboBox();
	private Button btnAddClass = new Button();
	private BeanItemContainer<Subject> bicSubject = new BeanItemContainer<Subject>(Subject.class);
	private BeanItemContainer<Classe> bicClasse = new BeanItemContainer<Classe>(Classe.class);
	
	public ViewClassSelection(BusEvenement busEvenement) {
		super(busEvenement);
		layout.setSpacing(true);
		
		layout.addComponent(cmbSubject);
		layout.addComponent(cmbClass);
		layout.addComponent(btnAddClass);
	}

	@Override
	public Layout getLayout() {
		initCmbSubject();
		initCmbClass();
		initBtnClass();
		return layout;
	}
	
	public void setSubjects(List<Subject> subjects) {
		bicSubject.removeAllItems();
		bicSubject.addAll(subjects);
	}

	private void initBtnClass() {
		btnAddClass.setCaption("Ajouter une classe");
		btnAddClass.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = -6198430328789270420L;

			@Override
			public void buttonClick(ClickEvent event) {
				busEvenement.notifier(new EventRequestAddClass());
			}
		});
		
	}

	private void initCmbClass() {
		cmbClass.setEnabled(false);
		cmbClass.setContainerDataSource(bicClasse);
		cmbClass.setItemCaptionPropertyId(Classe_.name.getName());
		cmbClass.setInputPrompt("Classe");
		cmbClass.addValueChangeListener(new ValueChangeListener() {
			
			private static final long serialVersionUID = 6864519605409164787L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Classe classe = (Classe) cmbClass.getValue();
				busEvenement.notifier(new EventSelectClass(classe));
				
			}
		});
	}
	
	public void setClasses(Set<Classe> set) {
		bicClasse.removeAllItems();
		bicClasse.addAll(set);
		cmbClass.setEnabled(set.size() > 0);
	}

	private void initCmbSubject() {
		cmbSubject.setContainerDataSource(bicSubject);
		cmbSubject.setItemCaptionPropertyId(Subject_.name.getName());
		cmbSubject.setInputPrompt("Matière");
		cmbSubject.addValueChangeListener(new ValueChangeListener() {
			
			private static final long serialVersionUID = 6864519605409164787L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Subject subject = (Subject) cmbSubject.getValue();
				busEvenement.notifier(new EventSelectSubject(subject));
			}
		});
		
	}

	public void selectClass(Subject subject, Classe bean) {
		cmbSubject.select(findSubjectInCmb(subject));
		cmbClass.select(findClasseInCmb(bean));
	}
	
	private Subject findSubjectInCmb(Subject subject) {
		for (Object s : cmbSubject.getItemIds()) {
			if (s instanceof Subject) {
				if (((Subject) s).getId().equals(subject.getId())) {
					return (Subject) s;
				}
				
			}
		}
		return null;
	}
	
	private Classe findClasseInCmb(Classe classe) {
		for (Object c : cmbClass.getItemIds()) {
			if (c instanceof Classe) {
				if (((Classe) c).getId().equals(classe.getId())) {
					return (Classe) c;
				}
			}
		}
		return null;
	}

}
