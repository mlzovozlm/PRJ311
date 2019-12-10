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
public class King extends Unit {

    public King(int x, int y, int side) {
        super(x, y, side);
        super.createImages(side, 0);
        this.first = 1;
    }

    @Override
    void resetAvailable(ArrayList<Unit> list) {
        availList.clear();
        ArrayList<Unit> otherSide = getOtherSide(list, this.side);
        //--
        for (int i = 0; i < otherSide.size(); i++) {
            otherSide.get(i).resetProtect(list);
        }
        //can't walk to grid occupied by same side
        //or available to enemies
        Unit xUnit;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                xUnit = getUnit(this.x + i, this.y + j, list);
                if (xUnit != null && isSameSide(xUnit)) {
                    continue;
                }
                this.availList.add(new Square(this.x + i, this.y + j));
            }
        }
        if (this.hasNotMoved()) {
            //castling right ->
            xUnit = getUnit(this.x + 3, this.y, list);
            if (xUnit != null) {
                Square xMove = new Square(this.x + 2, this.y);
                Unit yUnit = getUnit(this.x + 1, this.y, list);
                if (yUnit == null && xUnit.hasNotMoved() && isSameSide(xUnit)) {
                    this.availList.add(xMove);
                }
                for (int z = 0; z < otherSide.size(); z++) {
                    xUnit = otherSide.get(z);
                    for (int w = 0; w < xUnit.protectList.size(); w++) {
                        if (xUnit.protectList.get(w).y == this.y
                                && (xUnit.protectList.get(w).x == this.x
                                || xUnit.protectList.get(w).x == this.x + 1
                                || xUnit.protectList.get(w).x == this.x + 2)) {
                            this.deleteAvailable(this.x + 2, this.y);
                        }
                    }
                }
            }
            //castling left <-
            xUnit = getUnit(this.x - 4, this.y, list);
            if (xUnit != null) {
                Square xMove = new Square(this.x - 2, this.y);
                Unit yUnit = getUnit(this.x - 1, this.y, list);
                Unit zUnit = getUnit(this.x + 1, this.y, list);
                if (yUnit == null && zUnit == null && xUnit.hasNotMoved() && isSameSide(xUnit)) {
                    this.availList.add(xMove);
                }
                for (int z = 0; z < otherSide.size(); z++) {
                    xUnit = otherSide.get(z);
                    for (int w = 0; w < xUnit.protectList.size(); w++) {
                        if (xUnit.protectList.get(w).y == this.y
                                && (xUnit.protectList.get(w).x == this.x
                                || xUnit.protectList.get(w).x == this.x - 1
                                || xUnit.protectList.get(w).x == this.x - 2)) {
                            this.deleteAvailable(this.x - 2, this.y);
                        }
                    }
                }
            }
        }
    }

    @Override
    void resetProtect(ArrayList<Unit> list) {
        protectList.clear();
        for (int i = -1; i <= -1; i++) {
            for (int j = -1; j <= -1; j++) {
                protectList.add(new Square(this.x + i, this.x + j));
            }
        }
    }

    boolean checkedByRook(ArrayList<Unit> list) {
        int i = this.x, j = this.y;
        while (i <= 7) {
            i++;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
            } else if (!isSameSide(xUnit)) {
                if (xUnit instanceof Rook || xUnit instanceof Queen) {
                    return true;
                } else {
                    break;
                }
            } else if (isSameSide(xUnit)) {
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (i >= 0) {
            i--;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
            } else if (!isSameSide(xUnit)) {
                if (xUnit instanceof Rook || xUnit instanceof Queen) {
                    return true;
                } else {
                    break;
                }
            } else if (isSameSide(xUnit)) {
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (j >= 0) {
            j--;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
            } else if (!isSameSide(xUnit)) {
                if (xUnit instanceof Rook || xUnit instanceof Queen) {
                    return true;
                } else {
                    break;
                }
            } else if (isSameSide(xUnit)) {
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (j <= 7) {
            j++;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
            } else if (!isSameSide(xUnit)) {
                if (xUnit instanceof Rook || xUnit instanceof Queen) {
                    return true;
                } else {
                    break;
                }
            } else if (isSameSide(xUnit)) {
                break;
            }
        }
        return false;
    }

    boolean checkedByBishop(ArrayList<Unit> list) {
        int i = this.x, j = this.y;
        while (i <= 7 && j <= 7) {
            i++;
            j++;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
            } else if (!isSameSide(xUnit)) {
                if (xUnit instanceof Bishop || xUnit instanceof Queen) {
                    return true;
                } else {
                    break;
                }
            } else if (isSameSide(xUnit)) {
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (i >= 0 && j >= 0) {
            i--;
            j--;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
            } else if (!isSameSide(xUnit)) {
                if (xUnit instanceof Bishop || xUnit instanceof Queen) {
                    return true;
                } else {
                    break;
                }
            } else if (isSameSide(xUnit)) {
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (i <= 7 && j >= 0) {
            i++;
            j--;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
            } else if (!isSameSide(xUnit)) {
                if (xUnit instanceof Bishop || xUnit instanceof Queen) {
                    return true;
                } else {
                    break;
                }
            } else if (isSameSide(xUnit)) {
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (i >= 0 && j <= 7) {
            i--;
            j++;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
            } else if (!isSameSide(xUnit)) {
                if (xUnit instanceof Bishop || xUnit instanceof Queen) {
                    return true;
                } else {
                    break;
                }
            } else if (isSameSide(xUnit)) {
                break;
            }
        }
        return false;
    }

    boolean checkedByPawn(ArrayList<Unit> list) {
        int i = this.x + ((side == BLACK) ? 1 : -1);
        int j = this.y + ((side == BLACK) ? 1 : -1);
        Unit xUnit = getUnit(i, j, list);
        if (xUnit == null) {
        } else if (!isSameSide(xUnit) && xUnit instanceof Pawn) {
            return true;
        }
        i = this.x - ((side == BLACK) ? 1 : -1);
        j = this.y + ((side == BLACK) ? 1 : -1);
        xUnit = getUnit(i, j, list);
        if (xUnit == null) {
        } else if (!isSameSide(xUnit) && xUnit instanceof Pawn) {
            return true;
        }
        return false;
    }

    boolean checkedByKnight(ArrayList<Unit> list) {
        int step = -1, i, j;
        while (step <= 1) {
            if (step == 0) {
                step++;
                continue;
            }
            i = this.x + 2 * step;
            j = this.y + 1 * step;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
            } else if (!isSameSide(xUnit) && xUnit instanceof Knight) {
                return true;
            }
            i = this.x + 1 * step;
            j = this.y + 2 * step;
            xUnit = getUnit(i, j, list);
            if (xUnit == null) {
            } else if (!isSameSide(xUnit) && xUnit instanceof Knight) {
                return true;
            }
            i = this.x - 1 * step;
            j = this.y + 2 * step;
            xUnit = getUnit(i, j, list);
            if (xUnit == null) {
            } else if (!isSameSide(xUnit) && xUnit instanceof Knight) {
                return true;
            }
            i = this.x - 2 * step;
            j = this.y + 1 * step;
            xUnit = getUnit(i, j, list);
            if (xUnit == null) {
            } else if (!isSameSide(xUnit) && xUnit instanceof Knight) {
                return true;
            }
            step++;
        }
        return false;
    }

    boolean checkedByKing(ArrayList<Unit> list) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Unit xUnit = getUnit(this.x + i, this.y + j, list);
                if (xUnit instanceof King && !isSameSide(xUnit)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean isChecked(ArrayList<Unit> list) {
        //////////////////////////////
        return checkedByRook(list) || checkedByBishop(list) || checkedByKing(list)
                || checkedByKnight(list) || checkedByPawn(list);
    }
}
