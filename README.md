# Configuration de la base de données

## jdbc

Modifier le fichier src/main/java/jdbc/DatabaseConnection.java :

- variable URL : le lien vers la base de données
- variable USER : le nom de l'utilisateur
- variable PASSWORD : le mot de passe de l'utilisateur

## jpa

Modifier le fichier src/main/resources/META-INF/persistence.xml :

- champ jakarta.persistence.jdbc.url : le lien vers la base de données
- champ jakarta.persistence.jdbc.user : le nom de l'utilisateur
- champ jakarta.persistence.jdbc.password : le mot de passe de l'utilisateur

# Compilation

- mvn clean package

# Run project

## Run jdbc

- java -jar target/esirify-jdbc-jar-with-dependencies.jar

## Run jpa

- java -jar target/esirify-jpa-jar-with-dependencies.jar
