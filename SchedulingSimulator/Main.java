import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    private void runAlgorithm(int n, List<PCB> wl) {
        switch (n) {
            case 1:
                FCFS.runFCFS(wl);
            case 2:
                SJF.runSJF(wl);
            case 3:
                PPS.runPPS(wl);
            case 4:
                // Ask for time quantum
                Scanner rrScan = new Scanner(System.in);
                System.out.println("Please input Time Quantum!");
                String q = rrScan.nextLine();

                // Round Robin algorithm called here (Convert q to an integer)
        }
    }

    public static void main(String[] args) {
        List<PCB> waitingList = new ArrayList<>();

        // User Interface
//        System.out.println("Welcome to THE SCHEDULER\n");
//        Scanner userInput = new Scanner(System.in);
//        System.out.println("Please enter a File name: ");
//        String fileName = userInput.nextLine();

        // Read file
        try {
            File myFile = new File("input.txt");
            Scanner myReader = new Scanner(myFile);

            // Take each line of data and make a PCB object
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfData = data.split(" ", 4);

                // Input each Object into a PCB waiting queue
                waitingList.add(new PCB(Integer.parseInt(arrOfData[0]), Integer.parseInt(arrOfData[1]),
                        Integer.parseInt(arrOfData[2]), Integer.parseInt(arrOfData[3])));

            }
            Collections.sort(waitingList);
//            System.out.println(waitingList);
//            Iterator iter = waitingList.iterator();
//            for(int i = 0; i < waitingList.size(); i++) {
//                System.out.println("PID: " + waitingList.get(i).getPid() + "\tARRIVAL TIME: " + waitingList.get(i).getArrivalTime());
//            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found. Please try again!");
        }

//        System.out.println("Please select a Scheduling algorithm: ");
//        System.out.println("\t1. First Come First Serve");
//        System.out.println("\t2. Shortest Job First");
//        System.out.println("\t3. Preemptive Priority Scheduling");
//        System.out.println("\t4. Round Robin");
//
//        String choice = userInput.nextLine();
//        try {
//            if(choice != "1" && choice != "2" && choice != "3" && choice != "4" ) {
//                throw new Exception("Not a valid choice! Please try again");
//            }
//            else {
//                runAlgorithm(Integer.parseInt(choice), waitingList);
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }



        // Pass the queue into the scheduling algorithm being chosen by the user
        // PPS.runPPS(waitingList);
//        FCFS.runFCFS(waitingList);
//        SJF.runSJF(waitingList);
        RR.runRR(waitingList, 2);
    }

}