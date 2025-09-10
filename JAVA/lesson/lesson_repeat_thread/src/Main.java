public class Main {
    private static int a = 0;
    public static void main(String args[]) {
        Thread thread1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (Main.class) {
                    a++;
                    Main.class.notify(); // Уведомляем второй поток
                }
                try {
                    Thread.sleep(500);
                } catch(InterruptedException e) {
                    System.out.println("Поток 1 прерван");
                    Thread.currentThread().interrupt();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (Main.class) {
                    try {
                        Main.class.wait(); // Ждем уведомления
                        System.out.println(a);
                    } catch(InterruptedException e) {
                        System.out.println("Поток 2 прерван");
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
// Остановка через 5 секунд
        try {
            Thread.sleep(5000);
            thread1.interrupt();
            thread2.interrupt();
            synchronized (Main.class) {
                Main.class.notifyAll(); // Разбудить все потоки перед завершением
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}