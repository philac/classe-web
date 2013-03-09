package ca.classe.classe_web.page.subject;

import java.util.Set;

import org.apache.commons.lang.Validate;

import ca.classe.classe_modele.Competency;
import ca.classe.classe_modele.Competency_;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.ViewBaseImpl;
import ca.classe.classe_web.page.subject.evenement.EvenementModifyCompetency;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ViewCompetencyTable extends ViewBaseImpl {
	
	private Table table = new Table();
	private BeanItemContainer<Competency> beanItemContainer = new BeanItemContainer<Competency>(Competency.class);

	public ViewCompetencyTable(BusEvenement busEvenement) {
		super(busEvenement);
	}

	@Override
	public Layout getLayout() {
		VerticalLayout verticalLayout = new VerticalLayout();
		initTable();
		verticalLayout.addComponent(table);
		return verticalLayout;
	}

	private void initTable() {
		table.setContainerDataSource(beanItemContainer);
		table.addGeneratedColumn(Competency_.name.getName(), new ColumnGenerator() {
			
			private static final long serialVersionUID = -3450665686521036012L;

			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
				final HorizontalLayout layout = new HorizontalLayout();
				final Label label = new Label();
				final TextField field = new TextField();
				final Competency competency = (Competency) itemId;
				label.setValue(competency.getName());
				layout.addComponent(label);
				layout.addLayoutClickListener(new LayoutClickListener() {
					
					private static final long serialVersionUID = -4039136538572434269L;

					@Override
					public void layoutClick(LayoutClickEvent event) {
						swapComponents(layout, label, field);
						field.addBlurListener(new BlurListener() {
							
							private static final long serialVersionUID = -1669497376704756735L;

							@Override
							public void blur(BlurEvent event) {
								swapComponents(layout, field, label);
								competency.setName(field.getValue());
								busEvenement.notifier(new EvenementModifyCompetency(competency));
							}
						});
					}
				});
				return layout;
			}
		});
		table.setVisibleColumns(new Object[] {Competency_.name.getName(), Competency_.description.getName(), Competency_.weight.getName()});
		table.setColumnHeaders(new String[]{"Nom", "Description", "Pondération"});
	}

	public void setEntities(Set<Competency> set) {
		
		beanItemContainer.addAll(set);
	}
	
	private void swapComponents(HorizontalLayout layout, Property<String> old, Property<String> newComponent) {
		Validate.isTrue(old instanceof Component && newComponent instanceof Component);
		layout.replaceComponent((Component) old, (Component) newComponent);
		newComponent.setValue(old.getValue());
	}
}
