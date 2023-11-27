package org.jsp.MochiWalaApp;

import java.sql.*;
import java.util.*;

public class MochiWala {
    static Connection connection;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    static Scanner scanner = new Scanner(System.in);
    static String userEmail = "";

    public static void main(String[] args) {
        connectToDatabase();

        while (true) {
            System.out.println("Mochi Wala - Shoe That Defines Personality");
            System.out.println("Quality Service to Your Shoes and Fast Delivery to Your Doorstep");
            System.out.println();
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.println("4. Need Help???");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    loginUser();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    exitApp();
                    break;
                case 4:
                	System.out.println();
                	System.out.println("any help to call us 09109132351");
                	System.out.println();
                	break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

     static void connectToDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mochi_wala", "root", "admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loginUser() {
        System.out.print("Enter email/contact: ");
        String emailContact = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            String query = "SELECT * FROM users WHERE (email = ? OR contact = ?) AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, emailContact);
            preparedStatement.setString(2, emailContact);
            preparedStatement.setString(3, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userEmail = resultSet.getString("email");
                showMainMenu();
            } else {
                System.out.println("Invalid email/contact or password. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void registerUser() {
    	System.out.println();
        System.out.println("Register as a new user:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Contact: ");
        String contact = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("State: ");
        String state = scanner.nextLine();
        System.out.print("Pin Code: ");
        String pinCode = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            String query = "INSERT INTO users (name, contact, email, address, city, state, pin_code, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, contact);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, city);
            preparedStatement.setString(6, state);
            preparedStatement.setString(7, pinCode);
            preparedStatement.setString(8, password);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registration successful. Please login to continue.");
            } else {
                System.out.println("Registration failed. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showMainMenu() {
        while (true) {
        	System.out.println();
            System.out.println("1. Menu");
            System.out.println("2. Orders");
            System.out.println("3. Cart");
            System.out.println("4. Profile");
            System.out.println("5. Logout");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    showMenu();
                    break;
                case 2:
                    showOrders();
                    break;
                case 3:
                    showCart();
                    break;
                case 4:
                    showProfile();
                    break;
                case 5:
                    logout();
                    return;
                case 6:
                    exitApp();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
    
    private static void logout() {
        userEmail = "";
        System.out.println("Logout successful.");
    }
    
    private static void showCart() {
        try {
            String query = "SELECT * FROM cart WHERE user_email = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userEmail);
            resultSet = preparedStatement.executeQuery();
            System.out.println();
            System.out.println("Items in Cart:");
            while (resultSet.next()) {
                System.out.println("Item: " + resultSet.getString("item_name") + ", Price: ₹" + resultSet.getDouble("price"));
            }
            System.out.println();
            System.out.println("1. Proceed to Buy");
            System.out.println("2. Back to Menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    proceedToBuy();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void exitApp() {
        System.out.println("Thank you for using Mochi Wala. Exiting the application.");
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    
    private static void showMenu() {
        while (true) {
        	System.out.println();
        	System.out.println("Quick Service");
            System.out.println("1. Shoes & Sandals");
            System.out.println("2. Hand Bag");
            System.out.println("3. Jacket");
            System.out.println("4. Travel Bag");
            System.out.println("5. Belt & Purse");
            System.out.println("6. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int categoryChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (categoryChoice) {
                case 1:
                    showShoeSubMenu();
                    break;
                case 2:
                    showHandBagSubMenu();
                    break;
                case 3:
                    showJacketSubMenu();
                    break;
                case 4:
                    showTravelBagSubMenu();
                    break;
                case 5:
                    showBeltPurseSubMenu();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void showShoeSubMenu() {
        while (true) {
        	System.out.println();
            System.out.println("Shoes & Sandals");
            System.out.println("1. Cleaning ₹15.00");
            System.out.println("2. Polishing ₹20.00");
            System.out.println("3. Stitching ₹40.00");
            System.out.println("4. Washing ₹30.00");
            System.out.println("5. Back to Menu");

            System.out.print("Enter your choice: ");
            int shoeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (shoeChoice) {
                case 1:
                    addToCartOrFavorites("Cleaning (Shoes & Sandals)", 15.00);
                    break;
                case 2:
                    addToCartOrFavorites("Polishing (Shoes & Sandals)", 20.00);
                    break;
                case 3:
                    addToCartOrFavorites("Stitching (Shoes & Sandals)", 40.00);
                    break;
                case 4:
                    addToCartOrFavorites("Washing (Shoes & Sandals)", 30.00);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void proceedToBuy() {
        try {
            String getCartItemsQuery = "SELECT * FROM cart WHERE user_email = ?";
            preparedStatement = connection.prepareStatement(getCartItemsQuery);
            preparedStatement.setString(1, userEmail);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String itemName = resultSet.getString("item_name");
                double price = resultSet.getDouble("price");

                String insertOrderQuery = "INSERT INTO orders (user_email, item_name, price) VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(insertOrderQuery);
                preparedStatement.setString(1, userEmail);
                preparedStatement.setString(2, itemName);
                preparedStatement.setDouble(3, price);
                preparedStatement.executeUpdate();
            }

            String clearCartQuery = "DELETE FROM cart WHERE user_email = ?";
            preparedStatement = connection.prepareStatement(clearCartQuery);
            preparedStatement.setString(1, userEmail);
            preparedStatement.executeUpdate();

            System.out.println("Items from cart have been added to your orders.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void showHandBagSubMenu() {
        while (true) {
        	System.out.println();
            System.out.println("Hand Bag");
            System.out.println("1. Cleaning ₹50.00");
            System.out.println("2. Polishing ₹100.00");
            System.out.println("3. Stitching ₹2.00");
            System.out.println("4. Runner ₹15.00");
            System.out.println("5. Back to Menu");

            System.out.print("Enter your choice: ");
            int handBagChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (handBagChoice) {
                case 1:
                    addToCartOrFavorites("Cleaning (Hand Bag)", 50.00);
                    break;
                case 2:
                    addToCartOrFavorites("Polishing (Hand Bag)", 100.00);
                    break;
                case 3:
                    addToCartOrFavorites("Stitching (Hand Bag)", 2.00);
                    break;
                case 4:
                    addToCartOrFavorites("Runner (Hand Bag)", 15.00);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }


    private static void showJacketSubMenu() {
    	while (true) {
    		System.out.println();
            System.out.println("Shoes & Sandals");
            System.out.println("1. Cleaning ₹15.00");
            System.out.println("2. Polishing ₹20.00");
            System.out.println("3. Stitching ₹40.00");
            System.out.println("4. Washing ₹30.00");
            System.out.println("5. Back to Menu");

            System.out.print("Enter your choice: ");
            int shoeChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (shoeChoice) {
                case 1:
                    addToCartOrFavorites("Cleaning (Shoes & Sandals)", 15.00);
                    break;
                case 2:
                    addToCartOrFavorites("Polishing (Shoes & Sandals)", 20.00);
                    break;
                case 3:
                    addToCartOrFavorites("Stitching (Shoes & Sandals)", 40.00);
                    break;
                case 4:
                    addToCartOrFavorites("Washing (Shoes & Sandals)", 30.00);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void showTravelBagSubMenu() {
    	while (true) {
    		System.out.println();
            System.out.println("Shoes & Sandals");
            System.out.println("1. Cleaning ₹15.00");
            System.out.println("2. Polishing ₹20.00");
            System.out.println("3. Stitching ₹40.00");
            System.out.println("4. Washing ₹30.00");
            System.out.println("5. Back to Menu");

            System.out.print("Enter your choice: ");
            int shoeChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (shoeChoice) {
                case 1:
                    addToCartOrFavorites("Cleaning (Shoes & Sandals)", 15.00);
                    break;
                case 2:
                    addToCartOrFavorites("Polishing (Shoes & Sandals)", 20.00);
                    break;
                case 3:
                    addToCartOrFavorites("Stitching (Shoes & Sandals)", 40.00);
                    break;
                case 4:
                    addToCartOrFavorites("Washing (Shoes & Sandals)", 30.00);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void showBeltPurseSubMenu() {
    	while (true) {
    		System.out.println();
            System.out.println("Shoes & Sandals");
            System.out.println("1. Cleaning ₹15.00");
            System.out.println("2. Polishing ₹20.00");
            System.out.println("3. Stitching ₹40.00");
            System.out.println("4. Washing ₹30.00");
            System.out.println("5. Back to Menu");

            System.out.print("Enter your choice: ");
            int shoeChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (shoeChoice) {
                case 1:
                    addToCartOrFavorites("Cleaning (Shoes & Sandals)", 15.00);
                    break;
                case 2:
                    addToCartOrFavorites("Polishing (Shoes & Sandals)", 20.00);
                    break;
                case 3:
                    addToCartOrFavorites("Stitching (Shoes & Sandals)", 40.00);
                    break;
                case 4:
                    addToCartOrFavorites("Washing (Shoes & Sandals)", 30.00);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addToCartOrFavorites(String itemName, double price) {
    	System.out.println();
    	System.out.println();
        System.out.println("1. Add to cart");
        System.out.println("2. Add to favorites");
        System.out.println("3. Back to Menu");

        System.out.print("Enter your choice: ");
        int optionChoice = scanner.nextInt();
        scanner.nextLine(); 

        switch (optionChoice) {
            case 1:
                addToCart(itemName, price);
                break;
            case 2:
                addToFavorites(itemName, price);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private static void addToCart(String itemName, double price) {
        try {
            String query = "INSERT INTO cart (user_email, item_name, price) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, itemName);
            preparedStatement.setDouble(3, price);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(itemName + " added to the cart.");
            } else {
                System.out.println("Failed to add " + itemName + " to the cart.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addToFavorites(String itemName, double price) {
        try {
            String query = "INSERT INTO favorites (user_email, item_name, price) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, itemName);
            preparedStatement.setDouble(3, price);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(itemName + " added to favorites.");
            } else {
                System.out.println("Failed to add " + itemName + " to favorites.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


private static void showProfile() {
    try {
        String query = "SELECT * FROM users WHERE email = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userEmail);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
        	System.out.println();
            System.out.println("Name: " + resultSet.getString("name"));
            System.out.println("Contact: " + resultSet.getString("contact"));
            System.out.println("E-mail: " + resultSet.getString("email"));
            System.out.println("Address: " + resultSet.getString("address"));
            System.out.println("City: " + resultSet.getString("city"));
            System.out.println("State: " + resultSet.getString("state"));
            System.out.println("Pin Code: " + resultSet.getString("pin_code"));

            System.out.println("1. Update Profile");
            System.out.println("2. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int optionChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (optionChoice) {
                case 1:
                    updateProfile();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private static void updateProfile() {
	System.out.println();
    System.out.println("Update Profile Information:");

    System.out.print("Enter new Name: ");
    String newName = scanner.nextLine();
    System.out.print("Enter new Contact: ");
    String newContact = scanner.nextLine();
    System.out.print("Enter new Address: ");
    String newAddress = scanner.nextLine();
    System.out.print("Enter new City: ");
    String newCity = scanner.nextLine();
    System.out.print("Enter new State: ");
    String newState = scanner.nextLine();
    System.out.print("Enter new Pin Code: ");
    String newPinCode = scanner.nextLine();

    try {
        String query = "UPDATE users SET name = ?, contact = ?, address = ?, city = ?, state = ?, pin_code = ? WHERE email = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newName);
        preparedStatement.setString(2, newContact);
        preparedStatement.setString(3, newAddress);
        preparedStatement.setString(4, newCity);
        preparedStatement.setString(5, newState);
        preparedStatement.setString(6, newPinCode);
        preparedStatement.setString(7, userEmail);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("Failed to update profile. Please try again.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


private static void showOrders() {
 try {
     String getOrdersQuery = "SELECT * FROM orders WHERE user_email = ?";
     preparedStatement = connection.prepareStatement(getOrdersQuery);
     preparedStatement.setString(1, userEmail);
     resultSet = preparedStatement.executeQuery();
     System.out.println();
     System.out.println("Your Orders:");

     while (resultSet.next()) {
         String itemName = resultSet.getString("item_name");
         double price = resultSet.getDouble("price");

         System.out.println(itemName + " - ₹" + price);
     }

     if (!resultSet.isBeforeFirst()) {
         System.out.println("No orders found.");
     }
 } catch (SQLException e) {
     e.printStackTrace();
 }
}
}
