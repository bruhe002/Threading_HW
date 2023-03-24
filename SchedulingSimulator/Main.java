import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    static boolean errorFlag = false;

    private static void runAlgorithm(int n, List<PCB> wl) {
        switch (n) {
            case 1:
                FCFS.runFCFS(wl);
                break;
            case 2:
                SJF.runSJF(wl);
                break;
            case 3:
                PPS.runPPS(wl);
                break;
            case 4:
                // Ask for time quantum
                Scanner rrScan = new Scanner(System.in);
                System.out.println("Please input Time Quantum!");
                String q_str = rrScan.nextLine();
                try {
                    int q = Integer.parseInt(q_str);
                    RR.runRR(wl, q);
                } catch (NumberFormatException e) {
                    errorFlag = true;
                    System.out.println("Not a Number! Exiting Code...");
                }
                // Round Robin algorithm called here (Convert q to an integer)
        }
    }

    public static void main(String[] args) {
        List<PCB> waitingList = new ArrayList<>();

        // User Interface
        System.out.println("Welcome to THE SCHEDULER\n");
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter a File name: ");
        String fileName = userInput.nextLine();

        // Read file
        do {
            errorFlag = false;
            try {
                File myFile = new File(fileName);
                Scanner myReader = new Scanner(myFile);

                // Take each line of data and make a PCB object
                while(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] arrOfData = data.split(" ", 4);

                    // Input each Object into a PCB waiting queue
                    waitingList.add(new PCB(Integer.parseInt(arrOfData[0]), Integer.parseInt(arrOfData[1]),
                            Integer.parseInt(arrOfData[2]), Integer.parseInt(arrOfData[3])));

                }
                // Sort the list based on arrival time
                Collections.sort(waitingList);
                for(int i = 0; i < waitingList.size(); i++) {
                    System.out.println("Process" + waitingList.get(i).getPid());
                }

                myReader.close();
            } catch (FileNotFoundException e) {
                errorFlag = true;
                System.out.println("ERROR: File not found. Please try again!");
            }
        } while(errorFlag);

        do {
            errorFlag = false;
            System.out.println("Please select a Scheduling algorithm: ");
            System.out.println("\t1. First Come First Serve");
            System.out.println("\t2. Shortest Job First");
            System.out.println("\t3. Preemptive Priority Scheduling");
            System.out.println("\t4. Round Robin");

            String choice = userInput.nextLine();
            try {
                if(choice.equals("1") && choice.equals("2") && choice.equals("3") && choice.equals("4") ) {
                    System.out.println("My choice is " + choice);
                    throw new Exception("Not a valid choice! Please try again");
                }
                else {
                    runAlgorithm(Integer.parseInt(choice), waitingList);
                }
            } catch (Exception e) {
                errorFlag = true;
                System.out.println(e.getMessage());
            }
        } while(errorFlag);

        // Pass the queue into the scheduling algorithm being chosen by the user
//        PPS.runPPS(waitingList);
//        FCFS.runFCFS(waitingList);
//        SJF.runSJF(waitingList);
//        RR.runRR(waitingList, 2);
    }

}