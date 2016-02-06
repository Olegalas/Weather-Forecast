package view;

import model.Weather;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dexter on 06.02.16.
 */
public class WeatherPanel extends JPanel {

    private JLabel tempLabel = new JLabel("Temperature (k) :");
    private JLabel pressureLabel = new JLabel("Pressure (hPa) :");
    private JLabel humidLabel = new JLabel("Humidity (%) :");
    private JLabel maxTemperatureLabel = new JLabel("MaxTemperature (k) :");
    private JLabel minTemperatureLabel = new JLabel("MinTemperature (k) :");
    private JLabel mainLabel = new JLabel("Weather :");
    private JLabel descriptionLabel = new JLabel("Description :");

    private JLabel tempLabelValue = new JLabel();
    private JLabel pressureLabelValue = new JLabel();
    private JLabel humidLabelValue = new JLabel();
    private JLabel maxTemperatureLabelValue = new JLabel();
    private JLabel minTemperatureLabelValue = new JLabel();
    private JLabel mainLabelValue = new JLabel();
    private JLabel descriptionLabelValue = new JLabel();

    public WeatherPanel(){
        initWeatherPanel();
    }

    private void initWeatherPanel() {
        setLayout(new GridLayout(7, 2));

        add(tempLabel);
        add(tempLabelValue);
        add(pressureLabel);
        add(pressureLabelValue);
        add(humidLabel);
        add(humidLabelValue);
        add(maxTemperatureLabel);
        add(maxTemperatureLabelValue);
        add(minTemperatureLabel);
        add(minTemperatureLabelValue);
        add(mainLabel);
        add(mainLabelValue);
        add(descriptionLabel);
        add(descriptionLabelValue);

    }

    public void initWeatherLabels(Weather weather) {

        tempLabelValue.setText(weather.getTemperature());
        pressureLabelValue.setText(weather.getPressure());
        humidLabelValue.setText(weather.getHumid());
        maxTemperatureLabelValue.setText(weather.getMaxTemperature());
        minTemperatureLabelValue.setText(weather.getMinTemperature());
        mainLabelValue.setText(weather.getMain());
        descriptionLabelValue.setText(weather.getDescription());

    }
}
