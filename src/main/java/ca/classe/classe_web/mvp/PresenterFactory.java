package ca.classe.classe_web.mvp;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.page.subject.ModelSubject;
import ca.classe.classe_web.page.subject.PresenterSubject;
import ca.classe.classe_web.page.subject.PresenterSubjects;
import ca.classe.classe_web.page.subject.ViewCompetencyTable;
import ca.classe.classe_web.page.subject.ViewModifySubject;
import ca.classe.classe_web.page.subject.ViewSubjectTable;


public class PresenterFactory {

	private PresenterSubjects presenterSubjects;
	private PresenterSubject presenterSubject;
	private BusEvenement busEvenement;
	
	public PresenterFactory(BusEvenement busEvenement) {
		this.busEvenement = busEvenement;
	}
	
	public <P extends PresenterBase<? extends ModelBase, ? extends ViewBase>> PresenterBase<? extends ModelBase, ? extends ViewBase> createPresenter(Class<? extends PresenterBase <? extends ModelBase, ? extends ViewBase>> clazz) {
		if (clazz.equals(PresenterSubjects.class)) {
			return getInstancePresenterSubjects();
		}
		if (clazz.equals(PresenterSubject.class)) {
			return getInstancePresenterSubject();
		}
		return null;
	}
	
	private PresenterSubjects getInstancePresenterSubjects() {
		if (presenterSubjects == null) {
			presenterSubjects = new PresenterSubjects(ModelSubject.getInstance(), new ViewSubjectTable(busEvenement), busEvenement);
		}
		
		return presenterSubjects;
	}
	
	private PresenterSubject getInstancePresenterSubject() {
		if (presenterSubject == null) {
			presenterSubject = new PresenterSubject(ModelSubject.getInstance(), new ViewCompetencyTable(busEvenement), new ViewModifySubject(busEvenement), busEvenement);
		}
		
		return presenterSubject;
	}
}
