package bridge.devices_and_remotes;

import bridge.devices_and_remotes.devices.Radio;
import bridge.devices_and_remotes.devices.SmartTv;
import bridge.devices_and_remotes.devices.Tv;
import bridge.devices_and_remotes.remotes.AdvancedRemote;
import bridge.devices_and_remotes.remotes.BasicRemote;
import bridge.devices_and_remotes.remotes.SmartRemote;

import java.util.function.BooleanSupplier;

public class Verification {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        SmartTv tv1 = new SmartTv();
        BasicRemote basic = new BasicRemote(tv1);
        basic.power();
        check("BasicRemote turns SmartTv on", tv1::isEnabled);
        basic.volumeUp();
        check("BasicRemote raises SmartTv volume to 40", () -> tv1.getVolume() == 40);
        new AdvancedRemote(tv1).mute();
        check("AdvancedRemote mutes SmartTv", () -> tv1.getVolume() == 0);

        SmartTv tv2 = new SmartTv();
        tv2.browseInternet("example.com");
        check("SmartTv refuses to browse when off", () -> tv2.getCurrentPage() == null);
        tv2.enable();
        tv2.browseInternet("example.com");
        check("SmartTv adds https:// to address", () -> "https://example.com".equals(tv2.getCurrentPage()));
        tv2.openApp("netflix");
        check("SmartTv launches installed app (case-insensitive)", () -> "Netflix".equals(tv2.getCurrentApp()));
        check("Opening an app closes the browser", () -> tv2.getCurrentPage() == null);
        tv2.openApp("Unknown App");
        check("Unknown app keeps the current app", () -> "Netflix".equals(tv2.getCurrentApp()));
        tv2.setVolume(150);
        check("SmartTv clamps volume to 100", () -> tv2.getVolume() == 100);
        tv2.setChannel(5);
        check("Changing channel leaves the app", () -> tv2.getCurrentApp() == null && tv2.getChannel() == 5);
        tv2.disable();
        check("Turning off clears browser and app",
                () -> !tv2.isEnabled() && tv2.getCurrentPage() == null && tv2.getCurrentApp() == null);

        SmartTv tv3 = new SmartTv();
        SmartRemote smart = new SmartRemote(tv3);
        check("Voice 'turn on' is understood", () -> smart.voiceControl("turn on"));
        check("... and turns the TV on", tv3::isEnabled);
        smart.voiceControl("turn on");
        check("Saying 'turn on' twice does not toggle it off", tv3::isEnabled);
        smart.voiceControl("  VOLUME   UP ");
        check("Voice command is case and whitespace insensitive", () -> tv3.getVolume() == 40);
        smart.voiceControl("volume 75");
        check("Voice 'volume 75' sets volume", () -> tv3.getVolume() == 75);
        smart.voiceControl("mute");
        check("Voice 'mute' mutes", () -> tv3.getVolume() == 0);
        smart.voiceControl("channel 12");
        check("Voice 'channel 12' sets channel", () -> tv3.getChannel() == 12);
        smart.voiceControl("channel down");
        check("Voice 'channel down' decrements channel", () -> tv3.getChannel() == 11);
        smart.voiceControl("browse yle.fi");
        check("Voice 'browse' opens web page", () -> "https://yle.fi".equals(tv3.getCurrentPage()));
        smart.voiceControl("open youtube");
        check("Voice 'open' launches app", () -> "YouTube".equals(tv3.getCurrentApp()));
        check("Unknown command returns false", () -> !smart.voiceControl("make coffee"));
        check("'volume loud' (not a number) returns false", () -> !smart.voiceControl("volume loud"));
        check("Empty command returns false", () -> !smart.voiceControl("   "));
        smart.browseInternet("refactoring.guru");
        check("Internet button opens web page", () -> "https://refactoring.guru".equals(tv3.getCurrentPage()));
        smart.voiceControl("turn off");
        check("Voice 'turn off' turns the TV off", () -> !tv3.isEnabled());

        Radio radio = new Radio();
        SmartRemote radioRemote = new SmartRemote(radio);
        radioRemote.voiceControl("power on");
        radioRemote.voiceControl("volume 55");
        check("SmartRemote controls a Radio by voice", () -> radio.isEnabled() && radio.getVolume() == 55);
        check("Smart feature on Radio returns false", () -> !radioRemote.voiceControl("browse example.com"));
        radioRemote.browseInternet("example.com");
        check("Internet button on Radio does not crash", () -> true);

        Tv oldTv = new Tv();
        SmartRemote tvRemote = new SmartRemote(oldTv);
        tvRemote.voiceControl("turn on");
        tvRemote.voiceControl("next channel");
        check("SmartRemote controls an ordinary Tv", () -> oldTv.isEnabled() && oldTv.getChannel() == 2);

        System.out.println();
        System.out.println("==== " + passed + " passed, " + failed + " failed ====");
        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void check(String description, BooleanSupplier condition) {
        boolean ok = condition.getAsBoolean();
        if (ok) {
            passed++;
        } else {
            failed++;
        }
        System.out.println((ok ? "[PASS] " : "[FAIL] ") + description);
    }
}
