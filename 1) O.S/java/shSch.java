public class shSch {
    int total_time_spend;
    int total_time_waited;

    shProc[] process;

    public shSch(shProc[] procs) {
        this.process = procs;

        for(int i = 0; i < process.length; ++i) {
            for(int j = i; j < process.length; ++j) {
                if(process[i].arrive_time > process[j].arrive_time) {
                    shProc temp = process[i];
                    process[i] = process[j];
                    process[j] = temp;
                }
            } 
        }


    }

    public void printProcesses() {
        for(int i = 0; i < process.length; ++i) {
            System.out.println("ET: " + process[i].arrive_time + " BT" + process[i].burst_time);
        } 
    }


    public void exec() {
        int total_burst_time = 0;
        boolean execute = true;
        while(execute) {
            
            if(checkReady()) {
                int indexProcess = processToExec();
                System.out.println("Running process: " + indexProcess);
                if(indexProcess > 0)
                    runProcess(indexProcess, 1);
            }

            if(allDone()) {
                execute = false; 
            }

            reduceETA(1); 
            total_time_spend++;
        } 
    }

    private boolean checkReady() {
        for(int i = 0; i < process.length; ++i) {
            if(process[i].arrive_time == 0) {
                return true;
            } 
        } 
        return false;
    }

    private boolean allDone() {
    
        for(int i = 0; i < process.length; ++i) 
            if(process[i].burst_time > 0) return false;

        return true;
    }
    private void runProcess(int index, int amount) {
        process[index].burst_time -= amount;
        if(process[index].burst_time <= 0)
            process[index].burst_time = 0;
   }

    /* private int runProcessForSecs(shProc process, int time) { */
    /*     System.out.println("Running Process id (" + process.id + ") for " + time); */
    /*     process.burst_time -= time; */
    /* } */

    private int processToExec() {
        int shortest = 1000000;
        int index = -1;
        for(int i = 0; i < this.process.length; ++i) {
           if( (this.process[i].burst_time > 0) && 
               (this.process[i].arrive_time <= 0) &&
               ( this.process[i].burst_time < shortest ) )
           {
               System.out.println("hello");
               index = i;
           }
        }  

        return index;
    }

    private void reduceETA(int eta) {
        for(int i = 0; i < process.length; ++i) {
            if(process[i].burst_time > 0)
                process[i].arrive_time -= eta;
        }
    }


    public void printStuff() {
        String form = "pprocess id: %d has %d exec time, waited: %d";
        for(int i = 0; i < process.length; ++i) {
            System.out.println(String.format(form, process[i].id, process[i].burst_time, process[i].wait_time));
        }

        System.out.println("time spent exec: " + total_time_spend);
        System.out.println("time spent waiting: " + total_time_waited);
        System.out.println("Avrg time: " + total_time_waited/ process.length);
    }
}
