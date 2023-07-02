# Library Management System

The Library Management System is a study Spring MVC project designed to streamline book management processes for local libraries. The overall idea is to provide librarians with basic tools for registering readers, issuing and returning books, and managing overall book inventory.

## Features

- User Management: Add, edit, and delete reader information, including full name and date of birth.
- Book Management: Add, edit, and delete book details, such as title, author, and publication year. Books are associated with individual readers.
- Reader Listing: A page displaying a list of registered readers, facilitating easy search and access to individual profiles.
- Book Listing: An overview of available books in the library, with detailed information including title, author, and availability status.
- Reader Details: Personalized pages displaying reader information and the list of borrowed books. If no books are borrowed, a message is displayed.
- Book Details: Detailed information about a specific book, including the borrower's name if currently on loan. If available, a message indicates availability.
- Book Return: Mark returned books as available using the "Release Book" button on the book's page.
- Book Borrowing: Assign available books to readers using the "Assign Book" button, selecting the borrower from a dropdown list.
- Data Validation: All user input is validated using Spring Validation and custom validations to ensure data integrity.

## Technology Stack

- Framework: Spring
- Database: PostgreSQL
- Front-end: Thymeleaf, HTML, CSS
- Back-end: Java, Spring MVC, Hibernate
- Dependencies: Spring Data JPA, Spring Validation, PostgreSQL Connector, Thymeleaf

## Getting Started

To run the Library Management System locally:

1. Install PostgreSQL and create a new database named "LibraryDB".
2. Clone the repository: `git clone https://github.com/valkrisd/SpringMVCLibrary.git`
3. Open the project in your preferred IDE.
4. Configure the database connection in the `database.properties` file.
5. Build and run the application.
6. Access the application in your browser at `http://localhost:8080`.

## Contact

For any inquiries or feedback, please contact me at **vladislav.niiazov@gmail.com**
