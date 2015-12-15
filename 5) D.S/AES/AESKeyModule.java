import java.util.*;
public class AESKeyModule {

    byte[][] expandedKeys = new byte[44][4];

    public AESKeyModule(String input) {
        
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; j++) {
                expandedKeys[j][i] = (byte)input.charAt(i*4 + j);
            }
        }

    } 

    public byte[][] expandKey() {
        //Note(sharo): including the first 4 words of the original key
        //we will have 44 words, each have 4 bytes, i.e: 176 bytes

        for (int i = 4; i < 44; i++) {
            if(i%4 == 0) {
                expandedKeys[i] =  expandWord(expandedKeys[i-4], expandedKeys[i-1], i);
            } else {
                expandedKeys[i] =  AESOps.xorArray(expandedKeys[i-1], expandedKeys[i-4]);
            }
        }

        
        printArray(expandedKeys);

        
        return expandedKeys;
    }

    public void printArray(byte[][] array) {
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(Integer.toHexString((array[i][j] & 0xFF)) + " ");
            }
            System.out.println();
            if((i+1)%4 == 0)
                System.out.println();
        }
    }

    public void printArray() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; j++) {
                System.out.print(Integer.toHexString((expandedKeys[i][j] & 0xFF)) + " ");
            }
            System.out.println();
        }
    }

    private byte[] expandWord(byte[] wordI, byte[] wordI3, int roundNumber) {
        byte[] newWord = new byte[wordI.length];
        wordI3 = gFunction(wordI3, roundNumber);
        newWord = AESOps.xorArray(wordI, wordI3); 
        return newWord;
    }    

    private byte[] gFunction(byte[] inputWord, int roundNumber) {
        byte[] temp = new byte[inputWord.length];
        temp = AESOps.rotateL(inputWord, 1);
        for (int i = 0; i < 4; i++) {
            temp[i] = AESOps.SubSt(temp[i]);
        }

        temp[0] ^= AESOps.rCon[roundNumber/4 - 1];

        return temp;
    }

    
    public static void main(String[] args) {
        new AESKeyModule("1234567890123456");
    }
} 
