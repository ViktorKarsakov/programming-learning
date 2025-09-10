import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"))){
            bw.write("здарова");
            bw.newLine();
            bw.write("С++");
            bw.write("С++");
            bw.write("С++");
            bw.write("С++");
        } catch (IOException e){
            throw new RuntimeException(e);

        }
    }
}