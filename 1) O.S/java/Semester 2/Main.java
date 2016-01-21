import java.util.Scanner;
public class Main  {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter Number of processes: ");
        int temp = input.nextInt();
        shProcess[] test = new shProcess[temp];

        System.out.print("Enter Quanta: ");
        int quanta = input.nextInt();

        for (int i = 0; i < test.length; ++i) {
            System.out.printf("Enter burst for processe(%d): ", i+1);
            temp = input.nextInt();
            test[i] = new shProcess(i+1, temp, 0);
        }

        shRoundRobin shar = new shRoundRobin(test, quanta);        

        shar.run();

    }
}
