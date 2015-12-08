public class AESMainModule {
    private AESState stateT;
    

 
    private static final int[][] Constants = 
    {
        {2, 3, 1, 1},
        {1, 2, 3, 1},
        {1, 1, 2, 3},
        {3, 1, 1, 2}
    }; 
    
    public AESMainModule(String input) {
        stateT = new AESState(input);
    }

    public AESMainModule() {

    }

    //Note(sharo): test method
    private void test() {
        stateT.printArray();
        System.out.println();
        mixColumns(stateT).printArray(); 
    }

    public void encryptText(String input) {
        stateT = new AESState(input);
        stateT.printArray();
        
        System.out.println();
        stateT = subBytes(stateT);
        stateT.printArray();

        System.out.println();
        stateT = shiftRows(stateT);
        stateT.printArray();

        System.out.println();
        stateT = mixColumns(stateT);
        stateT.printArray();
    }

    public AESState subBytes(AESState state) {
        for(int i = 0; i < state.bytes.length; ++i) {
                state.bytes[i] = AESOps.SubSt(state.bytes[i]); 
        }
        return state; 
    } 

    public AESState shiftRows(AESState state) {
        for(int i = 0; i < 4; ++i) {
            state.setRow(i, AESOps.rotateL(state.getRow(i), i));
        } 

        return state;
    }

    public AESState mixColumns(AESState state) {
        //Note(sharo): 4 columns in each state
        AESState mixedCols = new AESState();
        byte[] result = new byte[16];
        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                result[i + 4*j] = columnMulFF(state.getColumn(i), Constants[j]);
            } 
        }
      
        mixedCols.bytes = result; 
        return mixedCols;
    }
     

    //Note(sharo): given two arrays multiply them
    private byte columnMulFF(byte[] col1, int[] col2) {
        byte[] temp = new byte[col1.length];
        byte lel = 0;
        for(int i = 0; i < col1.length; ++i) {
            lel ^= galoisFieldMultiplication(col1[i], col2[i]);
        } 

        return lel;
    }

    //Note(sharo): GF(2^8) multiplication between the numbers
    private byte galoisFieldMultiplication(int leftNumber, int rightNumber) {
        int leftNum  = leftNumber   & 0xFF; 
        int rightNum = rightNumber & 0xFF;

        if((leftNum == 0) || (rightNumber == 0) ) return (byte)0;
        int result = AESOps.L(leftNum) + AESOps.L(rightNumber);
        if(result > 0xff) result -= 0xff;
        result = AESOps.E(result);
        return (byte)(result & 0xff);
    }

    public static void main(String[] args) {
        AESMainModule make = new AESMainModule();

        make.encryptText("1234567890123456");
        
    }

}
