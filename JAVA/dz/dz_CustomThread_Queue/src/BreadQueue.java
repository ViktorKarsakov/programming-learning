import java.util.LinkedList;
import java.util.Queue;

public class BreadQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int maxSize = 5;

    public synchronized void addBread(int bread) throws InterruptedException {
        while (queue.size() == maxSize){
            wait();
        }
        queue.offer(bread);
        System.out.println("Пекарня дробавила " + bread + " хлеба");
        notify();
    }

    public synchronized void removeBread() throws InterruptedException {
        while (queue.isEmpty()){
            wait();
        }
        int bread = queue.poll();
        System.out.println("покупатель купил " + bread + " хлеба");
        notify();

    }
}
