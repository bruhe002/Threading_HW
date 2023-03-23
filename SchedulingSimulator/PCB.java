public class PCB {
    private final int pid;
    private final int arrival_time;
    private final int burst_time;
    private final int priority;

    private int used_time;

    private int wait_time;
    private int response_time;
    private int turnaround_time;

    public PCB() {
        this.pid = 0;
        this.arrival_time = 0;
        this.burst_time = 0;
        this.priority = 0;
        this.used_time = 0;
    }

    public PCB(int pd, int at, int bt, int pty) {
        this.pid = pd;
        this.arrival_time = at;
        this.burst_time = bt;
        this.priority = pty;
        this.used_time = 0;
    }

    public void incUsedTime() {
        this.used_time++;
    }

    public void setWaitTime(int n) { this.wait_time = n; }
    public void setResponseTime(int n) { this.response_time = n; }
    public void setTurnaroundTime(int n) { this.turnaround_time = n; }

    public int getPid() { return this.pid; }
    public int getArrivalTime() { return this.arrival_time; }
    public int getBurstTime() { return this.burst_time; }
    public int getPriority() { return this.priority; }
    public int getUsedTime() { return this.used_time; }

    public void getWaitTime(int n) { return this.wait_time; }
    public void getResponseTime(int n) { return this.response_time; }
    public void getTurnaroundTime(int n) { return this.turnaround_time; }
}