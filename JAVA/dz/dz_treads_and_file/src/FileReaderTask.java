import java.io.BufferedReader;
import java.io.IOException;

public class FileReaderTask implements Runnable{
    private BufferedReader reader;


    public FileReaderTask(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        String line;
        while(true){
            synchronized (reader){
                try{
                    line = reader.readLine();
                    if (line == null){
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(line != null){
                System.out.println(Thread.currentThread().getName() + ": " + line);
            }
        }
    }
}
