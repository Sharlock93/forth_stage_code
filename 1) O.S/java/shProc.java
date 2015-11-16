public class shProc {
    int id;
    int burst_time;
    int wait_time;
    int arrive_time;
    int turn_around_time;
    int finish_time;


    public shProc(int id, int burst_time, int arrive_time) {
        this.id = id;
        this.burst_time = burst_time;
        this.wait_time = 0;
        this.arrive_time = arrive_time;
    }
}
