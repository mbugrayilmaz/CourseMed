package org.coursemed.classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import org.coursemed.tools.Tools;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

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
        DecimalFormat decimalFormat = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));

        double rating = ratingList.stream().mapToDouble(d -> d).average().orElse(0);

        return Double.parseDouble(decimalFormat.format(rating));
    }

    public String getRatingAndTotal() {
        return getRating() + "/10, Total ratings: " + ratingList.size();
    }

    public String getNameWithRating() {
        return getFullName() + " (" + getRatingAndTotal() + ")";
    }
}
