import java.util.Scanner;

public class shSchMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int procSize = input.nextInt();
        shProc[] procs = new shProc[procSize];
        for(int i = 0; i < procs.length; ++i) { 
            System.out.print("Enter Execution time for process Id (" + i + "): ");
            int procTime = input.nextInt();  
            System.out.print("Enter Arrive time for process Id (" + i + "): ");
            int arriveTime = input.nextInt();
            procs[i] = new shProc(i, procTime, arriveTime);
        }
        /*  */
        /*  */
        /* procs[0] = new shProc(0, 24, 1); */
        /* procs[1] = new shProc(1, 3, 0); */
        /* procs[2] = new shProc(2, 3, 2); */

        shSch shar = new shSch(procs);
        System.out.println();
        shar.exec();
        /* shar.executeFCFS(); */
        shar.printStuff();

    }
}
