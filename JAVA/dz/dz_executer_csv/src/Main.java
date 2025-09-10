import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        WriteCSV.writeToCSV("input.csv", 10);
        ReadCSV.readAndCopy("input.csv", "output.csv");
    }
}