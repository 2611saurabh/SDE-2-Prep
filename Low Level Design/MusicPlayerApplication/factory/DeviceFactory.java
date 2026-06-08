package MusicPlayerApplication.factory;

import MusicPlayerApplication.devices.BluetoothSpeakerAdapter;
import MusicPlayerApplication.devices.HeadphonesAdapter;
import MusicPlayerApplication.devices.IAudioOutputDevice;
import MusicPlayerApplication.devices.WiredSpeakerAdapter;
import MusicPlayerApplication.enums.DeviceType;
import MusicPlayerApplication.external.BluetoothSpeakerAPI;
import MusicPlayerApplication.external.HeadphonesAPI;
import MusicPlayerApplication.external.WiredSpeakerAPI;

public class DeviceFactory {

    public static IAudioOutputDevice createDevice(DeviceType deviceType){

        if(deviceType == DeviceType.BLUETOOTH){
            return new BluetoothSpeakerAdapter(new BluetoothSpeakerAPI());
        }
        else if(deviceType == DeviceType.WIRED){
            return new WiredSpeakerAdapter(new WiredSpeakerAPI());
        }
        else{ //Headphones
            return new HeadphonesAdapter(new HeadphonesAPI());
        }
    }
}
