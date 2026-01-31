
import java.util.*;
 
class process{
    String pid;
    int arrivalTime;
    int completionTime;
    int burstTime;
    int turnAroundTime;
    int waitingTime;
    int responseTime;
    int remainingTime;

    process(String pid,int arrivalTime,int burstTime){
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.responseTime =-1;
    }

}

class pair{
    int time;
    String pid;
    pair(int time,String pid){
        this.time = time;
        this.pid = pid;
    }
}
public class RoundRobin {

    static void roundrobin(process p[],int tq){
        ArrayList<pair> gantt = new ArrayList<>();

        int n = p.length;
        int time =0;
        StringBuilder sb = new StringBuilder("");

        Queue<process> queue = new LinkedList<>();

        int idx =0;
        int completed = 0;


        while(completed < n){
            while(idx<n&&p[idx].arrivalTime<=time){
                queue.add(p[idx]);
                idx++;
            }

            if (queue.isEmpty()) {
                time++;
                continue;
            }
            
                
                process run = queue.poll();
                 if (gantt.isEmpty() || !gantt.get(gantt.size() - 1).pid.equals(run.pid)) {
            gantt.add(new pair(time, run.pid));
        }


                if (run.responseTime == -1) {
                run.responseTime = time - run.arrivalTime;
            }

            int exec = Math.min(run.remainingTime, tq);
        run.remainingTime -= exec;
        time += exec;

        while (idx < n && p[idx].arrivalTime <= time) {
            queue.add(p[idx]);
            idx++;
        }
            
               if (run.remainingTime == 0) {
            run.completionTime = time;
            run.turnAroundTime =run.completionTime -run.arrivalTime;
            run.waitingTime =run.turnAroundTime -run.burstTime;
                completed++;
            } else {
                queue.add(run);
            }

     

        }
         System.out.println("\nGantt Chart:");


    for (pair g : gantt) {
        System.out.print( g.time + "-" + g.pid + "-");
    }
    System.out.print(time);
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
        System.out.println("enter the time quantum");
        int tq = sc.nextInt();
        System.out.println("enter the process  arrivaltime  bursttime");
        for(int i = 0;i<n;i++){
            String pid = sc.next();
            int arrivalTime = sc.nextInt();
            int burstTime = sc.nextInt();
            p[i]=new process(pid, arrivalTime, burstTime);
        }
        Arrays.sort(p,Comparator.comparingInt(a->a.arrivalTime));
        
         roundrobin(p,tq);
         printTable(p);

    }
    
}
