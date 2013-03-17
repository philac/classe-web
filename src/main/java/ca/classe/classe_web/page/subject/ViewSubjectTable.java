package ca.classe.classe_web.page.subject;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.vaadin.tokenfield.TokenComboBox;
import org.vaadin.tokenfield.TokenField;

import ca.classe.classe_modele.Competency;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_modele.Subject_;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.components.TableWithDeleteColumn;
import ca.classe.classe_web.components.events.DeleteEvent;
import ca.classe.classe_web.components.events.OnButtonClickEvent;
import ca.classe.classe_web.mvp.ViewBaseImpl;
import ca.classe.classe_web.page.subject.evenement.EvenementDeleteSubject;
import ca.classe.classe_web.page.subject.evenement.EvenementModifySubject;
import ca.classe.classe_web.page.subject.evenement.EvenementNavigateModifySubject;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.Sizeable.Unit;
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

public class ViewSubjectTable extends ViewBaseImpl {

	private static final long serialVersionUID = -2906930327616159639L;
	
	private TableWithDeleteColumn table;
	private VerticalLayout layout;
	BeanItemContainer<Subject> bicSubject = new BeanItemContainer<Subject>(Subject.class);
	// Find the application directory
	private String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	// Image as a file resource
	private FileResource modifyIcon = new FileResource(new File(basepath + "/WEB-INF/images/icon_pencil.png"));
	
	private FileResource addIcon = new FileResource(new File(basepath + "/WEB-INF/images/plus.png"));

	public ViewSubjectTable(BusEvenement busEvenement) {
		super(busEvenement);
		layout = new VerticalLayout();
		table = new TableWithDeleteColumn("Matières",
				busEvenement, new DeleteEvent() {
					
					@Override
					public EvenementDeleteSubject getDeleteEvent(Object itemId) {
						return new EvenementDeleteSubject((Subject) itemId);
					}
				}, new OnButtonClickEvent() {
					@Override
					public void execute(Object itemId) {
					}
				});
	}

	public void init() {
		if (!initialized) {
			table.initTable();
			layout.addComponent(table);
			table.setContainerDataSource(bicSubject);
			table.addGeneratedColumn(Subject_.name.getName(), new ColumnGenerator() {
				
				private static final long serialVersionUID = -5582612078498034364L;
	
				@Override
				public Object generateCell(Table source, Object itemId, Object columnId) {
					final Subject subject = (Subject) itemId;
					final Label label = new Label(subject.getName());
					final HorizontalLayout hl = new HorizontalLayout();
					final TextField field = new TextField();
					hl.addComponent(label);
					hl.addLayoutClickListener(new LayoutClickListener() {
						
						private static final long serialVersionUID = -2741245649978592560L;
	
						@Override
						public void layoutClick(LayoutClickEvent event) {
							hl.replaceComponent(label, field);
							field.setValue(subject.getName());
							field.focus();
							field.addBlurListener(new BlurListener() {
								
								private static final long serialVersionUID = 2103543258685792042L;
	
								@Override
								public void blur(BlurEvent event) {
									subject.setName(field.getValue());
									hl.removeAllComponents();
									Label label2 = new Label(subject.getName());
									hl.addComponent(label2);
									busEvenement.notifier(new EvenementModifySubject(ViewSubjectTable.this, subject));
								}
							});
						}
					});
					return hl;
				}
			});
			table.addGeneratedColumn(Subject_.competencies.getName(), new ColumnGenerator() {
				
				private static final long serialVersionUID = -4139399975888872413L;
	
				@Override
				public Object generateCell(Table source, Object itemId, Object columnId) {
					final HorizontalLayout hl = new HorizontalLayout();
					final Subject subject = (Subject) itemId;
	
					String formattedCompetencies = getFormattedCompetencies(subject);
					final Label label = new Label(formattedCompetencies);
					final Image addImage = new Image();
					addImage.setIcon(addIcon);
					if (StringUtils.isNotBlank(formattedCompetencies)) {
						hl.addComponent(label);
					} else {
						hl.addComponent(addImage);
					}
					
					final TokenField field = new TokenField();
					final TokenComboBox cbx = extractCbxFromTokenField(field);
					
					hl.addLayoutClickListener(new LayoutClickListener() {
						
						private static final long serialVersionUID = -73572737721639494L;
	
						@Override
						public void layoutClick(LayoutClickEvent event) {
							if (hl.getComponent(0).equals(label)) {
								hl.replaceComponent(label, field);
								field.setValue(subject.getCompetencies());
							} else if (hl.getComponent(0).equals(addImage)) {
								hl.replaceComponent(addImage, field);
							}
							
							cbx.addBlurListener(new BlurListener() {
								
								private static final long serialVersionUID = -7453534976461633250L;
	
								@Override
								public void blur(BlurEvent event) {
									Set<Object> comps = (Set<Object>) field.getValue();
									if (comps != null ) {
										Set<Competency> competencies = new LinkedHashSet<Competency>();
										for (Object comp : comps) {
											Competency competency = null;
											if (comp instanceof String) {
												competency = new Competency();
												competency.setName((String) comp);
												competency.setSubject(subject);
											} else if (comp instanceof Competency) {
												competency = (Competency) comp;
											}
											if (competency != null) {
												competencies.add(competency);
											}
										}
										subject.setCompetencies(competencies);
										busEvenement.notifier(new EvenementModifySubject(ViewSubjectTable.this, subject));
										
									}
									if (comps == null || subject.getCompetencies() == null || subject.getCompetencies().isEmpty()) {
										hl.replaceComponent(field, addImage);
									} else {
										label.setValue(getFormattedCompetencies(subject));
										hl.replaceComponent(field, label);
									}
								}
							});
						}
					});
					return hl;
				}
	
				private String getFormattedCompetencies(Subject subject) {
					StringBuilder content = new StringBuilder();
					for (Competency competency : subject.getCompetencies()) {
						content.append(competency.getName());
						content.append(", ");
					}
					if ( content.lastIndexOf(",") > 0) {
						content.delete(content.lastIndexOf(","), content.lastIndexOf(",") + 1);
					}
					return content.toString();
				}
	
				private TokenComboBox extractCbxFromTokenField(TokenField field) {
					Iterator<Component> it = field.getLayout().iterator();
					while (it.hasNext()) {
						Component c = it.next();
						if (c instanceof TokenComboBox) {
							return (TokenComboBox) c;
						}
					}
					return null;
				}
			});
			table.addGeneratedColumn("modify", new ColumnGenerator() {
				
				private static final long serialVersionUID = -4238432792751556346L;
	
				@Override
				public Object generateCell(Table source, Object itemId, Object columnId) {
					final Subject subject = (Subject) itemId;
					Image modifyButton = new Image("", modifyIcon);
					modifyButton.addClickListener(new ClickListener() {
						
						private static final long serialVersionUID = 5082244489286145248L;
	
						@Override
						public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
							busEvenement.notifier(new EvenementNavigateModifySubject(subject.getId()));
						}
					});
					return modifyButton;
				}
			});
			table.setVisibleColumns(new Object[] {Subject_.name.getName(), Subject_.competencies.getName(), "modify", "delete"});
			table.setColumnHeaders(new String[] {"nom", "compétence", "", ""});
			table.setColumnWidth(Subject_.name.getName(), 100);
			table.setColumnWidth(Subject_.competencies, 450);
			table.setColumnWidth("delete", 50);
			table.setColumnWidth("modify", 50);
			table.setPageLength(10);
			table.setWidth(100, Unit.PERCENTAGE);
			initialized = true;
		}
	}

	@Override
	public Layout getLayout() {
		return layout;
	}
	
	public void setEntities(List<Subject> subjects) {
		bicSubject.removeAllItems();
		bicSubject.addAll(subjects);
	}
}
