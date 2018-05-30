# EmployeeManagerApp
This App Manages Employee List and Manager Signup and Login


Server Side -

1. Java
2. Spring + Spring Boot
3. Hibernate

UI - 

1. HTML
2. JS
3. JQuery

DB -

Oracle 11g


How to run the code :-

A. Before starting the application you must create two tables in schema of your DB, located in src/main/resources/sql 
B. Change application.properties which is in src/main/resources according to your DB connection details 
C. Now run application by importing EmployeeManagerApp to eclipse & Run OR run - mvn clean install and mvn spring-boot:run in cmd from Root folder of the code  

 
- Application UI can be accessed with -

a. http://localhost:1234/EmployeeManager/static/login
b. SignUp from Login Page
c. Login using Signup Details
d. Empty Employee table will be displayed on Home screen
e. Add Employee by clicking on Add Employee button
f. You can Update/Delete employee from Home screen
g. Logout