package ro.kronsoft.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ro.kronsoft.training.dto.DoctorDto;
import ro.kronsoft.training.entitis.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	List<Doctor> findByFirstNameIgnoreCase(String firstName);
	List<Doctor> findByHouseNumber(Integer houseNumber);
	
	
	Doctor getById(Long id);
	
	boolean existsById(Long id);
	
	Doctor findByEmail(String string);
	boolean existsByEmail(String email);
	
    @Query(nativeQuery = true, value="SELECT COUNT(*) FROM doctor WHERE email = :email AND id <> :id")
	long countByEmailNotId(@Param("email")String email, @Param("id")Long id);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//this is HQE	
//	@Query("SELECT d FROM Doctor d LEFT JOIN FETCH d.patients p WHERE d.id = :id")
//	Doctor findByIdFull(@Param("id") Long id);//@param- ca sa ia id -ul de sus
//	
//	@Query(nativeQuery=true,value="SELECT count(*) FROM doctor WHERE email ILIKE'%gmail%'")
//	Integer countAllWithGmail();

	
	
}
