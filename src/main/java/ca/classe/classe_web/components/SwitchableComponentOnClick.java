package ca.classe.classe_web.components;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.components.events.ModifyEvent;

import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class SwitchableComponentOnClick extends HorizontalLayout {

	private static final long serialVersionUID = 5604418905428551072L;
	
	private Label label = new Label();
	private TextField field = new TextField();
	private AssignableFromSwitchableComponent assignable;
	private ModifyEvent modifyEvent;
	private BusEvenement busEvenement;
	private String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	private FileResource addIcon = new FileResource(new File(basepath + "/WEB-INF/images/plus.png"));
	private Image addImage = new Image();
	
	public SwitchableComponentOnClick(BusEvenement busEvenement, AssignableFromSwitchableComponent assignable, ModifyEvent modifyEvent, Object source) {
		this.busEvenement = busEvenement;
		this.assignable = assignable;
		this.modifyEvent = modifyEvent;
		label.setValue(assignable.getValue());
		initComponent(source);
	}
	
	public void initComponent(final Object source) {
		addImage.setIcon(addIcon);
		if (StringUtils.isNotBlank(assignable.getValue())) {
			addComponent(label);
		} else {
			addComponent(addImage);
		}
		addLayoutClickListener(new LayoutClickListener() {
			
			private static final long serialVersionUID = -2741245649978592560L;

			@Override
			public void layoutClick(LayoutClickEvent event) {
				if (getComponent(0).equals(label)) {
					swapComponents(label, field);
				} else if (getComponent(0).equals(addImage)) {
					replaceComponent(addImage, field);
				}
			}
		});
		
		field.addBlurListener(new BlurListener() {
			
			private static final long serialVersionUID = 2103543258685792042L;

			@Override
			public void blur(BlurEvent event) {
				if (StringUtils.isNotBlank(field.getValue())) {
					swapComponents(field, label);
				} else {
					replaceComponent(field, addImage);
				}
				assignable.setValue(field.getValue());
				busEvenement.notifier(modifyEvent.getEvent(source, assignable.getItemObject()));
			}
		});
	}
	
	private void swapComponents(Property<String> old, Property<String> newComponent) {
		Validate.isTrue(old instanceof Component && newComponent instanceof Component);
		replaceComponent((Component) old, (Component) newComponent);
		newComponent.setValue(old.getValue());
	}
}
