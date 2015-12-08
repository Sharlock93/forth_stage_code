public class Peterson {

    static boolean[] flag = new boolean[2];
    static int turn;
    
    static int sharedData = 0;

    public static void main(String[] args) {
        System.out.println("hello");
        String test = "hello";
        Thread1 p1 = new Thread1();
        Thread2 p2 = new Thread2();
        while(true) {
            new Thread1().start();
            new Thread2().start();     
        }
    }

    

    static class Thread1 extends Thread {
        @Override
        public void run() {
            flag[0] = true;
            turn = 1;
            while(flag[1] && ( turn == 1 ))
                System.out.println("Waiting...");

            System.out.println("Before inc: " + sharedData);
            sharedData++;
            System.out.println("After inc: " + sharedData);

            flag[0] = false;
        } 
    }
    
    static class Thread2 extends Thread {
        @Override
        public void run() {
            flag[1] = true;
            turn = 0;
            while(flag[0] && ( turn == 0 ))
                System.out.println("Waiting...");
            
            System.out.println("Before dec: " + sharedData);
            sharedData--;
            System.out.println("After dec: " + sharedData);

            flag[1] = false;
        } 
    }
    
}
