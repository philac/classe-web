package ca.classe.classe_web.page.classe;

import java.util.List;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_modele.Subject_;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.components.AssignableFromSwitchableComponent;
import ca.classe.classe_web.components.SwitchableComponentOnClick;
import ca.classe.classe_web.components.events.ModifyEvent;
import ca.classe.classe_web.mvp.ViewBaseImpl;
import ca.classe.classe_web.page.classe.events.CloseEvent;
import ca.classe.classe_web.page.classe.events.EventModifyClass;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

public class ViewModifyClass extends ViewBaseImpl {
	
	private SwitchableComponentOnClick switchNameField;
	private SwitchableComponentOnClick switchLevelField;
	private ComboBox cmbSubject = new ComboBox();
	private Label lblSubjectSwitch;
	private Button btnClose;
	private Classe classe;
	private AssignableLevel assignableLevel = new AssignableLevel();	private AssignableName assignableName = new AssignableName();
	private ClassModifyEvent classModifyEvent = new ClassModifyEvent();
	private HorizontalLayout hrlSubject = new HorizontalLayout();
	private BeanItemContainer<Subject> bicSubjects = new BeanItemContainer<Subject>(Subject.class);
	private HorizontalLayout layout = new HorizontalLayout();
	
	public ViewModifyClass(BusEvenement busEvenement) {
		super(busEvenement);
		initButton();
		this.switchNameField = new SwitchableComponentOnClick(busEvenement, assignableName, classModifyEvent);
		this.switchLevelField = new SwitchableComponentOnClick(busEvenement, assignableLevel, classModifyEvent);
		lblSubjectSwitch = new Label();
		layout.setSpacing(true);
		layout.addComponent(new Label("Nom : "));
		layout.addComponent(switchNameField);
		layout.addComponent(new Label("Niveau : "));
		layout.addComponent(switchLevelField);
		layout.addComponent(new Label("Matière : "));
		hrlSubject.addComponent(lblSubjectSwitch);
		layout.addComponent(hrlSubject);
		layout.addComponent(btnClose);
	}

	@Override
	public Layout getLayout() {
		return layout;
	}

	private void initButton() {
		btnClose = new Button("X");
		btnClose.addClickListener(new ClickListener(){

			private static final long serialVersionUID = 4543042944027852044L;

			@Override
			public void buttonClick(ClickEvent event) {
				busEvenement.notifier(new CloseEvent(ViewModifyClass.this, classe));
			}
			
		});
	}

	private void initCmbLblSubject() {
		cmbSubject.setImmediate(true);
		cmbSubject.setContainerDataSource(bicSubjects);
		cmbSubject.setItemCaptionPropertyId(Subject_.name.getName());
		lblSubjectSwitch.setValue(classe.getSubject().getName());
		hrlSubject.addLayoutClickListener(new LayoutClickListener() {
			
			private static final long serialVersionUID = 113406637314005162L;

			@Override
			public void layoutClick(LayoutClickEvent event) {
				hrlSubject.replaceComponent(lblSubjectSwitch, cmbSubject);
				cmbSubject.select(findSubjectInCmb(classe.getSubject()));
			}
		});
		
		cmbSubject.addBlurListener(new BlurListener() {
			
			private static final long serialVersionUID = 3875230716444316349L;

			@Override
			public void blur(BlurEvent event) {
				lblSubjectSwitch.setValue(((Subject) cmbSubject.getValue()).getName());
				classe.setSubject((Subject) cmbSubject.getValue());
				busEvenement.notifier(new EventModifyClass(classe));
				hrlSubject.replaceComponent(cmbSubject, lblSubjectSwitch);
			}
		});
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
	
	public void setClasse(Classe classe) {
		this.classe = classe;
		this.assignableLevel.setItemObject(classe);
		this.assignableName.setItemObject(classe);
		switchLevelField.initComponent(this);
		switchNameField.initComponent(this);
		this.classModifyEvent.setClasse(classe);
	}
	
	public void setSubjects(List<Subject> subjects) {
		bicSubjects.removeAllItems();
		bicSubjects.addAll(subjects);
		initCmbLblSubject();
	}
	
	private class AssignableName implements AssignableFromSwitchableComponent {
		
		private Classe classe;

		@Override
		public String getValue() {
			return classe != null ? classe.getName() : "";
		}

		@Override
		public void setValue(Object value) {
			classe.setName((String) value);
		}

		@Override
		public Object getItemObject() {
			return classe;
		}
		
		public void setItemObject(Classe classe) {
			this.classe = classe;
		}
	}
	
	private class AssignableLevel implements AssignableFromSwitchableComponent {
		
		private Classe classe;

		@Override
		public String getValue() {
			return classe != null ? classe.getLevel() : "";
		}

		@Override
		public void setValue(Object value) {
			classe.setLevel((String) value);
		}

		@Override
		public Object getItemObject() {
			return classe;
		}
		
		public void setItemObject(Classe classe) {
			this.classe = classe;
		}
	}
	
	private class ClassModifyEvent implements ModifyEvent {

		private Classe classe;
		
		@Override
		public EventModifyClass getEvent(Object source, Object itemId) {
			return new EventModifyClass(this.classe);
		}
		
		public void setClasse(Classe classe) {
			this.classe = classe;
		}
		
	}
}
