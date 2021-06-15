package ro.kronsoft.training.dto;

import java.io.Serializable;
import java.security.ProtectionDomain;

public abstract class BaseDto implements Serializable {
	
	
	private static final long serialVersionUID = 3218422276380958353L;

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
