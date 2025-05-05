
import java.util.List;

final class Cell {
    private Piece piece;
    private int x;
    private int y;

    public Cell(int x, int y, Piece piece){
        this.setPiece(piece);
        this.setX(x);
        this.setY(y);
    }

    public Piece getPiece(){
        return this.piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public int getX(){
        return this.x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return this.y;
    }

    public void setY(int y){
        this.y = y;
    }
}

abstract class Move {
    private Player player; 
    private Cell start; 
    private Cell end; 
    private Piece pieceMoved; 
    private Piece pieceCaptured; 
    private boolean castleMove = false;

    public Move(Player player, Cell start, Cell end){
        start.getPiece();
    }

    public boolean isCastleMove(){
        return this.castleMove;
    }

    public void setCastlingMove(boolean castleMove){
        this.castleMove = castleMove;
    }

    public boolean isPieceCaptured(){
        return false;
    }

}

abstract class Piece {
    private boolean capture = false;
    private boolean white = false;

    public Piece(boolean white){
        this.setWhite(white);
    }

    public boolean isWhite(){
        return this.white;
    }

    public final void setWhite(boolean white){
        this.white = white;
    }

    public boolean isCaptured(){
        return this.capture;
    }

    public void setCaptured( boolean capture ){
        this.capture = capture;
    }

    public abstract boolean canMove(Board board, Cell start, Cell end);
}

class King extends Piece{
    private boolean castleDone = false;

    public King(boolean white){
        super(white);
    }

    public boolean isCastleDone(){
        return this.castleDone;
    }

    public void setCastleDone(boolean castleDone){
        this.castleDone = castleDone;
    }

    private boolean isValidCastling(Board board, Cell start, Cell end){
        if(this.isCastleDone()){
            return false;
        }
        return castleDone;
    }

    @Override
    public boolean canMove(Board board, Cell start, Cell end){
        if(end.getPiece().isWhite() == this.isWhite()){
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        if(x + y == 1){
            return true;
        }

        return this.isValidCastling(board,start,end);
    }

    public boolean isCastleMove(Cell start, Cell end){
        
        return false;
        
    }

    
}

class Queen extends Piece{

    public Queen(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Cell start, Cell end) {
        if(end.getPiece().isWhite() == this.isWhite()){
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return false;
    }

}

class Rook extends Piece{

    public Rook(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Cell start, Cell end) {
        return false;
    }
    
}

class Bishop extends Piece{

    public Bishop(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Cell start, Cell end) {
        if(end.getPiece().isWhite() == this.isWhite()){
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return false;
    }
}

class Knight extends Piece {
    public Knight(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Cell start, Cell end){
        if(end.getPiece().isWhite() == this.isWhite()){
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x*y == 2;
    }

    
}

class Pawn extends Piece{

    public Pawn(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Cell start, Cell end) {
        if(end.getPiece().isWhite() == this.isWhite()){
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return false;
    }
}

class Board {
    Cell[][] boxes;

    public Board(){
        this.resetBoard();
    }

    public Cell getBox(int x,int y) throws Exception{
        if(x < 0 || x > 7 || y < 0 || y > 7){
            throw new Exception("Box out of bound");
        }
        return boxes[x][y];
    }

    public final void resetBoard(){
        // White Pieces
        boxes[0][0] = new Cell(0,0,new Rook(true));
        boxes[0][1] = new Cell(1,0,new Knight(true)); 
        boxes[0][2] = new Cell(2,0,new Bishop(true));
        boxes[0][3] = new Cell(3,0,new Queen(true));
        boxes[0][4] = new Cell(4,0,new King(true));
        boxes[0][5] = new Cell(5,0,new Bishop(true));
        boxes[0][6] = new Cell(6,0,new Knight(true));
        boxes[0][7] = new Cell(7,0,new Rook(true));
        boxes[1][0] = new Cell(0,0,new Pawn(true));
        boxes[1][1] = new Cell(1,1,new Pawn(true));
        boxes[1][2] = new Cell(2,1,new Pawn(true));
        boxes[1][3] = new Cell(3,1,new Pawn(true));
        boxes[1][4] = new Cell(4,1,new Pawn(true));
        boxes[1][5] = new Cell(5,1,new Pawn(true));
        boxes[1][6] = new Cell(6,1,new Pawn(true));
        boxes[1][7] = new Cell(7,1,new Pawn(true));

        // Black Pieces
        boxes[7][0] = new Cell(0,7,new Rook(false));
        boxes[7][1] = new Cell(1,7,new Knight(false)); 
        boxes[7][2] = new Cell(2,7,new Bishop(false));
        boxes[7][3] = new Cell(3,7,new Queen(true));
        boxes[7][4] = new Cell(4,7,new King(false));
        boxes[7][5] = new Cell(5,7,new Bishop(false));
        boxes[7][6] = new Cell(6,7,new Knight(false));
        boxes[7][7] = new Cell(7,7,new Rook(false));
        boxes[6][0] = new Cell(0,6,new Pawn(false));
        boxes[6][1] = new Cell(1,6,new Pawn(false));
        boxes[6][2] = new Cell(2,6,new Pawn(false));
        boxes[6][3] = new Cell(3,6,new Pawn(false));
        boxes[6][4] = new Cell(4,6,new Pawn(false));
        boxes[6][5] = new Cell(5,6,new Pawn(false));
        boxes[6][6] = new Cell(6,6,new Pawn(false));
        boxes[6][7] = new Cell(7,6,new Pawn(false));

        // Empty boxes
        for(int i = 2; i < 6; i++){
            for(int j = 0; j < 8; j++){
                boxes[i][j] = new Cell(i,j,null);
            }
        }
    }
}

enum GameStatus {
    ACTIVE,
    BLACK_WIN,
    WHITE_WIN,
    FORFEIT,
    DRAW,
    RESIGNATION
}

abstract class Player {
    public boolean whiteSide;

    public boolean isWhiteSide(){
        return this.whiteSide;
    }

}

public class Game {
    private Player[] players;
    private Board board;
    private Player currentTurn;
    private GameStatus status;
    private List<Move> movesPlayed;

    private void initialize(Player p1, Player p2){
        players[0] = p1;
        players[1] = p2;

        board.resetBoard();

        if(p1.isWhiteSide()){
            this.currentTurn = p1;
        } else {
            this.currentTurn = p2;
        }

        movesPlayed.clear();
    }

    public boolean isEnd(){
        return this.getStatus() != GameStatus.ACTIVE;
    }

    public GameStatus getStatus(){
        return this.status;
    }

    public void setStatus(GameStatus status){
        this.status = status;
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) throws Exception{
        Cell startBox = board.getBox(startX, startY);
        Cell endBox = board.getBox(endX, endY);
        Move move = new Move(player,startBox, endBox);
        return this.makeMove(move,player);
    }

    private boolean makeMove(Move move, Player player){
        Piece sourcePiece = move.getStart().getPiece();
        if(sourcePiece == null){
            return false;
        }

        if(player != currentTurn){
            return false;
        }

        if(sourcePiece.isWhite() != player.isWhiteSide()){
            return false;
        }

        if(!sourcePiece.canMove(board,move.getStart(), move.getEnd())){
            return false;
        }

        Piece captPiece = move.getStart().getPiece();
        if(captPiece != null){
            captPiece.setCaptured(true);
            move.setPieceCaptured(captPiece);
        }

        if(sourcePiece != null && sourcePiece instanceof King && sourcePiece.isCastleMove()){
            move.setCastlingMove(true);
        }

        movesPlayed.add(move);

        move.getEnd().setPiece(move.getStart().getPiece());
        move.getStart.setPiece(null);

        if (captPiece != null && captPiece instanceof King) {
            if(player.isWhiteSide()){
                this.setStatus(GameStatus.WHITE_WIN);
            } else{
                this.setStatus(GameStatus.BLACK_WIN);
            }
        }

        if(this.currentTurn == players[0]){
            this.currentTurn  = players[1];
        } else{
            this.currentTurn = players[0];
        }

        return true;
    }

    public static void main(String[] args) {

    }
}
