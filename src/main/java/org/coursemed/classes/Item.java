package org.coursemed.classes;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Item {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty(0);

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }
}
