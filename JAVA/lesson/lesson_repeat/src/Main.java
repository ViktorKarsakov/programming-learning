import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<String> lines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
            String line;
            while ((line = reader.readLine()) != null){
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Object lock = new Object();

        for(String line : lines){
            executor.submit(() -> {
                synchronized (lock){
                    try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))){
                        String threadName = Thread.currentThread().getName();
                        writer.write("Строка " + line + " обработана потоком " + threadName);
                        writer.newLine();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
        }

        executor.shutdown();
    }
}
