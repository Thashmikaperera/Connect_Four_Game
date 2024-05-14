package lk.ijse.dep.service;

public class HumanPlayer extends Player{

    public HumanPlayer(Board board) {
        super(board);
    }
    @Override
    public void movePiece(int col){
        System.out.println("Human ");
        if(board.isLegalMove(col)){
            board.updateMove(col,Piece.BLUE);
            board.getBoardUI().update(col,true);
            Winner winner = board.findWinner();
            if(!winner.getWinningPiece().equals(Piece.EMPTY)){
                this.board.getBoardUI().notifyWinner(winner);
            }else if (!this.board.existLegalMoves()){
                this.board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        }
    }
}
