package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainMenuController {

    @FXML
    private Button createNewDBButton;

    public void showMyDB(ActionEvent event) throws IOException {

    }

    public void CreateNewDB() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("\\scenes\\newDBForm.fxml")));
        Stage stage = (Stage) createNewDBButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }


}
