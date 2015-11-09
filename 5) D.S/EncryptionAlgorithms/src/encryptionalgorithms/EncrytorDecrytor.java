package encryptionalgorithms;

/**
 *
 * @author Sharo
 */
public class EncrytorDecrytor {
    //Note(sharo): Additive Cipher encryption
    public static String AdditiveCipherEc(String plaintext, int key) {
        plaintext = plaintext.toLowerCase();
        String ciphertext = "";
        
        int aNumber = (int)('a');
        
        for(int chars : plaintext.toCharArray()) {
            ciphertext += (char)(((chars-aNumber + key)%26) + aNumber);
        }
        
        return ciphertext;
    }
    //Note(sharo): Additive Cipher Decryption
    public static String AdditiveCipherDec(String ciphertext, int key) {
        String plaintext = "";
        
        int aNumber = (int)'a';
        for(int chars : ciphertext.toCharArray()) {
            if((chars-aNumber-key) <= 0) chars += 26;
            plaintext += (char)(((chars-aNumber-key)%26) + aNumber);
        }
        
        //System.out.println((0-1)%26);
        
        return plaintext;
    }
    
    //Note(sharo): Rail Fence Cipher Encryption
    public static String RailFenceEnc(String plaintext) {
        String ciphertext_part_even = "";
        String ciphertext_part_odd = "";
        
        for(int i = 0; i < plaintext.length(); ++i) {
            if(i%2 == 0) {
                ciphertext_part_even += plaintext.charAt(i);
            } else {
                ciphertext_part_odd += plaintext.charAt(i);
            }
        }
        
        return ciphertext_part_even + ciphertext_part_odd;
    }
    
    //Note(sharo): Rail Fence Cipher Decryption
    public static String RailFenceDec(String ciphertext) {
        String plaintext = "";

        int half_size = (int)(Math.round(ciphertext.length()/2.0));
        String plaintext_even = ciphertext.substring(0, half_size);
        String plaintext_odd = ciphertext.substring(half_size, ciphertext.length());
        
        char[] plain_shit = new char[ciphertext.length()];
        for(int i = 0; i < plaintext_even.length(); ++i) {
            plain_shit[i*2] = plaintext_even.charAt(i);
        }

        for(int i = 0; i < plaintext_odd.length(); ++i) {
            plain_shit[i*2 + 1] = plaintext_odd.charAt(i);
        }


 
        return new String(plain_shit);
    }
    
    
    
}
