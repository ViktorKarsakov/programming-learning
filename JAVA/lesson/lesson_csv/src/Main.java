import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = "input.csv";

        CsvWriter.writeCsv(inputFile);
        System.out.println("Чтение данных их CSV");
        CsvReader.readCsv(inputFile);
    }
}