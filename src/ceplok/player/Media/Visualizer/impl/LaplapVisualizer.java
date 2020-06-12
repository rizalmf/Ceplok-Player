/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceplok.player.Media.Visualizer.impl;

import ceplok.player.Media.Visualizer.Visualizer;
import static java.lang.Integer.min;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author RIZAL
 */
public class LaplapVisualizer implements Visualizer{
    private final String name = "Laplap Visualizer";
    
    private Integer numBands;
    private HBox visualBox;
    private Double width = 0.0;
    private Double height = 0.0;
    private Double degree = 0.0;
    private Double midX = 0.0;
    private Double midY = 0.0;
    private Double radiusCenter = 55.0;
    private Double radiusRote = 20.0;//40
    private Double roteAngle = 0.0;
    private Double bandHeight = 0.0;
    private Double halfBandHeight = 0.0;
    private Pane pane;
    private Pane paneX;
    
    private final Double startHue = 260.0;
   
    private Circle[] circles;
    private Rectangle[] rectangles;
       
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void start(Integer numBands, HBox visualBox) {
        end();
        
        this.numBands = numBands;
        this.visualBox = visualBox;
        
        height = visualBox.getHeight();
        width = visualBox.getWidth();
        midX = (width/2)-3;
        midY = height/2;
        pane = new Pane();
        pane.setPrefWidth(width);
        pane.setPrefHeight(height);
        paneX = new Pane(pane);
        pane.toFront();
        pane.setLayoutX(-3);
//        pane.setStyle("-fx-background-color: white;");
//        pane.setPadding(new Insets(0, -4, 0, -4));
        circles = new Circle[numBands];
        rectangles = new Rectangle[numBands];
        for (int i = 0; i < numBands; i++) {
            Circle circle = new Circle();
            degree = ((double)i * ((2* PI) / numBands));
           
            circle.setCache(true);
            double x =midX + (radiusCenter * cos(degree));
            double y =midY - (radiusCenter * sin(degree));
            circle.setCenterX(x);
            circle.setCenterY(y);
            circle.setRadius((double) 10);//radiusRote/2.5
            circle.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
            pane.getChildren().add(circle);
            circles[i] = circle; 
            
            Rectangle rect = new Rectangle(x-4.5, y-4.5, 9, 9);
            rect.setArcWidth(2);rect.setArcHeight(2);
            pane.getChildren().add(rect);
            rectangles[i] = rect;
        }
        visualBox.getChildren().clear();
        visualBox.getChildren().add(paneX);
    }
    @Override
    public void end() {
         if (circles != null) {
//             for (Circle circle : circles) {
//                 visualBox.getChildren().remove(circle);
//             }
            visualBox.getChildren().clear();
            circles = null;
        } 
    }
    
    @Override
    public void update(double timestamp, double duration, float[] magnitudes, float[] phases) {
        if (circles == null) {
            return;
        }
       
        Integer num = min(circles.length, magnitudes.length);
        
        for (int i = 0; i < num; i++) {
            circles[i].setFill(Color.hsb(startHue - (magnitudes[i] * -6.0), 1.0, 1.0, 1.0));
            rectangles[i].setFill(Color.hsb((startHue/2) - (magnitudes[i] * -6.0), 1.0, 1.0, 1.0));
            rectangles[i].setRotate(rectangles[i].getRotate()-1);
        }
        double A =(double) (60.0 +magnitudes[0])/55;A *= 100;
        String as = (A+"").replaceAll("\\.", "").substring(0, 2);
        paneX.setStyle("-fx-background-color: #ffffff"+as+";" );
        pane.setRotate(pane.getRotate()+1);
    }
    
}
