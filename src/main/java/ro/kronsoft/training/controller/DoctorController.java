package ro.kronsoft.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ro.kronsoft.training.dto.DoctorDto;
import ro.kronsoft.training.entitis.Doctor;
import ro.kronsoft.training.entitis.Patient;
import ro.kronsoft.training.service.DoctorService;
import ro.kronsoft.training.service.PatientService;
import ro.kronsoft.training.transformer.DoctorTransformer;


@RestController
@RequestMapping("/doctor") 
public class DoctorController {

	@Autowired
	private DoctorTransformer doctorTrasformer;
	
	@Autowired
	private DoctorService doctorServise;
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public  List<DoctorDto> getDoctor(){
		return doctorServise.findDoctor().stream().map(doctorTrasformer::toDto).collect(Collectors.toList());
	}
	
	
	@PostMapping(value="/create", consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)//avem body la request // produce schimbari chiar daca este apelatata de aceeasi de mai multe ori
	@ResponseStatus(HttpStatus.CREATED)
	public  DoctorDto createDoctor(@Validated @RequestBody DoctorDto doctor) {
		return doctorTrasformer.toDto(doctorServise.saveDoctor(doctorTrasformer.toEntity(doctor)));
	}
	
	@GetMapping(value = "/findByFirstName",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DoctorDto> getDoctorByfirstName(@RequestParam String firstName){
		return doctorServise.findDoctor(firstName).stream().map(doctorTrasformer::toDto).collect(Collectors.toList());
	}
	
	@PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)//are body pe respond/request 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateDoctor(@Validated @RequestBody DoctorDto doctor){
		doctorServise.saveDoctor(doctorTrasformer.toEntity(doctor));
	}
	
	@DeleteMapping(value = "/{id}/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDoctor(@PathVariable Long id) {// daca numele parametrumui e diferit de path punem @PathVariable("numele")
		doctorServise.deleteDoctor(id);
	}
	
	
	@DeleteMapping(value = "/deleteAll")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllDoctors() {
		doctorServise.deleteDoctors();
	}
	
//	@PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)//are body pe respond/request 
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void updateDoctor1(@RequestBody Doctor doctor){
//		patientService.savePatient(patient);
//	}
	
	
	
	
}
