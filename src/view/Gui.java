package view;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application{
    private Scene scene;
    private Controller controller;
    @Override
    public void start(Stage stage) throws Exception {
        controller = new Controller();
        stage = new Stage();
        scene = new MainScene().getScene(controller, stage);
        stage.setTitle("Analyze file");
        stage.setScene(scene);
        stage.show();
    }
}
