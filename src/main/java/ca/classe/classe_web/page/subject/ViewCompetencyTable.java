package ca.classe.classe_web.page.subject;

import java.util.Set;

import ca.classe.classe_modele.Competency;
import ca.classe.classe_modele.Competency_;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.ViewBaseImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Table;
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
		table.setVisibleColumns(new Object[] {Competency_.name.getName(), Competency_.description.getName(), Competency_.weight.getName()});
		table.setColumnHeaders(new String[]{"Nom", "Description", "Pondération"});
	}

	public void setEntities(Set<Competency> set) {
		
		beanItemContainer.addAll(set);
	}
}
