import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class WorkingFile {


    public void readFromFile() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String searchLine = scanner.nextLine();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (searchLine.equalsIgnoreCase(line)) {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("newFile.txt"))) {
                        bw.write(line);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Нет такой строки");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}

