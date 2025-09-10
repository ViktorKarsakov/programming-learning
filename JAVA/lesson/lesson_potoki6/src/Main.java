public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread producer = new Thread(() -> {
           for (int i = 0; i <= 10; i++){
               try{
                   buffer.produce(i);
                   Thread.sleep(1000);
               } catch (InterruptedException e){
                   System.out.println(e.getMessage());
               }
           }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i <= 10; i++){
                try{
                    buffer.consume();
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        });

        producer.start();
        consumer.start();

    }
}