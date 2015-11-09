public class shSch {
    int total_time_spend;
    int total_time_waited;

    shProc[] process;

    public shSch(shProc[] procs) {
        this.process = procs;
    }


    public void exec() {
        boolean execute = true;
        while(execute) {
            reduceETA(total_time_spend);
            total_time_spend++;
        }
    }

    /* private int runProcessForSecs(shProc process, int time) { */
    /*     System.out.println("Running Process id (" + process.id + ") for " + time); */
    /*     process.burst_time -= time; */
    /* } */

    private int processToExec() {
        int shortest = 0;
        for(int i = 1; i < this.process.length; ++i) {
           if(( this.process[i].burst_time < this.process[shortest].burst_time ) && ( this.process[i].arriveTime == 0 )) {
               shortest = i;
           }
        } 

        return shortest;
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
