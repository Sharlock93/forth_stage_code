public class DESPlainTextSubModule {
    private String plainText;
    private long   key;
    private DESKeySubModule keySch;

    public DESPlainTextSubModule(String plainText, long key) {
        this.plainText = plainText;
        this.key = key;
        keySch = new DESKeySubModule(key);
    }

    public String generateCipher() {
    
        return "";
    }


    public String generateCipher(String plainText) {
    
        return "";
    }
    
    private long getsomthing() {
        return 0L;
    }

    public static void main(String[] args) {
        DESPlainTextSubModule shar = new DESPlainTextSubModule("Hello World", 0x133457799BBCDFF1L);
        String hel = "hellowr";
        long test = 0;
        for(int i = 0; i < hel.length(); ++i) {
                test |= (long)(hel.charAt(i)) << (8*(8-2-i));
        }

        System.out.println(Long.toBinaryString(test));
    }


}
