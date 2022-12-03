package il.neuralpsy.edustuff;

import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.model.UserType;
import il.neuralpsy.edustuff.repository.UserRepository;
import il.neuralpsy.edustuff.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EdustuffApplication {

		public static void main(String[] args){

			SpringApplication.run(EdustuffApplication.class, args);
		}

	@Bean
	public CommandLineRunner loadData(UserTypeRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new UserType(1, "STUDENT"));
			repository.save(new UserType(2, "TEACHER"));
		};


	}
}
