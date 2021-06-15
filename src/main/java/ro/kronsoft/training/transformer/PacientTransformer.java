package ro.kronsoft.training.transformer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.kronsoft.training.dto.PacientDto;
import ro.kronsoft.training.entitis.Patient;
import ro.kronsoft.training.repository.DoctorRepository;
import ro.kronsoft.training.repository.PatientRepository;

@Component // cand se da run Spring o ca creeze un obiect de tipul clasei
public class PacientTransformer extends AbstractTrasformer<Patient, PacientDto> {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public PacientDto toDto(Patient patient) {
		PacientDto dto = new PacientDto();
		BeanUtils.copyProperties(patient, dto);

		if (patient.getBirthday() != null) {
			dto.setBirthday(patient.getBirthday().toString());
		}
		dto.setDoctorId(patient.getDoctor().getId());

		return dto;
	}

	@Override
	public Patient toEntity(PacientDto dto) {
		Patient patient = findOrCreateNew(dto.getId());
		BeanUtils.copyProperties(dto, patient);
		if (dto.getBirthday() != null) {

			patient.setBirthday(LocalDate.parse(dto.getBirthday()));

		}

		patient.setDoctor(doctorRepository.getById(dto.getDoctorId()));
		return patient;
	}

	@Override
	protected Patient findOrCreateNew(Long id) {
		return id == null ? new Patient() : patientRepository.findById(id).orElseGet(Patient::new);

	}

}
