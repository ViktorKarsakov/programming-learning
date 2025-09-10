import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ReadCSV {
    public static void readAndCopy(String inputFile, String outputFile) throws FileNotFoundException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){
                String line;
                while ((line = reader.readLine()) != null){
                    String finalLine = line;
                    executor.submit(() -> {
                       synchronized (writer){
                           try {
                               writer.write(finalLine);
                               writer.newLine();
                           } catch (IOException e) {
                               throw new RuntimeException(e);
                           }
                       }
                    });
                }
            executor.shutdown();
            try {
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.err.println("Не все задачи чтения завершены вовремя");
                }
            } catch (InterruptedException e) {
                System.err.println("Прерывание ожидания чтения: " + e.getMessage());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
