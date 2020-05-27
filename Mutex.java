import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Queue;



public class Mutex {
	int OwnerID;
	ArrayList<Process> blockdQueue;
	int value=1;
	public Mutex() {
		blockdQueue=new ArrayList<Process>();
	}
	
    public void semPend(Process P) throws InterruptedException {
    	if(value==1) {
    		OwnerID=P.processID;
    		value=0;
    	//	System.out.println("heree  "+P.processID);
    	}
    	else {
    		P.setProcessState(P, ProcessState.Waiting);
    		blockdQueue.add(P);
    	//	System.out.println("d5lt else");
    		  P.suspended=true;
    		  P.suspend();
    		  
    	}
    	
    }
    
    public void semPost(Process p) {
    //	System.out.println("3mlt sempost");
    	if(p.processID==OwnerID) {
    		
    		if(!blockdQueue.isEmpty()) {
    		 Process P1=blockdQueue.remove(0);
    		// System.out.println("heree  "+p.processID);
    		 P1.setProcessState(P1, ProcessState.Ready);
    			OperatingSystem.ReadyQueue.add(P1);
				//P1.resume();
    			OwnerID=P1.processID;
        		value=0;
        		P1.resume();
    		//	OperatingSystem.scheduling();
    	 
    		}
    		value=1;
    	}
    }

}
