package Z.Z02.GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final LeftPanel leftPanel = new LeftPanel(this);
    private final WikipediaPanel wikipediaPanel = new WikipediaPanel();
    public MainFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setLayout(new BorderLayout());
        this.add(leftPanel, BorderLayout.WEST);
        this.add(wikipediaPanel, BorderLayout.CENTER);
    }
    public void updateMainFrame(InputFrame inputFrame) {
        leftPanel.updateLeftPanel(inputFrame);
        wikipediaPanel.updateWikipediaPanel(inputFrame);
    }
}
