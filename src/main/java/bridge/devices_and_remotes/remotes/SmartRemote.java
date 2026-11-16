package bridge.devices_and_remotes.remotes;

import bridge.devices_and_remotes.devices.Device;
import bridge.devices_and_remotes.devices.SmartDevice;

import java.util.Locale;

/**
 * NEW: a refined abstraction. Adds voice control and internet/app buttons on top of the
 * advanced remote. It still talks to the device only through the bridge interfaces, so it
 * works with every {@link Device}. Smart-only features are used when the device is a
 * {@link SmartDevice} and are politely refused otherwise.
 */
public class SmartRemote extends AdvancedRemote {

    public SmartRemote(Device device) {
        super(device);
    }

    /** Dedicated "internet" button on the remote. */
    public void browseInternet(String url) {
        System.out.println("Remote: browse " + url);
        SmartDevice smartDevice = asSmartDevice();
        if (smartDevice != null) {
            smartDevice.browseInternet(url);
        }
    }

    /** Dedicated "apps" button on the remote. */
    public void openApp(String appName) {
        System.out.println("Remote: open app " + appName);
        SmartDevice smartDevice = asSmartDevice();
        if (smartDevice != null) {
            smartDevice.openApp(appName);
        }
    }

    /**
     * Interprets a spoken command and translates it into remote operations.
     * Supported commands:
     * "turn on", "turn off", "volume up", "volume down", "volume N", "mute",
     * "channel up", "channel down", "channel N", "browse URL", "open APP".
     *
     * @return true if the command was understood and executed, false otherwise
     */
    public boolean voiceControl(String command) {
        System.out.println("Remote: voice command \"" + command + "\"");
        if (command == null || command.isBlank()) {
            System.out.println("Remote: sorry, I didn't hear anything.");
            return false;
        }

        String normalized = command.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " ");

        // 1) Fixed phrases mapped directly to existing remote buttons.
        switch (normalized) {
            case "turn on", "power on" -> {
                if (!device.isEnabled()) {
                    power();
                }
                return true;
            }
            case "turn off", "power off" -> {
                if (device.isEnabled()) {
                    power();
                }
                return true;
            }
            case "volume up", "louder" -> {
                volumeUp();
                return true;
            }
            case "volume down", "quieter" -> {
                volumeDown();
                return true;
            }
            case "mute" -> {
                mute();
                return true;
            }
            case "channel up", "next channel" -> {
                channelUp();
                return true;
            }
            case "channel down", "previous channel" -> {
                channelDown();
                return true;
            }
            default -> {
                // Commands with an argument are handled below.
            }
        }

        // 2) Commands of the form "<verb> <argument>".
        String[] words = normalized.split(" ", 2);
        String verb = words[0];
        String argument = words.length > 1 ? words[1] : "";

        switch (verb) {
            case "volume" -> {
                Integer level = parseNumber(argument);
                if (level != null) {
                    System.out.println("Remote: set volume to " + level + "%");
                    device.setVolume(level);
                    return true;
                }
            }
            case "channel" -> {
                Integer channel = parseNumber(argument);
                if (channel != null) {
                    System.out.println("Remote: set channel to " + channel);
                    device.setChannel(channel);
                    return true;
                }
            }
            case "browse" -> {
                if (!argument.isEmpty()) {
                    if (asSmartDevice() == null) {
                        return false;
                    }
                    browseInternet(argument);
                    return true;
                }
            }
            case "open" -> {
                if (!argument.isEmpty()) {
                    if (asSmartDevice() == null) {
                        return false;
                    }
                    openApp(argument);
                    return true;
                }
            }
            default -> {
                // Not a known verb.
            }
        }

        System.out.println("Remote: sorry, I didn't understand \"" + command + "\".");
        return false;
    }

    /** Returns the device as a SmartDevice, or prints a message and returns null if it is not one. */
    private SmartDevice asSmartDevice() {
        if (device instanceof SmartDevice smartDevice) {
            return smartDevice;
        }
        System.out.println("Remote: this device does not support smart features.");
        return null;
    }

    private static Integer parseNumber(String text) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
