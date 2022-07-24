=======
The library primefaces 3.4 is not in the web repositories. So we have to install it locally. 
This library is in the lib folder in the root path.
This is the command for installing a library with maven:
mvn install:install-file -Dfile=<path-to-file> -DgroupId=<group-id> -DartifactId=<artifact-id> -Dversion=<version> -Dpackaging=<packaging>

Then, open the console an go to the path where is the library and execute:
mvn install:install-file -Dfile=primefaces-3.4.jar -DgroupId=org.primefaces -DartifactId=primefaces -Dversion=3.4  -Dpackaging=jar

After that, you can build the project.