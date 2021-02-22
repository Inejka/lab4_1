import javafx.application.Application;
import MainPane.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MainPane root = new MainPane();
        //DrawPane root = new DrawPane();
        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("Graph editor");
        stage.setScene(scene);
        stage.show();
        /*stage.setOnCloseRequest(e->{
            try {
                ObjectOutputStream test = new ObjectOutputStream(new FileOutputStream("Test.dat"));
                test.writeObject(root.graph);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}