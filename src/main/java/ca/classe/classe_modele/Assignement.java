package ca.classe.classe_modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ASSIGNMENT")
public class Assignement extends BaseEntite<Integer> {

	private static final long serialVersionUID = 551034802190948267L;
	private String title;
	private Float total;
	private Float weight;
	private AssignmentType assignmentType;
	
	@Column(name="TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="TOTAL")
	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	@Column(name="WEIGHT")
	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	@Override
	@Id
	@Column(name="IDASSIGNMENT")
	public Integer getId() {
		return super.getId();
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ASSIGNMENT_TYPE_IDASSIGNMENT_TYPE")
	public AssignmentType getAssignmentType() {
		return assignmentType;
	}

	public void setAssignmentType(AssignmentType assignmentType) {
		this.assignmentType = assignmentType;
	}

}
