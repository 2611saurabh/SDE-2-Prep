package MusicPlayerApplication.core;

import MusicPlayerApplication.devices.IAudioOutputDevice;
import MusicPlayerApplication.models.Song;

public class AudioEngine {

    private Song currSong;
    private boolean songIsPaused;

    public AudioEngine(){
        currSong = null;
        songIsPaused = false;
    }

    public String getCurrentSongTitle(){
        if(currSong != null){
            return currSong.getTitle();
        }
        return "";
    }

    public boolean isPaused(){
        return songIsPaused;
    }

    public void play(IAudioOutputDevice aod, Song song){
        if(song == null){

            System.out.println("Cannot play null song");
            return;
        }

        //Resume if same song is paused
        if(songIsPaused && song == currSong){
            songIsPaused = false;
            System.out.println("Resuming song "+ song.getTitle() + "\n");
            aod.playAudio(song);
            return;
        }

        currSong = song;
        songIsPaused = false;
        System.out.println("Playing song: "+song.getTitle()+"\n");
        aod.playAudio(song);
    }

    public void pause(){

        if(currSong == null){
            System.out.println("No Song is currently Playing to pause");
        }

        if(songIsPaused){
            System.out.println("Song is already paused");
        }

        songIsPaused = true;
        System.out.println("Pausing song: "+currSong.getTitle()+"\n");

    }
}
