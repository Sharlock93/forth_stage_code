public class shRoundRobin {
    private shProcess[] processes;
    private int quanta;

    public shRoundRobin(shProcess[] prot, int quanta) {
        processes = prot;
        this.quanta = quanta;
    }

    public void run() {
        //Note(sharo): start from -1 because first process is zero
        int nextProcess = -1;
        int totalTime = 0;
        while(!allDone()) {
            nextProcess = (++nextProcess) % processes.length;
            
            if(processes[nextProcess].finished)
                continue;
            
            int timeReminder = processes[nextProcess].execute(totalTime, quanta);
            totalTime = totalTime + quanta - timeReminder;
 
            printRunning(nextProcess, quanta, timeReminder);
            for (int i = 0; i < processes.length; i++) {
                if(i == nextProcess) continue;
                processes[i].wait(quanta - timeReminder);
            }
        } 

        for (int i = 0; i < processes.length; i++) {
            processes[i].print();
        }
    }

    private boolean allDone() {
        for (int i = 0; i < processes.length; ++i) {
            if(!processes[i].finished) return false;
        } 

        return true;
    }

    private void printRunning(int index, int quanta, int remaining) {
        String info = "ID: %d, ran for: %d, remaining burst: %d \n"; 
        System.out.printf(info, processes[index].getID(), quanta - remaining, processes[index].getBurst());
    }
}
