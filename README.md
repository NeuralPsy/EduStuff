# EduStuff - Educational Stuff
Application for interaction of students and teachers


There is a couple of possible roles for users: "teacher" and "student"
Each of them may create tasks and comment it. Every action (create task, add comment and removing of a task) is being tracking and listing in the index page.

Only users of the "teacher" role are able to see other students actions.

Studens are able to see only their own actions, but the may "Take" task and do something with it.
If task is taken, it's not available to be taken by other students.

If today is user's birthday, little congratulation alert pops up when it's first login of user during a day.





### How to start

Before you start:
* You need to install MySQL on your machine if it is not installed
* Then create new database and name it as you wish

1. Clone this repository: git clone https://github.com/NeuralPsy/EduStuff.git
2. Open repository in your IDE
3. Open application.properties file which is in root folder
4. Edit these lines in accordance to your MySQL settings:
spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_NEW_MYSQLDATABASE_NAME

spring.datasource.username=MYSQL_USERNAME

spring.datasource.password=MYSQL_PASSWORD

5. Then start src/main/java/il/neuralpsy/edustuff/EdustuffApplication.java file

6. Open a web-browser and type "localhost:8080". Go to this adress

If everything is fine the login page should be loaded

Voila! Now it's working!

