package mainPane.drawPane.algorithm;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.List;

public class Drawer {
    public void makeColorNode(Shape toChange) {
        toChange.setStroke(Color.RED);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void makeColorEdges(List<? extends Shape> toChange) {
        for (Shape i : toChange) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i.setStroke(Color.PURPLE);
        }
    }

    public void makeColorBridges(List<? extends Shape> toChange) {
        for (Shape i : toChange)
            i.setStroke(Color.BLUE);
    }
}
