This Java software package relies on the existence of an Intersystems Cache database with 
the created database classes. Because the examiners may not be able to install a Cache 
database and test this software system, a Video has been provided with the electronic media
sent with this Project. The Video will show the execution of the Java software package and
all the database classes in the Cache database.

All dummy data that was inserted into the Cache database was done by means of the
two Java files in the Package: AddDatatoDb. The NewZone.java file contains a 
database code template from which it can be instantiated for each new zone in the
Cache database. The CreateData.java file has a main() method which then executes
the creation of the new zones which are then stored in the database. This is not
part of the software to manage a BIND DNS implementation, but rather it provides
the means of testing the software application.

The directory 'cachedatabasedefinitions' contains a text file with all the Cache database
class definitions. Normally, they are separate .cls files, however, for size and space, 
they have been amalgamated into one .txt file for simplicity.

The main class for the Project's software as a whole is 'DnsGui.java', which is
contained in the package 'Main'. The package 'JUnitTests' within the Test Packages
(or ProjectSoftware/test/JUnitTests') contains JUnit tests that were used in 
the software development process of the ProjectSoftware. They are not used in any
way for the experiments.

The code for the experiments involving the Cache database ONLY are contained
in the Test Packages in the package 'JUnitExperiments'. The code for the other
experiments are contained in the other Java Project called 'RDBMSExperiments'.
