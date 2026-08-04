package StructuralDesignPattern;

/*
 ==========================================================
                IMPLEMENTATION HIERARCHY
 ==========================================================

 Provider tells HOW the notification will be sent.

 We use an interface because every provider only needs to
 follow one contract (sendMessage()).

 Examples:
 Gmail
 Outlook
 WhatsApp
 Facebook

 New providers can be added without modifying Notification
 classes (Open/Closed Principle).
 */

interface Provider {

    void sendMessage();
}

// =======================
// Email Providers
// =======================

class Gmail implements Provider {

    @Override
    public void sendMessage() {
        System.out.println("Sending message using Gmail");
    }
}

class Outlook implements Provider {

    @Override
    public void sendMessage() {
        System.out.println("Sending message using Outlook");
    }
}

// =======================
// SMS Providers
// =======================

class SimMessage implements Provider {

    @Override
    public void sendMessage() {
        System.out.println("Sending SMS using SIM");
    }
}

class TrueCallerMessage implements Provider {

    @Override
    public void sendMessage() {
        System.out.println("Sending SMS using TrueCaller");
    }
}

// =======================
// Social Media Providers
// =======================

class Whatsapp implements Provider {

    @Override
    public void sendMessage() {
        System.out.println("Sending message using WhatsApp");
    }
}

class Facebook implements Provider {

    @Override
    public void sendMessage() {
        System.out.println("Sending message using Facebook");
    }
}


/*
 ==========================================================
                ABSTRACTION HIERARCHY
 ==========================================================

 Notification tells WHAT is being sent.

 We use an abstract class because every notification
 shares one common object:

        Provider provider

Instead of duplicating this variable and constructor
inside every subclass.

Bridge Connection:

Notification
      HAS-A
      Provider
 */

abstract class Notification {

    // Common object shared by every notification

    protected Provider provider;

    public Notification(Provider provider) {
        this.provider = provider;
    }

    // Every notification sends differently
    public abstract void send();
}


/*
 ==========================================================
            REFINED ABSTRACTION
 ==========================================================

 EmailNotification decides WHAT to send.

 It DOES NOT know whether provider is Gmail,
 Outlook or something else.

 It simply delegates the work to Provider.
 */

class EmailNotification extends Notification {

    public EmailNotification(Provider provider) {
        super(provider);
    }

    @Override
    public void send() {

        System.out.println("Preparing Email Notification...");

        // Delegate implementation to Provider
        provider.sendMessage();
    }
}


/*
 ==========================================================
            REFINED ABSTRACTION
 ==========================================================
 */

class SMSNotification extends Notification {

    public SMSNotification(Provider provider) {
        super(provider);
    }

    @Override
    public void send() {

        System.out.println("Preparing SMS Notification...");

        provider.sendMessage();
    }
}


/*
 ==========================================================
            REFINED ABSTRACTION
 ==========================================================
 */

class SocialMediaNotification extends Notification {

    public SocialMediaNotification(Provider provider) {
        super(provider);
    }

    @Override
    public void send() {

        System.out.println("Preparing Social Media Notification...");

        provider.sendMessage();
    }
}


/*
 ==========================================================
                    CLIENT
 ==========================================================

 Client can mix ANY notification with ANY provider.

 No new subclasses are required.

 This is the biggest advantage of Bridge Pattern.
 */

public class BridgeDesignPattern {

    public static void main(String[] args) {

        // Email using Gmail
        Notification notification =
                new EmailNotification(new Gmail());

        notification.send();

        System.out.println("-----------------------");

        // Email using Outlook
        notification =
                new EmailNotification(new Outlook());

        notification.send();

        System.out.println("-----------------------");

        // SMS using SIM
        notification =
                new SMSNotification(new SimMessage());

        notification.send();

        System.out.println("-----------------------");

        // SMS using TrueCaller
        notification =
                new SMSNotification(new TrueCallerMessage());

        notification.send();

        System.out.println("-----------------------");

        // Social Media using WhatsApp
        notification =
                new SocialMediaNotification(new Whatsapp());

        notification.send();

        System.out.println("-----------------------");

        // Social Media using Facebook
        notification =
                new SocialMediaNotification(new Facebook());

        notification.send();
    }
}