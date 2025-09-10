public class BankAccount {
    private int amount;

    public synchronized void deposit(int money){
        if(money >= 0){
            amount += money;
            System.out.println("Вы пополнили на сумму: " + money + ", на счету: " + amount);
        } else {
            System.out.println("Сумма меньше или равна нулю");
        }
    }

    public synchronized void withdraw(int money){
        if(amount >= 0 || money >= 0){
            amount -= money;
            System.out.println("Вы сняли сумму: " + money + ", на счету: " + amount);
        } else {
            System.out.println("Сумма снятия не может быть меньше или равно нулю");
        }
    }

    public int getAmount() {
        return amount;
    }
}
