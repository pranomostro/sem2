package de.tum.in.www1.eist;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class UniversityApp extends Application {
	static final Logger logger = Logger.getLogger(UniversityApp.class);
	private String title = "University App";
	
	@Override
    public void start(Stage primaryStage) {
		BasicConfigurator.configure();
		logger.debug("App started");
		
		final StackPane stackPane = new StackPane();
		final Scene scene = new Scene(stackPane, 300, 250);
		
        final Button button = new Button();
        button.setText(getButtonText());
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	final Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle(title);
            	alert.setContentText(button.getText());
            	alert.showAndWait();
            }
        });
        
        stackPane.getChildren().add(button);

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String getButtonText() {
        return "EIST";
    }

	public static void main(String[] args) {
        launch(args);
    }
}
