import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WriteCSV {
    private static final String[] NAMES = {"Петя", "Вася", "Катя", "Денис", "Витя", "Леша", "Оля"};

    public static void writeToCSV(String filename, int count) throws IOException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Name,Age,Salary");
            writer.newLine();

            for (int i = 0; i < count; i++) {
                executor.submit(() -> {
                    String name = NAMES[random.nextInt(NAMES.length)];
                    int age = random.nextInt(30 - 18 + 1) + 18;
                    int salary = random.nextInt(150000 - 80000 + 1) + 80000;
                    String line = name + "," + age + "," + salary;
                    synchronized (writer) {
                        try {
                            writer.write(line);
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
                    System.err.println("Не все задачи записи завершены вовремя");
                }
            } catch (InterruptedException e) {
                System.err.println("Прерывание ожидания записи: " + e.getMessage());
            }
        } catch (IOException e) {
            e.getMessage();

        }
    }
}
