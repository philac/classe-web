package ca.classe.classe_service.entite;

import java.util.List;

import ca.classe.classe_modele.Student;
import ca.classe.classe_service.ServiceBase;

public interface ServiceStudent extends ServiceBase{

	List<Student> loadStudents();

}
