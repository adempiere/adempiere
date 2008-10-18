/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * Created on Dec 15, 2005 by praveen
 *
 */
package org.posterita.beans;

public class TrendsBean 
{
    private String model;
    private String colour;
    private int a1,m1;
    private int a2,m2;
    private int a3,m3;
    private int sA1,sA2,sA3;
    private int sM1,sM2,sM3;    
    
    public TrendsBean(){}    

    
    public int getA1() {
        return a1;
    }
    public void setA1(int a1) {
        this.a1 = a1;
    }
    public int getA2() {
        return a2;
    }
    public void setA2(int a2) {
        this.a2 = a2;
    }
    public int getA3() {
        return a3;
    }
    public void setA3(int a3) {
        this.a3 = a3;
    }
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public int getM1() {
        return m1;
    }
    public void setM1(int m1) {
        this.m1 = m1;
    }
    public int getM2() {
        return m2;
    }
    public void setM2(int m2) {
        this.m2 = m2;
    }
    public int getM3() {
        return m3;
    }
    public void setM3(int m3) {
        this.m3 = m3;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getSA1() {
        return sA1;
    }
    public void setSA1(int sa1) {
        sA1 = sa1;
    }
    public int getSA2() {
        return sA2;
    }
    public void setSA2(int sa2) {
        sA2 = sa2;
    }
    public int getSA3() {
        return sA3;
    }
    public void setSA3(int sa3) {
        sA3 = sa3;
    }
    public int getSM1() {
        return sM1;
    }
    public void setSM1(int sm1) {
        sM1 = sm1;
    }
    public int getSM2() {
        return sM2;
    }
    public void setSM2(int sm2) {
        sM2 = sm2;
    }
    public int getSM3() {
        return sM3;
    }
    public void setSM3(int sm3) {
        sM3 = sm3;
    }
}
