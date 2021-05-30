package org.coursemed.classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.coursemed.tools.Tools;

import java.util.ArrayList;
import java.util.Objects;

public class Course extends Item {
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleDoubleProperty price = new SimpleDoubleProperty();
    private final SimpleStringProperty priceString = new SimpleStringProperty();

    private final SimpleObjectProperty<Teacher> teacher = new SimpleObjectProperty<>();

    private ArrayList<Subject> subjects = new ArrayList<>();

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getPriceString() {
        return Tools.getFormattedBalance(price.get());
    }

    public Teacher getTeacher() {
        return teacher.get();
    }

    public SimpleObjectProperty<Teacher> teacherProperty() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher.set(teacher);
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void deleteSubject(Subject subject) {
        subjects.remove(subject);
    }

    public String getTeacherName() {
        return teacher.get().getFirstName() + " " + teacher.get().getLastName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return this.getId() == course.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
