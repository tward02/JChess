/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chess.engine.player.ai;

import com.chess.engine.board.Board;

/**
 *
 * @author tyler
 */
public interface BoardEvaluator {
    
    int evaluate(Board board, int depth);
    
}
