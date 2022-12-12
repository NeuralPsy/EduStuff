package il.neuralpsy.edustuff;

import il.neuralpsy.edustuff.model.Subject;
import il.neuralpsy.edustuff.model.TaskStatus;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.model.UserType;
import il.neuralpsy.edustuff.repository.SubjectRepository;
import il.neuralpsy.edustuff.repository.TaskStatusRepository;
import il.neuralpsy.edustuff.repository.UserRepository;
import il.neuralpsy.edustuff.repository.UserTypeRepository;
import org.modelmapper.ModelMapper;
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
	public CommandLineRunner loadUserTypes(UserTypeRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new UserType(1, "STUDENT"));
			repository.save(new UserType(2, "TEACHER"));
		};


	}

	@Bean
	public CommandLineRunner loadTaskStatus(TaskStatusRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new TaskStatus(1, "IN PROGRESS"));
			repository.save(new TaskStatus(2, "AVAILABLE"));
		};


	}

	@Bean
	public CommandLineRunner loadSubjects(SubjectRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Subject(1, "HISTORY"));
			repository.save(new Subject(2, "MATH"));
			repository.save(new Subject(3, "BIOLOGY"));
		};


	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}



}
