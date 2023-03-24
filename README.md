# Threading_HW
In this folder, you will find two more folders.
Each one contains the code and classes for the different parts of the hw
  - **MultiThreadSorting** contains the code for Part One of the homework
  - **SchedulingSimulator** contains the code for Part Two of the homework
  
## MultiThreadSorting
This folder contains three Java Files necessary to run the program
  - Main.java
  - SortingThread.java
  - MerginThread.java


 **MergingThread** and **SortingThread** are classes called by the *main* Function
 
 
 *main* will run two **SortingThread** objects simultaneously which will sort half of the global array provided in the code.
 
 
 Once those threads are done sorting, a third thread of a **MergingThread** object is ran to combine the the two sorted array into one global sorted array.
 
 
 
 
### To run this program:
 
Simply hit the run option on your IDE, given that your configurations are correct.
*OR*
Run the program on the command line by typing the following command:
`java Main`
 
 
 
 *NOTE*
 
 If you change the global array to run different tests, make sure you recompile before you run, using the command:
 `javac Main.java`
 
 Once running, the terminal will output messages regarding the threads that are currently running, and once they complete, will print out the resulting sorted array.
 
 
 ## SchedulingSimulator
 This folder contains six Java Files necessary to run the program
  - Main.java
  - PCB.java
  - FCFS.java (First Come First Serve)
  - SJF.java (Shortest Job First)
  - PPS.java (Preemptive Priority Scheduling)
  - RR.java (Round Robin)

The Four Algorithms (FCFS, SJF, PPS, RR) are java classes with one function: running the algorithm.


This program simulates the scheduling of processes within an Operating System, given a specific algorithm which decides the order that process will be ran.

### Algorithms

*First Come First Serve (FCFS)* is an algorithm which will run the processes by the order of which it arrives

*Shortest Job First (SJF)* is an algorithm which will run the processes based on the order of processes with the shortest burst time

*Preemptive Priority Scheduling (PPS)* is an algorithm which will the processes based on the priority attribute all processes have. Later processes with higher priority can interrupt currently running processes.

*Round Robin (RR)* is an algorithm which will alternate between processes based on a **Time Quantum** variable provided by the user. Once the Time Quantum is fulfilled for one process a new process will begin.


