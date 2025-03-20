import java.util.Scanner;

/**
 * Handles the main application logic including user interaction,
 * input validation, and encryption/decryption flow.
 */
public class CaesarCipherApp {
    private final Scanner scanner;
    private final CipherService cipherService;

    /**
     * Constructor initializes the scanner for user input and cipher service for encryption/decryption.
     */
    public CaesarCipherApp() {
        scanner = new Scanner(System.in);
        cipherService = new CipherService();
    }

    /**
     * Main application loop that handles user interactions.
     */
    public void run() {
        boolean running = true;

        System.out.println("Welcome to the Caesar Cipher Encryption/Decryption Tool");
        System.out.println("----------------------------------------------------");

        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    handleEncryption();
                    break;
                case 2:
                    handleDecryption();
                    break;
                case 3:
                    running = false;
                    System.out.println("Thank you for using the Caesar Cipher Tool. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    /**
     * Displays the main menu options to the user.
     */
    private void displayMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. Encrypt a message");
        System.out.println("2. Decrypt a message");
        System.out.println("3. Exit");
        System.out.print("Enter your choice (1-3): ");
    }

    /**
     * Gets and validates user menu choice.
     * @return The validated user choice as an integer.
     */
    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a number.");
            scanner.next(); // Consume invalid input
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return choice;
    }

    /**
     * Handles the encryption process flow.
     */
    private void handleEncryption() {
        System.out.println("\n--- Encryption Mode ---");
        String plaintext = getTextInput("Enter the plaintext message: ");

        if (plaintext.isEmpty()) {
            System.out.println("Error: Empty input is not allowed.");
            return;
        }

        int shift = getShiftValue();

        String ciphertext = cipherService.encrypt(plaintext, shift);

        System.out.println("\nEncryption Results:");
        System.out.println("Original text: " + plaintext);
        System.out.println("Shift value: " + shift);
        System.out.println("Encrypted text: " + ciphertext);
    }

    /**
     * Handles the decryption process flow.
     */
    private void handleDecryption() {
        System.out.println("\n--- Decryption Mode ---");
        String ciphertext = getTextInput("Enter the ciphertext message: ");

        if (ciphertext.isEmpty()) {
            System.out.println("Error: Empty input is not allowed.");
            return;
        }

        int shift = getShiftValue();

        String plaintext = cipherService.decrypt(ciphertext, shift);

        System.out.println("\nDecryption Results:");
        System.out.println("Encrypted text: " + ciphertext);
        System.out.println("Shift value: " + shift);
        System.out.println("Decrypted text: " + plaintext);
    }

    /**
     * Gets text input from the user.
     * @param prompt The message to display to the user.
     * @return The user's input as a String.
     */
    private String getTextInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Gets and validates the shift value from the user.
     * @return The validated shift value.
     */
    private int getShiftValue() {
        while (true) {
            System.out.print("Enter the shift value (1-25): ");

            if (scanner.hasNextInt()) {
                int shift = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (shift >= 1 && shift <= 25) {
                    return shift;
                } else {
                    System.out.println("Shift value must be between 1 and 25. Please try again.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }
}