/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceplok.player.Media.Visualizer.impl;

import ceplok.player.Media.Visualizer.Visualizer;
import static java.lang.Integer.min;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author RIZAL
 */
public class StandardVisualizer implements Visualizer{
    private final String name = "Standard Visualizer";
    
    private Integer numBands;
    private HBox visualBox;
    
    private final Double bandHeightPercentage = 1.3;
    private final Double minEllipseRadius = 10.0;  // 10.0
    
    private Double width = 0.0;
    private Double height = 0.0;
    
    private Double bandWidth = 0.0;
    private Double bandHeight = 0.0;
    private Double halfBandHeight = 0.0;
    
    private final Double startHue = 71.0;
    
    private Rectangle[] rects;
    
    @Override
    public void start(Integer numBands, HBox visualBox) {
        end();
        
        this.numBands = numBands;
        this.visualBox = visualBox;
        visualBox.setPrefWidth(338);
        visualBox.setPrefHeight(166);
        height =362d;
        width =500d;
        bandWidth = width / numBands;
        bandHeight = height * bandHeightPercentage;
        halfBandHeight = bandHeight / 2;
        rects = new Rectangle[numBands];
        HBox box = new HBox(0.8);
        VBox.setVgrow(box, Priority.ALWAYS);
        box.setStyle("-fx-alignment:bottom-left;");
        StackPane spane = new StackPane();
        for (int i = 0; i < numBands; i++) {
            Rectangle rect = new Rectangle();
//            pane.setStyle("-fx-background-color:black;");
            rect.setWidth(bandWidth / 2);
            rect.setHeight(minEllipseRadius);
            box.getChildren().add(rect);
            rects[i] = rect;
        }
        box.setPrefSize(332, 162);
        box.setClip(new Rectangle(332, 162));
        Pane pane = new Pane(box);
        pane.setClip(new Rectangle(332, 162));
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

    @Override
    public void update(double timestamp, double duration, float[] magnitudes, float[] phases) {
        if (rects == null) {
            return;
        }
        Integer num = min(rects.length, magnitudes.length);
        //lock yellow
        for (int i = 0; i < 3; i++) {
            rects[i].setHeight((((45.0 + magnitudes[i])/60.0) * halfBandHeight + minEllipseRadius));
            rects[i].setFill(Color.hsb(startHue + (magnitudes[i] /5.2d), 1.0, 1.0));
        }
        for (int i = 3; i < num; i++) {
            rects[i].setHeight((((60.0 + magnitudes[i])/60.0) * halfBandHeight + minEllipseRadius));
            rects[i].setFill(Color.hsb(startHue + (magnitudes[i] /5.2d), 1.0, 1.0));
        }
    }
    
}
