public class Main {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();

        Thread thread = new Thread(() -> {
            account.deposit(1000);
        });
        Thread thread2 = new Thread(() -> {
            account.withdraw(500);
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();
    }
}