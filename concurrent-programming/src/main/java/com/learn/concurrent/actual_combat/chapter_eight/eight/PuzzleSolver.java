package com.learn.concurrent.actual_combat.chapter_eight.eight;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * Solver that recognizes when no solution exists
 *
 * Create by liguo on 2021/11/18
 **/
public class PuzzleSolver <P, M> extends ConcurrentPuzzleSolver<P, M>{
    PuzzleSolver(Puzzle<P, M> puzzle) {
        super(puzzle);
    }

    private final AtomicInteger taskCount = new AtomicInteger(0);

    @Override
    protected Runnable newTask(P p, M m, PuzzleNode<P, M> node) {
        return new CountingSolverTask(p, m, node);
    }

    class CountingSolverTask extends SolverTask {
        CountingSolverTask(P pos, M move, PuzzleNode<P, M> prev) {
            super(pos, move, prev);
            taskCount.incrementAndGet();
        }

        @Override
        public void run() {
            try {
                super.run();
            } finally {
                // 不存在解答，活动任务数量
                if (taskCount.decrementAndGet() == 0) {
                    solution.setValue(null);
                }
            }
        }
    }

}
