package Z.Z02.GUI;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javafx.embed.swing.JFXPanel;
import javafx.scene.web.WebView;

import javax.swing.*;

public class WikipediaPanel extends JPanel {
    private final JFXPanel jfxPanel = new JFXPanel();
    public WikipediaPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(jfxPanel);
    }
    public void updateWikipediaPanel(InputFrame inputFrame) {
        var cityName = inputFrame.getCity();
        Platform.runLater(() -> {
            var webView = new WebView();
            webView.getEngine().load("https://en.wikipedia.org/wiki/" + cityName);
            jfxPanel.setScene(new Scene(new Group(webView)));
        });
    }
}
