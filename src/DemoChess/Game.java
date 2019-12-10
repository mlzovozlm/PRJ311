/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoChess;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Bao Anh Luu
 */
public class Game implements Serializable {

    public static final int BLACK = 0, WHITE = 1;
    
    static Unit getUnit(int x, int y, ArrayList<Unit> list) {
        Iterator<Unit> unitIterator = list.iterator();
        while (unitIterator.hasNext()) {
            Unit xUnit = null;
            xUnit = unitIterator.next();
            if (xUnit.x == x && xUnit.y == y) {
                return xUnit;
            }
        }
        return null;
    }

    static King getAllyKing(ArrayList<Unit> list, int side) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof King && list.get(i).side == side) {
                return (King) list.get(i);
            }
        }
        return null;
    }

    static King getEnemyKing(ArrayList<Unit> list, int side) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof King && list.get(i).side != side) {
                return (King) list.get(i);
            }
        }
        return null;
    }

    static ArrayList<Unit> getOtherSide(ArrayList<Unit> list, int side) {
        ArrayList<Unit> otherSide = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).side != side) {
                otherSide.add(list.get(i));
            }
        }
        return otherSide;
    }

    static ArrayList<Unit> getSameSide(ArrayList<Unit> list, int side) {
        ArrayList<Unit> sameSide = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).side == side) {
                sameSide.add(list.get(i));
            }
        }
        return sameSide;
    }
}
