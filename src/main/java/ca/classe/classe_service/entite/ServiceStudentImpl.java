package ca.classe.classe_service.entite;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ca.classe.classe_modele.Student;
import ca.classe.classe_service.ServiceBaseImpl;

@Service
public class ServiceStudentImpl extends ServiceBaseImpl implements ServiceStudent{

	private DaoStudent daoStudent;

	@Inject
	public ServiceStudentImpl(DaoStudent daoStudent) {
		this.daoStudent = daoStudent;
	}
	
	@Override
	public List<Student> loadStudents() {
		return daoStudent.loadAll();
	}
}
