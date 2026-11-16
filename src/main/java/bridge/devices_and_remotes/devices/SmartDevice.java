package bridge.devices_and_remotes.devices;

/**
 * NEW: an extended implementation interface for devices that are connected to the internet.
 * It keeps every primitive operation of {@link Device}, so a smart device can still be
 * controlled by any existing remote, and adds smart-only primitives on top.
 */
public interface SmartDevice extends Device {
    /** Opens the given web address in the device's browser. */
    void browseInternet(String url);

    /** Returns the page currently open in the browser, or null when nothing is open. */
    String getCurrentPage();

    /** Launches an installed streaming/media app, e.g. "YouTube" or "Netflix". */
    void openApp(String appName);

    /** Returns the app currently running, or null when no app is open. */
    String getCurrentApp();
}
