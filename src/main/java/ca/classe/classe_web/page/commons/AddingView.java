package ca.classe.classe_web.page.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.classe.classe_modele.BaseEntite;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_web.mvp.ViewBaseImpl;
import ca.classe.classe_web.page.subject.evenement.EvenementCancel.Observer;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

public abstract class AddingView<E extends BaseEntite<? extends Serializable>> extends ViewBaseImpl {
	
	protected BeanItem<E> beanItem;

	public AddingView(BusEvenement busEvenement) {
		super(busEvenement);
	}

	private static final long serialVersionUID = -3047154496261709186L;
	
	private HorizontalLayout layout = new HorizontalLayout();
	private Button addButton = new Button("Ajouter");
	private Button cancelButton = new Button("Annuler");
	
	private List<HorizontalLayout> fields = new ArrayList<HorizontalLayout>();
	
	protected void init() {
		addFields();
		initButtons();
		bindData();
		layout.addComponent(addButton);
		layout.addComponent(cancelButton);
	}
	
	private void initButtons() {
		addButton.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 410355243184137556L;

			@Override
			public void buttonClick(ClickEvent event) {
				busEvenement.notifier(getAddEvent());
			}
		});
		cancelButton.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 3427100556688360911L;

			@Override
			public void buttonClick(ClickEvent event) {
				busEvenement.notifier(getCancelEvent());
			}
		});
	}

	private void addFields() {
		for (HorizontalLayout hl : fields) {
			layout.addComponent(hl);
		}
		
	}

	protected abstract void bindData();
	protected abstract Evenement<?> getAddEvent();
	
	protected void addField(String caption, Field<?> field) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(new Label(caption));
		layout.addComponent(field);
		fields.add(layout);
	}
	
	@Override
	public Layout getLayout() {
		return layout;
	}
	
	public abstract void reinitFields();

	protected abstract Evenement<Observer> getCancelEvent();
}
