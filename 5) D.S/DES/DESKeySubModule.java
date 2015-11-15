public class DESKeySubModule {
    private long Key64Int;
    private char[] Key8Char = new char[8];

    private int C032;
    private int D032;

    private char[] C0char = new char[4];
    private char[] D0char = new char[4];

    private int[][] subKeys = new int[16][2];
    private long[]  subKeyF = new long[16];

    //Note(sharo): PC-1 DES table
    private static final int[] PC1 =  {57, 49, 41, 33, 25, 17, 9, 1, 58,
                                       50, 42, 34, 26, 18, 10, 2, 59, 51,
                                       43, 35, 27, 19, 11, 3, 60, 52, 44,
                                       36, 63, 55, 47, 39,  31, 23, 15, 7,
                                       62, 54, 46, 38, 30, 22, 14, 6, 61, 53,
                                       45, 37, 29, 21, 13, 5, 28, 20, 12, 4};

    private static final int[] PC2 = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21,
                                      10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20,
                                      13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51,
                                      45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42,
                                      50, 36, 29, 32};
    //Note(sharo): rotation rounds
    private static final int[] Rotations = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    public DESKeySubModule(long key) {

        Key64Int = key;

        GenerateCandD();
        GenerateSubKeyCD();
        GenerateSubKeyPC2();
    }

    public DESKeySubModule(char[] key) {

    }

    private void GenerateCandD() {
        long Key56Bit = permutateUsingTable(Key64Int, PC1, 64);
        //Note(sharo): magic number? nope, get the 28 bits from 56
        C032 = (int)( Key56Bit >>> 28);
        D032 = (int)( Key56Bit << 36 >>> 36 );
    }

    private void GenerateSubKeyPC2() {
        for(int i = 0; i < subKeyF.length; ++i) {
            subKeyF[i] = ((long)(subKeys[i][0]) << 28) | subKeys[i][1];
            subKeyF[i] = permutateUsingTable(subKeyF[i], PC2, 56);
            System.out.println("Key " + (i+1) + ":       " + Long.toBinaryString(subKeyF[i]));
        }
    }
    

    public long getRoundKey(int roundNumber) {
        return subKeyF[roundNumber];
    }

    private void GenerateSubKeyCD() { 
        subKeys[0][0] = rotateLeft2Bit(C032, Rotations[0]);
        subKeys[0][1] = rotateLeft2Bit(D032, Rotations[0]);

        for(int i = 1; i < subKeys.length; ++i) {
            subKeys[i][0] = rotateLeft2Bit(subKeys[i-1][0], Rotations[i]); 
            subKeys[i][1] = rotateLeft2Bit(subKeys[i-1][1], Rotations[i]); 
        }
        //Note(sharo): debug subkey output    
        /* for(int i = 0; i < subKeys.length; ++i) { */
        /*     System.out.println("C" + (i+1) + ": " + Integer.toBinaryString(subKeys[i][0])); */
        /*     System.out.println("D" + (i+1) + ": " + Integer.toBinaryString(subKeys[i][1])); */
        /* } */
    }

    private int rotateLeft2Bit(int i, int distance)  {
        int mask3029 = 3 << 28;
        int mask32313029 = (0xF_FF_FF_FF);
        int checkResult = ( ( i << distance ) & mask3029 ) >> 28;
        int result = ((i << distance) & mask32313029) | checkResult;
        return result;
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

    public static void main(String[] args) {

        long number =  0b0001001100110100010101110111100110011011101111001101111111110001L;

        DESKeySubModule shar = new DESKeySubModule(number);

        /* System.out.println(shar.permutateUsingTable(number,test, 64)); */

    }
}
