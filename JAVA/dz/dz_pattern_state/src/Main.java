interface VendingMachineState{
    void payDrink(VendingMachine machine);
    void selectDrink(VendingMachine machine);
    void dispenseDrink(VendingMachine machine);
}

class NoPayDrink implements VendingMachineState{

    @Override
    public void payDrink(VendingMachine machine) {
        System.out.println("Напиток оплачен");
        machine.setState(new HasPayDrink());
    }

    @Override
    public void selectDrink(VendingMachine machine) {
        System.out.println("Напиток не оплачен");
    }

    @Override
    public void dispenseDrink(VendingMachine machine) {
        System.out.println("Напиток не оплачен и не выбран");
    }
}

class HasPayDrink implements VendingMachineState{

    @Override
    public void payDrink(VendingMachine machine) {
        System.out.println("Напиток уже оплачен");
    }

    @Override
    public void selectDrink(VendingMachine machine) {
        System.out.println("Напиток выбран");
        machine.setState(new DispensingDrink());
    }

    @Override
    public void dispenseDrink(VendingMachine machine) {
        System.out.println("Сначала выберите напиток");
    }
}

class DispensingDrink implements VendingMachineState{

    @Override
    public void payDrink(VendingMachine machine) {
        System.out.println("Напиток оплачен, идет выдача напитка");

    }

    @Override
    public void selectDrink(VendingMachine machine) {
        System.out.println("Напиток выбран, идет выдача напитка");
    }

    @Override
    public void dispenseDrink(VendingMachine machine) {
        System.out.println("Напиток выдан");
        machine.setState(new NoPayDrink());
    }
}

class VendingMachine{
    private VendingMachineState state;

    public VendingMachine() {
        this.state = new NoPayDrink();
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public void payDrink(){
        state.payDrink(this);
    }

    public void selectDrink(){
        state.selectDrink(this);
    }

    public void dispenseDrink(){
        state.dispenseDrink(this);
    }
}

public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        machine.payDrink();
        machine.selectDrink();
        machine.dispenseDrink();

        machine.selectDrink();
        machine.dispenseDrink();
        machine.payDrink();
    }
}