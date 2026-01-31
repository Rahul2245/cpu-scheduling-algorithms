

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



public class FCFS{
    static int time =0;
    static void fcfs(Queue<process> queue) {
        int n = queue.size();
        StringBuilder sb =new StringBuilder("");
        process CPUrunning = null;
        while(!queue.isEmpty()){
           process p = queue.peek();

        // CPU idle
        if (time < p.arrivalTime) {
            time++;
            continue;
        }

         // Execute process
        p = queue.poll();
        sb.append(time + "--" + p.pid + "--");

        p.responseTime = time - p.arrivalTime;
        p.waitingTime = p.responseTime;
         time += p.burstTime;

         p.completionTime = time;
         p.turnAroundTime = p.completionTime - p.arrivalTime;

         sb.append(time + " ");



            
        }


System.out.println("Gantt Chart:");
System.out.println(sb);


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

        Arrays.sort(p,Comparator.comparingInt(a->a.arrivalTime));
        Queue<process> queue = new LinkedList<>();
        for(int i=0;i<n;i++){
            queue.add(p[i]);
        }

        fcfs(queue);
        printTable(p);

    }

}