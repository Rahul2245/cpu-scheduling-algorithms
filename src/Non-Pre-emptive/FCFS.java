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
            if(CPUrunning==null&&queue.peek().arrivalTime<=time){
                CPUrunning = queue.poll();
                sb.append(time+"--"+CPUrunning.pid+"--");
                time += CPUrunning.burstTime;
                if(queue.isEmpty()){
                sb.append(time);
            }
                CPUrunning=null;
                
                continue;

                

            }else{
                time++;
            }
            
            




            
        }



System.out.println(sb);

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

    }

}