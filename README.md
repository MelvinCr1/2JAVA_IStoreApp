# IStoreApp - Readme

## 1. Context of the project

iStore ltd is a company which aim to produce the best experience for all companies storing goods.
The task is to produce a complete application using Java to allow users to access stored goods information or other relevant data. This should also allow users to leverage inventory by adding/removing goods.


## 2. Project description

### 2.1 - General features to implement
The project must include the following functionalities:

Basic authentication need to be setup
    - When running the application, the first window should ask the user
      - to create an account (will require a email/password)
      - a way to login (using only login/password)
    - To successfully create an account the email should have been "whitelisted" first
    - Password should not be stored directly on the database (think about security)
    - Login should be an email
    - If an error is thrown during the login or create user action you need to give the user what is the issue
      
User management solution
    - Create, Read, Update, Delete user
    - User is at least {id, email, pseudo, password, role}
    - Normal users can read information about another user (not the password)
    - You can create a new user even without being logged (just need your email to be whitelisted)
    - You can only update yourself (other users cannot update you EXCEPT if admin)
    - You can only delete yourself (other users cannot delete you EXCEPT if admin)
    
Admin management
    - The first user is by default an admin (or you can create a default admin/admin user)
    - Admin can "whitelist" emails to allow people to register
    - Admin can update or delete an employee account
    - Admin can create a new "store"
    - Admin can create and delete a new item in the inventory
    
Inventory management
    - An inventory is linked linked to a store (one inventory per store)
    - An inventory contains items
      - items have at least the following properties: id, name, price
      - each item should have a limited number in storage (cannot be lower than 0)
    - Inventory need to be browsable - you need to be able to display all the items
    - An employee can increase or decrease the number of one item in stock (selling or receiving item)
    
Store management
    - Store can only be created / deleted by Admin
    - Should only have two properties: id and name
    - A store only have one inventory
    - Admin can add an employee to the store
      - Employee only have access to store they have been added
    - View (for administrator and employee with access) a list of everyone with access to the store


## 3. Installation and Configuration Guide

### Prerequisites
Before starting the installation, make sure your system has the following:

- Java Development Kit (JDK) version 11 or higher.
- MySQL Server or another relational database management system.
- Apache Maven for dependency management and project building.
- Git for cloning the project repository.

### Installation
1- Clone this Git repository to your local machine
2- Set up the database

Make sure your MySQL server is running before starting the application.


## 4. Rendering

#### The rendering of the project containing:
- The source code
- Additional files (sounds, images, etc.)
- Technical documentation
- The game manual


#### The technical documentation contains:

- Justification of the choice of language and graphic library
- Rationale for splitting code into multiple packages
- Code documentation


## Built With

* [Intellij IDEA 2023.3.3] - The IDE used


## 5. Authors

* **Melvin Cureau** - *Initial work* - [MelvinCureau](https://github.com/MelvinCr1)

See also the list of [contributors](https://github.com/MelvinCr1/2JAVA_IStoreApp/contributors) who participated in this project.


## 6. Licence

This project is under the MIT license. See the LICENSE file for more details.
