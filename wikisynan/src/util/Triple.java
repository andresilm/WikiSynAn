/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author aluna
 */
public class Triple<X,Y,Z> { 
  private X x; 
  private Y y; 
  private Z z;
 
  
  public Triple(X x, Y y, Z z) { 
    this.x = x; 
    this.y = y; 
    this.z = z;
    
  } 

    /**
     * @return the x
     */
    public X getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(X x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public Y getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(Y y) {
        this.y = y;
    }

    /**
     * @return the z
     */
    public Z getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(Z z) {
        this.z = z;
    }

    

    
} 