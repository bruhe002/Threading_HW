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
//                    System.out.println(waitingQueue.get(i).getPid() + " is currently being added\t" + waitingQueue.size());
                    readyQueue.add(waitingQueue.get(i));
                    // Once added, remove from the waiting queue
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

            // If the current process is NOT idle, the code should run and check whether it finished or not

            // Has the process completed it's execution
            if(currentPCB.getBurstTime() == currentPCB.getUsedTime()) {
                // Run calculations
                System.out.println("\nProcess PID " + currentPCB.getPid() + " FINISHED running at time "
                        + running_time + "\n");
                try{
                    Thread.sleep(1000); // Let the process work so user can see what is happening
                } catch (InterruptedException e){}

                // Set turnaround time
                currentPCB.setTurnaroundTime(running_time - currentPCB.getArrivalTime());

                // Set wait time
                currentPCB.setWaitTime(currentPCB.getTurnaroundTime() - currentPCB.getBurstTime());

                // Add to a finished queue
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
            } else {
                System.out.println("CPU is IDLE at time " + running_time + '\n');
            }

//            System.out.println(running_time);
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
        float cpuUtilRate = 0;

        for(int i = 0; i < finishedQueue.size(); i++) {
            awt += finishedQueue.get(i).getWaitTime();
            att += finishedQueue.get(i).getTurnaroundTime();
            cpuUtilRate += finishedQueue.get(i).getBurstTime();
        }

        awt /= finishedQueue.size();
        att /= finishedQueue.size();

        cpuUtilRate = (cpuUtilRate / running_time) * 100;

        System.out.format("Average Wait Time = %.2f milliseconds\n", awt);
        System.out.format("Average Response Time = %.2f milliseconds\n", (att - awt));
        System.out.format("Average Turnaround Time = %.2f milliseconds\n", att);
        System.out.format("CPU Utilization Rate = %.2f percent\n", cpuUtilRate);

        try {
            Thread.sleep(5000);
        } catch (Exception e) {}
    }
}