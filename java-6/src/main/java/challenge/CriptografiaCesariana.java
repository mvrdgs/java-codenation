package challenge;

public class CriptografiaCesariana implements Criptografia {

    private void checkText(String texto) {
        if (texto == null) throw new NullPointerException();
        if (texto.isEmpty()) throw new IllegalArgumentException();
    }

    @Override
    public String criptografar(String texto) {
        checkText(texto);

        String text = texto.toLowerCase();
        StringBuilder encryptedString = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (character >= 'a' && character <= 'w') {
                int encryptedChar = character + 3;
                encryptedString.append((char) encryptedChar);
            } else if (character >= 'x' && character <= 'z') {
                int encriptedChar = character - 23;
                encryptedString.append((char) encriptedChar);
            } else {
                encryptedString.append(character);
            }
        }

        return encryptedString.toString();
    }

    @Override
    public String descriptografar(String texto) {
        checkText(texto);

        String text = texto.toLowerCase();
        StringBuilder decryptedString = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (character >= 'd' && character <= 'z') {
                int decrypted = character - 3;
                decryptedString.append((char) decrypted);
            } else if (character >= 'a' && character <= 'c') {
                int decryptedChar = character + 23;
                decryptedString.append((char) decryptedChar);
            } else {
                decryptedString.append(character);
            }
        }

        return decryptedString.toString();
    }
}
