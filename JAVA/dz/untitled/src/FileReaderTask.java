import java.io.*;

public class FileReaderTask implements Runnable{
    private BufferedReader reader;
    private String fileName;
    private int THREAD_COUNT = 5;

    public FileReaderTask(String fileName) {
        this.fileName = fileName;
    }

    public void startReader(){
        try{
            reader = new BufferedReader(new FileReader(fileName));
            Thread[] threads = new Thread[THREAD_COUNT];
            for (int i = 0; i < THREAD_COUNT; i++){
                threads[i] = new Thread(this::run);
                threads[i].start();
            }
            for (Thread thread : threads){
                thread.join();
            }
        } catch (FileNotFoundException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        String line;
        while (true){
            synchronized (reader){
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.getMessage();
                    break;
                }
            }
            if(line == null){
                break;
            }
            System.out.println(Thread.currentThread().getName() + ": " + line);
        }
    }
}
