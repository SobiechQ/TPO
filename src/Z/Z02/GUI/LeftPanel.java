package Z.Z02.GUI;

import Z.Z02.ApiConnectionHandling.*;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    private final JPanel weatherPanel = new JPanel();
    private final JPanel exchangePanel = new JPanel();
    private final JPanel nbpPanel = new JPanel();
    public LeftPanel(MainFrame mainFrame) {
        this.setLayout(new GridLayout(4, 1));
        this.add(weatherPanel);
        this.add(exchangePanel);
        this.add(nbpPanel);
        var button = new JButton("Update Information");
        this.add(button);
        button.addActionListener(e -> new InputFrame(mainFrame));
    }
    public void updateLeftPanel(InputFrame inputFrame) {
        weatherPanel.removeAll();
        exchangePanel.removeAll();
        nbpPanel.removeAll();

        CountryApi.CountryData countryData;
        try {
            countryData = new CountryApi(inputFrame.getCountry()).getData();
        } catch (ApiCallException e) {
            countryData = new CountryApi.CountryData("Error", "Error", "Error");
        }
        try {
            var weatherData = new WeatherApi(inputFrame.getCity(), countryData).getData();
            weatherPanel.add(new JTextArea(String.format("Weather in: %s\nTemperature: %.2f°C\nPressure: %.2f hPa", inputFrame.getCity(), weatherData.temperature(), weatherData.pressure())));
        } catch (ApiCallException e) {
            weatherPanel.add(new JTextArea(String.format("Unable to get weather for: %s", inputFrame.getCity())));
        }
        try {
            var exchangeData = new ExchangeApi(countryData.currency(), inputFrame.getCurrencyCode()).getData();
            exchangePanel.add(new JTextArea(String.format("Exchange from:\n%s to %s\nRate: %.2f", countryData.currency(), inputFrame.getCurrencyCode(), exchangeData.conversionRate())));
        } catch (ApiCallException e) {
            exchangePanel.add(new JTextArea(String.format("Unable to Exchange from:\n%s to %s\n", countryData.currency(), inputFrame.getCurrencyCode())));

        }
        try {
            var nbpData = new NbpApi(countryData.currency()).getData();
            nbpPanel.add(new JTextArea(String.format("NBP Exchange rate for %s:\nRate: %.2f", countryData.currency(), nbpData.rate())));
        } catch (ApiCallException e) {
            nbpPanel.add(new JTextArea(String.format("Unable to get NBP Exchange rate for %s", countryData.currency())));
        }
        this.revalidate();
        this.repaint();

    }
}
