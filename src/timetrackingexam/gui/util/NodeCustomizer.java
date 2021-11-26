
package timetrackingexam.gui.util;

import javafx.scene.Node;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;

/**
 *
 * @author fauxtistic
 */
public class NodeCustomizer {
    
    /**
     * Sets effects for hovering over node with mouse, and for no longer hovering over it
     * @param node to set effects for
     */
    public static void nodeEffect(Node node) {
        node.setOnMouseEntered(e -> {
            DropShadow ds = new DropShadow();
            ds.setInput(new Bloom(0.7));
            node.setEffect(ds);
        });
        node.setOnMouseExited(e -> {
           node.setEffect(null); 
        });
    }
    
}
