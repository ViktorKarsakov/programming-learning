public class Main {
    public static void main(String[] args) throws InterruptedException {
        //меин на фотке
        CustomThread thread = new CustomThread();
        CustomThread thread2 = new CustomThread();

        thread.setName("Поток 1");
        thread2.setName("Поток 2");

        thread.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();
    }
}