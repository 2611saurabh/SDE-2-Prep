package BehaviouralDesignPattern;

interface ApprovalHandler {

    void setNext(ApprovalHandler next);

    void approve(int amount);
}

abstract class BaseApprovalHandler implements ApprovalHandler {

    protected ApprovalHandler next;

    @Override
    public void setNext(ApprovalHandler next) {
        this.next = next;
    }
}

class Employee extends BaseApprovalHandler {


    @Override
    public void approve(int amount) {

        if (amount <= 1000) {
            System.out.println("Employee approved ₹" + amount);
        } else {
            next.approve(amount);
        }
    }
}

class Manager extends BaseApprovalHandler {


    @Override
    public void approve(int amount) {

        if(amount <= 10000){
            System.out.println("Manager approved " + amount);
        }
        else{
            next.approve(amount);
        }
    }
}

class Director extends BaseApprovalHandler {



    @Override
    public void approve(int amount) {
        if(amount <= 50000){
            System.out.println("Director approved " + amount);
        }
        else{
            next.approve(amount);
        }
    }
}

class CEO extends BaseApprovalHandler {



    @Override
    public void approve(int amount) {

        if (amount > 50000) {
            System.out.println("CEO approved ₹" + amount);
        }
    }
}

public class ChainOfResponseabilityDesignPattern {

    public static void main(String[] args) {
        Employee employee = new Employee();
        Manager manager = new Manager();
        Director director = new Director();
        CEO ceo = new CEO();

        employee.setNext(manager);
        manager.setNext(director);
        director.setNext(ceo);

        employee.approve(100);
    }
}
