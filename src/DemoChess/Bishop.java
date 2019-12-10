/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoChess;

import java.util.ArrayList;

/**
 *
 * @author Bao Anh Luu
 */
public class Bishop extends Unit {
    
    public Bishop(int x, int y, int side) {
        super(x, y, side);
        super.createImages(side, 4);
        this.first = 1;
    }

    @Override
    void resetAvailable(ArrayList<Unit> list) {
        availList.clear();
        addAvailableBishop(list);
    }

    @Override
    void resetProtect(ArrayList<Unit> list) {
        protectList.clear();
        addProtectBishop(list);
    }
}
