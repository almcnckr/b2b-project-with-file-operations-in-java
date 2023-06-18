package application;

import business.FileLogger;
import business.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	Logger logger = new FileLogger();
	Alert processInfoAlert = new Alert(AlertType.INFORMATION);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("NishTech");
			primaryStage.getIcons().add(new Image("/view/logo.png"));
			primaryStage.show();
			primaryStage.setResizable(false);

		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Bir yetkili iletişime geçin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}