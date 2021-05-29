package org.coursemed.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.coursemed.classes.Admin;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;
import java.sql.SQLException;

public class AdminManageAdminsController {

    private ObservableList<Admin> adminList = FXCollections.observableArrayList();

    @FXML
    private TableView<Admin> adminTable;

    @FXML
    private void initialize() {
        try {
            adminList.addAll(CustomDbTools.getAdmins());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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

            CustomDbTools.deleteItem(admin,"admin");

            adminList.remove(admin);
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
}
