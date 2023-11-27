# Mochi Wala - Java JDBC SQL App
![images](https://github.com/abhishektamrakar/MochiWalaApp/assets/88223580/043dfb21-bfec-45fd-bb85-ed04d4f8d903)

Mochi Wala is a Java JDBC SQL-based e-commerce application that allows users to explore and purchase various shoe and accessory services. The app focuses on providing a personalized shopping experience for users.

## Features

- User Authentication: Register, login, and logout securely.
- Menu Options: Explore a menu of shoe and accessory services.
- Cart Management: Add items to the cart, view the cart, and proceed to buy.
- Order History: View previous and current orders.
- Profile Management: View and update user profile information.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- MySQL Database
- JDBC Driver

### Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/mochi-wala.git
   ```

2. Create the database and tables in MySQL using the provided SQL script.

3. Update the `db.properties` file with your database connection details.

4. Compile and run the Java application.

   ```bash
   javac MochiWalaApp.java
   java MochiWalaApp
   ```

## Database Schema

The application uses the following database tables:

1. **users:**
   - user_id (int, auto_increment, primary key)
   - name (varchar)
   - contact (varchar)
   - email (varchar, unique)
   - address (varchar)
   - city (varchar)
   - state (varchar)
   - pin_code (varchar)
   - password (varchar)

2. **cart:**
   - cart_id (int, auto_increment, primary key)
   - user_email (varchar, foreign key referencing users.email)
   - item_name (varchar)
   - price (double)

3. **orders:**
   - order_id (int, auto_increment, primary key)
   - user_email (varchar, foreign key referencing users.email)
   - item_name (varchar)
   - price (double)

## Usage

Follow the on-screen prompts to navigate through the application. Use the menu options to explore services, manage the cart, view orders, update your profile, and more.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```

Replace the placeholders with your actual repository URL, database details, and any additional information you'd like to include.
