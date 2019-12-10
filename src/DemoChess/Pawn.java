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
public class Pawn extends Unit {

    int enPassant;

    public Pawn(int x, int y, int side) {
        super(x, y, side);
        super.createImages(side, 5);
        this.first = 1;
        enPassant = 0;
    }

    @Override
    void resetAvailable(ArrayList<Unit> list) {
        availList.clear();
        //promote
        if (this.y == 7 * ((side == BLACK) ? 1 : 0)) {
            return;
        }
        int i = this.x;
        int j = this.y + ((side == BLACK) ? 1 : -1);
        Unit xUnit = getUnit(i, j, list);
        if (xUnit == null) {
            //first move forward 2
            if (first == 1 && getUnit(i, this.y + 2 * ((side == BLACK) ? 1 : -1), list) == null) {
                //first = 0;
                availList.add(new Square(this.x, this.y + 2 * ((side == BLACK) ? 1 : -1)));
            }
            //move forward 1
            availList.add(new Square(this.x, this.y + ((side == BLACK) ? 1 : -1)));
        }
        //there's something to eat diagonally
        i = this.x + ((side == BLACK) ? 1 : -1);
        j = this.y + ((side == BLACK) ? 1 : -1);
        xUnit = getUnit(i, j, list);
        if (xUnit != null && !isSameSide(xUnit)) {
            availList.add(new Square(i, j));
        }
        i = this.x - ((side == BLACK) ? 1 : -1);
        j = this.y + ((side == BLACK) ? 1 : -1);
        xUnit = getUnit(i, j, list);
        if (xUnit != null && !isSameSide(xUnit)) {
            availList.add(new Square(i, j));
        }
        //en passant
        if (this.y == ((side == BLACK) ? 4 : 3)) {
            //en passant right ->
            i = this.x + 1;
            j = this.y;
            xUnit = getUnit(i, j, list);
            if (xUnit instanceof Pawn && !isSameSide(xUnit)) { //checking destination [null] is unneccessary
                Pawn xPawn = (Pawn) xUnit;
                if (xPawn.enPassant == 1) {
                    availList.add(new Square(i, j + ((side == BLACK) ? 1 : -1)));
                }
            }
            //en passant left <-
            i = this.x - 1;
            xUnit = getUnit(i, j, list);
            if (xUnit instanceof Pawn && !isSameSide(xUnit)) { //checking destination [null] is unneccessary
                Pawn xPawn = (Pawn) xUnit;
                if (xPawn.enPassant == 1) {
                    availList.add(new Square(i, j + ((side == BLACK) ? 1 : -1)));
                }
            }
        }
    }

    @Override
    void resetProtect(ArrayList<Unit> list) {
        protectList.clear();
        int i = this.x + ((side == BLACK) ? 1 : -1);
        int j = this.y + ((side == BLACK) ? 1 : -1);
        Unit xUnit = getUnit(i, j, list);
        protectList.add(new Square(i, j));
        i = this.x - ((side == BLACK) ? 1 : -1);
        j = this.y + ((side == BLACK) ? 1 : -1);
        xUnit = getUnit(i, j, list);
        protectList.add(new Square(i, j));
    }
}
