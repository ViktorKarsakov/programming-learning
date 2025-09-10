import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvWriter {
    public static void writeCsv(String fileName){
        List<Student> student = new ArrayList<>();
        student.add(new Student("Viktor", "Karsakov", "Java41", "26"));
        student.add(new Student("Viktor", "Karsakov", "Java41", "26"));
        student.add(new Student("Viktor", "Karsakov", "Java41", "26"));

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write("FirstName, LastName, Group, Grade \n");
            for (Student s : student){
                writer.write(s.toCsv() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
