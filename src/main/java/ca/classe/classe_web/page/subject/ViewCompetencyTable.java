package ca.classe.classe_web.page.subject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import ca.classe.classe_modele.Competency;
import ca.classe.classe_modele.Competency_;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.components.AssignableFromSwitchableComponent;
import ca.classe.classe_web.components.SwitchableComponentOnClick;
import ca.classe.classe_web.components.TableClasse;
import ca.classe.classe_web.components.events.DeleteEvent;
import ca.classe.classe_web.components.events.ModifyEvent;
import ca.classe.classe_web.components.events.OnButtonClickEvent;
import ca.classe.classe_web.mvp.ViewBaseImpl;
import ca.classe.classe_web.page.subject.evenement.EvenementDeleteCompetency;
import ca.classe.classe_web.page.subject.evenement.EvenementModifyCompetency;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ViewCompetencyTable extends ViewBaseImpl {
	
	private TableClasse table;
	private BeanItemContainer<Competency> beanItemContainer = new BeanItemContainer<Competency>(Competency.class);
	private VerticalLayout verticalLayout;
	private Map<Integer, Float> weightValues = new HashMap<Integer, Float>();
	private String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	private FileResource addIcon = new FileResource(new File(basepath + "/WEB-INF/images/plus.png"));

	public ViewCompetencyTable(BusEvenement busEvenement) {
		super(busEvenement);
		table = new TableClasse("Compétences", busEvenement).addDeleteColumn( new CompetencyDeleteEvent(), new OnDeleteButtonClickEvent());
	}

	@Override
	public Layout getLayout() {
		init();
		return verticalLayout;
	}

	private void init() {

		if (!initialized) {
			verticalLayout = new VerticalLayout();
			initTable();
			verticalLayout.addComponent(table);
			initialized = true;
		}		
	}

	private void initTable() {
		table.initTable();
		table.setHeight(-1, Unit.PIXELS);
		table.setWidth(95, Unit.PERCENTAGE);
		
		table.setContainerDataSource(beanItemContainer);
		table.addGeneratedColumn(Competency_.name.getName(), new ColumnGenerator() {
			
			private static final long serialVersionUID = -3450665686521036012L;

			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
				return new SwitchableComponentOnClick(busEvenement, new AssignableCompetencyName((Competency) itemId), new CompetencyModifyEvent(), ViewCompetencyTable.this);
			}
		});
		table.addGeneratedColumn(Competency_.description.getName(), new ColumnGenerator() {
			
			private static final long serialVersionUID = 7481409252111937955L;

			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
				return new SwitchableComponentOnClick(busEvenement, new AssignableCompetencyDescription((Competency) itemId), new CompetencyModifyEvent(), ViewCompetencyTable.this);
			}
		});
		table.addGeneratedColumn(Competency_.weight.getName(), new ColumnGenerator() {
			
			private static final long serialVersionUID = 4703044328011657089L;

			@Override
			public Object generateCell(Table source, final Object itemId, Object columnId) {

				final HorizontalLayout layout = new HorizontalLayout();
				final Competency competency = (Competency) itemId;
				final Label label = new Label(competency.getWeight() + "");
				final Image addImage = new Image();
				addImage.setIcon(addIcon);
				final TextField field = new TextField();
				if (competency.getWeight() != null) {
					layout.addComponent(label);
				} else {
					layout.addComponent(addImage);
				}
				
				layout.addLayoutClickListener(new LayoutClickListener() {
					
					private static final long serialVersionUID = -5097342185606913264L;

					@Override
					public void layoutClick(LayoutClickEvent event) {
						if (layout.getComponent(0).equals(label)) {
							swapComponents(layout, label, field);
						} else if (layout.getComponent(0).equals(addImage)) {
							layout.replaceComponent(addImage, field);
						}
					}
				});
				
				field.addBlurListener(new BlurListener() {
					
					private static final long serialVersionUID = -5018855485844191677L;

					@Override
					public void blur(BlurEvent event) {
						field.setComponentError(null);
						if (StringUtils.isBlank(field.getValue())) {
							layout.replaceComponent(field, addImage);
							competency.setWeight(null);
							weightValues.put(competency.getId(), 0F);
							busEvenement.notifier(new EvenementModifyCompetency(competency));
						} else {
							try {
								Float fieldValue = Float.valueOf(field.getValue());
								competency.setWeight(fieldValue);
								weightValues.put(competency.getId(), competency.getWeight());
								if (validateTotalWeight()) {
									swapComponents(layout, field, label);
									busEvenement.notifier(new EvenementModifyCompetency(competency));
								} else {
									field.setComponentError(new UserError("La somme totale des pondération doit être de 100"));
								}
							} catch (NumberFormatException e) {
								field.setComponentError(new UserError("Veuillez entrer une valeur numérique"));
							}
						}
					}
				});
				return layout;
			}
		});
		table.setFooterVisible(true);
		table.setVisibleColumns(new Object[] {Competency_.name.getName(), Competency_.description.getName(), Competency_.weight.getName(), "delete"});
		table.setColumnHeaders(new String[]{"Nom", "Description", "Pondération", ""});
	}
	
	public void displayTotal() {
		table.setColumnFooter(Competency_.weight.getName(), "Total: " + compileWeightTotal());
	}
	
	private boolean validateTotalWeight() {
		return compileWeightTotal().floatValue() <= 100F;
	}
	
	private Float compileWeightTotal() {
		Float total = 0F;
		for (Float value : weightValues.values()) {
			total += value == null ? 0F : value;
		}
		return total;
	}
	
	private void initWeightTotalMap() {
		weightValues.clear();
		for (Competency competency : beanItemContainer.getItemIds()) {
			weightValues.put(competency.getId(), competency.getWeight());
		}
	}

	public void setEntities(Set<Competency> set) {
		beanItemContainer.removeAllItems();
		beanItemContainer.addAll(set);
		initWeightTotalMap();
		displayTotal();
	}
	
	private void swapComponents(HorizontalLayout layout, Property<String> old, Property<String> newComponent) {
		Validate.isTrue(old instanceof Component && newComponent instanceof Component);
		layout.replaceComponent((Component) old, (Component) newComponent);
		newComponent.setValue(old.getValue());
	}
	
	private class CompetencyDeleteEvent implements DeleteEvent {
		@Override
		public EvenementDeleteCompetency getEvent(Object source, Object itemId) {
			return 	new EvenementDeleteCompetency((Competency) itemId);
		}
	}
	
	private class OnDeleteButtonClickEvent implements OnButtonClickEvent {
		@Override
		public void execute(Object itemId) {
			weightValues.remove(((Competency) itemId).getId());
		}
	}
	
	private class AssignableCompetencyName implements AssignableFromSwitchableComponent {

		private Competency competency;
		
		public AssignableCompetencyName(Competency competency) {
			this.competency = competency;
		}
		
		@Override
		public String getValue() {
			return competency.getName();
		}

		@Override
		public void setValue(Object value) {
			competency.setName((String) value);
		}

		@Override
		public Object getItemObject() {
			return competency;
		}
	}
	
	private class AssignableCompetencyDescription implements AssignableFromSwitchableComponent {

		private Competency competency;
		
		public AssignableCompetencyDescription(Competency competency) {
			this.competency = competency;
		}
		
		@Override
		public String getValue() {
			return competency.getDescription();
		}

		@Override
		public void setValue(Object value) {
			competency.setDescription((String) value);
		}

		@Override
		public Object getItemObject() {
			return competency;
		}
	}
	
	private class CompetencyModifyEvent implements ModifyEvent {

		@Override
		public EvenementModifyCompetency getEvent(Object source, Object itemId) {
			return new EvenementModifyCompetency((Competency) itemId);
		}
		
	}
}
