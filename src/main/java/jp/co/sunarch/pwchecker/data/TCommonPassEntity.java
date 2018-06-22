package jp.co.sunarch.pwchecker.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="t_common_pass")
public class TCommonPassEntity {

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false)
	@Size(max = 64)
	private String password;

	@Column(nullable = false)
	private int count;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
