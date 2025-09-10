interface DeliveryStrategy{
    void deliver(String address);
}

class CourierDelivery implements DeliveryStrategy{

    @Override
    public void deliver(String address) {
        if(address == null){
            throw new IllegalArgumentException("Адрес не может быть пустым");
        }
        System.out.println("Доставка курьером на адрес: " + address);
    }
}

class PostDelivery implements DeliveryStrategy{
    private String index;

    public PostDelivery(String index) {
        this.index = index;
    }

    @Override
    public void deliver(String address) {
        if(address == null){
            throw new IllegalArgumentException("Адрес не может быть пустым");
        }
        System.out.println("Доставка почтой на адрес: " + address + ", индекс: " + index);
    }
}

class PickupDelivery implements DeliveryStrategy{
    private String pickupPoint;

    public PickupDelivery(String pickupPoint){
        if(pickupPoint == null){
            throw new IllegalArgumentException("Пукт самовывоза не может быть пустым");
        }
        this.pickupPoint = pickupPoint;
    }
    @Override
    public void deliver(String address) {
        System.out.println("Самовывоз из пукта: " + pickupPoint + " (адрес клиента: " + address + ")");
    }
}

class Order{
    private DeliveryStrategy deliveryStrategy;
    private String address;

    public Order(DeliveryStrategy deliveryStrategy, String address){
        if(address == null){
            throw new IllegalArgumentException("Адрес не может быть пустым");
        }
        if(deliveryStrategy == null){
            throw new IllegalArgumentException("Способ доставки не может быть пустным");
        }
        this.address = address;
        this.deliveryStrategy = deliveryStrategy;
    }

    public void setDeliveryStrategy(DeliveryStrategy deliveryStrategy) {
        if(deliveryStrategy == null){
            throw new IllegalArgumentException("Способ доставки не может быть пустым");
        }
        this.deliveryStrategy = deliveryStrategy;
    }

    public void deliver(){
        deliveryStrategy.deliver(address);
    }
}

public class Main {
    public static void main(String[] args) {
        try{
            Order order = new Order(new CourierDelivery(), "Мира 1");
            order.deliver();

            order.setDeliveryStrategy(new PickupDelivery("ТРЦ Планета"));
            order.deliver();

            order.setDeliveryStrategy(new PostDelivery("660099"));
            order.deliver();

        } catch (IllegalArgumentException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}