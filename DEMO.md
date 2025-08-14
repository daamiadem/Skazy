# 🎯 Guide de Démonstration - Skazy Puzzle Solver

## 🚀 Démarrage Rapide

### Option 1: Script automatique (Recommandé)

```bash
./start.sh
```

### Option 2: Démarrage manuel

```bash
# Terminal 1 - Backend
cd skazy-backend
mvn spring-boot:run

# Terminal 2 - Frontend
cd skazy-frontend
npm start
```

## 🌐 Accès à l'Application

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080/api/solutions
- **Console H2**: http://localhost:8080/h2-console

## 🎮 Fonctionnalités à Tester

### 1. Génération de Solutions

- Cliquez sur **"Générer une Solution"**
- Observez le temps de génération affiché
- La nouvelle solution apparaît en haut de la liste

### 2. Visualisation des Solutions

- Chaque solution affiche :
  - Une grille 6x4 avec les chiffres
  - Le statut (Correcte/Incorrecte)
  - Le temps de génération
  - La date de création

### 3. Recherche

- Utilisez la barre de recherche pour filtrer les solutions
- Tapez des chiffres ou caractères pour voir les résultats

### 4. Actions CRUD

- **Voir** : Cliquez sur l'icône 👁️
- **Modifier** : Cliquez sur l'icône ✏️ (à implémenter)
- **Supprimer** : Cliquez sur l'icône 🗑️

### 5. Gestion Globale

- **Supprimer Tout** : Supprime toutes les solutions avec confirmation

## 🧮 Le Puzzle Vietnamien

Le casse-tête consiste à compléter cette grille :

```
[ ] + [ ] - [ ] = 66
[ ] × [ ] = [ ] = 13
[ ] + [ ] + [ ] = 12
[ ] × [ ] + [ ] = 11
[ ] + [ ] - [ ] = 10
[ ] ÷ [ ] × [ ] = [ ]
```

**Règles** :

- Utiliser uniquement les chiffres 1 à 9
- Chaque chiffre ne peut être utilisé qu'une fois
- Respecter les opérations mathématiques

## 🔧 Fonctionnalités Techniques

### Backend Spring Boot

- ✅ **JPA/Hibernate** avec base H2
- ✅ **APIs REST** complètes
- ✅ **Architecture en couches**
- ✅ **Gestion des erreurs**
- ✅ **CORS configuré**

### Frontend React

- ✅ **TypeScript** pour la sécurité des types
- ✅ **Material-UI** pour l'interface moderne
- ✅ **Gestion d'état** avec hooks React
- ✅ **Appels API** avec Axios
- ✅ **Design responsive**

## 📊 Tests et Validation

### Test du Backend

```bash
cd skazy-backend
mvn test
```

### Test du Frontend

```bash
cd skazy-frontend
npm test
```

### Test des APIs

```bash
# Lister toutes les solutions
curl http://localhost:8080/api/solutions

# Générer une solution
curl -X POST http://localhost:8080/api/solutions/generate

# Rechercher des solutions
curl "http://localhost:8080/api/solutions/search?q=1"
```

## 🐛 Dépannage

### Problème de port

- **Port 8080 occupé** : Modifiez `server.port` dans `application.properties`
- **Port 3000 occupé** : React proposera automatiquement un autre port

### Problème de base de données

- Accédez à http://localhost:8080/h2-console
- Vérifiez la configuration dans `application.properties`

### Problème de CORS

- Le backend est configuré pour accepter les requêtes de localhost:3000
- Vérifiez l'annotation `@CrossOrigin` dans `SolutionController`

## 🎨 Personnalisation

### Modifier le thème

- Éditez `App.tsx` et modifiez `createTheme()`
- Changez les couleurs primaires et secondaires

### Ajouter des fonctionnalités

- **Validation des solutions** : Implémentez la logique dans `SolutionService`
- **Export des données** : Ajoutez des endpoints dans `SolutionController`
- **Historique des modifications** : Créez une nouvelle entité `SolutionHistory`

## 📱 Déploiement

### Backend en production

```bash
cd skazy-backend
mvn clean package
java -jar target/skazy-backend-0.0.1-SNAPSHOT.jar
```

### Frontend en production

```bash
cd skazy-frontend
npm run build
# Servir le dossier build avec nginx ou autre serveur web
```

## 🏆 Points Forts du Projet

1. **Architecture propre** : Séparation des responsabilités
2. **Code maintenable** : Bonnes pratiques Spring Boot et React
3. **Interface moderne** : Material-UI avec design responsive
4. **APIs RESTful** : Endpoints bien structurés
5. **Gestion d'état** : Hooks React pour une logique claire
6. **Tests** : Structure de tests prête
7. **Documentation** : README et guides complets

---
