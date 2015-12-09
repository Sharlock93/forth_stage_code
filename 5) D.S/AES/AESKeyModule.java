import java.util.*;
public class AESKeyModule {

    byte[][] originalKeys128 = new byte[4][4];
  
    public AESKeyModule(String input) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; j++) {
                originalKeys128[j][i] = (byte)input.charAt(i*4 + j);
            }
        }

        test();
    }

    private void test() {
        byte[] l = {1, 2, 3, 4};
    }

    public void printArray() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; j++) {
                System.out.print(Integer.toHexString((originalKeys128[i][j] & 0xFF)) + " ");
            }
            System.out.println();
        }
    }

    public byte[] expandWord(byte[] wordI, byte[] wordI3) {
        byte[] newWord = new byte[wordI.length];
        wordI3 = gFunction(wordI3);
        return newWord;
    }

    private byte[] gFunction(byte[] inputWord) {
        byte[] temp = new byte[inputWord.length];
        temp = AESOps.rotateL(inputWord, 1);
        for (int i = 0; i < 4; i++) {
            temp[i] = AESOps.SubSt(temp[i]);
        }

        return temp;
    }

    
    public static void main(String[] args) {
        new AESKeyModule("1234567890123456");
    }
} 
