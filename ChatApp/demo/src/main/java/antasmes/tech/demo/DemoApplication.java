package antasmes.tech.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Application.launch(FXApp.class, args);
	}
}
