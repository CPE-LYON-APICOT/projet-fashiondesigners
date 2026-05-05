# Fiche rendu projet

> Ce document est un bilan destiné au client. Présentez ce qui a été livré, ce qui fonctionne, et tournez habilement ce qui manque. Pas de jargon technique — on parle de fonctionnalités et de valeur perçue.

## Rappel du projet

Notre projet est un Jeu de type "Clicker" dis jeu incrémentale. Il proposera plusieurs "Ressources" à cliquer. Exemple : bois, poisson, fer, blé et ortie.
Grâce à ces ressources le joueur pourra crafter de nouveaux "Objets" à l'aide de "Recettes" afin d'améliorer et automatiser sa production pour créer de nouveaux objets encore plus utiles et puissants. **Pour un maximum de DOPAMINE !**

## Ce qui a été livré

<!-- Présentez les fonctionnalités livrées. Captures d'écran / GIFs animés bienvenus. -->
<!-- Placez vos images dans docs/images/ et référencez-les avec : ![description](images/nom-du-fichier.png) -->

### Fonctionnalité 1 — *Clicker*
Le but est d'appuyer sur un bouton, en cliquant dessus le joueur gagne une unité ressource. C'est la fonctionnalité centrale du jeu, elle permet au joueur de gagner des ressources pour progresser.

### Fonctionnalité 2 — *Recette*
Le joueur peut crafter des objets à l'aide de recettes. Chaque recette nécessite un certain nombre de ressources pour être réalisée. Les objets crafter permettent d'augmenter la production de ressources ou de débloquer de nouvelles fonctionnalités.


## Ce qui n'a pas été livré (et pourquoi)

### Fonctionnalité 3 — *Objet*
Les objets seront soit des "Outils", des "Usines" ou des "Améliorations". Les outils permettent de favoriser la production de ressources par clique et d'en défavoriser d'autres, les usines produisent des ressources automatiquement sans que le joueur ait besoin de cliquer, et les améliorations augmentent l'efficacité des autres objets et/ou la session de jeu.

### Pourquoi cette fonctionnalité n'a pas été livrée ?
Cette fonctionnalite n'as pas été livré car elle necessite une feature qui va avec et qui n'est actuellement pas mis en place. C'est pour cela que si on l'implemente ca va surcharger l'application et donc ne pas fournir une bonne experience utilisateur.

### Fonctionnalité 4 — *Métier et Expérience*
A chaque clique le joueur gagne de l'expérience dans le métier lié à la ressource cliquée. En gagnant de l'expérience, le joueur peut monter de niveau dans ce métier, ce qui débloque de nouvelles recettes et objets liés à ce métier. Exemple : Mineur niveau 20 débloque la ressource "Cuivre".Le joueur lui aussi prendra en niveau et débloquera de nouvelles recettes pour produire des objets encore plus puissants.

### Pourquoi cette fonctionnalité n'a pas été livrée ?

La fonctionnalite n'as pas ete implementé car l'interface utilisateur n'as pas ete concus pour afficher la progression des niveaux, de plus il etait complique de modifier le schema de donnée à ce stade de l'implementation, enfin nous n'avons pas trouver de consensus sur les paliers de progression; il faut garder le jeu addictif tout en etant accessible et cela risquait de desequilibrer le jeu.
Cette fonctionnalité pourra etre implemente plus tard.

### Fonctionnalité 5 — *Ambiance*
Notre jeu comprendra des images et des sons pour rendre l'expérience plus immersive et agréable. Les images représenteront les ressources, les objets et les recettes, tandis que les sons fourniront des feedbacks auditifs pour les actions du joueur, comme le gain de ressources ou la réalisation d'une recette.

### Pourquoi cette fonctionnalité n'a pas été livrée ? 

Cette fonctionnalité n’a pas été livrée car elle concernait principalement l’amélioration visuelle et sonore du jeu, et non son fonctionnement principal.
On a choisi de se concentrer d’abord sur les fonctionnalités essentielles, comme la collecte de ressources, l’inventaire et les recettes. L’ajout d’images et de sons aurait demandé du temps supplémentaire pour intégrer correctement les fichiers, gérer leur affichage et éviter les problèmes de performance.

### Fonctionnalité 6 — *Idle (non hors ligne)*
Même lorsque le joueur n'est pas actif, il continue de gagner des ressources grâce aux objets qu'il a crafté. Cela permet au joueur de progresser même lorsqu'il n'est pas en train de jouer activement.

### Pourquoi cette fonctionnalité n'a pas été livrée ?

Cette fonctionnalité n’a pas été livrée car elle demandait la mise en place d’un système automatique de génération de ressources, même lorsque le joueur ne clique pas directement.
Cela nécessitait de gérer le temps écoulé, les objets craftés par le joueur, la quantité de ressources générées automatiquement et la mise à jour régulière de l’inventaire. 
On a préféré se concentrer sur les fonctionnalités principales du jeu, comme la collecte manuelle, l’inventaire et les recettes.

## Perspectives

### Court terme - *Économie*

- **Système d'achats complet** : Implémenter une boutique permettant de dépenser les ressources craftées contre des bonus de production.

### Moyen terme - *Feedback*

- **Système de notifications** : Envoi d'alertes visuelles et sonores du type "Clicker prêt !", "Ressources suffisantes pour une recette" ou "Niveau supérieur atteint".


### Plus loin - *Personnalisation*

- **Système de Thèmes Saisonniers** : Possibilité de changer l'apparence complète du jeu selon la période de l'année (Thème Noël avec de la neige sur les icônes, Thème Halloween, etc.).


