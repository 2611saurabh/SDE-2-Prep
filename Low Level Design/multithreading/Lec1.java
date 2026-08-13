package multithreading;

class OrderService{

    public static void main(String[] args) throws  InterruptedException {

        System.out.println("Placing order...\n");

        sendSms();
        System.out.println("Task 1 done...\n");

        sendEmail();
        System.out.println("Task 2 done...\n");

        String eta = calculateEta();
        System.out.println("Order Placed. Estimated Time of Arrival: "+ eta);

        System.out.println("Task 3 done...\n");
    }


    public static void sendSms() {

        try{
            Thread.sleep(3000);
            System.out.println("Sending SMS...\n");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    public static void sendEmail() {
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private static String calculateEta() {

        try{
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        return "25 minutes";
    }
}

class SmsSend extends Thread{
    @Override
    public void run() {
        try{
            Thread.sleep(3000);
            System.out.println("Sending SMS...");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class EmailSend extends Thread{

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("Email was Sent");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class Lec1 {

    public static void main(String[] args) {
        /*
        try{
            OrderService.main(args);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

         */

        SmsSend smsSend = new SmsSend();
        EmailSend emailSend = new EmailSend();
        System.out.println("Task Starting");
        smsSend.start();
        System.out.println("Task 1 Started");
        emailSend.start();
        System.out.println("Task 2 Started");

        try{
            smsSend.join();
            emailSend.join();
            System.out.println("Task done");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
