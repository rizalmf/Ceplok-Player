/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceplok.player.model;

import java.io.File;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;

/**
 *
 * @author RIZAL
 */
public class DataProp {

    private final ObjectProperty<Media> mediaData = new SimpleObjectProperty<>();
    private final ObjectProperty<File> fileData = new SimpleObjectProperty<>();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();

    public String getDuration() {
        return duration.get();
    }

    public void setDuration(String value) {
        duration.set(value);
    }

    public StringProperty durationProperty() {
        return duration;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String value) {
        title.set(value);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public File getFileData() {
        return fileData.get();
    }

    public void setFileData(File value) {
        fileData.set(value);
    }

    public ObjectProperty fileDataProperty() {
        return fileData;
    }

    public Media getMediaData() {
        return mediaData.get();
    }

    public void setMediaData(Media value) {
        mediaData.set(value);
    }

    public ObjectProperty mediaDataProperty() {
        return mediaData;
    }
    
}
