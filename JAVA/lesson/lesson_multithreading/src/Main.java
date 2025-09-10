import javax.swing.plaf.TableHeaderUI;

public class Main {
    public static void main(String[] args) {
/*

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 10; i++){
                    if(i % 2 == 0){
                        System.out.println("Четное " + i);
                        try{
                            Thread.sleep(1000);
                        } catch (InterruptedException e){
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });



        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 10; i++){
                    if(i % 2 != 0){
                        System.out.println("Нечетное " + i);
                    }
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }
            }


        });

        thread.start();
        thread2.start();*/

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= 10; i++){
                    System.out.println(5 * i);
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= 10; i++){
                    System.out.println(5 * i);
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        thread.start();
        thread2.start();
    }
}