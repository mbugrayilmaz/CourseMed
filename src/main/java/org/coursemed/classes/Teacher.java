package org.coursemed.classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import org.coursemed.tools.Tools;

import java.util.ArrayList;
import java.util.stream.DoubleStream;

public class Teacher extends User {
    private SimpleDoubleProperty balance = new SimpleDoubleProperty();
    private SimpleStringProperty balanceString = new SimpleStringProperty();
    private ArrayList<Double> ratingList = new ArrayList<>();

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

    public ArrayList<Double> getRatingList() {
        return ratingList;
    }

    public void setRatingList(ArrayList<Double> ratingList) {
        this.ratingList = ratingList;
    }

    public double getRating() {
        return ratingList.stream().mapToDouble(d -> d).average().orElse(0);
    }

    public String getRatingAndTotal() {
        return getRating() + "/10, Total ratings: " + ratingList.size();
    }

    public String getNameWithRating() {
        return getFullName() + " (" + getRatingAndTotal() + ")";
    }
}
