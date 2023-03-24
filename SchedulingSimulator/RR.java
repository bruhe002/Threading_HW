import java.util.*;

public class RR {
    public static void runRR(List<PCB> waitingQueue, int timeQuantum) {
        int running_time = 0;
        int time_quantum_counter = timeQuantum;
        List<PCB> readyQueue = new ArrayList<>();
        List<PCB> finishedQueue = new ArrayList<>();
        PCB idlePCB = new PCB();
        PCB currentPCB = idlePCB;
        do {
            // Check for arrivals
            for(int i = 0; i < waitingQueue.size(); i++) {
                if(waitingQueue.get(i).getArrivalTime() == running_time) {
                    // Check Priority
                    readyQueue.add(waitingQueue.get(i));

                    // Once added remove from the current queue
                    waitingQueue.remove(i);
                    i--;
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

            // Has the process completed it's execution
            if(currentPCB.getBurstTime() == currentPCB.getUsedTime()) {
                // Run calculations
                System.out.println("\nProcess PID " + currentPCB.getPid() +
                        " FINISHED running at time " + running_time);
                currentPCB.setTurnaroundTime(running_time - currentPCB.getArrivalTime());
                currentPCB.setWaitTime(currentPCB.getTurnaroundTime() - currentPCB.getBurstTime());
                finishedQueue.add(currentPCB);

                // Check if we are done
                if(readyQueue.size() == 0 && waitingQueue.size() == 0) {
                    break;
                } else {
                    // Get the next process
                    if(readyQueue.size() != 0) {
//                        System.out.println(currentPCB.getPid() + "\tchanges to\t" + readyQueue.get(0).getPid());
                        currentPCB = readyQueue.get(0);
                        readyQueue.remove(0);
                    }
                    // If there are processes waiting but the readyQueue is empty
                    else {
                        currentPCB = idlePCB; // GO idle
                    }

                    // Let user know a time quantum shift occured
                    System.out.println("------------------------------------------");
                    // Reset the time quantum
                    time_quantum_counter = timeQuantum;
                }
            }
            // If the process has NOT finished,
            else {
                // Check if the time_quantum_counter is 0
                if(time_quantum_counter == 0) {
                    // If yes, Add next process from ready queue
                    if(readyQueue.size() != 0) {
                        readyQueue.add(currentPCB);
                        currentPCB = readyQueue.get(0);
                        readyQueue.remove(0);
                    }

                    // Let user know a time quantum shift occured
                    System.out.println("------------------------------------------");
                    // Reset time_quantum_counter
                    time_quantum_counter = timeQuantum;
                }

            }

            // Run the process if not idle
            if(currentPCB.getPid() != -1) {
                currentPCB.incUsedTime();
                System.out.println("Process PID " + currentPCB.getPid() + " is CURRENTLY running at time " + running_time);
            }

            // Increment running time
            ++running_time;

            // Decrement Quantum counter
            --time_quantum_counter;


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

        System.out.format("Average Wait Time = %.2f\n", awt);
        System.out.format("Average Response Time = %.2f\n", (att - awt));
        System.out.format("Average Turnaround Time = %.2f\n", att);
    }
}