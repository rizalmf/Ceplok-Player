/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceplok.player.Media.Visualizer;

import javafx.scene.layout.HBox;

/**
 *
 * @author RIZAL
 */
public interface Visualizer {
    public void start(Integer numBands, HBox visualBox);
    public void end();
    public String getName();
    public void update(double timestamp, double duration, float[] magnitudes, float[] phases);
}
