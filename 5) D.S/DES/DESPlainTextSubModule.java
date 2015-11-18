public class DESPlainTextSubModule {
    private DESKeySubModule keySch;

    private static final int[] SBoxs = {
    14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7, 0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8,
    4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0, 15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13,
    15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10, 3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5,
    0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15, 13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9,
    10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8, 13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1,
    13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7, 1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12,
    7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15, 13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9,
    10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4, 3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14,
    2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9, 14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6,
    4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14, 11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3,
    12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11, 10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8,
    9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6, 4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13,
    4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1, 13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6,
    1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2, 6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12,
    13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7, 1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2,
    7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8, 2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11
    };

    //Note(sharo): initial Permutation
    private static final int[] IP = {
    58,50,42,34,26,18,10,2, 60,52,44,36,28,20,12,4, 62,54,46,38,30,22,14,6,
    64,56,48,40,32,24,16,8, 57,49,41,33,25,17,9,1, 59,51,43,35,27,19,11,3,
    61,53,45,37,29,21,13,5, 63,55,47,39,31,23,15,7
    };
    
    //Note(sharo): Final Inverse Permutation
    private static final int[] FP = {
    40,8,48,16,56,24,64,32, 39,7,47,15,55,23,63,31, 38,6,46,14,54,22,62,30,
    37,5,45,13,53,21,61,29, 36,4,44,12,52,20,60,28, 35,3,43,11,51,19,59,27,
    34,2,42,10,50,18,58,26, 33,1,41,9,49,17,57,25
    };

    //Note(sharo): Expansion Tables
    private static final int[] ET = {
    32,1,2,3,4,5, 4,5,6,7,8,9, 8,9,10,11,12,13, 12,13,14,15,16,17,
    16,17,18,19,20,21, 20,21,22,23,24,25, 24,25,26,27,28,29, 28,29,30,31,32,1
    }; 
    
    //Note(sharo): Permutation after the SBoxs
    private static final int[] PSbox = {
    16,7,20,21, 29,12,28,17, 1,15,23,26, 5,18,31,10, 2,8,24,14, 32,27,3,9,
    19,13,30,6, 22,11,4,25
    };

    //Note(sharo): class to handle the 64 bit value,
    //and make L and R 32 bit values
    class LR32 {
        public int L;
        public int R;
        public long P;
        public long RE48;
        public LR32() {
            L = 0;
            R = 0;
            P = 0;
            RE48 = 0;
        }

        public LR32(long P) {
            this.P = P;
            L = (int)( P >>> 32);
            R = (int)( P << 32 >>> 32 );
            RE48 = expandR48();
        }

        private long expandR48() {
            return permutateUsingTable((long)(R), ET, 32);
        }

        public int getSBoxGroup(int group) {
            //Note(sharo): groups must start from 1 to 8, not 0
            //Note(sharo): 63 means six ones in binary
            int shift = (48 - group*6);
            int re6bits = (int)(RE48 >>> shift) & 63;
            return re6bits;
        }
    }



    //Note(sharo): initiate the DESPlainTextSubModule with a Key
    public DESPlainTextSubModule(long key) {
        keySch = new DESKeySubModule(key); 
    }

    //Note(sharo): public API to encrypt a message String using the Key 
    public String encryptText(String input) {
        String[] groupsOf8 = generateCipher(input);
        String cipherText = "";
        for(int i = 0; i < groupsOf8.length; ++i) {
            cipherText += Long.toHexString(encrypt(getStringBinary(groupsOf8[i])));
        }

        return cipherText;
    }

    private long encrypt(long input) {
        long nextRoundInput = permutateUsingTable( input, IP, 64 );
        for(int i = 1; i <= 16; ++i ) {
           nextRoundInput = Round(nextRoundInput, keySch.getRoundKey(i));
        }

        LR32 finalRound = new LR32(nextRoundInput);

        long roundOutput = ((long) finalRound.R << 32 ) |
                           ((finalRound.L) & 0xFFFF_FFFFL );
        roundOutput = permutateUsingTable(roundOutput, FP, 64);
        return roundOutput;
    } 

    private long Round(long inputP, long roundKey) {
        LR32 round = new LR32(inputP);
        round.RE48 = round.RE48 ^ roundKey;
        int R32Sbox = 0;
        for(int i = 1; i <= 8; ++i) {
            R32Sbox = ( R32Sbox << 4) | SBoxReduction6Bit(i, round.getSBoxGroup(i));
        }
        int nextRoundR = (int)permutateUsingTable( R32Sbox, PSbox, 32)^round.L;
        long roundOutput = ((long) round.R << 32 ) | ((nextRoundR) & 0xFFFF_FFFFL );
        return roundOutput;
    }
    
    private int SBoxReduction6Bit(int sBoxNumber, int input6B) {
        int row = ( (0b00100000 & input6B) >>> 4 ) | (input6B & 1);
        int column    = ( 0b00011110 & input6B) >>> 1;
        return SBoxs[64*(sBoxNumber-1) + row*16 +  column];
    }

    private long permutateUsingTable(long number, int[] permutation, int numberOfBits) { 
        long shiftBy = 0; 
        long checkBit = 0;
        long bitCheckResult = 0;
        long res = 0;

        for(int i = 0; i < permutation.length; ++i) {
            long whereToPutBit = (permutation.length - i -1);
            checkBit =(01L << (numberOfBits - permutation[i])); 
            bitCheckResult =  (number & checkBit);
            res |= (bitCheckResult != 0) ? (01L << whereToPutBit) : 0;
        }

        return res;
    }

    public String[] generateCipher(String plainText) {
        String[] groupedTexts = new String[(int)Math.ceil(plainText.length()/8.0)];
        for(int j = 0; j < groupedTexts.length; ++j) {
            String groupText = "";
            for(int i = j*8; i < plainText.length(); ++i) {
                groupText += plainText.charAt(i);
                if(i == 0) continue;
                if(i % 7 == 0) break;
            }
            while(groupText.length() < 8) groupText += "0";
            groupedTexts[j] = groupText;
        }
        return groupedTexts;
    }
        
    private long getStringBinary(String input) {        
        long result = 0;
        //Note(sharo): possible magic number, i.e: input must be 8 bytes, or only 8 chars
        for(int i = 0; i < input.length(); ++i) {
            result |= (long)(input.charAt(i)) << (56-i*8);
        }
        return result; 
    }
 
}
