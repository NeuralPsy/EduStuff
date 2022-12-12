package il.neuralpsy.edustuff;

import il.neuralpsy.edustuff.controller.UserRestController;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.model.UserType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class EdustuffApplicationTests {

	@Autowired
	private final UserRestController userRestController;

	@Autowired
	private final ModelMapper modelMapper;


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
