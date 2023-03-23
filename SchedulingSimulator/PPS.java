import java.util.*;

public class PPS {
    public static void runPPS(List<PCB> waitingQueue) {
        int running_time = 0;
        List<PCB> readyQueue = new ArrayList<>();
        List<PCB> finishedQueue = new ArrayList<>();
        PCB idlePCB = new PCB();
        PCB currentPCB = idlePCB;
        do {
            // Check for arrivals
            for(int i = 0; i < waitingQueue.size(); i++) {
                if(waitingQueue.get(i).getArrivalTime() == running_time) {
                    // Check Priority
                    // If incoming priority is less than the current Priority
                    if(waitingQueue.get(i).getPriority() < currentPCB.getPriority()) {
                        //readyQueue.add(currentPCB);
                        readyQueue.add(0, waitingQueue.get(i));
                    }
                    // If incoming priority is the same as the current Priority
                    else if(waitingQueue.get(i).getPriority() == currentPCB.getPriority()) {
                        readyQueue.add(0, waitingQueue.get(i));
                    }
                    // If incoming priority is greater than current priority
                    else {
                        // Place Process in the correct spot on the ready queue based on priority
                        boolean notAdded = true;
                        for(int j = 0; j < readyQueue.size(); j++) {
                            if(waitingQueue.get(i).getPriority() < readyQueue.get(j).getPriority()) {
                                readyQueue.add(j, waitingQueue.get(i));
                                notAdded = false; // Use flag to determine if the current process was added
                                j++;
                            }
                        }

                        // IF not added, then make sure to add at the end
                        if(notAdded) {
                            readyQueue.add(waitingQueue.get(i));
                        }
                    }

                    // Once added remove from the current queue
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
                    // Is priority lower than the next element?
                    else if(currentPCB.getPriority() > readyQueue.get(0).getPriority()) {
                        readyQueue.add(currentPCB);
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