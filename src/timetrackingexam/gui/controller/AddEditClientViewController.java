
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import timetrackingexam.be.Client;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.AlertFactory;
import timetrackingexam.gui.util.NodeCustomizer;
import timetrackingexam.gui.util.TooltipFactory;

/**
 * FXML Controller class
 *
 * @author fauxtistic
 */
public class AddEditClientViewController implements Initializable {

    private AppModel am;
    private Client selectedClient;
    
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtRate;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private JFXButton btnSaveClient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        am = AppModel.getInstance();
        selectedClient = am.getCurrentClient();
        if (selectedClient != null) {
            setClientInformation(selectedClient);
        }
        initKeys();
        initTooltips();
        initButtonEffects();
    }    

    /**
     * Displays client information in text fields
     * @param client to display information from
     */
    private void setClientInformation(Client client) {
        txtName.setText(client.getName());
        txtRate.setText(client.getDefaultrate() +"");
    }
    
    /**
     * Initializes actions on enter keystroke for nodes
     */
    private void initKeys() {
        actionOnEnterKey(txtName);
        actionOnEnterKey(txtRate);
    }
    
    /**
     * Sets tooltips for nodes
     */
    private void initTooltips() {
        btnSaveClient.setTooltip(TooltipFactory.create("Click to save client"));
        btnCancel.setTooltip(TooltipFactory.create("Click to exit window without saving changes"));
    }
    
    /**
     * Sets effects for nodes
     */
    private void initButtonEffects() {
        NodeCustomizer.nodeEffect(btnSaveClient);
        NodeCustomizer.nodeEffect(btnCancel);
    }
    
    /**
     * Sets action on using Enter key for node
     * Currently, when pressing enter, an attempt is made to validate information in text fields
     * @param node to apply action on enter keystroke for
     */
    private void actionOnEnterKey(Node node) {
        node.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                validateInformation();
            }
        });
    }
    
    /**
     * Starts process for saving changes by attempting to validate information in textfields
     * @param event 
     */
    @FXML
    private void saveClient(ActionEvent event) {               
        validateInformation();
    }
    
    /**
     * Attempts to validate information in text fields
     * Checks if text fields alle filled out, if rate is positive integer or zero, and if name is not used by another client
     */
    private void validateInformation() {
        String name = txtName.getText().trim();
        int rate;
        if (txtRate.getText().matches("^\\d+$")){
            rate = Integer.parseInt(txtRate.getText());
        } 
        else {
            AlertFactory.showErrorAlert("Hourly rate must be a positive integer or zero");
            return;
        }
        
        if (name.isEmpty()) {
            AlertFactory.showErrorAlert("You must enter a name");
            return;
        }
        
        if (selectedClient != null) {
            if (!name.equals(selectedClient.getName()) && am.checkIfClientNameIsUsed(name)) {
                AlertFactory.showErrorAlert("Name is already used by another client");
            }
            else {
                saveChanges(new Client(rate, name, selectedClient.getId()));
            }
        }
        else {
            if (am.checkIfClientNameIsUsed(name)) {
                AlertFactory.showErrorAlert("Name is already used by another client");
            }
            else {
                saveChanges(new Client(rate, name, 0));
            }
        }
    }
    
    /**
     * Saves changes made
     * Creates a new client or updates an existing one depending on which button was pushing when opening view
     * Also shows confirmation alert which will ask user if user is certain the changes should be made
     * @param client to create or update
     */
    private void saveChanges(Client client) {
        Alert alert = AlertFactory.createConfirmationAlert(String.format("%s%n%s", "Are you sure you want to save this", client));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (selectedClient == null) {
                am.createClient(client);
                am.fetch();
                close();
            } else {
                am.updateClient(client);
                am.fetch();
                close();
            }
        } else {
            alert.close();
        }
    }
    
    /**
     * Asks to close view
     * @param event 
     */
    @FXML
    private void cancel(ActionEvent event) {
        close();
    }
    
    /**
     * Closes view
     */
    private void close() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }    
    
}
