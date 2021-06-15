package ro.kronsoft.training.entitis;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 5823721150352405754L;

	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	@Column(name="ID", nullable=false)
	
	protected Long id;
	
	@Column(name="CREATER_TIME", nullable=false,updatable=false)
	protected LocalDateTime createdTime;
	
	@Column(name="LAST_MODIFIED_TIME", nullable=false)
	protected LocalDateTime lastModifiedTime;

	
	@PrePersist
	void prePersist() {
		this.createdTime=LocalDateTime.now();
		this.lastModifiedTime=LocalDateTime.now();
	}
	@PreUpdate
	void preUpdate() {
		this.lastModifiedTime=LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	
	
	
}
