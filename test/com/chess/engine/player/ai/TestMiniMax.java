/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chess.engine.player.ai;

/**
 *
 * @author tyler
 */

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MoveFactory;
import com.chess.engine.player.MoveTransition;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestMiniMax {
    
    @Test  
    public void testFoolsMate(){
    final Board board = Board.createStandardBoard();
    final MoveTransition t1 = board.currentPlayer().makeMove(MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("f2"), BoardUtils.getCoordinateAtPosition("f3")));
    
    assertTrue(t1.getMoveStatus().isDone());
    
    final MoveTransition t2 = t1.getTransitionBoard().currentPlayer().makeMove(MoveFactory.createMove(t1.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("e7"), BoardUtils.getCoordinateAtPosition("e5")));
    
    assertTrue(t2.getMoveStatus().isDone());
    
    final MoveTransition t3 = t2.getTransitionBoard().currentPlayer().makeMove(MoveFactory.createMove(t2.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("g2"), BoardUtils.getCoordinateAtPosition("g4")));
    
    assertTrue(t3.getMoveStatus().isDone());

    final MoveStrategy strategy = new MiniMax(4);
    
    final Move aiMove = strategy.execute(t3.getTransitionBoard());
    
    final Move bestMove = MoveFactory.createMove(t3.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d8"), BoardUtils.getCoordinateAtPosition("h4"));
    
    assertEquals(bestMove, aiMove);

        
    }
}
