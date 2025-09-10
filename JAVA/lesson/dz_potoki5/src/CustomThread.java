public class CustomThread extends Thread{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " выполняются...");
        super.run();
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " прерван...");
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " завершился...");
    }
}
