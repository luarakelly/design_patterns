package bridge.devices_and_remotes.devices;

import java.util.List;

/**
 * NEW: a concrete implementation of the bridge. Behaves like a normal TV for the basic
 * operations and additionally supports browsing the internet and launching apps.
 */
public class SmartTv implements SmartDevice {
    private static final List<String> INSTALLED_APPS = List.of("YouTube", "Netflix", "Spotify", "Yle Areena");

    private boolean on = false;
    private int volume = 30;
    private int channel = 1;
    private String currentPage = null;
    private String currentApp = null;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
    }

    @Override
    public void disable() {
        on = false;
        // Turning the TV off closes the browser and any running app.
        currentPage = null;
        currentApp = null;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        // Switching to a broadcast channel leaves the browser and any running app.
        currentPage = null;
        currentApp = null;
    }

    @Override
    public void browseInternet(String url) {
        if (!on) {
            System.out.println("SmartTV: cannot browse, the TV is turned off.");
            return;
        }
        currentApp = null;
        currentPage = url.startsWith("http") ? url : "https://" + url;
        System.out.println("SmartTV: opened " + currentPage);
    }

    @Override
    public String getCurrentPage() {
        return currentPage;
    }

    @Override
    public void openApp(String appName) {
        if (!on) {
            System.out.println("SmartTV: cannot open apps, the TV is turned off.");
            return;
        }
        for (String app : INSTALLED_APPS) {
            if (app.equalsIgnoreCase(appName)) {
                currentPage = null;
                currentApp = app;
                System.out.println("SmartTV: launched " + app);
                return;
            }
        }
        System.out.println("SmartTV: app \"" + appName + "\" is not installed.");
    }

    @Override
    public String getCurrentApp() {
        return currentApp;
    }

    @Override
    public void printStatus() {
        System.out.println("------------------------------------");
        System.out.println("| I'm smart TV.");
        System.out.println("| I'm " + (on ? "enabled" : "disabled"));
        System.out.println("| Current volume is " + volume + "%");
        System.out.println("| Current channel is " + channel);
        System.out.println("| Current web page is " + (currentPage == null ? "-" : currentPage));
        System.out.println("| Current app is " + (currentApp == null ? "-" : currentApp));
        System.out.println("------------------------------------\n");
    }
}
