//import java.util.concurrent.Semaphore;


public class Process extends Thread {

    public int processID;
    public ProcessState status = ProcessState.New;
    public boolean terminated=false;
    public boolean suspended;
    public Process(int m) {
        processID = m;
    }

    @Override
    public void run() {

        switch (processID) {
            case 1:
                try {
                    process1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    process2();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    process3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    process4();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                try {
                    process5();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
        }

    }

    private void process1() throws InterruptedException {

        OperatingSystem.PrintOnScreenSemaphore.semPend(this);
        OperatingSystem.TakingInputSemaphore.semPend(this);
        OperatingSystem.ReadingSemaphore.semPend(this);
        OperatingSystem.printText("Enter File Name 1: ");
        OperatingSystem.printText(OperatingSystem.readFile(OperatingSystem.TakeInput()));
       // System.out.println("done");
        OperatingSystem.ReadingSemaphore.semPost(this);

        OperatingSystem.TakingInputSemaphore.semPost(this);
        OperatingSystem.PrintOnScreenSemaphore.semPost(this);

        setProcessState(this, ProcessState.Terminated);
         //System.out.println("terminate");
    }

    private void process2() throws InterruptedException {
        OperatingSystem.PrintOnScreenSemaphore.semPend(this);
        OperatingSystem.TakingInputSemaphore.semPend(this);
        OperatingSystem.WrittingSemaphore.semPend(this);

        OperatingSystem.printText("Enter File Name 2: ");


        String filename = OperatingSystem.TakeInput();

        OperatingSystem.printText("Enter Data: " );

        String data = OperatingSystem.TakeInput();


        OperatingSystem.writefile(filename, data);
        OperatingSystem.WrittingSemaphore.semPost(this);

        OperatingSystem.TakingInputSemaphore.semPost(this);
        OperatingSystem.PrintOnScreenSemaphore.semPost(this);

        setProcessState(this, ProcessState.Terminated);
 
    }

    private void process3() throws InterruptedException {
        int x = 0;
        OperatingSystem.PrintOnScreenSemaphore.semPend(this);
        while (x < 301) { //301
        	 

            OperatingSystem.printText(x + "\n");

            x++;
        }
        OperatingSystem.PrintOnScreenSemaphore.semPost(this);
       
        setProcessState(this, ProcessState.Terminated);
 
//     if(OperatingSystem.index+1<OperatingSystem.ProcessTable2.size())  {
//    		OperatingSystem.scheduling(OperatingSystem.ProcessTable2.get(OperatingSystem.index+1));
//    	 
    //}
        }

    private void process4() throws InterruptedException {

        int x = 500;
        OperatingSystem.PrintOnScreenSemaphore.semPend(this);
       
       // Process s=this;
        while (x < 1001) { //1001

            OperatingSystem.printText(x + "\n");

            x++;
        }
        OperatingSystem.PrintOnScreenSemaphore.semPost(this);
        
        setProcessState(this, ProcessState.Terminated);
     //   OperatingSystem.ProcessTable2.remove(0);
//        if(OperatingSystem.index+1<OperatingSystem.ProcessTable2.size()) 
//    		OperatingSystem.scheduling(OperatingSystem.ProcessTable2.get(OperatingSystem.index+1));
//    
        }
    
    private void process5() throws InterruptedException {
        OperatingSystem.PrintOnScreenSemaphore.semPend(this);
        OperatingSystem.WrittingSemaphore.semPend(this);
        OperatingSystem.TakingInputSemaphore.semPend(this);
        OperatingSystem.printText("Enter LowerBound: ");


        String lower = OperatingSystem.TakeInput();

        OperatingSystem.printText("Enter UpperBound: ");


        String upper = OperatingSystem.TakeInput();

        int lowernbr = Integer.parseInt(lower);
        int uppernbr = Integer.parseInt(upper);
        String data = "";

        while (lowernbr <= uppernbr) {
            data += lowernbr++ + "\n";
        }

        OperatingSystem.writefile("P5.txt", data);

        OperatingSystem.TakingInputSemaphore.semPost(this);
        OperatingSystem.WrittingSemaphore.semPost(this);
        OperatingSystem.PrintOnScreenSemaphore.semPost(this);
        setProcessState(this, ProcessState.Terminated);
      //  OperatingSystem.ProcessTable2.remove(0);
//        if(OperatingSystem.index+1<OperatingSystem.ProcessTable2.size()) 
//    		OperatingSystem.scheduling(OperatingSystem.ProcessTable2.get(OperatingSystem.index+1));
    }

    public static void setProcessState(Process p, ProcessState s) {
        p.status = s;
        if (s == ProcessState.Terminated) {
            //OperatingSystem.ProcessTable.remove(OperatingSystem.ProcessTable.indexOf(p));
        }
    }

    public static ProcessState getProcessState(Process p) {
        return p.status;
    }
}
