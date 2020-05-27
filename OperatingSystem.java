import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class OperatingSystem {

    public static ArrayList<Thread> ProcessTable;
    public static ArrayList<Process> ReadyQueue = new ArrayList<>();
    public static Mutex WrittingSemaphore = new Mutex();
    public static Mutex TakingInputSemaphore = new Mutex();
    public static Mutex PrintOnScreenSemaphore = new Mutex();
    public static Mutex ReadingSemaphore = new Mutex();

    //system calls:
    // 1- Read from File
    @SuppressWarnings("unused")

    public static String readFile(String name) {
        //	System.out.println("readfile ");
        String Data = "";
        File file = new File(name);
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                Data += scan.nextLine() + "\n";
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return Data;
    }

    // 2- Write into file
    @SuppressWarnings("unused")
    public static void writefile(String name, String data) {
        try {
            BufferedWriter BW = new BufferedWriter(new FileWriter(name));
            BW.write(data);
            BW.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    //3- print to console
    @SuppressWarnings("unused")
    public static void printText(String text) {
        //System.out.println("printing text");
        System.out.println(text);

    }

    public static void scheduling() {
        while (!ReadyQueue.isEmpty()) {
            Process P = ReadyQueue.get(0);
            P.setProcessState(P, ProcessState.Running);
            if (P.suspended == true) {
                P.resume();
            } else {
                P.start();
            }
            while (P.isAlive()) {
                //		System.out.println("ProcessId: "+P.processID+" status: "+P.status);
            }
            // System.out.println("lalala");
            ReadyQueue.remove(0);
        }
    }


    @SuppressWarnings("unused")
    public static String TakeInput() throws InterruptedException {
        //System.out.println("take input");
        Scanner in = new Scanner(System.in);
        String data = in.nextLine();
        return data;

    }

    private static void createProcess(int processID) {
        Process p = new Process(processID);
        ProcessTable.add(p);
        Process.setProcessState(p, ProcessState.Ready);
       // ReadyQueue.add(p);
		p.start();
    }

    public static void main(String[] args) throws InterruptedException {
        ProcessTable = new ArrayList<Thread>();

        createProcess(4);
        createProcess(2);
        createProcess(3);
      //  Thread.sleep(1000);
     
        createProcess(5);
        createProcess(1);
        //scheduling();


    }
}



