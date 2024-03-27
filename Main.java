import javafx.concurrent.Worker;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame {
    private final JFXPanel jfxPanel = new JFXPanel();
    private WebEngine engine;
    String url = "file:///C:/Users/faraz/Desktop/WEATHER%20APP/Geocoding/src/map.html"; // Adjust the file path accordingly

    public Main() {
        super();
        initComponents();
        getContentPane().add(jfxPanel);
        setSize(500, 500);
        // Kill everything on closing the frame
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void initComponents() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                WebView view = new WebView();
                engine = view.getEngine();
                engine.load(url);
                Scene scene = new Scene(view);
                jfxPanel.setScene(scene);

                // Call sendCoordinates when the WebView is loaded
                engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue == Worker.State.SUCCEEDED) {
                        // WebView is loaded, call sendCoordinates
                        sendCoordinates();
                    }
                });
            }
        });
    }

    private void sendCoordinates() {
        // Call JavaScript function sendCoordinates
        engine.executeScript("sendCoordinates()");
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setVisible(true);
    }
}
