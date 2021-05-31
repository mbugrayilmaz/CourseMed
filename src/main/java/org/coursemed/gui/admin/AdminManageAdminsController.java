package org.coursemed.gui.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.coursemed.App;
import org.coursemed.classes.Admin;
import org.coursemed.classes.LoggingManager;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;
import java.sql.SQLException;

public class AdminManageAdminsController {

    private Admin loggedUser;

    @FXML
    private Label infoLabel;

    @FXML
    private Button deleteAdminButton;

    private ObservableList<Admin> adminList = FXCollections.observableArrayList();

    @FXML
    private TableView<Admin> adminTable;

    @FXML
    private void onAddAdmin(ActionEvent event) {
        try {
            App.setRoot("admin_add_admin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDeleteAdmin(ActionEvent event) {
        if (adminTable.getSelectionModel().getSelectedIndex() != -1) {
            Admin admin = adminTable.getSelectionModel().getSelectedItem();

            if (admin.equals(loggedUser)) {
                infoLabel.setText("You cannot delete yourself");
            } else {
                CustomDbTools.deleteItem(admin, "admin");

                adminList.remove(admin);

                infoLabel.setText("");
            }
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            App.setRoot("admin_main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Admin> getAdminList() {
        return adminList;
    }

    @FXML
    private void initialize() {
        try {
            loggedUser = (Admin) LoggingManager.getLoggedUser();

            adminList.addAll(CustomDbTools.getAdmins());

            adminTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    deleteAdminButton.setDisable(false);
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
