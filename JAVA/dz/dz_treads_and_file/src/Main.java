import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try(BufferedReader reader = new BufferedReader(new FileReader("input.txt"))){

            int threadCount = 5;
            Thread[] threads = new Thread[threadCount];

            for (int i = 0; i < threadCount; i++){
                FileReaderTask task = new FileReaderTask(reader);
                threads[i] = new Thread(task);
            }
            for(int i = 0; i < threadCount; i++){
                threads[i].start();
            }
            for (int i = 0; i < threadCount; i++){
                threads[i].join();
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}