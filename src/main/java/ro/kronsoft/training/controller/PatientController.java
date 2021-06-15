package ro.kronsoft.training.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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

import ro.kronsoft.training.dto.PacientDto;
import ro.kronsoft.training.entitis.Patient;
import ro.kronsoft.training.service.PatientService;
import ro.kronsoft.training.transformer.PacientTransformer;

@RestController//in loc de @controller aici si de @ResponseBody la fiecare methoda
@RequestMapping("/patient")//ca sa nu mai punem la fiecare metoda "/patient" 
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private PacientTransformer patientTransformer;
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)//se foloseste cand citim niste resurse, nu putem avea un body pe request(deci nu pot trimite informatii printr-un body)
	public  List<PacientDto> getPatient(){
		
	
		return patientService.findPatient()
				.stream()
				.map(patientTransformer::toDto)// patient->patientDtoList.add(patientTransformer.toDto(patient))
				.collect(Collectors.toList());//trasforma in lista
//		List<PacientDto> patientDtoList=new ArrayList<>();
//		
//		patientService.findPatient().forEach(patient->patientDtoList.add(patientTransformer.toDto(patient)));
//		
//		return  patientDtoList;
	}
	
	@GetMapping(value = "/findByLastName",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PacientDto> getPatientByLastName(@RequestParam String lastName){
	return patientService.findPatient(lastName).stream().map(patientTransformer::toDto).collect(Collectors.toList());
	}
	
	@PostMapping(value="/create", consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)//avem body la request // produce schimbari chiar daca este apelatata de aceeasi de mai multe ori
	@ResponseStatus(HttpStatus.CREATED)
	public  PacientDto createPatient(@Validated @RequestBody PacientDto patient) {
		
		return patientTransformer.toDto(patientService.savePatient(patientTransformer.toEntity(patient)));
	}
	
	
	@PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)//are body pe respond/request 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePatient(@RequestBody PacientDto patient){
		patientService.savePatient(patientTransformer.toEntity(patient));
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping(value = "/{id}/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePatient(@PathVariable Long id) {// daca numele parametrumui e diferit de path punem @PathVariable("numele")
		patientService.deletePatient(id);
	}
	
	@DeleteMapping(value = "/deleteAll")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllPatient() {
		patientService.deletePatients();
	}

	@GetMapping(value="/SortByFirstName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PacientDto> sortByFirstName() {
		return  patientService.sortByFirstName().stream().map(patientTransformer::toDto).collect(Collectors.toList());	
		}
	
	@GetMapping(value="/byDoctor",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PacientDto> getPatientsByDoctor(@RequestParam Long doctorId)
	{
		return patientService.getPatientByDoctorId(doctorId).stream().map(patientTransformer :: toDto).collect(Collectors.toList());
	}
}
