import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {
    public static void readCsv(String fileName) throws FileNotFoundException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null){
                Student student = Student.fromCsv(line);
                System.out.println(student + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
