package jp.co.sunarch.pwchecker.data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import jp.co.future.uroborosql.mapping.annotations.Table;

@Table(name = "t_common_pass")
public class TCommonPassEntity {

	@NotEmpty
	@Size(max = 64)
	private String password;

	@NotEmpty
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
}
