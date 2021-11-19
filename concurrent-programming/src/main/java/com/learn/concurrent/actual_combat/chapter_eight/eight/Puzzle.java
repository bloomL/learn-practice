package com.learn.concurrent.actual_combat.chapter_eight.eight;

import java.util.Set;

/**
 *
 * Abstraction for puzzles like the 'sliding blocks puzzle'
 *
 * Create by liguo on 2021/11/18
 **/
public interface Puzzle <P, M>{
    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);
}
