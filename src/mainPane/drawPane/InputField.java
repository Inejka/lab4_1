package mainPane.drawPane;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InputField {
    private final String text;

    public InputField() {
        TextField input = new TextField();
        Scene inputString = new Scene(input, 200, 20);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(inputString);
        input.setOnKeyPressed(e1 -> {
            if (e1.getCode() == KeyCode.ENTER)
                stage.close();
        });
        stage.showAndWait();
        text = input.getText();
    }

    public String getText() {
        return text;
    }
}
