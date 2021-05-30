package org.coursemed.gui.student;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.coursemed.classes.LoggingManager;
import org.coursemed.classes.Student;
import org.coursemed.gui.App;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;

public class StudentDepositMoneyController {
    private SimpleObjectProperty<Student> loggedUser=new SimpleObjectProperty<>();

    @FXML
    private Label balanceLabel;

    @FXML
    private TextField amountField;

    @FXML
    private Label infoLabel;

    @FXML
    private void onDeposit(ActionEvent event) {
        if (isValid()) {
            double amount = Double.parseDouble(amountField.getText());

            loggedUser.get().deposit(amount);

            balanceLabel.setText(loggedUser.get().getBalanceString());

            amountField.setText("0");

            CustomDbTools.updateStudent(loggedUser.get());
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            App.setRoot("student_main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Student getLoggedUser() {
        return loggedUser.get();
    }

    private boolean isValid() {
        if (amountField.getText().isEmpty()) {
            infoLabel.setText("You must specify an amount");

            return false;
        }

        double amount;

        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (final NumberFormatException e) {
            infoLabel.setText("Amount must be numerical");

            return false;
        }

        if (amount <= 0) {
            infoLabel.setText("Amount must be positive");

            return false;
        }

        infoLabel.setText("");

        return true;
    }

    @FXML
    private void initialize() {
        loggedUser.set((Student) LoggingManager.getLoggedUser());

        balanceLabel.setText(loggedUser.get().getBalanceString());
    }
}
