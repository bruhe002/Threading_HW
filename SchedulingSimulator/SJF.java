import java.util.*;

public class SJF {
    public static void runSJF(List<PCB> waitingQueue) {
        int running_time = 0;
        List<PCB> readyQueue = new ArrayList<>();
        List<PCB> finishedQueue = new ArrayList<>();
        PCB idlePCB = new PCB();
        PCB currentPCB = idlePCB;
        do {
            // Check for arrivals
            for(int i = 0; i < waitingQueue.size(); i++) {
                if(waitingQueue.get(i).getArrivalTime() == running_time) {
                    // Check Burst Time
                    // Place Process in the correct spot on the ready queue based on burst time
                    boolean notAdded = true;
                    for(int j = 0; j < readyQueue.size(); j++) {
                        if(waitingQueue.get(i).getBurstTime() < readyQueue.get(j).getBurstTime()) {
                            readyQueue.add(j, waitingQueue.get(i));
                            notAdded = false; // Use flag to determine if the current process was added
                            j = readyQueue.size(); // Finish the loop
                        }
                    }
                    // IF not added, then make sure to add at the end
                    if(notAdded) {
                        readyQueue.add(waitingQueue.get(i));
                    }

                    // Once added remove from the current queue
                    waitingQueue.remove(i);
                    --i;
                }
            }

            // Get first arrival
            // Is this the very first process? or are we coming back from an idle?
            if(currentPCB.getArrivalTime() == -1) {
                // Is the ready queue not empty?
                if(readyQueue.size() != 0) {
                    currentPCB = readyQueue.get(0);
                    readyQueue.remove(0);
                }
            }

            // If the current process is NOT idle, the code should run and check whether it finished or not

            // Has the process completed it's execution
            if(currentPCB.getBurstTime() == currentPCB.getUsedTime()) {
                // Run calculations
                System.out.println("\nProcess PID " + currentPCB.getPid() + " FINISHED running at time "
                        + running_time + "\n");
                try{
                    Thread.sleep(1000); // Let the process work so user can see what is happening
                } catch (InterruptedException e){}
                currentPCB.setTurnaroundTime(running_time - currentPCB.getArrivalTime());
                currentPCB.setWaitTime(currentPCB.getTurnaroundTime() - currentPCB.getBurstTime());
                finishedQueue.add(currentPCB);

                // Check if we are done
                if(readyQueue.size() == 0 && waitingQueue.size() == 0) {
                    break;
                } else {
                    // Get the next process
                    if(readyQueue.size() != 0) {
                        currentPCB = readyQueue.get(0);
                        readyQueue.remove(0);
                    }
                    // If there are processes waiting but the readyQueue is empty
                    else {
                        currentPCB = idlePCB; // GO idle
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

            try{
                Thread.sleep(1000); // Let the process work so user can see what is happening
            } catch (InterruptedException e){}
        } while(true);

        System.out.println("\nThe Execution has completed!\n");
        System.out.println("The results:");
        // Display results
        float awt = 0;
        float art = 0;
        float att = 0;

        // Find averages
        for(int i = 0; i < finishedQueue.size(); i++) {
            awt += finishedQueue.get(i).getWaitTime();
            att += finishedQueue.get(i).getTurnaroundTime();
        }

        awt /= finishedQueue.size();
        att /= finishedQueue.size();

        System.out.format("Average Wait Time = %.2f milliseconds\n", awt);
        System.out.format("Average Response Time = %.2f milliseconds\n", (att - awt));
        System.out.format("Average Turnaround Time = %.2f milliseconds\n", att);
    }
}