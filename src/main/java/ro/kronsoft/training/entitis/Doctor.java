package ro.kronsoft.training.entitis;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

//one(doctor)-to many(patient)
//one(doctor)-to one(address)
//many(doctors)-to many(medial_equipment)

@Entity
public class Doctor extends BaseEntity {

	private static final long serialVersionUID = -2946943736624915264L;

	@Column(name = "FIRST_NAME", nullable = false, length = 64)
	@Length(min = 0, max = 64)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false, length = 64)
	@Length(min = 0, max = 64)
	private String lastName;

	@Column(name = "EMAIL", nullable = false, length = 64, unique = true)
	@Email
	@Length(min = 0, max = 64)
	private String email;

	@Column(name = "ADDRESS", nullable = true, length = 64)
	private String address;

	@Column(name = "HOUSE_NUBER", nullable = false)
	private Integer houseNumber;

	@Column(name = "DOC_TYPE", length = 32)
	@Enumerated(EnumType.STRING)
	private DocTypeEnum docType;
	// fetch.EAGER-set-ul va fi merul umblut cu set-ul de pacienti
	// fetch.lazy- o sa fac eu legatura cu pacentii
	@OneToMany(cascade=CascadeType.ALL, mappedBy="doctor", fetch=FetchType.LAZY)//mappedBy- ownerul  

	private Set<Patient> patients=new HashSet<>();
//	
	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public DocTypeEnum getDocType() {
		return docType;
	}

	public void setDocType(DocTypeEnum docType) {
		this.docType = docType;
	}

	public Doctor(String firstName, String lastName, String email, String address, Integer houseNumber,
			DocTypeEnum docType) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.houseNumber = houseNumber;
		this.docType = docType;
	}

	public Doctor() {

	}

	@Override
	public String toString() {
		return "Doctor [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", address=" + address
				+ ", houseNumber=" + houseNumber + ", docType=" + docType + "]";
	}



}
