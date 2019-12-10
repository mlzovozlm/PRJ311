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
public class Rook extends Unit {

    public Rook(int x, int y, int side) {
        super(x, y, side);
        super.createImages(side, 2);
        this.first = 1;
    }

    @Override
    void resetAvailable(ArrayList<Unit> list) {
        availList.clear();
        addAvailableRook(list);
    }

    @Override
    void resetProtect(ArrayList<Unit> list) {
        protectList.clear();
        addProtectRook(list);
    }

}
