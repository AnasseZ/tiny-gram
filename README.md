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
Entrez votre message dans l'input **What's up** avec les hashtags contenus dans le message puis l'url (en png, jpg) de votre image dans l'input de la modal. Le post est ensuite enregistré correctement.  
  
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
| Moyenne       |    x      |        x   |        x |
| Variance      |    x     |        x|      x    |

### Temps de récupération de la timeline avec 10 messages 

| Followers     |     100         |     1000       |  5000          |
| :------------ | :-------------: | :------------: | :------------: |
| Moyenne       |     859         |        x |        x  |
| Variance      |   x     |       x  |        x  |

### Temps de récupération de la timeline avec 50 messages 

| Followers     |     100         |     1000       |  5000          |
| :------------ | :-------------: | :------------: | :------------: |
| Moyenne       |     5332        |        x  |        x  |
| Variance      |     x      |        x  |        x |

### Temps de récupération de la timeline avec 100 messages 

| Followers     |     100         |     1000       |  5000          |
| :------------ | :-------------: | :------------: | :------------: |
| Moyenne       |     8939      |        x  |       x |
| Variance      |    x      |      x  |        x  |

Les temps sont anormalement long depuis que Google cloud platform nous indique que nous avons atteint les quotas des ressources gratuites.

## Données

Les données sont peuplés une seule fois (5000 users, plus de 1500 Message/MessageIndex)
Les avatars, prénoms, noms, hashtags, sont choisis de manière aléatoire (environ une 10aine de données par attribut)
pour chaque création d'entité. Cf **tinygram/DataConstant.java** pour les données.
