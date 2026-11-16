package bridge.devices_and_remotes.remotes;

import bridge.devices_and_remotes.devices.Device;
import bridge.devices_and_remotes.devices.SmartDevice;

import java.util.Locale;

public class SmartRemote extends AdvancedRemote {

    public SmartRemote(Device device) {
        super(device);
    }

    public void browseInternet(String url) {
        System.out.println("Remote: browse " + url);
        SmartDevice smartDevice = asSmartDevice();
        if (smartDevice != null) {
            smartDevice.browseInternet(url);
        }
    }

    public void openApp(String appName) {
        System.out.println("Remote: open app " + appName);
        SmartDevice smartDevice = asSmartDevice();
        if (smartDevice != null) {
            smartDevice.openApp(appName);
        }
    }

    public boolean voiceControl(String command) {
        System.out.println("Remote: voice command \"" + command + "\"");
        if (command == null || command.isBlank()) {
            System.out.println("Remote: sorry, I didn't hear anything.");
            return false;
        }

        String normalized = command.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " ");

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
            }
        }

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
            }
        }

        System.out.println("Remote: sorry, I didn't understand \"" + command + "\".");
        return false;
    }

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
