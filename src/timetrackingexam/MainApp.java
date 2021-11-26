/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import timetrackingexam.gui.model.AppModel;

/**
 *
 * @author Rizvan
 */
public class MainApp extends Application
{
    
    public AppModel am; 
    
    @Override
    public void start(Stage stage) throws Exception
    {
        am = AppModel.getInstance();
        
        Parent root = FXMLLoader.load(getClass().getResource("gui/view/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Login as user");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
        
    }

    /**
     * Testing stop timer methods
     * @throws Exception 
     */
    
    @Override
    public void stop() throws Exception {
        if(am.timerIsRunning()){
            am.stopTimer();
        }
        super.stop();
    }
    
}
