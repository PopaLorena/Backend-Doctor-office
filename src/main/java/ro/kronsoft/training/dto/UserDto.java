package ro.kronsoft.training.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ro.kronsoft.training.validation.UserValidation;

@UserValidation
public class UserDto extends BaseDto {

	private static final long serialVersionUID = 1718050133423185385L;

	@NotNull
	@Size(min = 0, max = 50)
	private String username;
	@NotNull
	@Size(min = 0, max = 50)
	private String email;
	
	@Size(min = 0, max = 80)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
