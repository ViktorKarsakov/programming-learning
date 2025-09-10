import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("number.txt"))){
            int[] currentNumber = {1};
            int threadCount = 3;
            Thread[] threads = new Thread[threadCount];
            for(int i = 0; i < threadCount; i++){
                NumberWriteTask task = new NumberWriteTask(writer, currentNumber);
                threads[i] = new Thread(task);
            }
            for (int i = 0; i < threadCount; i++){
                threads[i].start();
            }
            for (int i = 0; i < threadCount; i++){
                threads[i].join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}