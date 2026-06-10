package StrategyDesignPattern;

import org.w3c.dom.ls.LSOutput;

/**
 * PaymentService
 * A checkout system lets a user pay using UPI, Card, or Wallet. The user picks the method at runtime (e.g. selected on screen). When they confirm, the system processes the payment with the chosen method.
 * Requirement that matters: adding a new payment method later (say, NetBanking) must not require editing any existing payment class or the checkout code.
 */
/*
class Payment{

    private double amount;
    private String paymentType;

    Payment(){

    }

    public void selectPayment(String paymentType, double amount){

        if(paymentType.equals("Debit Card")){

            System.out.println("Debited amoutn from debit card" +amount);
        }
        else if(paymentType.equals("Credit Card")){
            System.out.println("Debit amount fromcredit card " + amount);
        }
        else if(paymentType.equals( "UPI")){
            System.out.println("Debit amount form upi " + amount);
        }
        else{
            System.out.println("Payment done by cash on deleviery");
        }
    }
}
public class BruteApproach {

    public static void main(String[] args) {
        double amount = 1200.0;
        String modeOfPayment = "UPI";
        Payment pay = new Payment();

        pay.selectPayment(modeOfPayment,amount);
    }
}
*/


/**
 * Now first create one payment interface with method pay who take type of payment in amount this common for all paymenttype class like upi payment, credit card payment ,netbanking payment
 */

interface Payment{
    public void pay(double amount);
}

/**
 * Now from here we create seprate payment type calss where we implement interface because it have common method for all mode
 *
 *
 */

class PayThroughUPI implements Payment{

    @Override
    public void pay(double amount) {
        System.out.println("Payment done through UPI "+ amount);
    }
}

class PayThroughCreditCard implements Payment{

    @Override
    public void pay(double amount) {
        System.out.println("Payment done through credit card "+ amount);
    }
}

class PayThroughDebitCard implements Payment{

    @Override
    public void pay(double amount) {
        System.out.println("Payment done through Debit Card" + amount);
    }
}

/**
 * so above are type of payment method we create here we override payment method where we pass only amount to deduct but not paymentmode type
 * so how client know to which mode we need to select
 * For that we create on class where we use payment type thing to call that particular class
 */

class CheckOut{

    private Payment paymentMethod; //we invoke payment

    public CheckOut(Payment method){
        this.paymentMethod = method;
    }

    public void confirmPayment(double amount){
        paymentMethod.pay(amount);
    }
}


public class BruteApproach{

    public static void main(String[] args) {
        Payment paymentMethod = new PayThroughCreditCard();
        CheckOut checkOut = new CheckOut(paymentMethod);

        checkOut.confirmPayment(200.98);

    }
}

/*
Ride fare calculator. A ride's fare is computed by a pricing rule: NormalPricing, SurgePricing (multiplies by a surge factor), or PoolPricing (discounts for shared rides). The Ride holds a pricing strategy chosen at runtime. Adding a new pricing rule must not touch Ride or existing rules.
 */