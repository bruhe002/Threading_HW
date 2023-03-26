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
  
 *main* will run two **SortingThread** objects simultaneously which will sort half of the global array provided by the user.
 
 Once those threads are done sorting, a third thread of a **MergingThread** object is ran to combine the the two sorted array into one global sorted array.
 
### To run this program:
 
Simply hit the run option on your IDE, given that your configurations are correct.

*OR*

Compile the program through the terminal running this command:

`javac Main.java`

Then run the program by entering the following command:

`java Main`
 
 Once running, the terminal will welcome and ask the user for an array. Exception handlers are put in place to make sure the array is only of numerical symbols, separated by a single white space. 
 
 NOTE: If the array is given AFTER a white space, this will cause an exception and will ask you to input the array of numbers again.
 
 Once an acceptable array is given, the terminal will begin to output messages regarding the threads that are currently running; a message will be displayed for every sorting loop. This lets the user know the program is running. A `Thread.sleep(1000)` code is used to give the user the ability to see the progress easily.
 
 Once the sorting completes, the terminal will print out the resulting sorted array.
 
 The program will terminate once it's done.
 
 In order to run further testing, rerun the program using the same `java` command.
 
 
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

*Preemptive Priority Scheduling (PPS)* is an algorithm which will sort the processes based on the priority attribute all processes have. Later processes with higher priority can interrupt currently running processes.

*Round Robin (RR)* is an algorithm which will alternate between processes based on a **Time Quantum** variable provided by the user. Once the Time Quantum is fulfilled for one process (or the running process were to finish) a new process will begin.

### PCB Class

The PCB class simulates the structure of an actual PCB data structure used in Operating Systems. The PCB class for this program has several private member variables including: 
  - *pid*: The id of the process
  - *arrival_time*: The arrival Time of the process (measured in milliseconds)
  - *burst_time*: The time the process will run on the cpu (measured in milliseconds)
  - *priority*: The priority of the process, only used when ran through a PPS algorithm

**THESE VARIABLES ARE PROVIDED BY THE INPUT.TXT FILE**

The rest of the variables are used when conducting calculations.
  - *used_time*: the amount of time the process is used in an algorithm
  - *wait_time*: the amount of time the process does not run during an algorithm.
  - *turnaround_time*: the amount of time since the process arrived in the queue, from the total time it completed its burst.

**THESE VARIABLES MUST BE RESET EVERYTIME BEFORE THEY ARE RAN THROUGH ANOTHER ALGORITHM**
 
There are two PCB Constructors, a *default constructor* and an *overloaded constructor*.

The *default constructor* initializes all variables to negative one. Only idle processes are created with the *default constructor*. Idle process are needed whenever there is a process still in the waiting queue but none are in the ready queue. The CPU will still run and check the waiting queue until whenever the running time counter changes. 

### To run this program:

Compile the program through the terminal running this command:

`javac Main.java`

Then run the program by entering the following command:

`java Main`

Once the program begins to run, the user will be greeted and asked to enter the name of the file they wish to read. In this case, the file will always be *input.txt* so for testing, that is what the user has been entering. The file must have the format of an matrix of integers, where each row represents a process, while each column represents the processes' **PID**, **Arrival Time** (milliseconds), **Burst Time**(milliseconds), and **Priority** respectively.

Exception Handlers are put in place to make sure the file and its data are valid. Each row is read and split into an array separated by white space. Only the first four elements of that array are passed into the PCB Constructor. Any other elements found in a row will not be used. Each of the four elements are also checked if they are integers before entered into the array. Any exceptions thrown from the function will be caught and the program will loop back to asking for another file name.

Each row is created into a PCB object and is placed into an array which will be passed to one of the four algorithms. The array is sorted by arrival time once filled.

Once a file is accepted. the program will then display a menu asking the user to enter a number (An exception is used to make sure the input is correct). Input 1 will pass the array into the FCFS alogrithm; Input 2 will pass the array into the SJF algorithm; Input 3 will pass the array into the PPS algorithm; and Input 4 will pass the array into the RR algorithm.

As the algorithm is ran, the user will see a display of which process is running at a certain time. The algorithm will pause for 1 second to display the information. This 1 second represents the one millisecond of a process's burst time, and gives the user time to see the information. Once a process is finished running, a message will display that the process has ended and at what time. 

Once all the processes have finished running, the program will display the Average Wait Time (AWT), Average Turnaround Time (ATT), and Average Response Time (ART = ATT - AWT).

After the algorithm finishes running, the PCB variables are reset (except for those provide by *input.txt*) and the user is prompted to choose another algorithm to run. 

If the user chooses Input 5, the program will terminate.
