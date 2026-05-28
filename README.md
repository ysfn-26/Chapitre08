## 📝 Justification technique : Analyse de l'historique des commits

En consultant l'historique des commits de ce dépôt, vous constaterez que les premières versions portent la signature graphique d'un autre utilisateur GitHub. Je tiens à apporter la justification technique de cette situation, qui illustre le mécanisme de fonctionnement de Git en environnement partagé.

### Analyse du phénomène

Ce décalage provient de la distinction stricte que fait Git entre **l'auteur d'un commit** et **l'autorisation de déploiement (Push)** :

1. **La signature de l'auteur (Locale) :** Les premiers commits ont été validés sur un poste de travail où la configuration globale de Git (`user.name` et `user.email`) contenait encore les identifiants d'un autre étudiant. Lors de l'exécution de la commande `git commit`, Git grave de manière définitive ces métadonnées textuelles dans le snapshot local, agissant comme une simple signature sur papier.
2. **L'authentification de transport (Distante) :** Lors de l'envoi des fichiers via `git push`, l'authentification s'est faite avec mes propres clés d'accès et sur mon dépôt personnel. GitHub a accepté le transfert car j'en suis la propriétaire légitime. Cependant, lors de la génération de l'interface web, l'algorithme de GitHub inspecte l'adresse email gravée *à l'intérieur* du commit. S'il trouve un compte correspondant à cette adresse sur sa plateforme, il lie automatiquement le commit à ce profil tiers.

### Conclusion et alignement

Il ne s'agit donc pas d'une importation de code externe, mais d'un défaut d'initialisation de l'environnement de développement local (l'identité du terminal de l'IDE n'avait pas été réinitialisée). 
