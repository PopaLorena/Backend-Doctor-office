package ro.kronsoft.training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ro.kronsoft.training.entitis.Patient;

@Repository
//@Scope(value = "prototype")
public interface PatientRepository extends JpaRepository<Patient, Long> {



List<Patient> findByLastName(String lastName);

List<Patient> findByDoctorId(Long id);
//Optional<Patient> findById(Long id);

//List<Patient> findByLastNameOrderByFirstNameASC(String lastName);


}
