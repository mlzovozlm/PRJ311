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
public class Knight extends Unit {

    public Knight(int x, int y, int side) {
        super(x, y, side);
        super.createImages(side, 3);
        this.first = 1;
    }

    @Override
    void resetAvailable(ArrayList<Unit> list) {
        availList.clear();
        int step = -1, i, j;
        while (step <= 1) {
            if (step == 0) {
                step++;
                continue;
            }
            Unit xUnit;
            i = this.x + 2 * step;
            j = this.y + 1 * step;
            xUnit = getUnit(i, j, list);
            if (Math.abs(i) <= 7 && Math.abs(j) <= 7) {
                if (xUnit == null || !isSameSide(xUnit)) {
                    availList.add(new Square(i, j));
                }
            }
            i = this.x + 1 * step;
            j = this.y + 2 * step;
            xUnit = getUnit(i, j, list);
            if (Math.abs(i) <= 7 && Math.abs(j) <= 7) {
                if (xUnit == null || !isSameSide(xUnit)) {
                    availList.add(new Square(i, j));
                }
            }
            i = this.x - 1 * step;
            j = this.y + 2 * step;
            xUnit = getUnit(i, j, list);
            if (Math.abs(i) <= 7 && Math.abs(j) <= 7) {

                if (xUnit == null || !isSameSide(xUnit)) {
                    availList.add(new Square(i, j));
                }
            }
            i = this.x - 2 * step;
            j = this.y + 1 * step;
            xUnit = getUnit(i, j, list);
            if (Math.abs(i) <= 7 && Math.abs(j) <= 7) {

                if (xUnit == null || !isSameSide(xUnit)) {
                    availList.add(new Square(i, j));
                }
            }
            step++;
        }
    }

    @Override
    void resetProtect(ArrayList<Unit> list) {
        protectList.clear();
        int step = -1, i, j;
        while (step <= 1) {
            i = this.x + 2 * step;
            j = this.y + 1 * step;
            if (Math.abs(i) <= 7 && Math.abs(j) <= 7) {
                protectList.add(new Square(i, j));
            }
            i = this.x + 1 * step;
            j = this.y + 2 * step;
            if (Math.abs(i) <= 7 && Math.abs(j) <= 7) {
                protectList.add(new Square(i, j));
            }
            i = this.x - 1 * step;
            j = this.y + 2 * step;
            if (Math.abs(i) <= 7 && Math.abs(j) <= 7) {
                protectList.add(new Square(i, j));
            }
            i = this.x - 2 * step;
            j = this.y + 1 * step;
            if (Math.abs(i) <= 7 && Math.abs(j) <= 7) {
                protectList.add(new Square(i, j));
            }
            step++;
        }
    }
}
