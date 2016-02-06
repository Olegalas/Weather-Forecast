package view;

import controller.Controller;
import model.City;
import model.Country;
import model.Weather;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by dexter on 02.02.16.
 */
public class RequestFrame extends JFrame {

    private JLabel countryLabel = new JLabel("Country");
    private JLabel cityLabel = new JLabel("City");

    private JComboBox<String> countryCombo = new JComboBox<>();
    private JComboBox<City> cityCombo = new JComboBox<>();

    private JButton genWeatherButton = new JButton("Gen Weather");

    private Controller controller = new Controller();
    private Weather weather;
    private Country country;

    private JPanel navigatorPanel = new JPanel();
    private WeatherPanel weatherPanel = new WeatherPanel();

    public RequestFrame(){
        init();
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Weather Forecast");
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void init(){
        setLayout(new BorderLayout());
        initNavigatorPanel();
        initCountryComboBox();
        add(weatherPanel, BorderLayout.CENTER);

        countryCombo.addActionListener((e) -> {

            cityCombo.removeAllItems();

            country = controller.getCountries().get(countryCombo.getSelectedItem());

            for(City tmp : country.getCity()){
                cityCombo.addItem(tmp);
            }

        });

        genWeatherButton.addActionListener((e) -> {

            try {
                weather = controller.getWeather((City)cityCombo.getSelectedItem());
                weatherPanel.initWeatherLabels(weather);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }

        });

        add(genWeatherButton, BorderLayout.SOUTH);
    }

    private void initCountryComboBox() {
        for(String country : controller.getCountries().keySet()){
            countryCombo.addItem(country);
        }
    }

    private void initNavigatorPanel() {
        navigatorPanel.setLayout(new GridLayout(2, 2));

        navigatorPanel.add(countryLabel);
        navigatorPanel.add(cityLabel);
        navigatorPanel.add(countryCombo);
        navigatorPanel.add(cityCombo);

        add(navigatorPanel, BorderLayout.NORTH);
    }
}
