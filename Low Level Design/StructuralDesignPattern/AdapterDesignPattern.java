package StructuralDesignPattern;

// Target Interface (Client knows only this)
interface PaymentGateway {
    void pay(double amount);
}

// ===============================
// Third-Party SDKs (Cannot Modify)
// ===============================

class RazorPay {

    public void makePayment(double amount) {
        System.out.println("RazorPay Payment: ₹" + amount);
    }
}

class Stripe {

    public void sendMoney(double amount) {
        System.out.println("Stripe Payment: ₹" + amount);
    }
}

class UPI {

    public void transferMoney(double amount) {
        System.out.println("UPI Payment: ₹" + amount);
    }
}

// =====================================
// Adapter for RazorPay
// =====================================

class RazorPayAdapter implements PaymentGateway {

    private final RazorPay razorPay;

    public RazorPayAdapter(RazorPay razorPay) {
        this.razorPay = razorPay;
    }

    @Override
    public void pay(double amount) {
        razorPay.makePayment(amount);
    }
}

// =====================================
// Adapter for Stripe
// =====================================

class StripeAdapter implements PaymentGateway {

    private final Stripe stripe;

    public StripeAdapter(Stripe stripe) {
        this.stripe = stripe;
    }

    @Override
    public void pay(double amount) {
        stripe.sendMoney(amount);
    }
}

// =====================================
// Adapter for UPI
// =====================================

class UPIAdapter implements PaymentGateway {

    private final UPI upi;

    public UPIAdapter(UPI upi) {
        this.upi = upi;
    }

    @Override
    public void pay(double amount) {
        upi.transferMoney(amount);
    }
}

// =====================================
// Client
// =====================================

public class AdapterDesignPattern {

    public static void main(String[] args) {

        // Create third-party SDK object
        RazorPay razorPay = new RazorPay();

        // Wrap it inside Adapter
        PaymentGateway gateway = new RazorPayAdapter(razorPay);

        // Client only knows pay()
        gateway.pay(1000);

        // -------------------------------

        Stripe stripe = new Stripe();
        gateway = new StripeAdapter(stripe);
        gateway.pay(2500);

        // -------------------------------

        UPI upi = new UPI();
        gateway = new UPIAdapter(upi);
        gateway.pay(500);
    }
}