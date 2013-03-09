package ca.classe.classe_modele;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "CLASS")
public class Classe extends BaseEntite<Integer>{
	
	private static final long serialVersionUID = 1372181489263250860L;
	private String name;
	private String level;
	private Set<Student> students;
	private Set<Subject> subjects;
	
	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IDCLASS")
	public Integer getId() {
		return super.getId();
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="LEVEL")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
    @Valid
    @BatchSize(size= 30)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "classe", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "CLASS_HAS_SUBJECT",
		joinColumns = { 
			@JoinColumn(name = "CLASS_IDCLASS") 
			}, inverseJoinColumns = {
			@JoinColumn(name = "SUBJECT_IDSUBJECT") })
	public Set<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
}
