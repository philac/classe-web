package ca.classe.classe_web.page.classe;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.components.AssignableFromSwitchableComponent;
import ca.classe.classe_web.components.SwitchableComponentOnClick;
import ca.classe.classe_web.components.events.ModifyEvent;
import ca.classe.classe_web.mvp.ViewBaseImpl;
import ca.classe.classe_web.page.classe.events.EventModifyClass;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

public class ViewModifyClass extends ViewBaseImpl {
	
	private SwitchableComponentOnClick switchNameField;
	private SwitchableComponentOnClick switchLevelField;
	private ComboBox cmbSubject;
	private Label lblSubjectSwitch;
	private Button btnClose;
	private Classe classe;
	private AssignableLevel assignableLevel = new AssignableLevel();	private AssignableName assignableName = new AssignableName();
	private ClassModifyEvent classModifyEvent = new ClassModifyEvent(); 
	
	public ViewModifyClass(BusEvenement busEvenement) {
		super(busEvenement);
		this.switchNameField = new SwitchableComponentOnClick(busEvenement, assignableName, classModifyEvent, this);
		this.switchLevelField = new SwitchableComponentOnClick(busEvenement, assignableLevel, classModifyEvent, this);
	}

	@Override
	public Layout getLayout() {
		// TODO monter le layout et init les components.
		return null;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
		this.assignableLevel.setItemObject(classe);
		this.assignableName.setItemObject(classe);
		this.classModifyEvent.setClasse(classe);
	}
	
	private class AssignableName implements AssignableFromSwitchableComponent {
		
		private Classe classe;

		@Override
		public String getValue() {
			return classe.getName();
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
			return classe.getLevel();
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
