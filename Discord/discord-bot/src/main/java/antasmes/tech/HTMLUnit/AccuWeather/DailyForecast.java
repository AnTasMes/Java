package antasmes.tech.HTMLUnit.AccuWeather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import antasmes.MongoDB.Types.LogKeys;

public class DailyForecast extends Forecast {
    private String day;
    private String date;
    private String high;
    private String low;

    public DailyForecast(WebElement element) {
        super(element);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void deploy() {
        WebElement anchorData = element.findElement(By.tagName("a"));
        String[] dataString = anchorData.getText().split("\n");
        List<String> dataList = new ArrayList<String>(Arrays.asList(dataString));

        List<String> lo_hi_data = new ArrayList<String>(Arrays.asList(dataList.get(2).split(" /")));

        this.day = dataList.get(0);
        this.date = dataList.get(1);
        this.message = dataList.get(3);
        this.precipitation = dataList.get(4);

        this.low = lo_hi_data.get(0);
        this.high = lo_hi_data.get(1);

        // this.hasAlert = hasAlert();
    }

    @Override
    protected Boolean hasAlert() {
        // TODO Auto-generated method stub

        // Zaboravio sam kako izgleda klasa kada ima alert
        return false;
    }

    @Override
    public String toString() {
        return String.format("DailyForecast [day=%s, date=%s, low=%s, high=%s, precipitation=%s, message=%s]",
                day,
                date,
                low,
                high,
                precipitation,
                message);
    }

    @Override
    protected void makeMap() {
        this.map.put(LogKeys.DAY, this.day);
        this.map.put(LogKeys.DATE, this.date);
        this.map.put(LogKeys.LOW, this.low);
        this.map.put(LogKeys.HIGH, this.high);
        this.map.put(LogKeys.PRECIPITATION, this.precipitation);
        this.map.put(LogKeys.MESSAGE, this.message);
    }

}
