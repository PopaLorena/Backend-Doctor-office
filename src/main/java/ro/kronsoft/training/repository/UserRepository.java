package ro.kronsoft.training.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ro.kronsoft.training.entitis.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String user);
	
	
	boolean existsByUsername(String username);
	
	 @Query(nativeQuery = true, value="SELECT COUNT(*) FROM user WHERE username = :username AND id <> :id")
		long countByUsernameNotId(@Param("username")String username, @Param("id")Long id);

	 User getById(Long id);
}

