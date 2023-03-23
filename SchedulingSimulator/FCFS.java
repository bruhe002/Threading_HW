import java.util.*;

public class FCFS {
    public static void runFCFS(List<PCB> waitingQueue) {
        int running_time = 0;
        List<PCB> readyQueue = new ArrayList<>();
        List<PCB> finishedQueue = new ArrayList<>();
        PCB idlePCB = new PCB();
        PCB currentPCB = idlePCB;
        do {
            // Check for arrivals
            for(int i = 0; i < waitingQueue.size(); i++) {
                if(waitingQueue.get(i).getArrivalTime() == running_time) {
                    readyQueue.add(waitingQueue.get(i));
                    // Once added, remove from the waiting queue
                    waitingQueue.remove(i);
                }
            }

            // Get first arrival
            // Check if the ready queue is full
            if(waitingQueue.size() > 0 && readyQueue.size() == 0) {
                currentPCB = idlePCB;
            }
            // if it is then
            else {
                // Has the process completed it's execution
                if(currentPCB.getBurstTime() == currentPCB.getUsedTime()) {
                    // Run calculations
                    System.out.println("Process PID " + currentPCB.getPid() + " FINISHED running at time " + running_time);
                    currentPCB.setTurnaroundTime(running_time - currentPCB.getArrivalTime());
                    currentPCB.setWaitTime(currentPCB.getTurnaroundTime() - currentPCB.getBurstTime());
                    finishedQueue.add(currentPCB);

                    if(readyQueue.size() == 0 && waitingQueue.size() == 0) {
                        break;
                    } else {
                        // Get the next process
                        currentPCB = readyQueue.get(0);
                        readyQueue.remove(0);
                    }
                }
                if(readyQueue.size() != 0) {
                    // Is this the very first process? or are we coming back from an idle?
                    if(currentPCB.getArrivalTime() == -1) {
                        currentPCB = readyQueue.get(0);
                        readyQueue.remove(0);
                    }
                }

            }

            // Run the process if not idle
            if(currentPCB.getPid() != -1) {
                currentPCB.incUsedTime();
                System.out.println("Process PID " + currentPCB.getPid() + " is CURRENTLY running at time " + running_time);
            }

            // Increment running time
            ++running_time;
        } while(true);

        System.out.println("The Execution has completed!");
        // Display results
        float awt = 0;
        float art = 0;
        float att = 0;

        for(int i = 0; i < finishedQueue.size(); i++) {
            awt += finishedQueue.get(i).getWaitTime();
            att += finishedQueue.get(i).getTurnaroundTime();
        }

        awt /= finishedQueue.size();
        att /= finishedQueue.size();

        System.out.format("Average Wait Time = %.2f\n", awt);
        System.out.format("Average Response Time = %.2f\n", (att - awt));
        System.out.format("Average Turnaround Time = %.2f\n", att);
    }
}