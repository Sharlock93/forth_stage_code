public class Main  {
    public static void main(String[] args) {
        shProcess[] test = new shProcess[3];

        for (int i = 0; i < test.length; ++i) {
            test[i] = new shProcess(i+1, i+1, 0);
        }

        shRoundRobin shar = new shRoundRobin(test);        

        shar.run();

    }
}
