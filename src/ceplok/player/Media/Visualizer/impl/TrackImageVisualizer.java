/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceplok.player.Media.Visualizer.impl;

import ceplok.player.CeplokPlayer;
import ceplok.player.Media.Visualizer.Visualizer;
import javafx.beans.property.DoubleProperty;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author RIZAL
 */
public class TrackImageVisualizer implements Visualizer{
    private final String name = "TrackImage Visualizer";
    
    private HBox visualBox;
    private ImageView trackImg;
    
    private StackPane spane;
    private Pane pane;
    
    public TrackImageVisualizer(ImageView trackImg){
        this.trackImg = trackImg;
    }
    @Override
    public void start(Integer numBands, HBox visualBox) {
        end();
        
        this.visualBox = visualBox;
        visualBox.setPrefWidth(166);
        visualBox.setPrefHeight(166);
        
        spane = new StackPane();
        spane.setRotate(0);
        spane.setPrefSize(162, 162);
        Image img = trackImg.getImage();
        ImageView imgView = new ImageView(img);
        imgView.setPreserveRatio(false);
        imgView.setFitWidth(156);imgView.setFitHeight(156);
        imgView.prefWidth(156); imgView.prefHeight(156);
        Rectangle r = new Rectangle(156, 156);
        r.setArcWidth(6);
        r.setArcHeight(6);
        imgView.setClip(r);
        pane = new Pane();
        pane.setClip(new Rectangle(162, 162));
        
        StackPane pgif = new StackPane();
        pgif.setPrefSize(156, 156);
        Image imgif = new Image(CeplokPlayer.class.getResourceAsStream("views/images/anm.gif"));
        ImageView ivgif = new ImageView(imgif);
        ivgif.setPreserveRatio(false);
        ivgif.setFitWidth(156);ivgif.setFitHeight(156);
        ivgif.prefWidth(156); ivgif.prefHeight(156);
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(-1);
        ivgif.setEffect(ca);
        pgif.getChildren().add(ivgif);
        
        spane.getChildren().addAll(imgView, pgif, pane);
        
        visualBox.getChildren().clear();
        visualBox.getChildren().add(spane);
    }
DoubleProperty dp;
    @Override
    public void end() {//flush
        if (spane != null) {
            visualBox.getChildren().clear();
        }
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void update(double timestamp, double duration, float[] magnitudes, float[] phases) {
        double A =((double)(60.0 +magnitudes[magnitudes.length/3]))/55;A *= 100;
        String as = (A+"").replaceAll("\\.", "").substring(0, 2);
        pane.setStyle("-fx-background-color: #000000"+as+";" );
    }
}
