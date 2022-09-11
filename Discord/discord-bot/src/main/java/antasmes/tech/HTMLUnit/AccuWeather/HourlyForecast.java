package antasmes.tech.HTMLUnit.AccuWeather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;

import antasmes.MongoDB.Types.LogKeys;

public class HourlyForecast extends Forecast {
    private String hour;
    private String temperature;
    private String realFeel;

    public HourlyForecast(WebElement element) {
        super(element);

        deploy();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void deploy() {
        String[] dataString = element.getText().split("\n");

        List<String> dataList = new ArrayList<String>(Arrays.asList(dataString)); // Ovo moze da se prebaci i u abstract
                                                                                  // klasu, pa da se odatle uzima

        this.hour = dataList.get(0);
        this.temperature = dataList.get(1);
        this.realFeel = dataList.get(2);
        this.message = dataList.get(3);
        this.precipitation = dataList.get(4);

        // System.out.println(dataString);

    }

    @Override
    protected Boolean hasAlert() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void makeMap() {
        this.map.put(LogKeys.HOUR, hour);
        this.map.put(LogKeys.TEMP, temperature);
        this.map.put(LogKeys.REAL_FEEL, realFeel);
        this.map.put(LogKeys.MESSAGE, message);
        this.map.put(LogKeys.PRECIPITATION, precipitation);
    }

    @Override
    public String toString() {
        return String.format("HourlyForecast [hour=%s, temperature=%s, %s, percepitation=%s, message=%s]",
                hour, temperature, realFeel, precipitation, message);
    }

}
