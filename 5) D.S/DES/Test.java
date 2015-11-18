public class Test {
    public static void main(String[] args) {
        String test = "hellowrr"; 
        String bits = "";
        
        int[] IP ={58,50,42,34,26,18,10,2,
            60,52,44,36,28,20,12,4,
            62,54,46,38,30,22,14,6,
            64,56,48,40,32,24,16,8,
            57,49,41,33,25,17,9,1,
            59,51,43,35,27,19,11,3,
            61,53,45,37,29,21,13,5,
            63,55,47,39,31,23,15,7 };

        for(int i = 0; i < test.length(); ++i) {
            String temp =  Integer.toBinaryString((int)(test.charAt(i))); 
            while(temp.length() < 8) temp = "0" + temp; 
            bits += temp;
        }
        
        String premutated = "";
        String message = "0000000100100011010001010110011110001001101010111100110111101111"; 
        for(int i = 0; i < bits.length() ; ++i) {
            premutated += message.charAt(IP[i]-1);
        }

        /* System.out.println(premutated); */
        int[][] sbox1 = new int[2][2];
        sbox("111111", sbox1);

    }

    public static int sbox(String input, int[][] sbox) {
        
        String row_sting = "" + input.charAt(input.length()-1) +  input.charAt(0);
        System.out.println(row_sting);
        int row = Integer.parseInt(row_sting, 2);
       
        String column_string = input.substring(1,5);
        System.out.println(column_string);
        int column = Integer.parseInt(column_string, 2);
        System.out.println(row + " " + column);
        
        return sBox[row][column];
    }
}
