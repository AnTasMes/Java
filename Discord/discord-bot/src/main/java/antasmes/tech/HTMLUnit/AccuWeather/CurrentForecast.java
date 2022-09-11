package antasmes.tech.HTMLUnit.AccuWeather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import antasmes.MongoDB.Types.LogKeys;

public class CurrentForecast extends Forecast {
    private String temperature;
    private String realFeel;
    private String realFeelShade;
    private String airQuality;
    private String time;

    public CurrentForecast(WebElement element) {
        super(element);
    }

    @Override
    protected void deploy() {
        WebElement anchorData = element.findElement(By.tagName("a"));
        String[] dataString = anchorData.getText().split("\n");
        List<String> dataList = new ArrayList<String>(Arrays.asList(dataString));

        this.time = dataList.get(1);
        this.temperature = dataList.get(2);
        this.realFeel = dataList.get(3).replace("RealFeel?", "");
        this.realFeelShade = dataList.get(5);
        this.airQuality = dataList.get(7);
        this.message = dataList.get(12);
    }

    @Override
    protected Boolean hasAlert() {
        return null;
    }

    @Override
    protected void makeMap() {
        map.put(LogKeys.TIMESTAMP, time);
        map.put(LogKeys.TEMP, temperature);
        map.put(LogKeys.REAL_FEEL, realFeel);
        map.put(LogKeys.REAL_FEEL_SHADE, realFeelShade);
        map.put(LogKeys.MESSAGE, message);
        map.put(LogKeys.AIR_QUALITY, airQuality);
    }

    @Override
    public String toString() {
        return String.format(
                "CurrentForecast [temperature=%s, %s, realFeelShade=%s, airQuality=%s, time=%s, message=%s]",
                temperature, realFeel, realFeelShade, airQuality, time, message);
    }
}
