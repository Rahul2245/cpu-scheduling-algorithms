import java.util.*;

class process {
   String pid;
   int arrivalTime;
   int completionTime;
   int burstTime;
   int turnAroundTime;
   int waitingTime;
   int responseTime;
   int priority;

   process(String var1, int var2, int var3, int var4) {
      this.pid = var1;
      this.arrivalTime = var2;
      this.burstTime = var3;
      this.priority = var4;
   }
}


public class Priority{
   


   static void priority(process p[]){
      
      int n =p.length;
      PriorityQueue<process> pq = new PriorityQueue<>(
       (a,b)->{
           return a.priority-b.priority;
         }
      );
      int time =0;
      int idx =0;
      process cpu =null;
      StringBuilder sb = new StringBuilder("");
      sb.append(time);
      while(idx<n||pq.size()>0){
         while(idx<n&&time>=p[idx].arrivalTime){
            pq.add(p[idx]);
            idx++;
         }
         if(pq.size()==0){
            time++;
            continue;
         }
         cpu = pq.poll();
         sb.append(time+"--"+cpu.pid+"--");
         cpu.responseTime = time - cpu.arrivalTime;
        cpu.waitingTime = cpu.responseTime;
         time += cpu.burstTime;

         cpu.completionTime = time;
         cpu.turnAroundTime = cpu.completionTime - cpu.arrivalTime;
        
         sb.append(time + " ");

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
            pr.waitingTime 
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
            int priority = sc.nextInt();
            p[i]=new process(pid, arrivalTime, burstTime, priority);
        }

        Arrays.sort(p,Comparator.comparingInt(a->a.arrivalTime));
      
        priority(p);
        
        printTable(p);

    }
}
