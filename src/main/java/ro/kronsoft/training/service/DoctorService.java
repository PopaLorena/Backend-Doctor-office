package ro.kronsoft.training.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.kronsoft.training.entitis.DocTypeEnum;
import ro.kronsoft.training.entitis.Doctor;
import ro.kronsoft.training.entitis.Patient;
import ro.kronsoft.training.repository.DoctorRepository;
import ro.kronsoft.training.repository.PatientRepository;
import ro.kronsoft.training.transformer.DoctorTransformer;

@Service
@Transactional
public class DoctorService {

	@Autowired
	private DoctorTransformer doctorTrasformer;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	@PostConstruct
	void createStartupDate() {
		try {
			if (doctorRepository.count() == 0 && patientRepository.count() == 0) {
				Doctor doc1 = doctorRepository.save(new Doctor("Aurelia", "Marin", "marinau@yahoo.com",
						"Str. Cl. Bucuresti", 32, DocTypeEnum.PHYSICIAN));
				Doctor doc2 = doctorRepository.save(new Doctor("Ionel", "Marcu", "ionel.doctor@yahoo.com",
						"Str. Nucului", 182, DocTypeEnum.THERAPIST));
				Doctor doc3 = doctorRepository.save(new Doctor("Damian", "Olaru", "damian-olaru@gmail.com",
						"Str. Florii", 11, DocTypeEnum.PHYSICIAN));
				Doctor doc4 = doctorRepository
						.save(new Doctor("Maria", "Pavel", "pmaria@gmail.com", "Bl. Saturn", 58, DocTypeEnum.SURGEON));

				patientRepository.save(new Patient("Alexandru", "Pop", "0754865895", doc4));
				patientRepository.save(new Patient("Mihai", "Pop", "0785642536", doc4));
				patientRepository.save(new Patient("Dorel", "Antonoaie", "0786472355", doc3));
				patientRepository.save(new Patient("Mihai", "Maxim", "0796387451", doc2));
				patientRepository.save(new Patient("Oana", "Pop", "0714752369", doc2));
				patientRepository.save(new Patient("Mihaela", "Roman", "0769835412", doc1));
				patientRepository.save(new Patient("Marcel", "Constantin", "0723986572", doc1));
				patientRepository.save(new Patient("Iulia", "Popescu", "0723456897", doc1));
			}
		} catch (Exception e) {
			System.out.println("fail silently as this is just dummy data insection method");
		}
	}

	public void printAllDoctors() {
		System.out.println("printing all doctors");
		List<Doctor> allDoctors = doctorRepository.findAll();
		allDoctors.forEach(d -> System.out.println(d.toString()));

	}

	void findByFirstName(String firstName, Integer houseNumber) {
		System.out.println("printing all doctors with first name " + firstName);
		List<Doctor> allDoctors = doctorRepository.findByFirstNameIgnoreCase(firstName);
		allDoctors.forEach(d -> System.out.println(d.toString()));

		List<Doctor> allDoctorsByHouseNR = doctorRepository.findByHouseNumber(houseNumber);
		allDoctorsByHouseNR.forEach(d -> System.out.println(d.toString()));

	}

//	void findDoctorByIdFull(Long id) {
//		Doctor doctor = doctorRepository.findByIdFull(id);
//
//		if (doctor == null) {
//			System.out.println("Doctor null");
//		} else {
//			System.out.println("This doctor has :" + doctor.getPatients().size() + " patients.");
//			System.out.println(doctor.toString());
//		}
//
//		try {
//			System.out.println("This doctor has :" + doctor.getPatients().size() + " patients.");
//			System.out.println(doctor.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("doctor is null");
//
//		}
//
//		Optional<Doctor> optDoctor = doctorRepository.findById(id);
//		if (optDoctor.isPresent()) {
//			Doctor doctor2 = optDoctor.get();
//			System.out.println("This doctor name is :" + doctor2.getFirstName());
//			System.out.println(doctor2.toString());
//		} else {
//
//			System.out.println("doctor is not present");
//		}
//		
//	}

	Doctor findDocById(Long id) {
		Optional<Doctor> optDoctor = doctorRepository.findById(id);
		return optDoctor.orElseThrow(() -> new RuntimeException("Doctor not found"));
	}

//	void printNrOfGmailAddress() {
//		System.out.println(doctorRepository.countAllWithGmail()+"nr doc");
//	}

	public Doctor updateDoctorEmail(Long id, String email) {
		Doctor toBeUpdated = this.findDocById(id);
		toBeUpdated.setEmail(email);
		return doctorRepository.save(toBeUpdated);

	}

	public List<Doctor> findPatient() {
		return doctorRepository.findAll();
	}

	public Doctor saveDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	public List<Doctor> findDoctor(String firstName) {
		return doctorRepository.findByFirstNameIgnoreCase(firstName);
	}

	public void deleteDoctor(Long id) {
		doctorRepository.deleteById(id);
	}

	public void deleteDoctors() {
		doctorRepository.deleteAll();
	}

	public List<Doctor> findDoctor() {
		return doctorRepository.findAll();
	}
	

}
