package BehaviouralDesignPattern;

import java.util.ArrayList;
import java.util.List;

interface Subscriber {

    void update(String video);
}

class EmailSubscriber implements Subscriber {

    @Override
    public void update(String video) {
        System.out.println("Email: " + video);
    }
}

class MobileSubscriber implements Subscriber {

    @Override
    public void update(String video) {
        System.out.println("Mobile notification: " + video);
    }
}

// ==============================
// Subject Interface
// ==============================
interface Channel {
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers(String videoTitle);
}

class YouTubeChannel implements Channel {

    // ???
    private List<Subscriber> subscribers = new ArrayList<>();
    private List<String> videos = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        // ???
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        // ???
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(String video) {
        // ???
        for (Subscriber subscriber : subscribers) {
            subscriber.update(video);
        }
    }

    public void uploadVideo(String video) {
        // 1. Print/upload the video
        // 2. Notify all subscribers
        videos.add(video);
        notifySubscribers(video);
    }
}

public class ObserverDesignPattern {

    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel();

        Subscriber email = new EmailSubscriber();
        Subscriber mobile = new MobileSubscriber();

        channel.subscribe(email);
        channel.subscribe(mobile);

        channel.uploadVideo("Observer Design Pattern");
    }
}
