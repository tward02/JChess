/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove.MajorAttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author tyler
 */
public class Queen extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};

    public Queen(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.QUEEN, piecePosition, pieceAlliance, true);
    }
    
    public Queen(final int piecePosition, final Alliance pieceAlliance, final boolean isFirstMove){
        super(PieceType.QUEEN, piecePosition, pieceAlliance, isFirstMove);
        
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition;
            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) || isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;
                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                    if (!candidateDestinationTile.isTileOccupied()) {

                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));

                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                        if (this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new MajorAttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));

                        }
                        break;
                    }

                }
            }
        }
        return ImmutableList.copyOf(legalMoves);

    }
    
    @Override
    public String toString(){
        return PieceType.QUEEN.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {

        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1 || candidateOffset == -9 || candidateOffset == 7);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {

        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 1 || candidateOffset == -7 || candidateOffset == 9);
    }
    
     @Override
    public Queen movePiece(final Move move) {
        return new Queen(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }

}
