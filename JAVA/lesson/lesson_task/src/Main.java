import java.io.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {

        String inputFile = "input.txt";
        Random random = new Random();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        int[] arr = new int[20];

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, true))){

            for (int i = 0; i < arr.length; i++){
                arr[i] = random.nextInt(100) + 1;
                writer.write(String.valueOf(arr[i]));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Object lock = new Object();

        for (int n : arr) {
            executor.submit(() -> {
                synchronized (lock) {

                    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                        String threadName = Thread.currentThread().getName();
                        while ((n = reader.readLine()) != null) {
                            System.out.println(threadName + ": " + n);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            });
        }
        executor.shutdown();
    }
}