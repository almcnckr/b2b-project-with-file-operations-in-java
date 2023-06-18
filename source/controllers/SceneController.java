package controllers;

import business.FileLogger;
import business.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SceneController {
	Logger logger = new FileLogger();
	Alert processInfoAlert = new Alert(AlertType.INFORMATION);

	private Stage stage;
	private Scene scene;
	private Parent root;

	public void getAdminScenes(ActionEvent event, String path) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		try {
			root = FXMLLoader.load(getClass().getResource("/view/" + path + ".fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.setTitle("NishTech");
			stage.getIcons().add(new Image("/view/logo.png"));
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Logları kontrol edin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}
	
	public void getUserScenes(ActionEvent event, String path) {
		processInfoAlert.setTitle("İşlem Bilgisi");
		try {
			root = FXMLLoader.load(getClass().getResource("/view/" + path + ".fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.setTitle("NishTech");
			stage.getIcons().add(new Image("/view/logo.png"));
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			processInfoAlert.setHeaderText("İşlem Başarısız! Bir yetkili ile iletişime geçin.");
			logger.log(e.getMessage());
			processInfoAlert.showAndWait();
		}
	}
}