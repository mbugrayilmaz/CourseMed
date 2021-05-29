package org.coursemed.classes;

import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class Subject extends Item {
    private final SimpleStringProperty title = new SimpleStringProperty();
    private final SimpleStringProperty videoUrl = new SimpleStringProperty();

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getVideoUrl() {
        return videoUrl.get();
    }

    public SimpleStringProperty videoUrlProperty() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl.set(videoUrl);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return this.getId() == subject.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
