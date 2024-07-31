import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordManager {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    private static final SecureRandom RANDOM = new SecureRandom();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Password Manager!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Generate a Password");
            System.out.println("2. Check Password Strength");
            System.out.println("3. Display Password Security Information");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    generatePassword(scanner);
                    break;
                case 2:
                    checkPasswordStrength(scanner);
                    break;
                case 3:
                    displaySecurityInformation();
                    break;
                case 4:
                    System.out.println("Exiting the Password Manager. Stay safe!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void generatePassword(Scanner scanner) {
        scanner.nextLine(); // Consume newline

        System.out.print("Include uppercase letters? (Yes/No): ");
        boolean includeUppercase = scanner.nextLine().equalsIgnoreCase("Yes");

        System.out.print("Include lowercase letters? (Yes/No): ");
        boolean includeLowercase = scanner.nextLine().equalsIgnoreCase("Yes");

        System.out.print("Include numbers? (Yes/No): ");
        boolean includeNumbers = scanner.nextLine().equalsIgnoreCase("Yes");

        System.out.print("Include symbols? (Yes/No): ");
        boolean includeSymbols = scanner.nextLine().equalsIgnoreCase("Yes");

        System.out.print("Enter desired password length: ");
        int length = scanner.nextInt();

        String password = generatePassword(includeUppercase, includeLowercase, includeNumbers, includeSymbols, length);
        System.out.println("Generated Password: " + password);
    }

    private static String generatePassword(boolean includeUppercase, boolean includeLowercase, boolean includeNumbers,
            boolean includeSymbols, int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Password length must be greater than 0.");
        }

        StringBuilder passwordAlphabet = new StringBuilder();
        if (includeUppercase)
            passwordAlphabet.append(UPPERCASE);
        if (includeLowercase)
            passwordAlphabet.append(LOWERCASE);
        if (includeNumbers)
            passwordAlphabet.append(DIGITS);
        if (includeSymbols)
            passwordAlphabet.append(SPECIAL_CHARACTERS);

        if (passwordAlphabet.length() == 0) {
            throw new IllegalArgumentException("No character sets selected.");
        }

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(passwordAlphabet.charAt(RANDOM.nextInt(passwordAlphabet.length())));
        }

        return password.toString();
    }

    private static void checkPasswordStrength(Scanner scanner) {
        scanner.nextLine(); // Consume newline

        System.out.print("Enter the password to check strength: ");
        String password = scanner.nextLine();

        int score = calculatePasswordStrength(password);

        System.out.println("Password Strength:");
        if (score <= 2) {
            System.out.println("Weak");
        } else if (score <= 4) {
            System.out.println("Medium");
        } else if (score == 5) {
            System.out.println("Good");
        } else {
            System.out.println("Great");
        }
    }

    private static int calculatePasswordStrength(String password) {
        int score = 0;

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigits = false;
        boolean hasSymbols = false;

        for (char c : password.toCharArray()) {
            if (UPPERCASE.indexOf(c) >= 0)
                hasUppercase = true;
            if (LOWERCASE.indexOf(c) >= 0)
                hasLowercase = true;
            if (DIGITS.indexOf(c) >= 0)
                hasDigits = true;
            if (SPECIAL_CHARACTERS.indexOf(c) >= 0)
                hasSymbols = true;
        }

        if (hasUppercase)
            score++;
        if (hasLowercase)
            score++;
        if (hasDigits)
            score++;
        if (hasSymbols)
            score++;

        if (password.length() >= 8)
            score++;
        if (password.length() >= 16)
            score++;

        return score;
    }

    private static void displaySecurityInformation() {
        System.out.println("\nPassword Security Information:");
        System.out.println("1. Avoid using the same password for multiple accounts.");
        System.out.println("2. Avoid character repetition, keyboard patterns, and dictionary words.");
        System.out.println("3. Include a mix of uppercase letters, lowercase letters, numbers, and symbols.");
        System.out.println("4. Use a minimum password length of 8 characters, but 16 or more is recommended.");
        System.out.println("5. Regularly update your passwords, especially for sensitive accounts.");
        System.out.println("6. Consider using a password manager to generate and store complex passwords.");
    }
}
