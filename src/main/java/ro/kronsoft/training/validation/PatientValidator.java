package ro.kronsoft.training.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.kronsoft.training.dto.PacientDto;
import ro.kronsoft.training.repository.DoctorRepository;

@Component
public class PatientValidator implements ConstraintValidator<PatientValidation, PacientDto> {

	@Autowired
	private DoctorRepository doctorRepository;

	
	
	
	
	@Override
	public boolean isValid(PacientDto value, ConstraintValidatorContext context) {
		Long doctorId = value.getDoctorId();

		boolean isValid = true;

		if (!doctorRepository.existsById(doctorId)) {
			context.buildConstraintViolationWithTemplate(
					String.format("the doctor id with id=%s doas not exist", doctorId)).addPropertyNode("doctorId")
					.addConstraintViolation();

			isValid = false;
		}

		String birthday=value.getBirthday();
		if(birthday!=null) {
			try {
				LocalDate.parse(birthday);
			}catch (DateTimeParseException e) {
				context.buildConstraintViolationWithTemplate(
						String.format("the birthday=%s doas not exist", birthday)).addPropertyNode("birthday")
						.addConstraintViolation();
				isValid=false;
			}
		}
		
		return isValid;
	}
	
}
