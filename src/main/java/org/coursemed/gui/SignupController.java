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

public class SignupController {

    private User user;

    private ArrayList<Student> studentList;
    private ArrayList<Teacher> teacherList;

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
    private RadioButton studentToggle;

    @FXML
    private RadioButton teacherToggle;

    @FXML
    private void onSignup(ActionEvent event) throws IOException {
        if (isValid()) {
            if (studentToggle.isSelected()) {
                Student student = (Student) user;
                student.setUsername(usernameField.getText());
                student.setPassword(passwordField.getText());
                student.setFirstName(firstNameField.getText());
                student.setLastName(lastNameField.getText());
                student.setBalance(0);

                CustomDbTools.addStudent(student);

                studentList.add(student);

                LoggingManager.setLoggedUser(student);

                App.setRoot("student_main");
            } else {
                Teacher teacher = (Teacher) user;
                teacher.setUsername(usernameField.getText());
                teacher.setPassword(passwordField.getText());
                teacher.setFirstName(firstNameField.getText());
                teacher.setLastName(lastNameField.getText());
                teacher.setBalance(0);

                CustomDbTools.addTeacher(teacher);

                teacherList.add(teacher);

                LoggingManager.setLoggedUser(teacher);

                App.setRoot("teacher_main");
            }
        }
    }

    @FXML
    private void onCancel(ActionEvent event) throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();

        toggleGroup.getToggles().addAll(studentToggle, teacherToggle);

        user = (User) Context.popContext();

        if (user instanceof Student) {
            studentToggle.setSelected(true);
        } else {
            teacherToggle.setSelected(true);
        }

        usernameField.setText(user.getUsername());

        passwordField.setText(user.getPassword());

        repeatPasswordField.setText(user.getPassword());

        try {
            studentList = CustomDbTools.getStudents();
            teacherList = CustomDbTools.getTeachers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean usernameAlreadyExists(ArrayList<? extends User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
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

        if (studentToggle.isSelected()) {
            if (usernameAlreadyExists(studentList, usernameField.getText())) {
                infoLabel.setText("Username already exists, please choose another one");

                return false;
            }
        } else {
            if (usernameAlreadyExists(teacherList, usernameField.getText())) {
                infoLabel.setText("Username already exists, please choose another one");

                return false;
            }
        }

        infoLabel.setText("");

        return true;
    }
}
