package Z.Z02.GUI;

import javax.swing.*;
import java.awt.*;

public class InputFrame extends JFrame {
    private final JTextField country = new JTextField();
    private final JTextField city = new JTextField();
    private final JTextField currencyCode = new JTextField();


    public InputFrame(MainFrame mainFrame) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setLayout(new GridLayout(4,2));
        this.add(new JLabel("Country: "));
        this.add(country);
        this.add(new JLabel("City: "));
        this.add(city);
        this.add(new JLabel("Currency Code: "));
        this.add(currencyCode);
        this.add(new JLabel("Submit"));
        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> {
            updateMainWindow(mainFrame);
        });
        this.add(submit);
    }
    private void updateMainWindow(MainFrame mainFrame) {
        mainFrame.updateMainFrame(this);
        this.dispose();
    }
    public String getCountry() {
        return country.getText();
    }
    public String getCity() {
        return city.getText();
    }
    public String getCurrencyCode() {
        return currencyCode.getText();
    }
}
