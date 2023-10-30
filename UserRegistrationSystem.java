import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistrationSystem {
    private static final String url = "jdbc:mysql://localhost:3306/userdb";
    private static final String user = "root";
    private static final String password = "password";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the User Registration System!");
        System.out.println("Please choose an option: \n1. Sign Up \n2. Log In \n3. Manage Profile \n4. Exit");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                signUp(scanner);
                break;
            case 2:
                logIn(scanner);
                break;
            case 3:
                manageProfile(scanner);
                break;
            case 4:
                System.out.println("Exiting the User Registration System...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void signUp(Scanner scanner) {
        System.out.println("Sign Up");
        System.out.println("Enter your username:");
        String username = scanner.next();
        System.out.println("Enter your password:");
        String password = scanner.next();
        System.out.println("Enter your email:");
        String email = scanner.next();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            System.out.println("Error occurred while registering the user.");
            e.printStackTrace();
        }
    }

    private static void logIn(Scanner scanner) {
        System.out.println("Log In");
        System.out.println("Enter your username:");
        String username = scanner.next();
        System.out.println("Enter your password:");
        String password = scanner.next();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("User logged in successfully!");
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while logging in the user.");
            e.printStackTrace();
        }
    }

    private static void manageProfile(Scanner scanner) {
        System.out.println("Manage Profile");
        System.out.println("Enter your username:");
        String username = scanner.next();
        System.out.println("Enter your new password:");
        String newPassword = scanner.next();
        System.out.println("Enter your new email:");
        String newEmail = scanner.next();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE users SET password = ?, email = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, newEmail);
            preparedStatement.setString(3, username);
            preparedStatement.executeUpdate();
            System.out.println("User profile updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error occurred while updating the user profile.");
            e.printStackTrace();
        }
    }
}