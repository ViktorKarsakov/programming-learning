import java.util.Random;
//дз
public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        int[] array = new int[6];
        for(int i = 0; i < array.length; i++){
            array[i] = random.nextInt(10);
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < array.length; i++ ){
                    if(i % 2 == 0){
                        System.out.println(array[i] + array[i + 1]);
                    }
                }
            }
        });
    }
}