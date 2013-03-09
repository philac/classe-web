package ca.classe.classe_modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMPETENCY")
public class Competency extends BaseEntite<Integer> {

	private static final long serialVersionUID = -7473000003882394498L;
	
	private String name;
	private String description;
	private Float weight;
	private Subject subject;

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "WEIGHT")
	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUBJECT_IDSUBJECT")
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "IDCOMPETENCY")
	public Integer getId() {
		return super.getId();
	}
	
	@Override
	public String toString() {
		return name != null? name : "";
	}

}
