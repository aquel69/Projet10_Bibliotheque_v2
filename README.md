# Projet7_Bibliotheque
Développement d'un nouveau système d’information de la bibliothèque d’une grande ville 

---

---

## I - Installation complete du projet

---  

### 1 - Récupération et installation du projet

---  

 - Tout d'abord, récupérez le repo dans cette page en copiant l'url comme indiqué en-dessous :  

![photo 1 Récupération URL](documentation/screenshots/installation_projet_1.png "Récuération URL")  

 - Ensuite, vous aurez besoin du logiciel GitBash que vous pouvez télécharger [ici](https://gitforwindows.org/), pour télécharger le clone du projet.

 - Installez le logiciel et Faite un clic droit à l'endroit ou vous souhaitez mettre votre projet, puis sélectionner Git Bash Here :  

![photo 2 Git Bash](documentation/screenshots/installation_projet_2.png "Git Bash")

 - Le logiciel Git Bash s'ouvre automatiquement, et se place directement dans l'emplacement, où vous vous trouvez. 
   Il ne vous reste plus qu'a saisir la ligne de commande suivante pour récupérer le projet dans votre système :  
   git clone https<span>://</span>github.com/aquel69/Projet7_Bibliotheque.git  
   
 Vous devriez avoir maintenant tous les fichiers du projets dans votre dossier :

![photo 3 dossier contenant projet](documentation/screenshots/installation_projet_3.png "dossier contenant projet")  

### 2 - Ouverture du projet avec IntelliJ IDEA

---  

 - Maintenant que notre projet se trouve dans notre dossier, nous allons pouvoir le lancer dans un IDE comme Intellij IDEA, 
   disponible à cette [adresse](https://www.jetbrains.com/fr-fr/idea/).
   
 - Une fois l'installation d'Intellij effectué, cliquez sur 'Open', sélectionnez le dossier du projet et cliquez sur 'ok'.  

 - Vous devez vous retrouvez avec une arborescence du projet comme celle-ci :  

![photo 4 arborescence projet](documentation/screenshots/installation_projet_4.png "arborescence projet")  

### 3 - Configuration du projet avec IntelliJ IDEA

---  

 - Il faut maintenant configurer le projet, pour ceci allez dans File -> Project Structure et dans le premier onglet 'Project'  
modifier le SDK comme indiquer sur le screen, avec la version 8 disponible [ici](https://www.oracle.com/fr/java/technologies/javase/javase-jdk8-downloads.html),
   si vous ne l'avez par encore installé :

![photo 5 modification sdk](documentation/screenshots/installation_projet_5.png "modification sdk")  

 - Faire la même procédure pour l'onglet SDKs : 

![photo 6 modification sdk](documentation/screenshots/installation_projet_6.png "modification sdk")

### 3 - Installation de PgAdmin 4 et création de la base de données

---  

 - Tout d'abord, installons l'interface pgAdmin 4 que vous pouvez télécharger [ici](https://www.pgadmin.org/download/).  
 - L'interface va nous permettre d'effectuer des requêtes postgresql, afin d'effectuer l'installation de notre base de données.  
 - Rdv dans l'interface de pgAdmin et ajoutez une base de données :  
   clic droit sur PostgreSQL 12 dans la colonne de gauche -> Create -> Database

![photo 1 creation bdd](documentation/screenshots/creation_bdd_1.png "creation BDD")  

 - Donnez-lui un nom puis valider.  

### 4 - Mise en place de la structure et récupération des data de la base de données

---  

 - Récupérons nos requêtes pour créer notre structure dans le dossier de notre projet :  
  database -> Backup -> backup_structure.sql
 - Sélectionnez toutes les requêtes, puis copiez les.
 - Dans l'interface PgAdmin 4 cliquez sur la base de données que vous venez de créer puis sur le bouton 'Query Tool' :  

![photo 2 creation bdd](documentation/screenshots/creation_bdd_2.png "creation BDD")  

 - Il ne reste plus qu'à coller les requêtes, dans la fenêtre que vous venez d'ouvrir, sélectionnez toutes vos requêtes et
   cliquer sur le bouton 'Execute' :

![photo 3 creation bdd](documentation/screenshots/creation_bdd_3.png "creation BDD")  

 - Regarder dans votre arborescence, vous devriez vous retrouvez avec toutes les tables :  

![photo 4 creation bdd](documentation/screenshots/creation_bdd_4.png "creation BDD")  

 - Effectuons les mêmes opérations pour récupérer la data, à l'aide du fichier 'backup_structure.sql se trouvant 
   également dans le dossier 'Backup' et votre base de données sera créée.


### 5 - Modification des chemins dans les modules 

---

 - Modifier le chemin du certificat https dans le fichier 'application.properties' dans le module 'interface abonné' :

![photo 1 modification chemin](documentation/screenshots/modification_des_chemins_1.png "modification chemin")

 - Ensuite dans les modules 'catalogue', 'authentification' et 'gestion', modifiez le fichier 'application properties'.  
   Remplacez les propriétés de connexion à la base de données :  

![photo 2 modification chemin](documentation/screenshots/modification_des_chemins_2.png "modification chemin")  

 - Dans le dernier module 'Batch', modifiez les propriétés de la base de données comme précédemment.  
   Mais aussi les propriétés de configuration de votre mail, afin d'envoyer les emails aux abonnés.  

![photo 3 modification chemin](documentation/screenshots/modification_des_chemins_3.png "modification chemin")


### 6 - Démarrage des microservices et accès à l'application

---

 - Maintenant que tout est en place pour faire fonctionner le logiciel, il reste plus qu'à démarrer les différents microservices.
 - Tout d'abord nous allons démarrer les microservices suivant :  
        - authentification-utilisateur  
        - gestion-utilisateur  
        - catalogue-livres  
   En se rendant dans les classes ...Application, et en cliuant sur la petite flèche verte 'Run':

![photo 1 démarrage microservice](documentation/screenshots/démarrage_des_microservice_1.png "démarrage microservice")  

 - Ensuite faites la même procedure pour le microservice 'interface-utilisateur'.
 - Démarrez ensuite votre navigateur, puis entrez l'adresse suivante : https://localhost:8443/
 - Si la page suivante s'affiche, c'est que le logiciel fonctionne :  

![photo 2 démarrage microservice](documentation/screenshots/démarrage_des_microservice_2.png "démarrage microservice")  

 - Pour la partie batch, l'envoi des emails se fera de la même façon, une fois la configuration du fichier properties,  
   vous n'aurez qu'à exécuter la classe 'BiblioBatchApplication'.

### 7 - Connexions aux différents utilisateurs

---

- Pour se connecter en tant qu'employé, il faut entrer l'adresse suivante : dupont.regis@yahoo.fr  
  et le mot de passe : 123.

- Sinon Pour se connecter en tant qu'abonné, il faut entrer les adresses se trouvant dans la table 'abonné' de la base de données. 
  Ainsi que le mot de passe commun à toutes les adresses : 123456.
  
- Sinon créez vous-même votre abonné. Pour ceci, rendez-vous dans la page authentification et créez votre propre compte.



## II - Procédure de la création du Projet et de sa Base de données 

---

### 1 - Mise en place du projet

---

Pour commencer, je me suis rendu sur le site : https://start.spring.io/, et j'ai ajouté les dépendances souhaitées :

![photo 1 initializer](documentation/screenshots/installation_du_projet_1.png "Initializer")

 - Spring-web qui réunit plusieurs starters, permettant de faire fonctionner comme nous le souhaitons notre microservice.
 - Thymeleaf nous permet d'accéder aux objets dans les pages HTML.
 - Lombok nous permet de nous faciliter la gestion des beans.  

Une fois générer, j'ai ajouté le dossier à mon projet puis mis le dossier en tant que module.

J'ai reproduit cette opération sur tous les microservices que je souhaitai créer :  

![photo 4 modules](documentation/screenshots/installation_du_projet_4.png "modules")

J'ai ajouté dans mes fichiers pom.xml, les dépendances dont j'avais l'utilité en fonction de mes besoins(screen du pom de mon module interface).  

![photo 1 pom](documentation/screenshots/installation_du_projet_5.png "Pom interface-utilisateur")

### 2 - Création de la base de données sous pgAdmin 4 - V4.19

---

Tout d'abord j'ai créé sous pgAdmin 4 (version 4.19), la base de données :

- Pour commencer, j'ai ajouté la dépendance de postgresql dans mes fichiers pom.xml utilisant la base de données.  


- Puis j'ai récupéré à partir du logiciel power architect la requête de création de la structure de ma base de données, qu'ensuite j'ai pu créer grace à l'interface pgAdmin 4. 

![photo 1 pgAdmin](documentation/screenshots/installation_du_projet_6.png "création BDD sous pgAdmin4")
  
- Je l'ai ensuite mis au propre grace au logiciel pgModeler.  

![photo 1 modèle physique de données](documentation/screenshots/installation_du_projet_3.png "modèle physique de données")

### 3 - Utilisation de Maven

---

 - Chaque microservice possède indépendamment son propre fichier pom, lui permettant de gérer ses propres dépendances. Grace à spring boot
 nous ajoutons la possibilité d'affiner la gestion des fonctionnalités à l'aide des starters.  
 - J'ai pu également utilisé différente version des dépendances, afin de corriger des soucis de compatibilité de version entre,
 spring boot et des librairies comme lombok.


---

## III - Fonctionnement du projet

---  

### 1 - Fonction des différents microservices

---  

 - Chaque microservices gèrent une partie de l'application(n-tiers). Sur mon projet, je les ai répartie en 5 modules :
    * Module biblio-interface-utilisateur
    * Module biblio-gestion-utilisateur
    * Module biblio-authentification-utilisateur
    * Module biblio-catalogue-livres
    * Module biblio-batch
  
 1. __Module biblio-interface-utilisateur :__
      Le premier module gère le côté client et gestion de l'interface des utilisateurs. Il contient tous les fichiers html,css, js, ainsi que les photos illustrant le site. Il récupère les données des autres microservices et les traites par le biais des controllers.  
    
    
 2. __Module biblio-catalogue-livres :__
      Ce module gère toute la partie catalogue, cela regroupe la gestion des ouvrages, des livres et des prêts. Ce module est connecté avec la base de données, et permet de les envoyer au module interface selon ses demandes.  
    

 3. __Module biblio-gestion-utilisateur :__
      Le module de gestion des utilisateurs gère l'ajout de compte utilisateur et la modification de ceux-ci. Ce module est également connecté avec la base de données, il récupère et envoi les données au module interface selon ses demandes.
 

 4. __Module biblio-authentification-utilisateur :__
      Ce module gère uniquement la partie authentification. Il permet la vérification des données saisies par l'utilisateur et renvoi au module interface, les données permettant de connecter l'utilisateur correspondant.  
    

 5. __Module biblio-batch :__
      Le dernier module permet le traitement des batchs. Il permet de récupérer dans la base de données les informations nécessaires à l'envoi de mail aux abonnées en fonction de leurs emprunts et de la restitution de ceux-ci. Les mails sont envoyés à raison de une fois par jour à minuit.
    

### 2 - Mise en place et fonctionnement de la communication entre les microservices

---  

 - Les modules communiquent entre eux par le biais de Feign. Feign est un client http qui permet, grace à des requêtes http, la communication entre les microservices. Tout d'abord nous mettons feign en place, pour cela nous ajoutons dans notre fichier pom la dépendance suivante :  

![photo 1 fichier pom](documentation/screenshots/communication_entre_module_1.png "fichier pom interface")  

 - J'ai ensuite ajouté feign dans mon microservices, suivie du module à scanner, ceci est possible grace à l'annotation suivante : 

![photo 2 classe lancement microservice interface](documentation/screenshots/communication_entre_module_2.png "fichier executable interface")  

 - Il ne reste plus qu'à ajouter le client Feign dans les interfaces contenues dans le package proxies, pour cela nous ajoutons l'annotation suivante :  

![photo 3 proxies Authentification](documentation/screenshots/communication_entre_module_3.png "interface Authentification proxies")  


### 3 - Fonctionnement des microservices

---  

 - Chaque microservice gèrent différentes couches :  
        - __Controller__ : contenant les différentes méthodes servant à récupérer des données. Et pour le microservice 'interface-utilisateur', 
        la gestion des différentes pages du site et de son interaction.  
        - __Dao__ : permettant d'interagir avec la base de données, l'utilisation de Spring Data JPA permettant de générer  
        toutes sortes d'opérations vers la base de données.  
        - __Model__ : Le model regroupe tous les beans, servant à manipuler les données. Les beans récupères les données via hibernate.
        Cela rend plus facile l'accès aux données dans la base, couplé avec Spring Data JPA, nous pouvons facilement à partir
        des annotations d'hibernate dans nos beans et une méthode de l'interface Spring Data avoir accès à la plupart des données dans nos objets.  
        - __Service__ : Cette couche dont je me suis servie dans certain microservice, m'a permis d'exploiter et de traiter
        différemment les données, afin que je puisse manipuler mes beans comme je le souhaitais.

### 4 - Documentation

---  

 - J'ai inclus dans mon projet également un dossier sur sa documentation et ses ressources :  
        - __Javadoc__ : permettant d'avoir des informations sur toutes les classes et méthodes.  
        - __Certificats Https__ : permettant de sécuriser les données entre le serveur et le client.  
        - __Backup__ : Les sauvegardes de ma base de données.  
        - __Modèle Physique de Données__ : permettant sous power Architect de mettre en place la base de données et de
        générer le code postgresql permettant de la créer.  
        - __Spécifications Fonctionnelles__ : toute la documentation technique permettant la mise en place du projet
        en amont, afin d'établir toutes les fonctions du site et de son interface, pour faciliter la mise en oeuvre de celui-ci. 
   
   
          
