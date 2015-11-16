public class DESPlainTextSubModule {
    private String plainText;
    private long   key;
    private DESKeySubModule keySch;
    private int[][] plainText32LR = new int[16][2];

    class LR32 {
        public int L;
        public int R;
        public long P;
        public LR32(long P) {
            this.P = P;
            L = (int)( P >>> 32);
            R = (int)( P << 32 >>> 32 );
        }

        public long expandR48() {
            return permutateUsingTable((long)(R), ET, 32);
        }
    }

    private static final int[] IP ={58,50,42,34,26,18,10,2,
                                  60,52,44,36,28,20,12,4,
                                  62,54,46,38,30,22,14,6,
                                  64,56,48,40,32,24,16,8,
                                  57,49,41,33,25,17,9,1,
                                  59,51,43,35,27,19,11,3,
                                  61,53,45,37,29,21,13,5,
                                  63,55,47,39,31,23,15,7 };

    private static final int[] ET ={32,1,2,3,4,5,
                                    4,5,6,7,8,9,
                                    8,9,10,11,12,13,
                                    12,13,14,15,16,17,
                                    16,17,18,19,20,21,
                                    20,21,22,23,24,25,
                                    24,25,26,27,28,29,
                                    28,29,30,31,32,1}; 

    public DESPlainTextSubModule(String plainText, long key) {
        this.plainText = plainText;
        this.key = key;
        keySch = new DESKeySubModule(key); 
        testGround();
    }

    private void testGround() {
        long string =0b0000_0001_0010_0011_0100_0101_0110_0111_1000_1001_1010_1011_1100_1101_1110_1111L;
        long PE = permutateUsingTable( string, IP, 64 );
        System.out.println("hello " + Long.toBinaryString(new LR32(PE).expandR48())); 
    }

    private LR32 Round(LR32 inputP, long roundKey) {
        LR32 ne 
    }
    
    public String generateCipher() {
         
        return "";
    }

    private long permutateUsingTable(long number, int[] permutation, int numberOfBits) { 
        long shiftBy = 0; 
        long checkBit = 0;
        long bitCheckResult = 0;
        long res = 0;

        for(int i = 0; i < permutation.length; ++i) {
            //Note(sharo): 64 is not really correct, and should be changed.
            long whereToPutBit = (permutation.length - i -1);
            checkBit =(01L << (numberOfBits - permutation[i])); 
            bitCheckResult =  (number & checkBit);
            /* resString += (bitCheckResult != 0) ? "1": "0";  */
            res |= (bitCheckResult != 0) ? (01L << whereToPutBit) : 0;
        }

        return res;
    }

    public String generateCipher(String plainText) {
    
        return "";
    }
        
    private long getStringBinary(String input) {        
        long result = 0;
        //Note(sharo): possible magic number, i.e: input must be 8 bytes, or only 8 chars
        for(int i = 0; i < input.length(); ++i) {
            result |= (long)(input.charAt(i)) << (56-i*8);
        }
        return result; 
    }

    public static void main(String[] args) {
        DESPlainTextSubModule shar = new DESPlainTextSubModule("Hello World", 0x133457799BBCDFF1L);
        String hel = "hellowrr";
        System.out.println(Long.toBinaryString(shar.getStringBinary(hel)));
    }


}
