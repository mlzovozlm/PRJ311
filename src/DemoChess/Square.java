/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoChess;

import java.io.Serializable;

/**
 *
 * @author Bao Anh Luu
 */
public class Square implements Serializable {
    int x, y;
    
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
