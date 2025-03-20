/**
 * Provides encryption and decryption functionality using the Caesar cipher algorithm.
 */
public class CipherService {
    // Constants for character range boundaries
    private static final char LOWERCASE_START = 'a';
    private static final char LOWERCASE_END = 'z';
    private static final char UPPERCASE_START = 'A';
    private static final char UPPERCASE_END = 'Z';
    private static final int ALPHABET_SIZE = 26;

    /**
     * Encrypts the plaintext using the Caesar cipher with the specified shift.
     *
     * @param plaintext The text to encrypt.
     * @param shift The number of positions to shift each character.
     * @return The encrypted text (ciphertext).
     */
    public String encrypt(String plaintext, int shift) {
        return processText(plaintext, shift);
    }

    /**
     * Decrypts the ciphertext using the Caesar cipher with the specified shift.
     *
     * @param ciphertext The text to decrypt.
     * @param shift The number of positions that were used to shift each character.
     * @return The decrypted text (plaintext).
     */
    public String decrypt(String ciphertext, int shift) {
        // To decrypt, we shift in the opposite direction (26 - shift) % 26
        return processText(ciphertext, (ALPHABET_SIZE - shift) % ALPHABET_SIZE);
    }

    /**
     * Processes text by applying the Caesar cipher shift to each character.
     * Preserves case and non-alphabetic characters.
     *
     * @param text The text to process.
     * @param shift The shift value to apply.
     * @return The processed text.
     */
    private String processText(String text, int shift) {
        // Validate input
        if (text == null || text.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder(text.length());

        // Process each character in the input text
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            char shiftedChar;

            // Apply shift only to alphabetic characters
            if (Character.isLowerCase(currentChar)) {
                shiftedChar = shiftCharacter(currentChar, shift, LOWERCASE_START, LOWERCASE_END);
            } else if (Character.isUpperCase(currentChar)) {
                shiftedChar = shiftCharacter(currentChar, shift, UPPERCASE_START, UPPERCASE_END);
            } else {
                // Non-alphabetic characters remain unchanged
                shiftedChar = currentChar;
            }

            // Append the processed character to the result
            result.append(shiftedChar);
        }

        return result.toString();
    }

    /**
     * Shifts a character by the specified amount within a given range.
     *
     * @param c The character to shift.
     * @param shift The amount to shift by.
     * @param rangeStart The start of the character range.
     * @param rangeEnd The end of the character range.
     * @return The shifted character.
     */
    private char shiftCharacter(char c, int shift, char rangeStart, char rangeEnd) {
        // Calculate the position in the alphabet (0-based)
        int position = c - rangeStart;

        // Apply the shift and handle wrap-around
        int newPosition = (position + shift) % ALPHABET_SIZE;

        // Convert back to a character
        return (char) (rangeStart + newPosition);
    }
}