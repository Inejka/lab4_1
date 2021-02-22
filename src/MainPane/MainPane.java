package MainPane;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainPane extends VBox {


    public MainPane() {
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(widthProperty());
        Menu menu1 = new Menu("Menu1"), menu2 = new Menu("Menu2");
        MenuItem menuItem1 = new MenuItem("MenuItem1");
        MenuItem menuItem2 = new MenuItem("MenuItem2");
        MenuItem menuItem3 = new MenuItem("MenuItem3");
        menu1.getItems().addAll(menuItem1, menuItem2, menuItem3);
        menuBar.getMenus().add(menu1);
        menuBar.getMenus().add(menu2);

        ToolBar toolBar = new ToolBar();
        toolBar.prefWidthProperty().bind(widthProperty());
        Button button1 = new Button("Hi mark");
        Button button2 = new Button("Hi Jhony");
        toolBar.getItems().addAll(button1, button2);

        TabPane tabPane = new TabPane();
        DrawPane drawPane = new DrawPane();

        Tab tab = new Tab("Hi");
        tab.setContent(drawPane);
        tabPane.getTabs().addAll(tab);

        tabPane.prefWidthProperty().bind((widthProperty()));
        tabPane.prefHeightProperty().bind(heightProperty());
        getChildren().addAll(menuBar,toolBar,tabPane);
    }
}
