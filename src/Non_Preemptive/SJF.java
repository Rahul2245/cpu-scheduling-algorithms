
import java.util.*;

class process{
    String pid;
    int arrivalTime;
    int completionTime;
    int burstTime;
    int turnAroundTime;
    int waitingTime;
    int responseTime;

    process(String pid,int arrivalTime,int burstTime){
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

}


public class SJF {



    static void sjf(List<process> list){
        int time = 0;
        StringBuilder sb = new StringBuilder("");
        process run = null;
        while(list.size()!=0){
            int idx=-1;
            for(int i=0;i<list.size();i++){
                if(list.get(i).arrivalTime<=time){
                    if(run!=null&&list.get(i).burstTime<run.burstTime){
                        run = list.get(i);
                        idx=i;
                    }else if(run==null){
                        run = list.get(i);
                        idx=i;


                    }
                }
            }
            if(idx==-1){
                time++;
                continue;
            }
            sb.append(time+"--"+run.pid+"--");
            list.remove(idx);
            run.responseTime = time - run.arrivalTime;
        run.waitingTime = run.responseTime;
         time += run.burstTime;

         run.completionTime = time;
         run.turnAroundTime = run.completionTime - run.arrivalTime;
            
            sb.append(time + " ");
            run =null;

            


        }


        
    }
    
    




     static void printTable(process[] p) {

    System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT\tRT");
    System.out.println("------------------------------------------------");

    for (process pr : p) {
        System.out.println(
            pr.pid + "\t" +
            pr.arrivalTime + "\t" +
            pr.burstTime + "\t" +
            pr.completionTime + "\t" +
            pr.turnAroundTime + "\t" +
            pr.waitingTime + "\t" +
            pr.responseTime
        );
    }
}

public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the no of prrocesses");
        int n =sc.nextInt();
        process p[] = new process[n];
        System.out.println("enter the process  arrivaltime  bursttime");
        for(int i = 0;i<n;i++){
            String pid = sc.next();
            int arrivalTime = sc.nextInt();
            int burstTime = sc.nextInt();
            p[i]=new process(pid, arrivalTime, burstTime);
        }
        List<process> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(p[i]);
        }
sjf(list);
      
        printTable(p);

    }
}
