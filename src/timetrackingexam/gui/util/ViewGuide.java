
package timetrackingexam.gui.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author fauxtistic
 */
public class ViewGuide {
    
    public static final String LOGIN_VIEW_PATH = "/timetrackingexam/gui/view/Login.fxml";
    public static final String LOGIN_VIEW_TITLE = "Login as user";
    public static final String PASSWORD_VIEW_PATH = "/timetrackingexam/gui/view/promts/ChangePasswordView.fxml";
    public static final String PASSWORD_VIEW_TITLE = "Change your password";
    public static final String PROJECTS_OVERVIEW_PATH = "/timetrackingexam/gui/view/ProjectsOverview.fxml";
    public static final String PROJECTS_OVERVIEW_TITLE = "Projects Overview";
    public static final String PROJECT_MANAGEMENT_PATH = "/timetrackingexam/gui/view/ProjectManagementView.fxml";
    public static final String PROJECT_MANAGEMENT_TITLE = "Project Management";
    public static final String USER_MANAGEMENT_PATH = "/timetrackingexam/gui/view/UserManagementView.fxml";
    public static final String USER_MANAGEMENT_TITLE = "User Management";
    
    /**
     * Opens a new view
     * @param viewPath path to .fxml file
     * @param title of new view
     * @param primStage stage the new view is opened from
     * @param closeCurrentView if the currently displaying view should close when opening new view
     * @param disableInputToOtherViews if the new view should allows user to interact with the new view only 
     */
    public static void openView(String viewPath, String title, Stage primStage, boolean closeCurrentView, boolean disableInputToOtherViews) {
        try {
            Parent loader = FXMLLoader.load(ViewGuide.class.getResource(viewPath));
            Scene scene = new Scene(loader);            
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);            
            if (closeCurrentView) {
                stage.show();
                primStage.close();
            }
            else {
                stage.initOwner(primStage);
                if (disableInputToOtherViews) {
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.showAndWait();
                }
                else {
                    stage.show();
                }
                                
            }
            
        } catch (IOException e) {
            AlertFactory.showErrorAlert("Could not open new window");
            Logger.getLogger(ViewGuide.class.getName()).log(Level.SEVERE, null, e);
            
        }
    }
    
    /**
     * Opens logout view and closes currently displaying view
     * @param primStage stage the new view is opened from
     */
    public static void logout(Stage primStage) {
        openView(LOGIN_VIEW_PATH, LOGIN_VIEW_TITLE, primStage, true, true);
    }
    
    /**
     * Opens change password view on top of currently displaying view
     * @param primStage stage the new view is opened from
     */
    public static void changePasswordView(Stage primStage) {
        openView(PASSWORD_VIEW_PATH, PASSWORD_VIEW_TITLE, primStage, false, true);
    }
    
    /**
     * Opens project overview and closes currently displaying view
     * @param primStage stage the new view is opened from
     */
    public static void projectsOverview(Stage primStage) {
        openView(PROJECTS_OVERVIEW_PATH, PROJECTS_OVERVIEW_TITLE, primStage, true, true);
    }
    
    /**
     * Opens project management view and closes currently displaying view
     * @param primStage stage the new view is opened from
     */
    public static void projectManagementView(Stage primStage) {
        openView(PROJECT_MANAGEMENT_PATH, PROJECT_MANAGEMENT_TITLE, primStage, true, true);
    }
    
    /**
     * Opens user management view and closes currently displaying view
     * @param primStage stage the new view is opened from
     */
    public static void userManagementView(Stage primStage) {
        openView(USER_MANAGEMENT_PATH, USER_MANAGEMENT_TITLE, primStage, true, true);
    }
    
}
