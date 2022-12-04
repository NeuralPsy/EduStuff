package il.neuralpsy.edustuff;

import il.neuralpsy.edustuff.controller.UserController;
import il.neuralpsy.edustuff.dto.UserDto;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.model.UserType;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class EdustuffApplicationTests {

	@Autowired
	private final UserController userController;

	@Autowired
	private final ModelMapper modelMapper;



//	@Test
//	void shouldAddUser() {
//		User maria = createUser1();
//		int userId = userController.addUser(maria).getUserId();
//		UserDto userFromDB = modelMapper.map(userController.getUserById(userId), UserDto.class);
//		assertThat(userFromDB.getUserId()).isEqualTo(userId);
//		assertThat(userFromDB.getName()).isEqualTo("Maria");
//		assertThat(userFromDB.getBirthdate()).isEqualTo("1990-02-03");
//	}

//	@Test
//	void shouldFindUserByEmail() {
//		User professor = createUser2();
//		User userFromDB = userController.addUser(professor);
//		assertThat(userFromDB.getEmail()).isEqualTo(professor.getEmail());
//	}

	private User createUser1() {
		User user = new User();
		user.setUserType(new UserType(1, "STUDENT"));
		user.setName("Maria");
		user.setBirthdate(LocalDate.parse("1990-02-03"));
		user.setEmail("pumpkin@gmail.com");
		return user;
	}

	private User createUser2() {
		User user = new User();
		user.setUserType(new UserType(2, "TEACHER"));
		user.setName("Mr. Professor");
		user.setBirthdate(LocalDate.parse("1976-11-09"));
		user.setEmail("professor@yahoo.com");
		return user;
	}

}
