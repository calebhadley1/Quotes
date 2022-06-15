# Quotes Web Application
Application built to create, read, update, and delete (CRUD) quotes through a web interface. Built using a React Frontend, Spring Boot Backend, and MongoDB database. This project's stack was selected with the goal of learning Spring Boot and MongoDB and improving my knowledge of React design patterns.

### Features:
- Register account with encrypted password using BCrypt password encoder
- Confirm account with access token provided on registration
- Login to existing account
- CRUD quotes

### TODO:
Email service so users can recieve their access token on registration to their email rather than just in JSON response/browser.
- [ ] Forgot/change password features.
- [ ] Daily email reminders of quotes that the user has inputted.
- [ ] Deploy an instance of the project on a Virtual Machine to run the service constantly.
- [ ] Document setup instructions

### Screenshots:
![Screenshots](https://user-images.githubusercontent.com/8209640/173702016-6893456a-0a0a-46f9-b0ba-b078569c222e.jpg)

### Installation and Setup:
Backend
- Clone github repo
- Open project in Intellij IDEA or equivalent IDE
- Launch database instance on port 8081 by running Quotes/Backend/docker-compose.yaml
![DockerScreenshot](https://user-images.githubusercontent.com/8209640/173709238-dd4375cc-d6ec-48ab-abc4-bdb073d3eb9a.png)
- Launch backend instance on port 8080 by running the QuotesApplication configuration that Intellij builds

Frontend
- Clone github repo
- Open project in VSCode or equivalent IDE
- Launch frontend instance on port 3000 by running:
```bash
cd Quotes/Frontend/quotes/src
npm install
npm start
```
