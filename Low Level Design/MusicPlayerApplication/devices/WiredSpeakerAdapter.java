package MusicPlayerApplication.devices;

import MusicPlayerApplication.external.WiredSpeakerAPI;
import MusicPlayerApplication.models.Song;

public class WiredSpeakerAdapter implements IAudioOutputDevice{

    private WiredSpeakerAPI wiredSpeakerAPI;

    public WiredSpeakerAdapter(WiredSpeakerAPI wiredSpeakerAPI){
        this.wiredSpeakerAPI = wiredSpeakerAPI;
    }

    @Override
    public void playAudio(Song song) {
        String payload = song.getTitle() + " by " + song.getArtist();
        wiredSpeakerAPI.playSoundViaCable(payload);
    }
}
