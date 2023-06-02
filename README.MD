
# Musée Rennes




## Contexte

Nous devons réaliser une application pour les musées rennais en mettant en avant les éléments de sécurité. 
L'objectif est de recenser les différents éléments sur un plan et de mettre en place des alertes. 
En effet, la base de données est constituées de différents capteurs qui possèdent des valeurs. Lorsqu'une règle est établie, il est possible d'afficher une alerte. 



## Fonctionnalités

- Création de plusieurs étages

- Création de Camera/Capteurs

- Déploiement de capteurs sur le plan pour connaitre leurs positions en cas d'Alertes

- Création d'Alertes


## Installation

### Les logiciels : 

![alt text](https://www.eclipse.org/downloads/assets/public/images/logo-eclipse.png) 

- IDE - Eclipse : https://www.eclipse.org/downloads/

![alt text](https://johobase.com/jb/wp-content/uploads/2020/03/sqlserver-management-studio-icon.png) 


- SQL - Microsoft SQL Serveur Management 18 : https://learn.microsoft.com/fr-fr/sql/ssms/sql-server-management-studio-ssms?view=sql-server-ver16&viewFallbackFrom=sql-server-ver18

![alt text](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.ktt3wOobRbl2sAxLxcvzrQHaHa%26pid%3DApi&f=1&ipt=733a57daef3f07f3b689d5fc7a3b0fac790d229c2be54c65bcc1f912e54c5de3&ipo=images) 


- Scene Builder : https://gluonhq.com/products/scene-builder/

### Les librairies : 

![alt text](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Flearn.devsfix.com%2Fwp-content%2Fuploads%2F2022%2F10%2Fjavafx.png&f=1&nofb=1&ipt=700966bf4e2f59b0dc1361c7dfcfff53b14d207678f207c77901887e70e4fceb&ipo=images) 


- JavaFX - version = javafx-sdk-19.0.2.1 : https://gluonhq.com/products/javafx/


- Maven Json - version = json-20230227  :  https://mvnrepository.com/artifact/org.json/json

- Java - sqljdbc42 : https://www.microsoft.com/fr-fr/download/details.aspx?id=54671
## Mise en place 

### Intégration de la base de données dans Microsoft SQL server

Pour réaliser l'intégration de la BD, il suffit d'utiliser le fichier .bacpac présent dans le Github. 

Pour finir, il faut vérifier que les services ci-dessous soient correctement lancés. 

![image](https://github.com/Chic0s/ProjetMuseeBTS/assets/96829109/a8f61982-fcb0-46ac-a57f-e80a590b499a)

En cas d'erreur TCP/IP, il est nécessaire de ce rendre dans le logiciel SQL Server Configuration Manager > Configuration du réseau SQL Server > TCP/IP et de le mettre en " Activé ". 

![image](https://github.com/Chic0s/ProjetMuseeBTS/assets/96829109/b141632b-5238-4715-a7ac-ff9bf761871a)


### Intégration du projet dans Eclipse 

Dans un premier temps, il est important d'intégrer les différentes librairies dans le projet. 
A noté, il est nécessaire d'utiliser un argument pour lancer le projet avec JavaFX. 
```
VM argument : --module-path "lien vers le dossier lib de la librairie JavaFX -> javafx-sdk-19.0.2.1\lib" --add-modules=javafx.controls,javafx.fxml
```
## Utilisation de L'éxécutable

Un dossier " executable " est présent dans le répo Github. Celui-ci possède une version runable du projet en .jar. Pour faciliter son utilisation, il est obligatoire de le lancer via le CMD et de mettre à jour le path du JavaFX. 

```

@echo off
java --module-path " votre path vers le dossier lib de JavaFX -> javafx-sdk-19.0.2.1\lib" --add-modules javafx.controls,javafx.fxml -jar MuseeSecurite.jar
PAUSE

```
## Diagramme

### Diagramme de classes

![image](https://github.com/Chic0s/ProjetMuseeBTS/assets/96829109/033b1759-26a8-4bf7-a641-468dea788edc)

### Diagramme de cas d'utilisation

![image](https://github.com/Chic0s/ProjetMuseeBTS/assets/96829109/23937665-8c30-4143-a430-a093d54afbab)
