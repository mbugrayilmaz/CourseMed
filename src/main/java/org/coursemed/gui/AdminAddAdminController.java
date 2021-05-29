package org.coursemed.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.coursemed.classes.Admin;
import org.coursemed.classes.User;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminAddAdminController {
    private ArrayList<Admin> adminList;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label infoLabel;

    @FXML
    private void initialize() {
        try {
            adminList = CustomDbTools.getAdmins();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void onAdd(ActionEvent event) throws IOException {
        if (isValid()) {
            Admin admin = new Admin();

            admin.setUsername(usernameField.getText());
            admin.setPassword(passwordField.getText());
            admin.setFirstName(firstNameField.getText());
            admin.setLastName(lastNameField.getText());

            CustomDbTools.addAdmin(admin);

            App.setRoot("admin_manage_admins");
        }
    }

    @FXML
    private void onCancel(ActionEvent event) throws IOException {
        App.setRoot("admin_manage_admins");
    }

    private boolean isValid() {
        if (usernameField.getText().isEmpty() ||
                passwordField.getText().isEmpty() ||
                repeatPasswordField.getText().isEmpty() ||
                firstNameField.getText().isEmpty() ||
                lastNameField.getText().isEmpty()) {

            infoLabel.setText("Please fill in all fields");

            return false;
        }

        if (!passwordField.getText().equals(repeatPasswordField.getText())) {
            infoLabel.setText("Passwords do not match");

            return false;
        }

        if (usernameAlreadyExists(adminList, usernameField.getText())) {
            infoLabel.setText("Username already exists, please choose another one");

            return false;
        }

        infoLabel.setText("");

        return true;
    }

    private boolean usernameAlreadyExists(List<? extends User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }
}
