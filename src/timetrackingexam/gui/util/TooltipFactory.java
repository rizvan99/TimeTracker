
package timetrackingexam.gui.util;

import javafx.scene.control.Tooltip;
import javafx.util.Duration;

/**
 *
 * @author fauxtistic
 */
public class TooltipFactory {
    
    public static final int DEFAULT_MILLIS_SHOW_DELAY = 500;
    public static final int DEFAULT_MILLIS_HIDE_DELAY = 0;
    
    /**
     * Creates and returns a tooltip with "default" values for delay in showing and hiding it
     * @param text to be displayed in tooltip
     * @return tooltip
     */
    public static Tooltip create(String text) {
        return create(text, DEFAULT_MILLIS_SHOW_DELAY, DEFAULT_MILLIS_HIDE_DELAY);
    }
    
    /**
     * Creates and returns a tooltip
     * @param text to be displayed in tooltip
     * @param millisShowDelay delay in milliseconds before displaying tooltip when hovering over node
     * @param millisHideDelay delay in milliseconds before hiding tooltip when no longer hovering over node
     * @return tooltip
     */
    public static Tooltip create(String text, int millisShowDelay, int millisHideDelay) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(Duration.millis(millisShowDelay));
        tooltip.setHideDelay(Duration.millis(millisHideDelay));
        return tooltip;
    }
    
}
