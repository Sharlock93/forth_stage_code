public class DESMain {
    public static void main(String[] args) {
        //Note(sharo): 0x means the number is in Hex
        //and 64 bits is 16 Hex digits
        //key must be either decimal, binary, or hex
        long Key = 0x3132333435363738L;
        DESPlainTextSubModule desTest = new DESPlainTextSubModule(Key);

        String plainText = "1234567";
        String cipherText = desTest.encryptText(plainText);//will use the key

        System.out.println("C: " + cipherText);
    }
}
