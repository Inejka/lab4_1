package mainPane;

import mainPane.drawPane.DrawPane;
import mainPane.drawPane.InputField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainPane extends VBox {
    public MainPane(Stage stage) {
        parentStage = stage;
        menuBarInit();
        toolBarInit();
        tabPaneInit();

        DrawPane drawPane = new DrawPane();
        Tab tab = new Tab("Hi");
        tab.setContent(drawPane);
        tabPane.getTabs().add(tab);
        getChildren().addAll(menuBar, toolBar, tabPane);
    }

    private final MenuBar menuBar = new MenuBar();
    private final ToolBar toolBar = new ToolBar();

    private final TabPane tabPane = new TabPane();

    private Stage parentStage;

    private Integer paneCount = 0;

    private final EventHandler<ActionEvent> newPaneE = e -> {
        DrawPane toAddPane = new DrawPane();
        Tab toAddTab = new Tab("untiled" + paneCount.toString());
        paneCount++;
        toAddTab.setContent(toAddPane);
        tabPane.getTabs().add(toAddTab);
    };

    private final EventHandler<ActionEvent> saveE = e -> ((DrawPane) tabPane.getSelectionModel().getSelectedItem().getContent()).save(parentStage);

    private final EventHandler<ActionEvent> loadE = e -> ((DrawPane) tabPane.getSelectionModel().getSelectedItem().getContent()).load(parentStage);

    private final EventHandler<ActionEvent> renameE = e -> ((DrawPane) tabPane.getSelectionModel().getSelectedItem().getContent()).setIdentifier(new InputField().getText());

    private final EventHandler<ActionEvent> deleteE = e -> ((DrawPane) tabPane.getSelectionModel().getSelectedItem().getContent()).removeSelected();

    private final EventHandler<ActionEvent> algorithmE = e -> ((DrawPane) tabPane.getSelectionModel().getSelectedItem().getContent()).algorithm();

    private final EventHandler<ActionEvent> clearE = e -> ((DrawPane) tabPane.getSelectionModel().getSelectedItem().getContent()).clear();

    private void menuBarInit() {
        menuBar.prefWidthProperty().bind(widthProperty());
        Menu file = new Menu("File"), edit = new Menu("Edit"), help = new Menu("Help");
        MenuItem newPane = new MenuItem("New");
        MenuItem save = new MenuItem("Save");
        MenuItem load = new MenuItem("Load");
        newPane.setOnAction(newPaneE);
        save.setOnAction(saveE);
        load.setOnAction(loadE);
        MenuItem helpMe = new MenuItem("Help me,HELP ME");
        help.getItems().add(helpMe);

        MenuItem button1 = new MenuItem("Rename");
        MenuItem button2 = new MenuItem("Delete");
        MenuItem button3 = new MenuItem("Algorithm");
        MenuItem button4 = new MenuItem("Clear");
        button1.setOnAction(renameE);
        button2.setOnAction(deleteE);
        button3.setOnAction(algorithmE);
        button4.setOnAction(clearE);

        file.getItems().addAll(newPane, save, load);
        edit.getItems().addAll(button1, button2, button3, button4);
        menuBar.getMenus().addAll(file, edit, help);
    }

    private void toolBarInit() {
        toolBar.prefWidthProperty().bind(widthProperty());
        Button button1 = null, button2 = null, button3 = null, button4 = null;

        try {
            button1 = new Button("", new ImageView(new Image(new FileInputStream("images/rename.png"))));
            button2 = new Button("", new ImageView(new Image(new FileInputStream("images/delete.png"))));
            button3 = new Button("", new ImageView(new Image(new FileInputStream("images/algorithm.png"))));
            button4 = new Button("", new ImageView(new Image(new FileInputStream("images/clear.png"))));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        button1.setOnAction(renameE);
        button2.setOnAction(deleteE);
        button3.setOnAction(algorithmE);
        button4.setOnAction(clearE);
        toolBar.getItems().addAll(button1, button2, button3, button4);
    }

    private void tabPaneInit() {
        tabPane.prefWidthProperty().bind((widthProperty()));
        tabPane.prefHeightProperty().bind(heightProperty());
    }
}
