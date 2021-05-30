# Bref, Mange
Application de service de livraison de nourriture. (Projet pour Faculté des sciences de Montpellier)

* [Pré-requis](#pré-requis)
* [Installation](#installation)
* [Remplir la base de données (optionnel)](#remplir-la-base-de-données-optionnel)
* [Lancer](#lancer)


## Pré-requis
La version minimal du SDK Android est **23**.

L'API (AP-EAT) AP-EAT doit être lancée afin de pouvoir utiliser l'application. Deux méthodes sont disponibles :

### Méthode 1 - Utiliser l'API publique (recommandé et par défaut)
Nous avons développé une API consommable publiquement et sécurisée. Vous pouvez donc simplement utiliser l'URL
`https://apeat.dorpax.io/v1/` dans Retrofit.

*Cette API ne sera plus disponible après le passage du jury de la FDS.*

### Méthode 2 - Lancer l'API en local
Si vous souhaitez lancer une API en local, vous pouvez suivre les étapes décrites dans le répertoire dédié au développement de l'API.
**AP-EAT :** [https://github.com/Dorpaxio/AP-EAT](https://github.com/Dorpaxio/AP-EAT)

## Installation
```bash
git clone git@github.com:Dorpaxio/AP-EAT.git
```
Utilisez ensuite Android Studio ou Intellij afin d'exécuter gradle et lancer l'application sur téléphone ou émulateur.
