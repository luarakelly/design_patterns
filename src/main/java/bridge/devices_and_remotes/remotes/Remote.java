package bridge.devices_and_remotes.remotes;

/**
 * Abstraction side of the bridge: the high-level controls a user presses.
 */
public interface Remote {
    void power();

    void volumeDown();

    void volumeUp();

    void channelDown();

    void channelUp();
}
