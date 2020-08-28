++++ Exécuter le code ++++
Ma consol dit qu'elle ne connait pas les commandes maven (mvn) ce qui m'empeche
de lancer le code dans la consol

++++ Structure ++++
Mon projet utilise, pour l'application graphique, les packages:
    - le main: esi.g53298.atl.sokoban
    - le model: esi.g53298.atl.sokoban.model
    - la vue: esi.g53298.atl.sokoban.view
Note: j'ai gardé le package du controller esi.g53298.atl.sokoban.controller 
pour pouvoir basuler sur un affichage consol facilement en passant en 
commentaire la ligne 33 de la classe Main.java et décommentant les ligne
 31 et 32


++++ esi.g53298.atl.sokoban ++++
Ce package contient qu'une seul classe qui est la classe principal utilisé pour démarer l'application

++++ esi.g53298.atl.sokoban.model ++++
Ce package contient le model de l'application.
La façade du model est la classe Game et elle implemente l'interface Subject.

++++ esi.g53298.atl.sokoban.view ++++
Ce package contient les classes HomeRoot et LevelRoot qui sont des classes héritant VBox.
    - HomeRoot permet d'afficher la fenetre de choix de niveau
    - LevelRoot permet d'afficher le niveau à jouer.
        • Cette classe implemente l'interface Observer
