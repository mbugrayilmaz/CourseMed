package org.coursemed.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.coursemed.classes.LoggingManager;
import org.coursemed.classes.Student;
import org.coursemed.classes.User;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentEditProfileController {

    private Student loggedUser;

    private ArrayList<Student> studentList;

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
    private void onSaveChanges(ActionEvent event) {
        if (isValid()) {
            loggedUser.setUsername(usernameField.getText());
            loggedUser.setPassword(passwordField.getText());
            loggedUser.setFirstName(firstNameField.getText());
            loggedUser.setLastName(lastNameField.getText());

            CustomDbTools.updateStudent(loggedUser);

            try {
                App.setRoot("student_main");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        try {
            App.setRoot("student_main");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        if (usernameAlreadyExists(studentList, usernameField.getText())) {
            infoLabel.setText("Username already exists, please choose another one");

            return false;
        }

        infoLabel.setText("");

        return true;
    }

    private boolean usernameAlreadyExists(ArrayList<? extends User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    @FXML
    private void initialize() {
        loggedUser = (Student) LoggingManager.getLoggedUser();

        usernameField.setText(loggedUser.getUsername());
        passwordField.setText(loggedUser.getPassword());
        repeatPasswordField.setText(loggedUser.getPassword());

        firstNameField.setText(loggedUser.getFirstName());
        lastNameField.setText(loggedUser.getLastName());

        try {
            studentList = CustomDbTools.getStudents();

            studentList.remove(loggedUser);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
