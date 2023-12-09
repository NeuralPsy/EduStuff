# EduStuff - Educational Stuff
EduStuff is a web application designed to facilitate student-teacher interactions, streamlining the learning and communication process. As the backend developer, my primary responsibility was to create and maintain the server-side of the application, ensuring reliable, secure, and efficient data processing. The frontend component was developed by me as a supplementary tool to demonstrate and test the backend functionality. This approach allowed me to focus on the critical aspects of server logic, while a simple yet functional user interface provided adequate interaction for testing and showcasing the system's core features.

The application offers two user roles: 'teacher' and 'student.' Both can create tasks, comment on them, and all actions, including task creation, commenting, and deletion, are tracked and displayed on the index page.

Teachers have the unique ability to view the actions of all students, whereas students can only see their activities. Students can 'take' tasks for completion. Once a task is taken, it becomes unavailable to others.

Additionally, the application features a thoughtful touch: if it's a user's birthday, they are greeted with a congratulatory alert upon their first login of the day.



### How to Start

Before beginning:
* Ensure MySQL is installed on your machine. If not, install it first.
* Create a new database and name it as preferred.

1. Clone the repository: `git clone https://github.com/NeuralPsy/EduStuff.git`
2. Open the repository in your IDE.
3. Locate the `application.properties` file in the root folder.
4. Update the following lines with your MySQL settings:
   - `spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME`
   - `spring.datasource.username=YOUR_MYSQL_USERNAME`
   - `spring.datasource.password=YOUR_MYSQL_PASSWORD`
5. Run `EdustuffApplication.java` in `src/main/java/il/neuralpsy/edustuff`.
6. In a web browser, navigate to `localhost:8080`.

The login page should load if the setup is correct.

Congratulations, you're all set!

