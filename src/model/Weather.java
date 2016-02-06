package model;

/**
 * Created by dexter on 02.02.16.
 */
public final class Weather {

    private final String temperature; // in C
    private final String pressure; // in hPa
    private final String humid; // in %
    private final String maxTemperature; // in C
    private final String minTemperature; // in C
    private final String main;
    private final String description;

    private Weather(String temperature, String pressure, String humid, String maxTemperature,
                    String minTemperature, String main, String description){
        this.temperature = temperature;
        this.pressure = pressure;
        this.humid = humid;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.main = main;
        this.description = description;
    }

    public static class Builder{

        private String temperature; // in C
        private String pressure; // in hPa
        private String humid; // in %
        private String maxTemperature; // in C
        private String minTemperature; // in C
        private String main;
        private String description;

        public Builder(){}

        public Builder temperature(String temperature){
            this.temperature = temperature; return this;
        }
        public Builder pressure(String pressure){
            this.pressure = pressure; return this;
        }
        public Builder humid(String humid){
            this.humid = humid; return this;
        }
        public Builder maxTemperature(String maxTemperature){
            this.maxTemperature = maxTemperature; return this;
        }
        public Builder minTemperature(String minTemperature){
            this.minTemperature = minTemperature; return this;
        }
        public Builder main(String main){
            this.main = main; return this;
        }
        public Builder description(String description){
            this.description = description; return this;
        }
        public Weather build(){
            return new Weather(temperature, pressure, humid, maxTemperature,
                    minTemperature, main, description);
        }
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumid() {
        return humid;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "temperature='" + temperature + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humid='" + humid + '\'' +
                ", maxTemperature='" + maxTemperature + '\'' +
                ", minTemperature='" + minTemperature + '\'' +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
