import java.util.*;

public class PCB implements Comparable<PCB>{
    private final int pid;
    private final int arrival_time;
    private final int burst_time;
    private final int priority;

    private int used_time;

    private int wait_time = 0;
    private int turnaround_time = 0;

    public PCB() {
        this.pid = -1;
        this.arrival_time = -1;
        this.burst_time = -1;
        this.priority = -1;
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
    public void setTurnaroundTime(int n) { this.turnaround_time = n; }

    public int getPid() { return this.pid; }
    public int getArrivalTime() { return this.arrival_time; }
    public int getBurstTime() { return this.burst_time; }
    public int getPriority() { return this.priority; }
    public int getUsedTime() { return this.used_time; }

    public int getWaitTime() { return this.wait_time; }
    public int getTurnaroundTime() { return this.turnaround_time; }

    @Override
    public int compareTo(PCB p) {
        return this.arrival_time - p.getArrivalTime();
    }

}