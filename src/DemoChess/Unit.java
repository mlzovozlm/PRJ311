/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoChess;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Bao Anh Luu
 */
public abstract class Unit extends Game implements Serializable {

    int x, y, side, first;
    transient Image img;
    transient ArrayList<Square> availList;
    transient ArrayList<Square> protectList;

    public Unit(int x, int y, int side) {
        this.x = x;
        this.y = y;
        this.side = side;
        this.availList = new ArrayList<>();
        this.protectList = new ArrayList<>();
    }

    public void clearAvailable() {
        this.availList.clear();
    }

    public void clearProtect() {
        this.protectList.clear();
    }

    public void deleteAvailable(int x, int y) {
        for (int i = 0; i < this.availList.size(); i++) {
            if (this.availList.get(i).x == x && this.availList.get(i).y == y) {
                this.availList.remove(i);
            }
        }
    }

    boolean isLocked() {
        return this.availList.isEmpty();
    }

    public void addAvailableRook(ArrayList<Unit> list) {
        int i = this.x, j = this.y;
        while (i <= 7) {
            i++;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
                availList.add(new Square(i, j));
            } else {
                if (!isSameSide(xUnit)) {
                    availList.add(new Square(i, j));
                }
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (i >= 0) {
            i--;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
                availList.add(new Square(i, j));
            } else {
                if (!isSameSide(xUnit)) {
                    availList.add(new Square(i, j));
                }
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (j >= 0) {
            j--;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
                availList.add(new Square(i, j));
            } else {
                if (!isSameSide(xUnit)) {
                    availList.add(new Square(i, j));
                }
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (j <= 7) {
            j++;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
                availList.add(new Square(i, j));
            } else {
                if (!isSameSide(xUnit)) {
                    availList.add(new Square(i, j));
                }
                break;
            }
        }
    }

    public void addProtectRook(ArrayList<Unit> list) {
        int i = this.x, j = this.y;
        while (i <= 7) {
            i++;
            Unit xUnit = getUnit(i, j, list);
            protectList.add(new Square(i, j));
            if (xUnit != null) {
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (i >= 0) {
            i--;
            Unit xUnit = getUnit(i, j, list);
            protectList.add(new Square(i, j));
            if (xUnit != null) {
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (j >= 0) {
            j--;
            Unit xUnit = getUnit(i, j, list);
            protectList.add(new Square(i, j));
            if (xUnit != null) {
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (j <= 7) {
            j++;
            Unit xUnit = getUnit(i, j, list);
            protectList.add(new Square(i, j));
            if (xUnit != null) {
                break;
            }
        }
    }

    public boolean hasNotMoved() {
        return this.first == 1;
    }

    public void addAvailableBishop(ArrayList<Unit> list) {
        int i = this.x, j = this.y;
        while (i <= 7 && j <= 7) {
            i++;
            j++;
            Unit xUnit = getUnit(i, j, list);
            if (xUnit == null) {
                availList.add(new Square(i, j));
            } else {
                if (!isSameSide(xUnit)) {
                    availList.add(new Square(i, j));
                }
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
                availList.add(new Square(i, j));
            } else {
                if (!isSameSide(xUnit)) {
                    availList.add(new Square(i, j));
                }
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
                availList.add(new Square(i, j));
            } else {
                if (!isSameSide(xUnit)) {
                    availList.add(new Square(i, j));
                }
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
                availList.add(new Square(i, j));
            } else {
                if (!isSameSide(xUnit)) {
                    availList.add(new Square(i, j));
                }
                break;
            }
        }
    }

    public void addProtectBishop(ArrayList<Unit> list) {
        int i = this.x, j = this.y;
        while (i <= 7 && j <= 7) {
            i++;
            j++;
            Unit xUnit = getUnit(i, j, list);
            protectList.add(new Square(i, j));
            if (xUnit != null) {
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (i >= 0 && j >= 0) {
            i--;
            j--;
            Unit xUnit = getUnit(i, j, list);
            protectList.add(new Square(i, j));
            if (xUnit != null) {
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (i <= 7 && j >= 0) {
            i++;
            j--;
            Unit xUnit = getUnit(i, j, list);
            protectList.add(new Square(i, j));
            if (xUnit != null) {
                break;
            }
        }
        i = this.x;
        j = this.y;
        while (i >= 0 && j <= 7) {
            i--;
            j++;
            Unit xUnit = getUnit(i, j, list);
            protectList.add(new Square(i, j));
            if (xUnit != null) {
                break;
            }
        }
    }

    abstract void resetAvailable(ArrayList<Unit> list);

    void resetAvailableExcludeCheck(ArrayList<Unit> list) {
        resetAvailable(list);
        Iterator<Square> SquareIterator = this.availList.iterator();
        while (SquareIterator.hasNext()) {
            int xx = this.x, yy = this.y, first = this.first;
            Square xMove = SquareIterator.next();
            Unit xUnit = getUnit(xMove.x, xMove.y, list);
            //prepare for en passant
            Unit yUnit = getUnit(xx + 1, yy, list);
            Unit zUnit = getUnit(xx - 1, yy, list);
            //------------
            this.move(xMove.x, xMove.y, list);
            if (xUnit != null) {
                list.remove(xUnit);
            } else if (xUnit == null && this instanceof Pawn) { //en passant
                if (this.x == xx + 1) {
                    list.remove(yUnit);
                } else if (this.x == xx - 1) {
                    list.remove(zUnit);
                }
            }
            King allyKing = getAllyKing(list, this.side);
            if (allyKing.isChecked(list)) {
                SquareIterator.remove();
            }
            if (xUnit != null) {
                list.add(xUnit);
            } else if (xUnit == null && this instanceof Pawn) { //restore after en passant
                if (this.x == xx + 1) {
                    list.add(yUnit);
                } else if (this.x == xx - 1) {
                    list.add(zUnit);
                }
            }
            backUp(xx, yy, first);
        }
    }

    abstract void resetProtect(ArrayList<Unit> list);

    boolean isChecking(ArrayList<Unit> list) {
        King enemyKing = getEnemyKing(list, this.side);
        for (int i = 0; i < this.availList.size(); i++) {
            if (this.availList.get(i).x == enemyKing.x && this.availList.get(i).y == enemyKing.y) {
                return true;
            }
        }
        return false;
    }

    void backUp(int x, int y, int first) {
        this.x = x;
        this.y = y;
        this.first = first;
    }

    boolean isSameSide(Unit xUnit) {
        return (this.side == xUnit.side);
    }

    void move(int x, int y, ArrayList<Unit> list) {
        Iterator<Square> SquareIterator = this.availList.iterator();
        while (SquareIterator.hasNext()) {
            Square xMove = SquareIterator.next();
            if (xMove.x == x && xMove.y == y) {
                this.x = x;
                this.y = y;
                this.first = 0;
            }
        }
    }

    public void createImages(int side, int i) {
        Image[][] chessPieceImages = new Image[2][6];
        try {
            //URL url = new URL("http://i.stack.imgur.com/memI0.png");
            //BufferedImage bi = ImageIO.read(url);
            BufferedImage bi = ImageIO.read(new File("memI0.png"));
            for (int ii = 0; ii < 2; ii++) {
                for (int jj = 0; jj < 6; jj++) {
                    chessPieceImages[ii][jj] = bi.getSubimage(
                            jj * 64, ii * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        this.img = chessPieceImages[side][i];
    }
}
