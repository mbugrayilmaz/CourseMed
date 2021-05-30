package org.coursemed.gui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.coursemed.classes.Context;
import org.coursemed.classes.LoggingManager;
import org.coursemed.gui.App;

import java.io.IOException;

public class AdminMainController {

    @FXML
    private void onManageStudents(ActionEvent event) {
        try {
            App.setRoot("admin_manage_students");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onManageTeachers(ActionEvent event) {
        try {
            App.setRoot("admin_manage_teachers");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onManageAdmins(ActionEvent event) {
        try {
            App.setRoot("admin_manage_admins");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEditProfile(ActionEvent event) {
        try {
            Context.pushContext("admin");

            App.setRoot("edit_profile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLogout(ActionEvent event) {
        try {
            LoggingManager.logout();

            App.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
