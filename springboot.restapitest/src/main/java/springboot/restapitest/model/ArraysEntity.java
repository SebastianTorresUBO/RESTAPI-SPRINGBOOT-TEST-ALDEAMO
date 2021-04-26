package springboot.restapitest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="arrays")
public class ArraysEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	private String input_array;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInput_array() {
		return input_array;
	}
	public void setInput_array(String input_array) {
		this.input_array = input_array;
	}
	
}
