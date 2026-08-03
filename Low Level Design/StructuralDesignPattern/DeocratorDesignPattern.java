package StructuralDesignPattern;

interface Pizza{
    public double cost();
    public  String description();
}


class MargheritaPizza implements Pizza{
    @Override
    public double cost() {
        return 45;
    }

    @Override
    public String description() {
        return "I'm margherita";
    }
}

class VegPizza implements Pizza{
    @Override
    public double cost() {
        return 10;
    }

    @Override
    public String description() {
        return "I'm VegPizza";
    }
}

abstract class PizzaDecorator implements Pizza{

    //instance of pizza on top of the we add items base class is alway piza
    protected Pizza pizza;

    //constrotor when we call PizzaDecorator it create Pizaa base pizza
    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

class CheeseDecorator extends PizzaDecorator{

    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    public double cost() {

        return pizza.cost() + 100;
    }

    @Override
    public String description() {
        return "";
    }
}




public class DeocratorDesignPattern {

    public static void main(String[] args) {

        Pizza pizza = new MargheritaPizza();

        pizza = new CheeseDecorator(pizza);
        System.out.println(pizza.cost());
    }
}
