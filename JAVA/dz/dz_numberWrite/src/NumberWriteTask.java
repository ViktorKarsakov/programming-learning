import java.io.BufferedWriter;
import java.io.IOException;

public class NumberWriteTask implements Runnable{
    private BufferedWriter writer;
    private int[] currentNumber;
    private int maxNumber;

    public NumberWriteTask(BufferedWriter writer, int[] currentNumber) {
        this.writer = writer;
        maxNumber = 100;
        this.currentNumber = currentNumber;
    }

    @Override
    public void run() {
        while (true){
            synchronized (writer){
                try {
                    if(currentNumber[0] > maxNumber){
                        break;
                    }
                    writer.write(String.valueOf(currentNumber[0]));
                    writer.newLine();
                    currentNumber[0]++;
                } catch (IOException e) {
                    e.getMessage();
                    break;
                }


            }
        }
    }
}
