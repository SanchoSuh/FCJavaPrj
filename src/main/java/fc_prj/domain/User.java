package fc_prj.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true, nullable=false, length=20)
	private String userId;
	
	@Column(nullable=false, length=20)
	private String password;
	
	@Column(nullable=false, length=20)
	private String name;
	
	private String email;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean setUser(User user) {
		if(this.password.equals(user.password)) {
			this.userId = user.userId;
			this.name = user.name;
			this.password = user.password;
			this.email = user.email;
			
			return true;
		}	
		return false;
	}
	
/*
	public String getPassword() {
		return this.password;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
*/
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}

	
}
