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
//                    // If incoming priority is less than the current Priority
//                    if(waitingQueue.get(i).getPriority() < currentPCB.getPriority()) {
////                        readyQueue.add(0, currentPCB);
////                        currentPCB = waitingQueue.get(i);
//                        readyQueue.add(0, waitingQueue.get(i));
//                    }
//                    // If incoming priority is the same as the current Priority
//                    else if(waitingQueue.get(i).getPriority() == currentPCB.getPriority()) {
//                        readyQueue.add(0, waitingQueue.get(i));
//                    }
//                    // If incoming priority is greater than current priority
//                    else {
                        // Place Process in the correct spot on the ready queue based on priority
                        boolean notAdded = true;
                        for(int j = 0; j < readyQueue.size(); j++) {
                            if(waitingQueue.get(i).getPriority() < readyQueue.get(j).getPriority()) {
                                readyQueue.add(j, waitingQueue.get(i));
                                notAdded = false; // Use flag to determine if the current process was added
                                j = readyQueue.size(); // Break out of the loop
                            }
                        }

                        // IF not added, then make sure to add at the end
                        if(notAdded) {
                            readyQueue.add(waitingQueue.get(i));
                        }
                    //}

                    // Once added remove from the current queue
                    waitingQueue.remove(i);
                    i--;
                }
            }

            // Get first arrival
            // Check if the ready queue is full
            // Is this the very first process? or are we coming back from an idle?
            if(currentPCB.getArrivalTime() == -1) {
                // Is the ready queue not empty?
                if(readyQueue.size() != 0) {
                    currentPCB = readyQueue.get(0);
                    readyQueue.remove(0);
                }
            }

            // Has the process completed it's execution
            if(currentPCB.getBurstTime() == currentPCB.getUsedTime()) {
                // Run calculations
                System.out.println("\nProcess PID " + currentPCB.getPid() + " FINISHED running at time " +
                        running_time + "\n");
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
            // If the process has NOT finished, check if the process in the ready queue has a higher priority
            else {
                // check if the ready queue is not empty
                if(readyQueue.size() != 0) {
                    // If it is, cause an interrupt of the current process
                    if(readyQueue.get(0).getPriority() < currentPCB.getPriority()) {
                        // NOT safe to assume the next process has a lower priority
                        System.out.println("--------------------------------------------");
                        // Need to check for only one element to maintain logic consistency
                        if(readyQueue.size() == 1) {
                            readyQueue.add(currentPCB);
                        } else {
                            for(int i = 1; i < readyQueue.size(); i++) {
                                if (readyQueue.get(i).getPriority() > currentPCB.getPriority()) {
                                    readyQueue.add(i, currentPCB);
                                    i = readyQueue.size();
                                }
                            }
                        }

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

        for(int i = 0; i < finishedQueue.size(); i++) {
            awt += finishedQueue.get(i).getWaitTime();
            att += finishedQueue.get(i).getTurnaroundTime();
        }

        awt /= finishedQueue.size();
        att /= finishedQueue.size();

        System.out.format("Average Wait Time = %.2f milliseconds\n", awt);
        System.out.format("Average Response Time = %.2f milliseconds\n", (att - awt));
        System.out.format("Average Turnaround Time = %.2f milliseconds\n", att);

        try {
            Thread.sleep(5000);
        } catch (Exception e) {}
    }
}