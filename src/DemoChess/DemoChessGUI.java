package DemoChess;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.border.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class DemoChessGUI extends Game implements Serializable {

    ArrayList<Unit> list = new ArrayList<>();
    transient static JFrame f;
    King blackKing;
    King whiteKing;
    ArrayList<String> history = new ArrayList<>();
    transient private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    transient private JButton[][] chessBoardSquares = new JButton[8][8];
    transient private JPanel chessBoard;
    transient private final JLabel message = new JLabel(
            "Chess Champ is ready to play!");
    transient private static final String COLS = "ABCDEFGH";

    boolean selected = false;
    private int turn;
    private boolean gameEnd;
    Unit fromUnit;
    Unit toUnit;
    int fiftyMove;

    DemoChessGUI() {
        initializeGui();
    }

    public final void initializeGui() {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);

        //------------------------------------------------
        Action resignAction = new AbstractAction("Resign") {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEnd = true;
                message.setText((turn == WHITE ? "BLACK" : "WHITE") + " Win");
                JOptionPane.showMessageDialog(f, (turn == WHITE ? "BLACK" : "WHITE") + " Win");
            }
        };
        resignAction.setEnabled(false);
        //--------------------------------------------------
        Action saveGameAction = new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        };
        saveGameAction.setEnabled(false);
        //---------------------------------------------
        Action loadGameAction = new AbstractAction("Load") {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame();
                saveGameAction.setEnabled(true);
                resignAction.setEnabled(true);
            }
        };
        //---------------------------------------------
        Action newGameAction = new AbstractAction("New") {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupNewGame();
                saveGameAction.setEnabled(true);
                resignAction.setEnabled(true);
            }
        };
        //----------------------------
        tools.add(newGameAction);
        tools.add(loadGameAction);
        tools.add(saveGameAction);
        tools.addSeparator();
        tools.add(resignAction);
        tools.addSeparator();
        tools.add(message);
        //--------------------------
        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(9, 9)) {

            /**
             * Override the preferred size to return the largest it can, in a
             * square shape. Must (must, must) be added to a GridBagLayout as
             * the only component (it uses the parent as a guide to size) with
             * no GridBagConstaint (so it is centered).
             */
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int) d.getWidth(), (int) d.getHeight());
                } else if (c != null
                        && c.getWidth() > d.getWidth()
                        && c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w > h ? h : w);
                return new Dimension(s, s);
            }
        };
        chessBoard.setBorder(new CompoundBorder(
                new EmptyBorder(5, 5, 5, 5),
                new LineBorder(Color.BLACK)
        ));
        // Set the BG to be ochre
        Color ochre = new Color(204, 119, 34);
        chessBoard.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(chessBoard);
        gui.add(boardConstrain);
        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int j = 0; j < chessBoardSquares.length; j++) {
            for (int i = 0; i < chessBoardSquares[j].length; i++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
//                b.setMinimumSize(new Dimension(64, 64));
//                b.setPreferredSize(new Dimension(64, 64));
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                setBoardColor(b, i, j);
                int y = j;
                int x = i;
                chessBoardSquares[j][i] = b;
                chessBoardSquares[j][i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (selected == false && !gameEnd) {
                            fromUnit = getUnit(x, y, list);
                            if (fromUnit != null && turn == fromUnit.side) {
                                allBoardResetProtectAvailable();
                                selected = true;
                                chessBoardSquares[fromUnit.y][fromUnit.x].setBackground(Color.red);
                            }
                            //if an unit is already picked
                        } else if (selected == true && !gameEnd) {
                            int fromX = fromUnit.x, fromY = fromUnit.y, first = fromUnit.first, listSize = list.size();
                            toUnit = getUnit(x, y, list);
                            if (toUnit == null || !(toUnit.isSameSide(fromUnit))) {
                                fromUnit.move(x, y, list);
                                if (fromX != fromUnit.x || fromY != fromUnit.y) {
                                    chessBoardSquares[fromY][fromX].setIcon(null);
                                    chessBoardSquares[fromUnit.y][fromUnit.x].setIcon(null);
                                    //en passant
                                    if (toUnit != null) {
                                        list.remove(toUnit);
                                    } else if (toUnit == null && fromUnit instanceof Pawn) {
                                        if (fromUnit.x == fromX + 1) {
                                            list.remove(getUnit(fromX + 1, fromY, list));
                                            chessBoardSquares[fromY][fromX + 1].setIcon(null);
                                        } else if (fromUnit.x == fromX - 1) {
                                            list.remove(getUnit(fromX - 1, fromY, list));
                                            chessBoardSquares[fromY][fromX - 1].setIcon(null);
                                        }
                                    }
                                    chessBoardSquares[fromUnit.y][fromUnit.x].setIcon(new ImageIcon(fromUnit.img));

                                    allBoardResetProtectAvailable();
                                    setBoardColor(chessBoardSquares[fromY][fromX], fromX, fromY);
                                    selected = false;
                                    //special case
                                    //enPassant
                                    resetEnPassant();
                                    Pawn xPawn;
                                    //pawn become 'en passantable'
                                    if (fromUnit instanceof Pawn) {
                                        if ((fromUnit.side == BLACK && fromY == 1 && fromUnit.y == 3)
                                                || fromUnit.side == WHITE && fromY == 6 && fromUnit.y == 4) {
                                            xPawn = (Pawn) fromUnit;
                                            xPawn.enPassant = 1;
                                        }
                                    }
                                    //castling
                                    Castling(fromX);
                                    //promote
                                    if (fromUnit instanceof Pawn && fromUnit.y == (fromUnit.side == BLACK ? 7 : 0)) {
                                        promote();
                                    }
                                    //reset FiftyMove
                                    if (fromUnit instanceof Pawn || list.size() < listSize) {
                                        fiftyMove = 0;
                                    } else {
                                        fiftyMove++;
                                    }
                                    //
                                    turn = 1 - turn;
                                    encodeBoard();
                                    gameEnd = gameEnd();
                                }
                            } else if (toUnit.isSameSide(fromUnit)) {
                                fromUnit = toUnit;
                                fromUnit.resetAvailableExcludeCheck(list);
                                setBoardColor(chessBoardSquares[fromY][fromX], fromX, fromY);
                                chessBoardSquares[fromUnit.y][fromUnit.x].setBackground(Color.red);
                            }
                        }
                    }
                });
            }
        }

        /*
         * fill the chess board
         */
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                            SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (9 - (ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[ii][jj]);
                }
            }
        }
    }

    void resetFiftyMove() {

    }

    boolean thirdRepetition() {
        String temp = history.get(history.size() - 1);
        int count = 0;
        for (int i = 0; i < history.size(); i++) {
            if (temp.compareToIgnoreCase(history.get(i)) == 0) {
                count++;
            }
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    void encodeBoard() {
        String temp = "";
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Unit xUnit = getUnit(i, j, list);
                if (xUnit == null) {
                    temp += "null";
                } else {
                    temp += xUnit.first + "" + xUnit.side;
                    if (xUnit instanceof King) {
                        temp += "K";
                    } else if (xUnit instanceof Pawn) {
                        temp += "P" + ((Pawn) xUnit).enPassant;
                    } else if (xUnit instanceof Queen) {
                        temp += "Q";
                    } else if (xUnit instanceof Rook) {
                        temp += "R";
                    } else if (xUnit instanceof Bishop) {
                        temp += "B";
                    } else if (xUnit instanceof Knight) {
                        temp += "KN";
                    }
                }
                temp += "|";
            }
        }
        history.add(temp);
    }

    void resetEnPassant() {
        ArrayList<Unit> otherSide = getOtherSide(list, fromUnit.side);
        for (int i = 0; i < otherSide.size(); i++) {
            if (otherSide.get(i) instanceof Pawn) {
                Pawn xPawn = (Pawn) otherSide.get(i);
                xPawn.enPassant = 0;
            }
        }
    }

    //pawn promote
    void promote() {
        String[] options = {"Queen", "Bishop", "Knight", "Rook"};
        int optionsChosen = JOptionPane.showOptionDialog(null, "Select your preferred promotion", "Promotion of Pawn",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);
        switch (optionsChosen) {
            case 1:
                list.add(new Bishop(fromUnit.x, fromUnit.y, fromUnit.side));
                break;

            case 2:
                list.add(new Knight(fromUnit.x, fromUnit.y, fromUnit.side));
                break;

            case 3:
                list.add(new Rook(fromUnit.x, fromUnit.y, fromUnit.side));
                break;
            default:
                list.add(new Queen(fromUnit.x, fromUnit.y, fromUnit.side));
                break;
        }
        list.remove(fromUnit);
        fromUnit = getUnit(fromUnit.x, fromUnit.y, list);
        chessBoardSquares[fromUnit.y][fromUnit.x].setIcon(new ImageIcon(fromUnit.img));
    }

    //king castling
    void Castling(int fromX) {
        if (!(fromUnit instanceof King)) {
            return;
        }
        Unit xUnit;
        if (fromUnit.x == fromX + 2) {
            xUnit = getUnit(fromUnit.x + 1, fromUnit.y, list);
            chessBoardSquares[xUnit.y][xUnit.x].setIcon(null);
            xUnit.x = fromUnit.x - 1;
            chessBoardSquares[xUnit.y][xUnit.x].setIcon(new ImageIcon(xUnit.img));
        } else if (fromUnit.x == fromX - 2) {
            xUnit = getUnit(fromUnit.x - 2, fromUnit.y, list);
            chessBoardSquares[xUnit.y][xUnit.x].setIcon(null);
            xUnit.x = fromUnit.x + 1;
            chessBoardSquares[xUnit.y][xUnit.x].setIcon(new ImageIcon(xUnit.img));
        }
    }

    boolean gameEnd() {
        allBoardResetProtectAvailable();
        //
        King enemyKing = turn == BLACK ? whiteKing : blackKing;
        King allyKing = turn == BLACK ? blackKing : whiteKing;
        //
        if (enemyKing.isChecked(list) && opponentHasNoMove()) {
            message.setText((fromUnit.side == BLACK ? "BLACK" : "WHITE") + " Win");
            JOptionPane.showMessageDialog(f, (fromUnit.side == BLACK ? "BLACK" : "WHITE") + " Win");
            return true;
        } else if (allyKing.isChecked(list) && allyHasNoMove()) {
            message.setText((fromUnit.side == WHITE ? "BLACK" : "WHITE") + " Win");
            JOptionPane.showMessageDialog(f, (fromUnit.side == WHITE ? "BLACK" : "WHITE") + " Win");
            return true;
            //
        } else if (!enemyKing.isChecked(list) && opponentHasNoMove()) {
            message.setText("Draw");
            JOptionPane.showMessageDialog(f, "Draw");
            return true;
            //
        } else if (thirdRepetition()) {
            message.setText("Draw");
            JOptionPane.showMessageDialog(f, "Draw");
            return true;
        } else if (fiftyMove == 100) {
            message.setText("Draw");
            JOptionPane.showMessageDialog(f, "Draw");
            return true;
        }
        return false;
    }

    boolean opponentHasNoMove() {
        ArrayList<Unit> otherSide = getOtherSide(list, turn);
        for (int i = 0; i < otherSide.size(); i++) {
            otherSide.get(i).resetAvailableExcludeCheck(list);
            if (!otherSide.get(i).isLocked()) {
                return false;
            }
        }
        return true;
    }

    boolean allyHasNoMove() {
        ArrayList<Unit> sameSide = getSameSide(list, turn);
        for (int i = 0; i < sameSide.size(); i++) {
            sameSide.get(i).resetAvailableExcludeCheck(list);
            if (!sameSide.get(i).isLocked()) {
                return false;
            }
        }
        return true;
    }

    void setBoardColor(JButton b, int i, int j) {
        if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {
            b.setBackground(Color.WHITE);
        } else {
            b.setBackground(Color.BLACK);
        }
    }

    void setWholeBoard() {
        for (int j = 0; j < chessBoardSquares.length; j++) {
            for (int i = 0; i < chessBoardSquares[j].length; i++) {
                setBoardColor(chessBoardSquares[j][i], i, j);
            }
        }
    }

    void allBoardResetProtectAvailable() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).resetProtect(list);
            list.get(i).resetAvailableExcludeCheck(list);
        }
    }

    public final JComponent getGui() {
        return gui;
    }

    /**
     * Initializes the icons of the initial chess board piece places
     */
    private final void setupNewGame() {
        list.clear();
        for (int i = 0; i < 8; i++) {
            list.add(new Pawn(i, 6, WHITE));
        }
        list.add(new Rook(0, 7, WHITE));
        list.add(new Knight(1, 7, WHITE));
        list.add(new Bishop(2, 7, WHITE));
        list.add(new Queen(3, 7, WHITE));
        whiteKing = new King(4, 7, WHITE);
        list.add(whiteKing);
        list.add(new Bishop(5, 7, WHITE));
        list.add(new Knight(6, 7, WHITE));
        list.add(new Rook(7, 7, WHITE));
        for (int i = 0; i < 8; i++) {
            list.add(new Pawn(i, 1, BLACK));
        }
        list.add(new Rook(0, 0, BLACK));
        list.add(new Knight(1, 0, BLACK));
        list.add(new Bishop(2, 0, BLACK));
        list.add(new Queen(3, 0, BLACK));
        blackKing = new King(4, 0, BLACK);
        list.add(blackKing);
        list.add(new Bishop(5, 0, BLACK));
        list.add(new Knight(6, 0, BLACK));
        list.add(new Rook(7, 0, BLACK));
        message.setText("Make your move!");

        turn = WHITE;
        gameEnd = false;
        selected = false;
        fiftyMove = 0;
        loadUnitToBoard();
        setWholeBoard();
    }

    void loadUnitToBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getUnit(j, i, list) != null) {
                    chessBoardSquares[i][j].setIcon(new ImageIcon(getUnit(j, i, list).img));
                } else {
                    chessBoardSquares[i][j].setIcon(null);
                }
            }
        }
    }

    void loadData(DemoChessGUI other) {
        this.blackKing = other.blackKing;
        this.whiteKing = other.whiteKing;
        this.turn = other.turn;
        this.list = other.list;
        this.gameEnd = other.gameEnd;
        this.fromUnit = other.fromUnit;
        this.toUnit = other.toUnit;
        this.history = other.history;
        this.fiftyMove = other.fiftyMove;
    }

    void saveGame() {
        try {
            FileOutputStream fos = new FileOutputStream("demoChess.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            JOptionPane.showMessageDialog(f, "Save success");
        } catch (IOException ex) {
            Logger.getLogger(DemoChessGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadGame() {
        try {
            FileInputStream fis = new FileInputStream("demoChess.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            DemoChessGUI demoChess = (DemoChessGUI) ois.readObject();
            ois.close();
            for (int i = 0; i < demoChess.list.size(); i++) {
                if (demoChess.list.get(i) instanceof Bishop) {
                    demoChess.list.get(i).createImages(demoChess.list.get(i).side, 4);
                }
                if (demoChess.list.get(i) instanceof King) {
                    demoChess.list.get(i).createImages(demoChess.list.get(i).side, 0);
                }
                if (demoChess.list.get(i) instanceof Knight) {
                    demoChess.list.get(i).createImages(demoChess.list.get(i).side, 3);
                }
                if (demoChess.list.get(i) instanceof Rook) {
                    demoChess.list.get(i).createImages(demoChess.list.get(i).side, 2);
                }
                if (demoChess.list.get(i) instanceof Queen) {
                    demoChess.list.get(i).createImages(demoChess.list.get(i).side, 1);
                }
                if (demoChess.list.get(i) instanceof Pawn) {
                    demoChess.list.get(i).createImages(demoChess.list.get(i).side, 5);
                }
            }
            this.list.clear();
            loadData(demoChess);
            loadUnitToBoard();
            setWholeBoard();
            JOptionPane.showMessageDialog(f, "Load success");
        } catch (IOException ex) {
            Logger.getLogger(DemoChessGUI.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DemoChessGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                DemoChessGUI cg = new DemoChessGUI();

                f = new JFrame("ChessChamp");
                f.add(cg.getGui());
                // Ensures JVM closes after frame(s) closed and
                // all non-daemon threads are finished
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        // Swing GUIs should be created and updated on the EDT
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency
        SwingUtilities.invokeLater(r);
    }
}
