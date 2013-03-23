package ca.classe.classe_modele;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name="SUBJECT")
public class Subject extends BaseEntite<Integer>{

	private static final long serialVersionUID = 5787266323296374618L;
	private String name;
	private Set<Competency> competencies;
	private Set<Classe> classes;
	
	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IDSUBJECT")
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

    @BatchSize(size= 30)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Competency> getCompetencies() {
		return competencies;
	}

	public void setCompetencies(Set<Competency> competencies) {
		this.competencies = competencies;
	}
	
    @BatchSize(size= 30)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Classe> getClasses() {
		return classes;
	}

	public void setClasses(Set<Classe> classes) {
		this.classes = classes;
	}
}
