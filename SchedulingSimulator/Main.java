import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        List<PCB> waitingList = new ArrayList<>();
        // Read file
        try {
            File myFile = new File("input.txt");
            Scanner myReader = new Scanner(myFile);

            // Take each line of data and make a PCB object
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfData = data.split(" ", 4);
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


        // Input each Object into a PCB waiting queue


        // Pass the queue into the scheduling algorithm being chosen by the user
//        PPS.runPPS(waitingList);
//        FCFS.runFCFS(waitingList);
        SJF.runSJF(waitingList);
    }
}