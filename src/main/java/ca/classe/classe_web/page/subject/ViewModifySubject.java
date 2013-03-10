package ca.classe.classe_web.page.subject;

import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.ViewBaseImpl;
import ca.classe.classe_web.page.subject.evenement.EvenementModifySubject;

import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;

public class ViewModifySubject extends ViewBaseImpl {

	private Subject subject;
	private final TextField field = new TextField();
	private final Label label = new Label();
	private final HorizontalLayout layout = new HorizontalLayout();
	
	public ViewModifySubject(BusEvenement busEvenement) {
		super(busEvenement);
	}

	public void setEntity(Subject subject) {
		this.subject = subject;
	}
	
	@Override
	public Layout getLayout() {
		return init();
	}

	private HorizontalLayout init() {
		if (!initialized) {
			label.setValue(subject.getName());
			layout.addComponent(label);
			layout.addLayoutClickListener(new LayoutClickListener() {
				
				private static final long serialVersionUID = 3219257584466195622L;
	
				@Override
				public void layoutClick(LayoutClickEvent event) {
					layout.replaceComponent(label, field);
					field.setValue(label.getValue());
					field.addBlurListener(new BlurListener() {
						
						private static final long serialVersionUID = 5874415595334527710L;
	
						@Override
						public void blur(BlurEvent event) {
							layout.replaceComponent(field, label);
							label.setValue(field.getValue());
							subject.setName(label.getValue());
							busEvenement.notifier(new EvenementModifySubject(subject));
						}
					});
				}
			});
			initialized = true;
		}
		
		return layout;
	}

}
