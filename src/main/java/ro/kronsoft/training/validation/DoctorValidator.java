package ro.kronsoft.training.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.kronsoft.training.dto.DoctorDto;
import ro.kronsoft.training.entitis.Doctor;
import ro.kronsoft.training.repository.DoctorRepository;

@Component
public class DoctorValidator implements ConstraintValidator<DoctorValidation, DoctorDto> {
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public boolean isValid(DoctorDto value, ConstraintValidatorContext context) {
		
		Long id=value.getId();
		String email=value.getEmail();
		boolean isValid = id ==null ? !doctorRepository.existsByEmail(email) : doctorRepository.countByEmailNotId(email, id)==0;
		
	
		if (!isValid ) {
			context.buildConstraintViolationWithTemplate(String.format("the email =%s does exists", value.getEmail()))
					.addPropertyNode("email").addConstraintViolation();

			isValid = false;
		}
		return isValid;

	}


}
