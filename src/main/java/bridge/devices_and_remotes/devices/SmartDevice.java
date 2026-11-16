package bridge.devices_and_remotes.devices;

public interface SmartDevice extends Device {

    void browseInternet(String url);

    String getCurrentPage();

    void openApp(String appName);

    String getCurrentApp();
}
