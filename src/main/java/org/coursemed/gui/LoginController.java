package org.coursemed.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.coursemed.App;
import org.coursemed.classes.*;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    private ToggleGroup toggleGroup;

    private ArrayList<Student> studentList;
    private ArrayList<Teacher> teacherList;
    private ArrayList<Admin> adminList;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label infoLabel;
    @FXML
    private RadioButton studentRadioButton;
    @FXML
    private RadioButton teacherRadioButton;
    @FXML
    private RadioButton adminRadioButton;

    @FXML
    private void onLogin(ActionEvent event) {
        if (isValid()) {
            String userType;
            ArrayList<? extends User> list;

            String username = usernameField.getText();
            String password = passwordField.getText();

            if (studentRadioButton.isSelected()) {
                userType = "student";
                list = studentList;
            } else if (teacherRadioButton.isSelected()) {
                userType = "teacher";
                list = teacherList;
            } else {
                userType = "admin";
                list = adminList;
            }

            goToUserPage(list, username, password, userType);
        }
    }

    @FXML
    private void onSignup(ActionEvent event) {
        User user;

        if (studentRadioButton.isSelected()) {
            user = new Student();
        } else {
            user = new Teacher();
        }

        user.setUsername(usernameField.getText());
        user.setPassword(passwordField.getText());

        Context.pushContext(user);

        try {
            App.setRoot("signup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        try {
            studentList = CustomDbTools.getStudents();
            teacherList = CustomDbTools.getTeachers();
            adminList = CustomDbTools.getAdmins();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(studentRadioButton);
        toggleGroup.getToggles().add(teacherRadioButton);
        toggleGroup.getToggles().add(adminRadioButton);
    }

    private void goToUserPage(List<? extends User> userList, String username, String password, String type) {
        User loggedUser = LoggingManager.login(userList, username, password);

        if (loggedUser != null) {
            try {
                infoLabel.setText("");

                App.setRoot(type + "_main");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            infoLabel.setText("Username or password is not correct");
        }
    }

    private boolean isValid() {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            infoLabel.setText("Please fill in all fields");

            return false;
        }

        infoLabel.setText("");

        return true;
    }
}
