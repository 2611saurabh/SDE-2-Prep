package MusicPlayerApplication.devices;

import MusicPlayerApplication.external.BluetoothSpeakerAPI;
import MusicPlayerApplication.models.Song;

public class BluetoothSpeakerAdapter implements IAudioOutputDevice{

    public BluetoothSpeakerAPI bluetoothSpeakerAPI;

    public BluetoothSpeakerAdapter(BluetoothSpeakerAPI api){
        bluetoothSpeakerAPI = api;
    }

    @Override
    public void playAudio(Song song) {
        String payload = song.getTitle() + " by " + song.getArtist();
        bluetoothSpeakerAPI.playSoundViaBluetooth(payload);
    }
}
