public class shSch {
    int total_time_spend;
    int total_time_waited;
    
    int execution_pointer;
    boolean preemptive;
    shProc[] process;

    public shSch(shProc[] procs) {
        this.process = procs;
        preemptive = true;
        execution_pointer = -1;

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
    
    public void executeFCFS() {
        int total_time_spend = 0;    
        for(int i = 0; i < process.length; ++i) {
            process[i].wait_time = total_time_spend - process[i].arrive_time;
            total_time_spend += process[i].burst_time;
            process[i].turn_around_time = process[i].burst_time  + process[i].wait_time;
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
        	int execute_for = 0;
            
            if(checkReady()) {
                execution_pointer = processToExec();
                if(execution_pointer >= 0) {
                	execute_for = (preemptive) ? 1 : process[execution_pointer].burst_time;
                    runProcess(execution_pointer, execute_for);
                    
                }
            } else {
            	System.out.println("Waiting...");
            	execute_for = 1;
            }

            if(allDone()) {
                execute = false; 
            }
            
//            if(preemptive) {
//            	
//            }
            //if(execution_pointer >= 0)
            	reduceETA(execution_pointer, (preemptive) ? 1 : execute_for); 
            total_time_spend++;
        } 
    }



    private boolean checkReady() {
        for(int i = 0; i < process.length; ++i) {
            if(process[i].arrive_time <= 0) {
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
        System.out.print("Running process: " + (index + 1) + " for " + amount + " seconds");
        System.out.println("has been waiting for " + process[index].wait_time + " BT is now: " + process[index].burst_time);
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
               //System.out.println("hello");
               shortest = this.process[i].burst_time;
               index = i;
           }
        }  

        return index;
    }

    private void reduceETA(int execute, int eta) {
        for(int i = 0; i < process.length; ++i) {
        	if(preemptive) {
	        	if( process[i].arrive_time > 0) {
	        		process[i].arrive_time -= eta;
	        	} else if((execute != i) && (process[i].burst_time > 0)) {
	        		process[i].wait_time++;
	        	}
        	} else  {
        		//Note(sharo): this a bit broken in non-preemptive, non ready-zero processes.
        		//the check must be on something else
        		if(process[i].burst_time > 0) {
        			process[i].arrive_time -= eta;
        			process[i].wait_time = Math.abs(process[i].arrive_time);
        		} else if((execute != i) &&
        				   (process[i].burst_time > 0))
        		{
        			process[i].arrive_time -= eta;
        			process[i].wait_time = Math.abs(process[i].arrive_time);
        		}
        		
        		
        	}	
        }
    }


    public void printStuff() {
        String form = "pprocess id: %d has %d exec time, waited: %d, turn: %d";
        for(int i = 0; i < process.length; ++i) {
            System.out.println(String.format(form, process[i].id, process[i].burst_time, process[i].wait_time, process[i].turn_around_time));
        }
//        System.out.println("time spent exec: " + total_time_spend);
//        System.out.println("time spent waiting: " + total_time_waited);
//        System.out.println("Avrg time: " + total_time_waited/ process.length);
    }
}
