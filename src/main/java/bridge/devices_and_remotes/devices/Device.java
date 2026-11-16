package bridge.devices_and_remotes.devices;

/**
 * Implementation side of the bridge.
 * Every device the remotes can control must implement these primitive operations.
 */
public interface Device {
    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int percent);

    int getChannel();

    void setChannel(int channel);

    void printStatus();
}
