package org.coursemed.classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import org.coursemed.tools.Tools;

import java.util.ArrayList;

public class Teacher extends User {
    private SimpleDoubleProperty balance = new SimpleDoubleProperty();
    private SimpleStringProperty balanceString = new SimpleStringProperty();
    private SimpleDoubleProperty rating = new SimpleDoubleProperty();
    private ArrayList<Course> courses;

    public double getBalance() {
        return balance.get();
    }

    public void setBalance(double balance) {
        this.balance.set(balance);
    }

    public String getBalanceString() {
        return Tools.getFormattedBalance(balance.get());
    }

    public SimpleDoubleProperty balanceProperty() {
        return balance;
    }

    public void deposit(double balance) {
        setBalance(getBalance() + balance);
    }

    public void withdraw(double balance) {
        setBalance(getBalance() - balance);
    }

    public double getRating() {
        return rating.get();
    }

    public SimpleDoubleProperty ratingProperty() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating.set(rating);
    }
}
