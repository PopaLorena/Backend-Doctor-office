package ro.kronsoft.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ro.kronsoft.training.dto.PacientDto;
import ro.kronsoft.training.entitis.Patient;
import ro.kronsoft.training.repository.PatientRepository;
import ro.kronsoft.training.transformer.PacientTransformer;

@Service
@Transactional
public class PatientService {

	@Autowired
	PacientTransformer pacientTransformer;

	@Autowired
	private PatientRepository patientRepository;

	public List<Patient> findPatient(String lastName) {
		return patientRepository.findByLastName(lastName);
	}

	public List<Patient> findPatient() {
		return patientRepository.findAll();
	}

	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	public void deletePatient(Long id) {
		patientRepository.deleteById(id);

	}

	public void deletePatients() {
		patientRepository.deleteAll();
	}

	public List<Patient> sortByFirstName() {

		List<Patient> patient = patientRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
		return patient;
	}

	public List<Patient> getPatientByDoctorId(Long id) {
		return patientRepository.findByDoctorId(id);
		
	}
	
	
	
}
