#Lab Assistant Server Setup

##**Project Set up**

Create the project from existing sources with Maven.
Right click on the file _**pom.xml**_ and select **Maven** -> **Reload project** to download the required dependencies.

##**Database connection**

Create a new database using postgreSQL.
Next to Host enter **_localhost_** and port number **_5432_**

Select User & Password as Authentication and enter **_postgres_** as User and your password from pgAdmin.
The database is called **_labtooldb_** and the url is the following:
**_jdbc:postgresql://localhost:5432/labtooldb_**

In the **_application.properties_** files change the database password with the one you have used to create the database in pgAdmin.
**_spring.datasource.password=_**

##Running the application

Right click on the file called **_LabToolJavaApplication_** and select **_Run_**, this will create the running configurations.