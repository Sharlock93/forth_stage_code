public class DESMain {
    public static void main(String[] args) {
        //Note(sharo): 0x means the number is in Hex
        //and 64 bits is 16 Hex digits
        long Key = 0x00_00_00_00_00_00_00_00L;
        DESPlainTextSubModule desTest = new DESPlainTextSubModule(Key);

        String plainText = "12345678";
        String cipherText = desTest.encryptText(plainText);//will use the key

        System.out.println("C: " + cipherText);
    }
}
