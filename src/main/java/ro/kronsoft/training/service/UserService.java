package ro.kronsoft.training.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.kronsoft.training.entitis.User;
import ro.kronsoft.training.entitis.UserAuthority;
import ro.kronsoft.training.repository.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	//	User user=  userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("not exist"));
	
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("The username does not exist!"));
	return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
			.password(user.getPassword()).authorities(user.getAuthority().toString()).build();
	
	}
	

	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	
	
	
	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setAuthority(UserAuthority.REGULER);
		return saveUser(user);
	}
	
	public void updateUser(User user) {
		String password=user.getPassword();
		String savePassword=userRepository.getById(user.getId()).getPassword();
		if(!password.equals(savePassword) && !passwordEncoder.matches(password,savePassword))
		{
			user.setPassword(passwordEncoder.encode(password));
		}
		
		saveUser(user);
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	@PostConstruct
	private void CreateAdmin() {
		String username="admin";
		if(!userRepository.existsByUsername(username)) {
		User user = new User();
		user.setUsername("admin");
		user.setEmail("admin@kronsoft.ro");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setAuthority(UserAuthority.ADMIN);
		saveUser(user);}
	} 
	private User saveUser(User user) {
		return userRepository.save(user);
	}



}
