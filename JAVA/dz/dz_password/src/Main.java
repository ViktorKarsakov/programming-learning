import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) {
        String filename = "password_database.txt"; //Файл с датасетом
        String regex = "^[a-z]{3}\\d[a-z]{2}\\d[a-z]$";
        ExecutorService service = Executors.newFixedThreadPool(4);
        Object lock = new Object();

            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                int lineNumber = 0;

                while ((line = reader.readLine()) != null) {
                    lineNumber++;

                    String finalLine = line;
                    int finalLineNumber = lineNumber;
                    service.submit(() -> {
                        String threads = Thread.currentThread().getName();
                        if (finalLine.matches(regex)) {
                            System.out.println("Поток " + threads + " Найдено совпадение в строке " + finalLineNumber + ": " + finalLine);
                        }
                    });
                }
            } catch (IOException e) {
                System.err.println("Ошибка при чтении файла: " + e.getMessage());
            } finally {
                service.shutdown();
            }

    }
}