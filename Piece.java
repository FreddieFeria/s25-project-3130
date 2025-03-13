import javax.swing.text.Position;

public abstract class Piece {

    public enum PieceColor {
        BLACK, WHITE;
    }
    
    protected Position pos;
    protected PieceColor color;

    public void  Pieces(PieceColor color, Position pos){
        this.color = color;
        this.pos = pos;
    }

    public PieceColor getColor(){
        return color;
    }

    public Position getPosition(){
        return pos;
    }

    public void setPosition(Position pos){
        this.pos = pos;
    }

    public abstract boolean isValidMove(Position newPosition, Piece[][] board);
}
