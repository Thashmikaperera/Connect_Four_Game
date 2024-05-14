package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private final Piece[][] pieces;
    private final BoardUI boardUI;

    public BoardImpl (BoardUI boardUI){
        this.boardUI=boardUI;
        pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];

        for(int i=0;i<pieces.length;i++){
            for(int j=0;j<pieces[i].length;j++){
                pieces[i][j]=Piece.EMPTY;
            }
        }

    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i =0; i<pieces[col].length ; i++){
            if(pieces[col][i]==Piece.EMPTY){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        int nextAvailableSpot = findNextAvailableSpot(col);
        if(nextAvailableSpot==-1){
            return false;
        }

        return true;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i =0; i<5 ; i++){
        boolean legalMoves=isLegalMove(i);
            if(legalMoves){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        int sp = findNextAvailableSpot(col);
        pieces[col][sp]=move;
    }

    @Override
    public Winner findWinner() {

        //Horizontal Winneer
        for (int i=0; i<6 ; i++) {
            if (pieces[i][1] == pieces[i][2] && pieces[i][2] == pieces[i][3] && pieces[i][1]!= Piece.EMPTY) {
                if (pieces[i][1] == pieces[i][0]) {
                    return new Winner(pieces[i][1], i, 0, i, 3);
                } else if (pieces[0][1] == pieces[0][4]) {
                    return new Winner(pieces[0][1], i, 1, i, 4);
                }
            }
        }

       //Vertical Winner
        for(int j=0;j<5;j++){
            if(pieces[2][j]==pieces[3][j] && pieces[2][j]!= Piece.EMPTY){
                if(pieces[1][j]==pieces[4][j]&&pieces[2][j]==pieces[1][j]){
                    return new Winner(pieces[1][j],1,j,4,j);
                }else if(pieces[0][j]==pieces[1][j]&&pieces[0][j]==pieces[2][j]){
                    return new Winner(pieces[1][j],0,j,3,j);
                }else if(pieces[4][j]==pieces[5][j]&&pieces[2][j]==pieces[4][j]){
                    return new Winner(pieces[1][j],2,j,5,j);
                }
            }
        }

       return new Winner(Piece.EMPTY);
    }
}
