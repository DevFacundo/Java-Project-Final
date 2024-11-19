Property Management Application

Overview

The Property Management Application is a software solution designed to meet the needs of real estate agents and property managers. It provides essential tools for managing various property types, handling client information, and facilitating key transactions.

The system supports managing different property types such as houses, apartments, land, and storage units, while also maintaining a record of clients interested in renting or purchasing. It includes features for adding, updating, and associating properties with their respective owners. The goal is to streamline the daily operations of real estate businesses by offering a reliable and efficient way to manage large volumes of data.

Additionally, the application boasts an intuitive interface that minimizes the need for complex training, making it easy for users to adopt quickly.
Features

    Manage various types of properties (houses, apartments, land, storage units, etc.).
    Register new properties and edit existing ones.
    Associate properties with owners.
    Manage different types of clients (owners, renters, buyers).
    Perform quick searches, add, update, and delete records.
    Data persistence using JSON files to retain information across sessions.

Technologies Used

    Java for the core programming logic.
    IntelliJ IDEA as the IDE.
    JSON for data persistence.

System Design

The system is built around three key classes:

    Property: A base class for all property types, including houses, apartments, commercial spaces, storage units, etc.
    Client: A base class representing clients, including property owners, renters, and buyers.
    Generic Class: This acts as the main controller, managing the relationship between properties and clients, and facilitating actions like searching, adding, updating, and deleting. The generic class is designed to handle any subclass of Property or Client, making the system flexible and scalable.

Each class has properly implemented equals and hashCode methods to ensure data integrity when performing operations like comparisons and updates.
Data Persistence:

The system uses JSON files to store data. This lightweight format is easy to read and edit, ensuring that data is preserved between program executions. JSON also allows for potential integration with web or mobile applications in the future.
Installation
Prerequisites:

    Java 8 or higher installed on your machine.
    IntelliJ IDEA or any compatible Java IDE.

Steps to Install:

    Clone the Repository: Use the following command to clone the project:
git clone <https://github.com/DevFacundo/java-property-management>

    Open the Project in IntelliJ IDEA:
        Launch IntelliJ IDEA.
        Click on "Open" and navigate to the folder where you cloned the repository.
        Select the project folder and click "OK" to open the project.

    Run the Application: After opening the project, you can run the application directly from the IDE. The entry point is the Main class, which contains the main() method.

Contributors

This project was developed by the following students of the Technical Degree in Programming from the city of Mar del Plata:

    Facundo Aguilera
    Juan Pablo Sommacal
    Franco Soria
    Yago Sosa

The project was completed as part of the final evaluation for the course.
License
