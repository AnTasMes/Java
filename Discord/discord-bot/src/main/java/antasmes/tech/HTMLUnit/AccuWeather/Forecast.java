package antasmes.tech.HTMLUnit.AccuWeather;

import org.openqa.selenium.WebElement;

import antasmes.MongoDB.Insertable;

public abstract class Forecast extends Insertable {
    protected String precipitation;
    protected String message;
    protected Boolean hasAlert;

    protected WebElement element;

    public Forecast(WebElement element) {
        super();
        this.element = element;
        deploy();
    }

    protected abstract void deploy();

    protected abstract Boolean hasAlert();

    protected abstract void makeMap();

    public String getPrecipitation() {
        return precipitation;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("Forecast [precipitation=%s, message=%s]", precipitation, message);
    }
}
