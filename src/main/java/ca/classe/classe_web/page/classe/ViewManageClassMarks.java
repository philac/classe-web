package ca.classe.classe_web.page.classe;

import java.util.HashSet;
import java.util.Set;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_modele.Competency;
import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.ViewBaseImpl;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;

public class ViewManageClassMarks extends ViewBaseImpl {
	
	private Classe classe;
	private HorizontalLayout layout = new HorizontalLayout();
	private TabSheet tabs = new TabSheet();
	private Set<Tab> addedTabs = new HashSet<Tab>();

	public ViewManageClassMarks(BusEvenement busEvenement) {
		super(busEvenement);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Layout getLayout() {

		layout.addComponent(tabs);
		return layout;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
		removeAllTabs();
		Set<Competency> competencies = classe.getSubject().getCompetencies();
		for (Competency comp : competencies) {
			addedTabs.add(tabs.addTab(new Label(comp.getName()), comp.getName()));
		}
	}

	private void removeAllTabs() {
		for(Tab t : addedTabs) {
			tabs.removeTab(t);
		}
	}
	
	public void setVisible(boolean visible) {
		layout.setVisible(visible);
	}
}
