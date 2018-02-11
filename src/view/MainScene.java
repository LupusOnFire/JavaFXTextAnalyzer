package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class MainScene {
    private Controller controller;
    private Stage primaryStage;
    private Scene scene;

    private VBox vContainer;
    private HBox hContainer, hBoxfilePicker;
    private TableView<Data> charTable, wordTable;
    private FileChooser fileChooser;
    private TextField txtFileChooser;
    private Button btnSelect, btnGo, btnUrlGo;

    public Scene getScene(Controller controller, Stage primaryStage) {
        this.controller = controller;
        this.primaryStage = primaryStage;

        fileChooser = new FileChooser();

        txtFileChooser = new TextField();
        txtFileChooser.setDisable(true);
        txtFileChooser.setMinWidth(400);

        btnSelect = new Button("Select a file");
        btnGo = new Button("Go!");

        hBoxfilePicker = new HBox();
        hBoxfilePicker.getChildren().addAll(txtFileChooser,btnSelect,btnGo);
        hBoxfilePicker.setAlignment(Pos.CENTER);


        btnGo.setOnAction(e -> btnGoClicked());
        btnSelect.setOnAction(e -> btnSelectClicked());


        charTable = new TableView();
        wordTable = new TableView();

        TableColumn<Data, String> charKeyColumn= new TableColumn<Data,String>("Character");
        charKeyColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
        charKeyColumn.prefWidthProperty().bind(charTable.widthProperty().multiply(0.36));

        TableColumn charValueColumn = new TableColumn("Count");
        charValueColumn.setCellValueFactory(new PropertyValueFactory<Data, Integer>("count"));
        charValueColumn.setStyle( "-fx-alignment: CENTER-RIGHT;");
        charValueColumn.prefWidthProperty().bind(charTable.widthProperty().multiply(0.2));

        TableColumn charPercentageColumn = new TableColumn("Percentage");
        charPercentageColumn.setCellValueFactory(new PropertyValueFactory<Data, Double>("percentage"));
        charPercentageColumn.setStyle( "-fx-alignment: CENTER-RIGHT;");
        charPercentageColumn.prefWidthProperty().bind(charTable.widthProperty().multiply(0.4));
        charPercentageColumn.setCellFactory(col ->
        new TableCell<Data, Double>() {
            @Override
            public void updateItem(Double percentage, boolean empty) {
                super.updateItem(percentage,empty);
                if(empty) {
                    setText(null);
                } else{
                    setText(String.format("%.2f%s", percentage.doubleValue(), "%"));
                }
            }
        });


        charTable.getColumns().addAll(charKeyColumn, charValueColumn, charPercentageColumn);
        charTable.setMaxWidth(640); charTable.setMinWidth(640);




        TableColumn wordKeyColumn = new TableColumn("Word");
        wordKeyColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
        wordKeyColumn.prefWidthProperty().bind(charTable.widthProperty().multiply(0.36));

        TableColumn wordValueColumn = new TableColumn("Count");
        wordValueColumn.setCellValueFactory(new PropertyValueFactory<Data, Integer>("count"));
        wordValueColumn.setStyle( "-fx-alignment: CENTER-RIGHT;");
        wordValueColumn.prefWidthProperty().bind(charTable.widthProperty().multiply(0.2));

        TableColumn wordPercentageColumn = new TableColumn("Percentage");
        wordPercentageColumn.setCellValueFactory(new PropertyValueFactory<Data, Double>("percentage"));
        wordPercentageColumn.setStyle( "-fx-alignment: CENTER-RIGHT;");
        wordPercentageColumn.prefWidthProperty().bind(charTable.widthProperty().multiply(0.4));
        wordPercentageColumn.setCellFactory(col ->
                new TableCell<Data, Double>() {
                    @Override
                    public void updateItem(Double percentage, boolean empty) {
                        super.updateItem(percentage,empty);
                        if(empty) {
                            setText(null);
                        } else setText(String.format("%.2f%s", percentage.doubleValue(), "%"));
                    }
                });

        wordTable.getColumns().addAll(wordKeyColumn, wordValueColumn, wordPercentageColumn);
        wordTable.setMaxWidth(640); wordTable.setMinWidth(640);
        wordTable.setMinHeight(700);


        hContainer = new HBox(charTable, wordTable);
        vContainer = new VBox(hBoxfilePicker, hContainer);
        vContainer.setAlignment(Pos.CENTER);
        vContainer.getStylesheets().add("view/style/style.css");
        scene = new Scene(vContainer,1280,720);
        return scene;
    }

    private void btnSelectClicked() {
        File file = fileChooser.showOpenDialog(new Stage());
        txtFileChooser.setText(file.getPath());
    }

    private void btnGoClicked() {
        try {
            controller.setFile(txtFileChooser.getText());
            charTable.setItems(FXCollections.observableList(controller.getCharDataList()));
            wordTable.setItems(FXCollections.observableList(controller.getWordDataList()));
        } catch (Exception e) {
            Stage stage = new Stage();
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(20);
            Button button = new Button("OK");
            button.setOnAction(t->stage.close());
            vBox.getChildren().addAll(new Label("NO SUCH FILE!!!"), button);
            stage.setTitle("Exception");
            stage.setScene(new Scene(vBox, 200, 100));
            stage.show();
        }
    }
}
