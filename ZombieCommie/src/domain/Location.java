/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 * Class that represents a location
 * @author Chad, Stephan, Matt, Kelly, Ian
 */
public class Location {
    /**
     *The x coordinate
     */
    public float positionX;
    /**
     *The y coordinate
     */
    public float positionY;
     /**
     * Constructor for location
     * @param x the x coordinate of the location
     * @param y the y coordinate of the location
     */
    public Location(float x, float y)
    {
        positionX = x;
        positionY = y;
    }
}
