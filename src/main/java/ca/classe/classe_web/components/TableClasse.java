package ca.classe.classe_web.components;

import java.io.File;

import org.jsoup.helper.Validate;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.components.events.DeleteEvent;
import ca.classe.classe_web.components.events.ModifyEvent;
import ca.classe.classe_web.components.events.OnButtonClickEvent;

import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Table;

public class TableClasse extends Table {

	private static final long serialVersionUID = 8493110332072163597L;
	private BusEvenement busEvenement;
	// Find the application directory
	private String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	private FileResource modifyIcon = new FileResource(new File(basepath + "/WEB-INF/images/icon_pencil.png"));

	
	public TableClasse(String caption, BusEvenement busEvenement){
		super(caption);
		this.busEvenement = busEvenement;
	}
	
	public void initTable() {
		setSizeUndefined();
	}
	
	public TableClasse addDeleteColumn(final DeleteEvent deleteEvent, final OnButtonClickEvent onDeleteButtonClickEvent) {
		Validate.notNull(deleteEvent);
		addGeneratedColumn("delete", new ColumnGenerator() {
			
			private static final long serialVersionUID = 5725803838136084976L;

			@Override
			public Object generateCell(Table source, final Object itemId, Object columnId) {
				Button delete = new Button("X");
				delete.addStyleName("deleteBtn");
				delete.addClickListener(new Button.ClickListener() {
					
					private static final long serialVersionUID = -7500287398111769642L;

					@Override
					public void buttonClick(ClickEvent event) {
						if (onDeleteButtonClickEvent != null) {
							onDeleteButtonClickEvent.execute(itemId);
						}
						busEvenement.notifier(deleteEvent.getEvent(TableClasse.this, itemId));
					}
				});
				return delete;
			}
		});
		return this;
	}
	
	public TableClasse addModifyColumn(final ModifyEvent modifyEvent, final OnButtonClickEvent onModifyButtonclickEvent) {
		addGeneratedColumn("modify", new ColumnGenerator() {
			
			private static final long serialVersionUID = -4238432792751556346L;

			@Override
			public Object generateCell(Table source, final Object itemId, Object columnId) {
				Image modifyButton = new Image("", modifyIcon);
				modifyButton.addClickListener(new ClickListener() {
					
					private static final long serialVersionUID = 5082244489286145248L;

					@Override
					public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
						if (onModifyButtonclickEvent != null) {
							onModifyButtonclickEvent.execute(itemId);
						}
						busEvenement.notifier(modifyEvent.getEvent(TableClasse.this, itemId));
					}
				});
				return modifyButton;
			}
		});
		return this;
	}
}
