package org.coursemed.classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import org.coursemed.tools.Tools;

import java.util.ArrayList;

public class Student extends User {
    private SimpleDoubleProperty balance = new SimpleDoubleProperty();
    private SimpleStringProperty balanceString = new SimpleStringProperty();
    private ArrayList<Course> courses;

    public double getBalance() {
        return balance.get();
    }

    public void setBalance(double balance) {
        this.balance.set(balance);
    }

    public void deposit(double balance) {
        setBalance(getBalance() + balance);
    }

    public boolean withdraw(double balance) {
        if (getBalance() - balance>=0){
            this.balance.set(getBalance() - balance);
            return true;
        }

        return false;
    }

    public String getBalanceString() {
        return Tools.getFormattedBalance(balance.get());
    }
}
