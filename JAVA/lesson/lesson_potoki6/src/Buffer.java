import java.util.LinkedList;
import java.util.Queue;

public class Buffer{
    private final Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 5;

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == LIMIT){
            wait();
        }
        queue.offer(value);
        System.out.println("Производитель добавил " + value);
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while(queue.isEmpty()){
            wait();
        }
        int value = queue.poll();
        System.out.println("Производитель забрал " + value);
        notify();
    }
}
