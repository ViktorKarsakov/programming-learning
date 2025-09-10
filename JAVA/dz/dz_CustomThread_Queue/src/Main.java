public class Main {
    public static void main(String[] args) throws InterruptedException {
        BreadQueue breadQueue = new BreadQueue();

        Thread addBread = new Thread(() -> {
           for (int i = 0; i < 10; i++){
               try{
                   breadQueue.addBread(i);
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   System.out.println(e.getMessage());
               }
           }
        });

        Thread removeBread = new Thread(() -> {
           for (int i = 0; i < 10; i++){
               try{
                   breadQueue.removeBread();
               } catch (InterruptedException e){
                   System.out.println(e.getMessage());
               }
           }
        });

        addBread.start();
        removeBread.start();

        addBread.join();
        removeBread.join();



    }
}