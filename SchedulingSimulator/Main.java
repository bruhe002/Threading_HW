import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    // Global Error flag for looping on bad inputs
    static boolean errorFlag = false;

    /*
        runAlgorithm() takes the input from a user in the algorithm selection menu
        and calls the appropriate function linked to the choice
        If chosen RR, extra code to obtain Time Quantum is necessary
    * */
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
                    // Round Robin algorithm called here (Convert q to an integer)
                    int q = Integer.parseInt(q_str);
                    RR.runRR(wl, q);
                } catch (NumberFormatException e) {
                    errorFlag = true;
                    System.out.println("Not a Number! Exiting Code...");
                }
                break;
            case 5:
                errorFlag = false;
        }
    }

    public static void main(String[] args) {
        List<PCB> waitingList = new ArrayList<>();

        // User Interface
        System.out.println("\nWelcome to THE SCHEDULER\n");

        // Ask user for file to read
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter a File name: ");
        String fileName = userInput.nextLine();

        // Read file
        do {
            // Reset errorFlag
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
                // Close the scanner
                myReader.close();
            } catch (FileNotFoundException e) {
                errorFlag = true;
                System.out.println("ERROR: File not found. Please try again!");
            } catch (Exception e) {
                errorFlag = true;
                System.out.println("ERROR: Incorrect File Format. Please review file and try again!");
            }
        } while(errorFlag); // Loop back if an error was thrown

        do {
            // Allow loop to run unless a user enters 5
            errorFlag = true;
            // Display Scheduling Menu
            System.out.println("Please select a Scheduling algorithm: ");
            System.out.println("\t1. First Come First Serve");
            System.out.println("\t2. Shortest Job First");
            System.out.println("\t3. Preemptive Priority Scheduling");
            System.out.println("\t4. Round Robin");
            System.out.println("\t5. Quit")

            // Obtain choice from user
            String choice = userInput.nextLine();
            try {
                // Check if choice is appropriate
                if(!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") ) {
                    // If not throw an exception
                    throw new Exception("Not a valid choice! Please try again");
                }
                else {
                    // Call runAlgorithm to run the choice scheduler
                    runAlgorithm(Integer.parseInt(choice), waitingList);
                }
            } catch (Exception e) {
                errorFlag = true;
                System.out.println(e.getMessage());
            }
        } while(errorFlag); // Loop back if error was thrown

        // Pass the queue into the scheduling algorithm being chosen by the user
//        PPS.runPPS(waitingList);
//        FCFS.runFCFS(waitingList);
//        SJF.runSJF(waitingList);
//        RR.runRR(waitingList, 2);
    }

}