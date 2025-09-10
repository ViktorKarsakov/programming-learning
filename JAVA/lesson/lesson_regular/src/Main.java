import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        String filename = "password_database.txt";
        String regex = "^\\(\\?i\\)Aa1\\(\\?-i\\)Bb2\\(\\?m\\)\\^Cc3\\$";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.matches(regex)) {
                    System.out.println("Найдено совпадение в строке " + lineNumber + ": " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}