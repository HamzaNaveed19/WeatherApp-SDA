import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class notification {
    
    public void displayTrayNotification(String title, String message) {
        // Load icon for the system tray
        BufferedImage iconImage = null;
        try {
            InputStream iconStream = App.class.getResourceAsStream("weather-app-icon.png");
            if (iconStream != null) {
                iconImage = ImageIO.read(iconStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create tray icon
        if (SystemTray.isSupported() && iconImage != null) {
            TrayIcon trayIcon = new TrayIcon(iconImage, "Weather App");
            trayIcon.setImageAutoSize(true);

            // Add action listener to the tray icon (optional)
            trayIcon.addActionListener(event -> {
                // Handle click event if needed
            });

            // Add tray icon to the system tray
            try {
                SystemTray.getSystemTray().add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }

            // Display notification
            trayIcon.displayMessage(title, message, TrayIcon.MessageType.WARNING);
        } else {
            System.out.println("System tray not supported or icon not available.");
        }
    }
}

