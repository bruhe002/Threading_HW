import java.util.*

public class PPS {
    public void runPPS(Queue<PCB> waitingQueue) {
        int running_time = 0;
        Queue<PCB> readyQueue = new LinkedList<>();

        do {
            // Check for arrivals
            Iterator iter = waitingQueue.iterator()
            while(iter.hasNext()) {
                PCB temp = iter.next();
                if(temp.arrival_time == running_time) {
                    readyQueue.add(iter.next(temp));
                }
            }


        } while(true);
    }
}