package ca.classe.classe_web.components;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.components.events.DeleteEvent;
import ca.classe.classe_web.components.events.OnButtonClickEvent;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table;

public class TableWithDeleteColumn extends Table {

	private static final long serialVersionUID = 8493110332072163597L;
	private BusEvenement busEvenement;
	private DeleteEvent deleteEvent;
	private OnButtonClickEvent onButtonClickEvent;
	
	public TableWithDeleteColumn(String caption, BusEvenement busEvenement, DeleteEvent deleteEvent, OnButtonClickEvent onButtonClickEvent){
		super(caption);
		this.busEvenement = busEvenement;
		this.deleteEvent = deleteEvent;
		this.onButtonClickEvent = onButtonClickEvent;
	}
	
	public TableWithDeleteColumn(BusEvenement busEvenment, DeleteEvent deleteEvent, OnButtonClickEvent onButtonClickEvent) {
		this("", busEvenment, deleteEvent, onButtonClickEvent);
	}
	
	public void initTable() {
		setSizeUndefined();
		addGeneratedColumn("delete", new ColumnGenerator() {
			
			private static final long serialVersionUID = 5725803838136084976L;

			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
				final Button delete = new Button("X");
				delete.addStyleName("deleteBtn");
				delete.setData(itemId);
				delete.addClickListener(new Button.ClickListener() {
					
					private static final long serialVersionUID = -7500287398111769642L;

					@Override
					public void buttonClick(ClickEvent event) {
						onButtonClickEvent.execute(delete.getData());
						busEvenement.notifier(deleteEvent.getDeleteEvent(delete.getData()));
					}
				});
				return delete;
			}
		});
	}
}
