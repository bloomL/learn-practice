package com.learn.concurrent.actual_combat.chapter_eight.eight;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * Concurrent version of puzzle solver
 *
 * Create by liguo on 2021/11/18
 **/
public class ConcurrentPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final ExecutorService exec;
    private final ConcurrentMap<P, Boolean> seen;
    protected final ValueLatch<PuzzleNode<P, M>> solution = new ValueLatch<>();

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
        this.exec = initThreadPool();
        this.seen = new ConcurrentHashMap<>();
        if (exec instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor tpe = (ThreadPoolExecutor) exec;
            tpe.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        }
    }

    private ExecutorService initThreadPool() {
        return Executors.newCachedThreadPool();
    }

    public List<M> solve() throws InterruptedException {
        try {
            P pos = puzzle.initialPosition();
            exec.execute(newTask(pos, null, null));
            // block until solution found
            PuzzleNode<P, M> solutionNode = solution.getValue();
            return solutionNode == null ? null : solutionNode.asMoveList();
        } finally {
            exec.shutdown();
        }
    }

    protected Runnable newTask(P p, M m, PuzzleNode<P, M> n) {
        return new SolverTask(p, m, n);
    }

    protected class SolverTask extends PuzzleNode<P, M> implements Runnable{
        SolverTask(P pos, M move, PuzzleNode<P, M> prev) {
            super(pos, move, prev);
        }

        @Override
        public void run() {
            // already solved or seen this position
            if (solution.isSet() || seen.putIfAbsent(pos, true) != null) {
                return;
            }
            if (puzzle.isGoal(pos)) {
                solution.setValue(this);
            }else {
                for (M move : puzzle.legalMoves(pos)) {
                    exec.execute(newTask(puzzle.move(pos,move), move, this));
                }
            }
        }
    }
}
