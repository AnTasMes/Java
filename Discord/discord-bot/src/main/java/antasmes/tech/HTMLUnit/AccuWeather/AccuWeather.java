package antasmes.tech.HTMLUnit.AccuWeather;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import antasmes.tech.Utilities;

public class AccuWeather {
    private String mainPage_url = "https://www.accuweather.com/";
    private WebDriver webDriver;
    private String location_url;

    public enum ForecastType {
        DAILY("daily", By.className("daily-wrapper"), DailyForecast.class),
        HOURLY("hourly", By.cssSelector(".hourly-card-nfl-header"), HourlyForecast.class),
        CURRENT(By.cssSelector(".page-content.content-module"), CurrentForecast.class);

        private String target;
        private By by;
        private Class<?> cls;

        private ForecastType(String target, By by, Class<?> cls) {
            this.target = target;
            this.by = by;
            this.cls = cls;
        }

        private ForecastType(By by, Class<?> cls) {
            this.target = "";
            this.by = by;
            this.cls = cls;
        }

        public String getTarget() {
            return target;
        }

        public By getBy() {
            return by;
        }

        public Class<?> getCls() {
            return cls;
        }
    }

    public AccuWeather(String location) {
        this.webDriver = setupDriver();
        this.webDriver.get(mainPage_url);
        System.out.println(webDriver.getTitle());

        this.location_url = searchForLocation(location);

        getForecast(ForecastType.CURRENT);
    }

    public List<Forecast> getForecast(ForecastType type) {
        List<Forecast> forecastList = new ArrayList<Forecast>();

        webDriver.get(location_url + type.getTarget());

        webDriver.findElements(type.getBy()).forEach(element -> {
            forecastList.add((Forecast) createInstance(type.getCls(), element));
        });

        Utilities.printList(forecastList);

        return forecastList;
    }

    // createInstance that creates an instance of a class

    private String searchForLocation(String location) {
        WebElement searchBox = webDriver.findElement(By.cssSelector(".search-input"));
        searchBox.sendKeys(location);
        searchBox.sendKeys(Keys.ENTER);

        switchWindow();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement searchResult = wait
                .until(webDriver -> webDriver.findElement(By.cssSelector(".locations-list.content-module")));

        WebElement city = searchResult.findElement(By.tagName("a")); // gets first result
        return city.getAttribute("href");
    }

    // https://www.accuweather.com/en/rs/belgrade/298198/weather-forecast/298198
    // https://www.accuweather.com/web-api/three-day-redirect?key=298198&target=daily
    // https://www.accuweather.com/web-api/three-day-redirect?key=298198&target=hourly
    private void switchWindow() {
        String currentWindow = webDriver.getWindowHandle();
        for (String window : webDriver.getWindowHandles()) {
            if (!window.equals(currentWindow)) {
                webDriver.switchTo().window(window);
            }
        }
    }

    private WebDriver setupDriver() {
        String driverPath = "D:\\Programming\\Drivers\\EdgeDriver\\msedgedriver.exe";
        System.setProperty("webdriver.edge.driver", driverPath);
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36";

        EdgeOptions options = new EdgeOptions();
        options.addArguments(
                "--headless",
                "--window-size=1920,1200",
                "--ignore-certificate-errors",
                "--disable-blink-features=AutomationControlled",
                "user-agent=" + userAgent,
                "--enable-javascript");

        try {
            return new EdgeDriver(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object createInstance(Class<?> cls, WebElement element) {
        try {
            return cls.getConstructor(WebElement.class).newInstance(element);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}