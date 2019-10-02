# factorymethod
This project needs the unityJDBC library jar for mongo to be manually loaded into your local maven repository.
Use comand:
mvn install:install-file -DgroupId=org.mongodb -DartifactId=unityjdbc -Dversion=1.0.0 -Dpackaging=jar -Dfile={filelocation}/unityjdbc.jar
Import in pom.xm:
        <dependency>
            <groupId>org.mongodb</groupId>
            <artifactId>unityjdbc</artifactId>
            <version>1.0.0</version>
        </dependency>
