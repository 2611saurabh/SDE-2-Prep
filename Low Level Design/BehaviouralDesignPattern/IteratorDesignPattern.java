package BehaviouralDesignPattern;

import java.util.ArrayList;
import java.util.List;
/*
class Video{

    private String name;


    public Video(String name){
        this.name=name;
    }

    public String getTitle(){
        return name;
    }
}

class YouTubePlaylist{

    private List<Video> videoList = new ArrayList<>();

    public void addVideo(Video video){
        videoList.add(video);
    }

    public List<Video> getVideos(){
        return videoList;
    }
}

//interface itrator
interface PlaylistIterator{
    boolean hasNext();
    Video next();
}

//concreate class
class YouTubePlaylistIterator implements PlaylistIterator{

    private List<Video> video;
    private int postion;

    //constructor takes playlist to itrate on
    YouTubePlaylistIterator(List<Video> video){
        this.video=video;
        this.postion=0;
    }

    @Override
    public boolean hasNext() {
        return postion < video.size();
    }

    @Override
    public Video next() {
        return hasNext() ? (Video) video.get(postion++) : null;
    }
}

public class IteratorDesignPattern {

    public static void main(String[] args) {
        YouTubePlaylist playlist = new YouTubePlaylist();
        playlist.addVideo(new Video("LLD Tutorial"));
        playlist.addVideo(new Video("System Design Basics"));

        /*
        // Loop through videos and print titles
        for (Video v : playlist.getVideoList()) {
            System.out.println(v.getTitle());
        }
        */
/*
        // Client directly creates the iterator using internal list (not ideal)
        PlaylistIterator iterator = new YouTubePlaylistIterator(playlist.getVideos());

        // Use the iterator to loop through the playlist
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }
    }
}

 */

import java.util.*;

// ========== Video class representing a single video ==========
class Video {
    private String title;

    public Video(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

// ================ Playlist interface ================
// (acts as a contract for collections that are iterable)
interface Playlist {
    // Method to return an iterator for the collection
    PlaylistIterator createIterator();
}

// ========== YouTubePlaylist class (Aggregate) ==========
// Implements Playlist to guarantee it provides an iterator
class YouTubePlaylist implements Playlist {
    private List<Video> videos = new ArrayList<>();

    // Method to add a video to the playlist
    public void addVideo(Video video) {
        videos.add(video);
    }

    // Instead of exposing the list, return an iterator
    @Override
    public PlaylistIterator createIterator() {
        return new YouTubePlaylistIterator(videos);
    }
}

// ========== Iterator interface (defines traversal contract) ==========
interface PlaylistIterator {
    boolean hasNext();   // Checks if more elements are left
    Video next();        // Returns the next element
}

// ========== Concrete Iterator class ==========
// Implements the actual logic for traversing the YouTubePlaylist
class YouTubePlaylistIterator implements PlaylistIterator {
    private List<Video> videos;
    private int position;

    // Constructor takes the collection to iterate over
    public YouTubePlaylistIterator(List<Video> videos) {
        this.videos = videos;
        this.position = 0;
    }

    // Check if more videos are left
    @Override
    public boolean hasNext() {
        return position < videos.size();
    }

    // Return the next video in the playlist
    @Override
    public Video next() {
        return hasNext() ? videos.get(position++) : null;
    }
}

// ========== Main method (Client code) ==========
public class IteratorDesignPattern {
    public static void main(String[] args) {
        // Create a playlist and add videos to it
        YouTubePlaylist playlist = new YouTubePlaylist();
        playlist.addVideo(new Video("LLD Tutorial"));
        playlist.addVideo(new Video("System Design Basics"));

        // Client simply asks for an iterator — no access to internal data structure
        PlaylistIterator iterator = playlist.createIterator();

        // Iterate through the playlist using the provided interface
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }
    }
}

