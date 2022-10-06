package antasmes.Commands;

import java.util.List;

import antasmes.Data.Roles.RoleEnum;
import antasmes.tech.bot;
import antasmes.tech.HTMLUnit.AccuWeather.AccuWeather;
import antasmes.tech.HTMLUnit.AccuWeather.Forecast;
import antasmes.tech.HTMLUnit.AccuWeather.AccuWeather.ForecastType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ShowForecast extends Command {
    private RoleEnum role = RoleEnum.ADMINISTRATOR;

    public ShowForecast(MessageReceivedEvent event, String[] args) {
        super(event, args);
        super.role = this.role;

        if (evaluateArgs(args) && checkRole()) {
            execute(args);
        }
    }

    @Override
    public void execute(String[] args) {
        informUser();

        AccuWeather accuWeather = new AccuWeather(args[1]);

        List<Forecast> forecasts = null;

        if (args[2].equals("current")) {
            forecasts = accuWeather.getForecast(ForecastType.CURRENT);
        } else if (args[2].equals("hourly")) {
            forecasts = accuWeather.getForecast(ForecastType.HOURLY);
        } else if (args[2].equals("daily")) {
            forecasts = accuWeather.getForecast(ForecastType.DAILY);
        } else {
            event.getChannel().sendMessage("Invalid forecast type").queue();
        }

        if (forecasts != null) {
            sendForecasts(forecasts);
        }
    }

    private Boolean evaluateArgs(String[] args) {
        try {
            System.out.println(args[1] + " " + args[2]);
            return true;
        } catch (Exception e) {
            event.getChannel().sendMessage("Invalid arguments: -Forecast city type").queue();
            return false;
        }
    }

    private void informUser() {
        event.getChannel().sendMessage("Fetching forecast...").queue();
        event.getChannel().sendTyping();
    }

    private void sendForecasts(List<Forecast> forecasts) {
        String message = "";
        for (Forecast forecast : forecasts) {
            message += forecast.toString() + "\n";
        }
        if (message != null) {
            event.getChannel().sendMessage(message).queue();
        }
    }
}
