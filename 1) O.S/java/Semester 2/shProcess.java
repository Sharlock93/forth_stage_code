public class shProcess {
    private int InitBurst = 0;
    private int RemainingBurst = 0;
    private int ArriveTime = 0;
    private int finisheTime = 0;
    private int WaitTime = 0;
    public boolean finished = false;
    private int ID;

    public shProcess(int id, int burst, int arriveTime) {
        ID = id;
        InitBurst = RemainingBurst = burst;
        ArriveTime = arriveTime;
    }


    public int execute(int totalTime, int executeFor) {
        //Note(sharo): did we finish the process?
        //how much remains if we are done
        int remaining = RemainingBurst - executeFor;
         
        //Note(sharo): we are not done yet.
        if(remaining >= 0)
            RemainingBurst = remaining;
        else {
            //Note(sharo): we finished before the quanta ran out
            RemainingBurst = 0;
        }

        if(RemainingBurst == 0) {
            finished = true;
            finisheTime = totalTime + executeFor - remaining*-1;
        }

        if(remaining >= 0)
            return 0;
        else 
            return remaining*-1;
    }

    public void wait(int time) {
        if(!finished)
            WaitTime += time;
    }

    public void print() {
        System.out.printf("ID: %d, Burst: %d, Remaining Burst: %d Wait: %d, Finish: %d, TurnAround: %d\n", 
                            ID, InitBurst, RemainingBurst, WaitTime, finisheTime, finisheTime-ArriveTime);
    }

    public int getID() {
        return ID;
    }

    public int getBurst() {
        return RemainingBurst;
    }
    
} 
