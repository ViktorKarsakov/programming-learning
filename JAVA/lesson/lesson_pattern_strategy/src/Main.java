interface PaymentStrategy {
    void pay(int amount);
}

// Оплата кредитной картой
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Оплачено " + amount + " руб. с помощью кредитной карты " + cardNumber);
    }
}

// Оплата через PayPal
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Оплачено " + amount + " руб. через PayPal " + email);
    }
}

// Оплата криптовалютой
class CryptoPayment implements PaymentStrategy {
    private String walletAddress;

    public CryptoPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Оплачено " + amount + " руб. криптовалютой на кошелек " + walletAddress);
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        if (paymentStrategy != null) {
            paymentStrategy.pay(amount);
        } else {
            System.out.println("Способ оплаты не выбран.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Выбираем стратегию оплаты
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        cart.checkout(1000); // Оплата кредитной картой

        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(500); // Оплата через PayPal

        cart.setPaymentStrategy(new CryptoPayment("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"));
        cart.checkout(200); // Оплата криптовалютой
    }
}