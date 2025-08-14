# 🧩 Skazy Puzzle Solver

Application web complète pour résoudre le casse-tête mathématique vietnamien de 2015, développée avec **Spring Boot** et **React**.

## 📋 Description

Le projet Skazy est une application web qui permet de :

- **Générer des solutions** au casse-tête vietnamien avec affichage du temps de calcul
- **Visualiser toutes les solutions** stockées en base de données
- **Rechercher des solutions** avec un filtre de recherche
- **Effectuer des opérations CRUD** : créer, lire, mettre à jour, supprimer des solutions
- **Supprimer toutes les solutions** de la base de données

## 🏗️ Architecture

### Backend (Spring Boot)

- **Java 17** avec **Spring Boot 3.2.0**
- **JPA/Hibernate** pour l'ORM
- **Base de données H2** en mémoire
- **APIs REST** pour la communication avec le frontend
- **Architecture en couches** : Controller → Service → Repository → Entity

### Frontend (React)

- **React 18** avec **TypeScript**
- **Material-UI** pour l'interface utilisateur moderne
- **Axios** pour les appels API
- **Responsive design** pour tous les appareils

## 🚀 Installation et Démarrage

### Prérequis

- Java 17 ou supérieur
- Node.js 22 ou supérieur
- Maven 3.6+

### Backend

```bash
cd skazy-backend
mvn spring-boot:run
```

Le backend sera accessible sur `http://localhost:8080`

- API REST : `http://localhost:8080/api/solutions`
- Console H2 : `http://localhost:8080/h2-console`

### Frontend

```bash
cd skazy-frontend
npm install
npm start
```

Le frontend sera accessible sur `http://localhost:3000`

## 📚 APIs Disponibles

### Solutions

- `GET /api/solutions` - Récupérer toutes les solutions
- `GET /api/solutions/{id}` - Récupérer une solution par ID
- `GET /api/solutions/search?q={query}` - Rechercher des solutions
- `POST /api/solutions` - Créer une nouvelle solution
- `PUT /api/solutions/{id}` - Mettre à jour une solution
- `DELETE /api/solutions/{id}` - Supprimer une solution
- `DELETE /api/solutions` - Supprimer toutes les solutions
- `POST /api/solutions/generate` - Générer une solution aléatoire

## 🧮 Le Casse-tête Vietnamien

Le puzzle consiste à compléter un tableau 6x4 en utilisant les chiffres de 1 à 9 sans répétition, en respectant les contraintes mathématiques :

- **Opérateurs** : +, -, ×, ÷, =
- **Résultats** : 13, 12, 11, 10, 66
- **Contrainte** : Chaque chiffre de 1 à 9 ne peut être utilisé qu'une seule fois

## 🎨 Fonctionnalités de l'Interface

- **Interface moderne** avec Material-UI
- **Génération de solutions** avec indicateur de temps
- **Visualisation des grilles** en format tableau
- **Recherche en temps réel** des solutions
- **Actions CRUD** intuitives
- **Design responsive** pour mobile et desktop
- **Indicateurs visuels** pour les solutions correctes/incorrectes

## 🧪 Tests

### Backend

```bash
cd skazy-backend
mvn test
```

### Frontend

```bash
cd skazy-frontend
npm test
```

## 📁 Structure du Projet

```
Skazy/
├── skazy-backend/                 # Backend Spring Boot
│   ├── src/
│   │   ├── main/java/com/skazy/backend/
│   │   │   ├── controller/        # Contrôleurs REST
│   │   │   ├── service/           # Logique métier
│   │   │   ├── repository/        # Accès aux données
│   │   │   └── entity/            # Entités JPA
│   │   └── resources/             # Configuration
│   └── pom.xml                    # Dépendances Maven
├── skazy-frontend/                # Frontend React
│   ├── src/
│   │   ├── components/            # Composants React
│   │   ├── services/              # Services API
│   │   ├── types/                 # Types TypeScript
│   │   └── App.tsx                # Composant principal
│   └── package.json               # Dépendances npm
└── README.md                      # Documentation
```

## 🔧 Configuration

### Base de Données H2

- **URL** : `jdbc:h2:mem:skazydb`
- **Utilisateur** : `sa`
- **Mot de passe** : `password`
- **Console** : `/h2-console`

### CORS

Le backend est configuré pour accepter les requêtes du frontend sur `http://localhost:3000`

## 🚀 Déploiement

### Backend

```bash
cd skazy-backend
mvn clean package
java -jar target/skazy-backend-0.0.1-SNAPSHOT.jar
```

### Frontend

```bash
cd skazy-frontend
npm run build
# Servir le dossier build avec un serveur web
```
