package MusicPlayerApplication.devices;

import MusicPlayerApplication.models.Song;

public interface IAudioOutputDevice {

    public void playAudio(Song song);
}
