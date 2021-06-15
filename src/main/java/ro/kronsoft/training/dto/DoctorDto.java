package ro.kronsoft.training.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ro.kronsoft.training.entitis.DocTypeEnum;
import ro.kronsoft.training.validation.DoctorValidation;

@DoctorValidation
public class DoctorDto extends BaseDto {

	
	private static final long serialVersionUID = 7627410224203338646L;

	@NotNull
	@Size(min = 0, max = 64)
	private String firstName;
	
	@NotNull
	@Size(min = 0, max = 64)
	private String lastName;
	
	@NotNull
	@Email
	@Size(min = 0, max = 64)
	private String email;
	
	@Size(min = 0, max = 128)
	private String address;
	
	private int houseNumber;
	
	private DocTypeEnum docType;

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

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public DocTypeEnum getDocType() {
		return docType;
	}

	public void setDocType(DocTypeEnum docType) {
		this.docType = docType;
	}

	@Override
	public String toString() {
		return "DoctorDto [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", address="
				+ address + ", houseNumber=" + houseNumber + ", docType=" + docType + "]";
	}

}
