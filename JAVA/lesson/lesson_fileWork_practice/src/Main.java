import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"))){
            bw.write("Необходимо создать текстовый файл. С помощью buffered writer записать данные в файл с возможностью добавления данных");
            bw.newLine();
            bw.write("а также считать данные с помощью методов readline и readlines");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))){
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}