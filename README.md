# API-Access

Automated tests that, when given a target url and an API Key, will decide whether to accept or decline potential customers. If the endpoint accepts a potential customer, an offer is included in the response. 


Execution Instructions:
0. Download the repository
1. If the user has VSCode or another equivalent IDE installed:
    (a) Ensure that the system running the tests has a JDK installed as well as the JAVA_HOME environment variable set in the respective system
    (b) Download the supported plugins needed to run tests, execute, and debug Java code (such as "Extension Pack for Java" and "Maven for Java" in VSCode) 
    (c) Click on File, Open Folder, and find the repository directory titled "Take-Home-Exercise" and open it.
        - The IDE should recognize it as a Java project, but if it doesn't then the user may have to create a Maven Java project similar to the properties 
          of this project and replace all the files with those of the repository (groupId=com.endpointcaller, artifactId=eligibility-tester,version=1.0-SNAPSHOT)
    (d) Go to the following file directory of the Eligibility-Tester project: `src\main\resources\application.properties`
    (e) Change the fields `loan.eligibilityservice.uri.target_url` and `loan.eligibilityservice.uri.api_key` to the proper values needed for execution
        - Note: an incorrect value for either will result in failed tests
    (f) Run/Debug the test found in `src\test\java\com\endpointcaller\EligibilityServiceTest.java` (In VSCode, can be found in the "Testing" tab)
2. If the user is running from command line:
    (a)  Ensure that the system running the tests has a JDK installed as well as the JAVA_HOME environment variable set in the respective system
        - Since this is a Maven Project, it is highly suggested to get OpenJDK and maven installed (as well as maven-assembly-plugin)
    (b) Ensure that the requisite jar files necessary for compiling and running are installed, (at least Junit v. 4.11, please check pom.xml)
    (c) Go to the following file directory of the Eligibility-Tester project: `src\main\resources\application.properties`
    (d) Change the fields `loan.eligibilityservice.uri.target_url` and `loan.eligibilityservice.uri.api_key` to the proper values needed for execution
        - Note: an incorrect value for either will result in failed tests
    (e) Follow the steps needed to compile the JAR file: https://www.sohamkamani.com/java/cli-app-with-maven/#compiling-our-jar-file
    (f) Follow the steps needed to run the unit tests located in `src\test\java\com\endpointcaller\EligibilityServiceTest.java`: 
        https://www.sohamkamani.com/java/cli-app-with-maven/#running-unit-tests
