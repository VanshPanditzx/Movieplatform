🎬 Movie Recommendation Platform
A full-stack Movie Recommendation & Analytics Platform built using Spring Boot, MongoDB, OMDb API, HTML, CSS, and JavaScript.

This application allows users to search movies in real-time, create accounts, manage watchlists, submit ratings and reviews, and receive personalized movie recommendations based on their preferences.

🚀 Features
✅ User Registration & Login
✅ Secure Authentication
✅ Movie Search (OMDb API Integration)
✅ View Movie Details (Poster, Genre, Director, IMDb Rating, Plot)
✅ Add/Remove Movies from Watchlist
✅ Submit Ratings & Reviews
✅ Genre-Based Recommendations
✅ User Dashboard
✅ Responsive UI

🛠 Tech Stack
Backend
Java 17
Spring Boot 3.x
Spring Data MongoDB
RESTful APIs
Maven
Database
MongoDB (Local / MongoDB Compass)
Frontend
HTML5
CSS3
JavaScript (Fetch API)
External API
OMDb API (Real-time Movie Data)
🏗 Architecture
The project follows a layered architecture:

Presentation Layer → HTML, CSS, JavaScript
Controller Layer → Spring Boot REST Controllers
Service Layer → Business Logic
Repository Layer → MongoDB Data Access
External API Layer → OMDb API Integration
📂 Project Structure
text

src/main/java/com/movie
│
├── controller
├── service
├── repository
├── models
└── MovieApplication.java

src/main/resources
├── static
│   ├── index.html
│   ├── login.html
│   ├── register.html
│   └── dashboard.html
└── application.properties
⚙️ Installation & Setup
Clone the repository:
text

git clone https://github.com/yourusername/movie-recommendation-platform.git
Configure application.properties:
text

spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=movieDB
server.port=8080
omdb.api.key=YOUR_OMDB_API_KEY
omdb.api.url=http://www.omdbapi.com/
Start MongoDB.

Run the Spring Boot application:

text

mvn spring-boot:run
Open in browser:
text

http://localhost:8080
🎯 Project Highlights
Real-time movie data integration
Clean REST API structure
MongoDB document-based storage
Secure login system
Personalized movie recommendations
Full-stack development implementation
📌 Future Enhancements
AI-based recommendation engine
Trailer integration
Dark mode UI
Social features
Cloud deployment
Mobile app version
📖 References
Spring Boot Documentation
MongoDB Documentation
OMDb API Documentation
MDN Web Docs
