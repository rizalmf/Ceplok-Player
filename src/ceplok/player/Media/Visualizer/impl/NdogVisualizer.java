/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceplok.player.Media.Visualizer.impl;

import ceplok.player.CeplokPlayer;
import ceplok.player.Media.Visualizer.Visualizer;
import static java.lang.Integer.min;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author RIZAL
 */
public class NdogVisualizer implements Visualizer{
    private final String name = "Donut Visualizer";
    
    private Integer numBands;
    private HBox visualBox;
    
    private final Double bandHeightPercentage = 1.3;
    private final Double minEllipseRadius = 10.0;  // 10.0
    
    private Double width = 0.0;
    private Double height = 0.0;
    
    private Double bandWidth = 0.0;
    private Double bandHeight = 0.0;
    private Double halfBandHeight = 0.0;
    private StackPane spane;
    private final Double startHue = 71.0;
    
    private Rectangle[] rects;
    
    @Override
    public void start(Integer numBands, HBox visualBox) {
        end();
        
        this.numBands = numBands;
        this.visualBox = visualBox;
        visualBox.setPrefWidth(166);
        visualBox.setPrefHeight(166);
        height =422d;
        width =500d;
        bandWidth = width / numBands;
        bandHeight = height * bandHeightPercentage;
        halfBandHeight = bandHeight / 2;
        rects = new Rectangle[numBands];
        spane = new StackPane();
        spane.setRotate(0);
        double x =(double) 360/numBands;
        for (int i = 0; i < numBands; i++) {
            Rectangle rect = new Rectangle();
//            rect.setStyle("-fx-background-color:black;");
            rect.setWidth(bandWidth / 2);
            rect.setHeight(minEllipseRadius);
            rect.setRotate(i*x);
            spane.getChildren().add(rect);
            rects[i] = rect;
        }
        spane.setPrefSize(162, 162);
//        spane.setClip(new Rectangle(162, 162));
        Image img = new Image(CeplokPlayer.class.getResourceAsStream("views/images/ceplok1.png"));
        ImageView imgView = new ImageView(img);
        imgView.setPreserveRatio(false);
        imgView.setFitWidth(80);imgView.setFitHeight(80);
        imgView.prefWidth(80); imgView.prefHeight(80);
        spane.getChildren().add(imgView);
        Pane pane = new Pane(spane);
        pane.setClip(new Rectangle(162, 162));
        visualBox.getChildren().clear();
        visualBox.getChildren().add(pane);
    }

    @Override
    public void end() {//flush
        if (rects != null) {
//            for (Rectangle r : rects) {
//                visualBox.getChildren().remove(r);
//            }
            visualBox.getChildren().clear();
            rects = null;
        } 
    }

    @Override
    public String getName() {
        return name;
    }
    private double xx =0;
    @Override
    public void update(double timestamp, double duration, float[] magnitudes, float[] phases) {
        if (rects == null) {
            return;
        }
        Integer num = min(rects.length, magnitudes.length);
        //lock yellow
        for (int i = 0; i < 3; i++) {
            rects[i].setHeight((((46.0 + magnitudes[i])/60.0) * halfBandHeight + minEllipseRadius));
            rects[i].setFill(Color.hsb(startHue + (magnitudes[i] /5.2d), 1.0, 1.0));
        }
        for (int i = 3; i < num; i++) {
            rects[i].setHeight((((71.0 + magnitudes[i])/60.0) * halfBandHeight + minEllipseRadius));
            rects[i].setFill(Color.hsb(startHue + (magnitudes[i] /5.2d), 1.0, 1.0));
        }
        spane.setRotate(xx++);
    }
}
