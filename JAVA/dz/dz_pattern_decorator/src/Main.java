interface Coffee{
    String structure();
    double cost();
}

class SimpleCoffee implements Coffee{

    @Override
    public String structure() {
        return "Простой кофе";
    }

    @Override
    public double cost() {
        return 150;
    }
}

class SyrupDecorator implements Coffee{
    private Coffee coffee;

public SyrupDecorator(Coffee coffee){
    this.coffee = coffee;
}

    @Override
    public String structure() {
        return coffee.structure() + " + сироп";
    }

    @Override
    public double cost() {
        return coffee.cost() + 100;
    }
}

class MilkDecorator implements Coffee{
    private Coffee coffee;

public MilkDecorator(Coffee coffee){
    this.coffee = coffee;
}

    @Override
    public String structure() {
        return coffee.structure() + " + молоко ";
    }

    @Override
    public double cost() {
        return coffee.cost() + 50;
    }
}

public class Main {
    public static void main(String[] args) {

        Coffee simpleCoffee = new SimpleCoffee();
        Coffee syrupCoffee = new SyrupDecorator(simpleCoffee);
        Coffee milkCoffee = new MilkDecorator(syrupCoffee);

        System.out.println(milkCoffee.structure() + milkCoffee.cost() + " руб.");

    }
}