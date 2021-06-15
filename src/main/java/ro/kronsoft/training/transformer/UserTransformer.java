package ro.kronsoft.training.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.kronsoft.training.dto.BaseDto;
import ro.kronsoft.training.dto.DoctorDto;
import ro.kronsoft.training.dto.UserDto;
import ro.kronsoft.training.entitis.Doctor;
import ro.kronsoft.training.entitis.User;
import ro.kronsoft.training.repository.UserRepository;

@Component
public class UserTransformer extends AbstractTrasformer<User, UserDto>{

	@Autowired
	 private UserRepository userRepository;
	
	@Override
	public UserDto toDto(User user) {
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(user, dto,"password");
		return dto;
	}

	@Override
	public User toEntity(UserDto dto) {
		User user = findOrCreateNew(dto.getId());
		BeanUtils.copyProperties(dto, user,"password");
		if(dto.getPassword()!=null) {
			user.setPassword(dto.getPassword());
		}
		return user;
	}

	@Override
	protected User findOrCreateNew(Long id) {
		return id == null ? new User() : userRepository.findById(id).orElseGet(User::new);
	}
	
	

}
