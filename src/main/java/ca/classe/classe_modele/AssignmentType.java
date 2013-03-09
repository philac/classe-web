package ca.classe.classe_modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ASSIGNMENT_TYPE")
public class AssignmentType extends BaseEntite<Integer> {

	private static final long serialVersionUID = -1174666879079762260L;
	private String name;
	
	@Override
	@Id
	@Column(name="IDASSIGNMENT_TYPE")
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
}
