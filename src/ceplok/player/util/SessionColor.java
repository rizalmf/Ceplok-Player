/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceplok.player.util;

import java.util.prefs.Preferences;

/**
 *
 * @author RIZAL
 */
public class SessionColor {
    private Preferences p;
    public void setColor(String color){
        p = Preferences.userRoot().node("ceplok_properties");
        p.put("color", color);
    }
    public String getColor(){
        p = Preferences.userRoot().node("ceplok_properties");
        return p.get("color", null);
    }
}
