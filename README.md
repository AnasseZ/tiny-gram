# Tiny-gram

Application Java propulsée sur le cloud avec Google App Engine ! Lien : http://1-dot-tiny-gram.appspot.com

## Groupe

Anasse ZOUGARH, Nada KENITA

## Frontend

Le client est développé en Angular 5.

Le code du front-end est [ici](https://github.com/AnasseZ/tiny-gram-client) .


## Fonctionnalités

Des mesures sont faites afin de comparer quelles sont les fonctionnalités qui scallent le mieux. Vous pouvez, à travers 3 utilisateurs prédéfinis:

- **Poster une photo avec sa description**:   
Entrez votre message dans l'input **What's up** avec les hashtags contenus dans le message ( exemple :  " Super appli ! #excellent #miage #alma #cloud ) puis l'url (en png, jpg) de votre image dans l'input de la modal. Le post est ensuite enregistré correctement.  
  
- **Voir votre timeline**:  
Automatique, vous avez 6 boutons permettant de faire varier les différents critères, dès qu'un bouton est pressé le rechargement de la timeline est automatique ! Nul besoin de recharger la page :)   Les hashtags sont cliquables et vous redirigerons vers la page affichant tous les posts ayant ce hashtag !     
    
- **Mettre à jours les informations relatives aux 3 utilisateurs_**:  
Via le bouton settings en dessous de votre photo de profil.  
<br><br>
- **Follow un utilisateur depuis son id**:  En cliquant sur le nombre de followers en dessous de votre profil, après avoir rechercher l'utilisateur depuis son id (allant de u0 à u5000) vous pouvez le follow si vous le souhaitez ( vous êtes rajouté à **sa** liste de followers)    
<br><br>
- **Voir les photos d'un utilisateur depuis son id**:  
Onglet Search-post, renseigner l'id (allant de u0 à u5000) et vous verrez tous ses posts. Faire varier la taille du résultat est possible mais non automatique comme pour la timeline précédemment expliqué, il faudra donc recliquer sur **Search** .  
<br><br>
- **Voir toutes les photos par catégorie selon l'hashtag de votre choix**:  
Onglet Search-post, renseigner le hashtag (avec ou sans le #, comme vous le souhaitez !) et vous verrez tous les posts ayant ce hashtag, dans la limite choisie. Faire varier la taille du résultat est possible mais non automatique comme pour la timeline précédemment expliqué, il faudra donc recliquer sur **Search** .
<br>

## Résultats (en ms !!)

### Temps d'envoie d'un message 

| Followers     |     100         |     1000       |  5000          |
| :------------ | :-------------: | :------------: | :------------: |
| Moyenne       |    317      |        697   |        1159 |

### Temps de récupération de la timeline avec 10 messages 

| Followers     |     100         |     1000       |  5000          |
| :------------ | :-------------: | :------------: | :------------: |
| Moyenne       |     859         |        1117 |        1602  |

### Temps de récupération de la timeline avec 50 messages 

| Followers     |     100         |     1000       |  5000          |
| :------------ | :-------------: | :------------: | :------------: |
| Moyenne       |     5332        |        5581  |        5923  |

### Temps de récupération de la timeline avec 100 messages 

| Followers     |     100         |     1000       |  5000          |
| :------------ | :-------------: | :------------: | :------------: |
| Moyenne       |     5698      |        8148  |       9450 |

### Constat de ces temps
Les temps sont anormalement long depuis que Google cloud platform nous indique que nous avons atteint les quotas des ressources gratuites.

Le calcul est également fait côté front-end, Sachant qu'on appelle nos endpoint en HTTP afin d'obtenir des **ressources** , nous n'avons aucun moyen de faire une vérification côté backend (entre le query.execute()) et renvoyer le resultat côté front-end. Nous faisons donc un calcul avec une soustraction entre une date juste avant l'appel HTTP et celle juste après ce dernier. D'où ces valeurs plus elevés que si on avait fait ce calcul dans une servlet avec un simple println du résultat de query.execute() .

Pour des besoins graphiques et d'expérience utilisateur, pour chaque message nous cherchons l'utilisateur ayant posté le message à travers l'**userId** contenu dans la classe Message, ce qui rallonge notre temps d'éxécution. Pourquoi ne pas avoir inclu et persisté un objet User directement dans la classe Message? Car si nous faisons ça, lorsque l'utilisateur modifiera ses informations personnelles il faudra alors mettre à jours tous ses messages, si nous voulons garder de la cohérence. 

## Données

Les données sont peuplés une seule fois et non visible à l'utilisateur ( cf **les deux servlets et index2.html .**)
Les avatars, prénoms, noms, hashtags, sont choisis de manière aléatoire (environ une 10aine de données par attribut)
pour chaque création d'entité. Cf **tinygram/DataConstant.java** pour les données.
