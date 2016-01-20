public class shRoundRobin {
    private shProcess[] processes;

    public shRoundRobin(shProcess[] prot) {
        processes = prot;
    }

    public void run() {
        //Note(sharo): start from -1 because first process is zero
        int nextProcess = -1;
        int quanta = 2;
        int totalTime = 0;
        while(!allDone()) {
            nextProcess = (++nextProcess) % processes.length;
            
            if(processes[nextProcess].finished)
                continue;
            
            int timeReminder = processes[nextProcess].execute(totalTime, quanta);
            totalTime = totalTime + quanta - timeReminder;

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
}
