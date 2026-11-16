package bridge.devices_and_remotes;

import bridge.devices_and_remotes.devices.Device;
import bridge.devices_and_remotes.devices.Radio;
import bridge.devices_and_remotes.devices.SmartTv;
import bridge.devices_and_remotes.devices.Tv;
import bridge.devices_and_remotes.remotes.AdvancedRemote;
import bridge.devices_and_remotes.remotes.BasicRemote;
import bridge.devices_and_remotes.remotes.SmartRemote;

public class Demo {
    public static void main(String[] args) {
        // Original demo: every device works with the original remotes,
        // including the new SmartTv.
        testDevice(new Tv());
        testDevice(new Radio());
        testDevice(new SmartTv());

        // New functionality: SmartRemote with a SmartTv.
        testSmartRemote(new SmartTv());

        // SmartRemote also works with an ordinary device; smart features are refused.
        testSmartRemote(new Radio());
    }

    public static void testDevice(Device device) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }

    public static void testSmartRemote(Device device) {
        System.out.println("Tests with smart remote.");
        SmartRemote smartRemote = new SmartRemote(device);
        smartRemote.voiceControl("Turn on");
        smartRemote.voiceControl("volume 45");
        smartRemote.voiceControl("channel 7");
        smartRemote.voiceControl("open Netflix");
        smartRemote.browseInternet("refactoring.guru");
        smartRemote.voiceControl("make me a sandwich");
        device.printStatus();
    }
}
