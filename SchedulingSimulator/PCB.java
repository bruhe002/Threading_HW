import java.util.*;

public class PCB implements Comparable<PCB>{
    // Variables given by the input.txt file
    private final int pid;
    private final int arrival_time;
    private final int burst_time;
    private final int priority;

    // Variables used for calculations
    private int used_time = 0;
    private int wait_time = 0;
    private int turnaround_time = 0;

    // Default Constructor
    // Used for idle process
    public PCB() {
        this.pid = -1;
        this.arrival_time = -1;
        this.burst_time = -1;
        this.priority = -1;
    }

    // Overloaded constructor
    // Used for plain process
    public PCB(int pd, int at, int bt, int pty) {
        this.pid = pd;
        this.arrival_time = at;
        this.burst_time = bt;
        this.priority = pty;
    }

    // Used to increment used_time during algorithms
    public void incUsedTime() {
        this.used_time++;
    }

    // Set methods for private data
    public void setWaitTime(int n) { this.wait_time = n; }
    public void setTurnaroundTime(int n) { this.turnaround_time = n; }

    // Get methods for private data
    public int getPid() { return this.pid; }
    public int getArrivalTime() { return this.arrival_time; }
    public int getBurstTime() { return this.burst_time; }
    public int getPriority() { return this.priority; }
    public int getUsedTime() { return this.used_time; }

    public int getWaitTime() { return this.wait_time; }
    public int getTurnaroundTime() { return this.turnaround_time; }

    // Method to compare PCBs via arrival time
    // Used in main method to sort waiting list
    @Override
    public int compareTo(PCB p) {
        return this.arrival_time - p.getArrivalTime();
    }

    // Used to reset the pcb variables to be used for another algorithm
    public void resetPCB() {
        this.used_time = 0;
        this.wait_time = 0;
        this.turnaround_time = 0;
    }

}