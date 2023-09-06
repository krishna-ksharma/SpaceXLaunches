package com.rsystems.assignment.spacexlaunchtracker.data.remote;

public class DispatchGroup {

    private int count;
    private Runnable runnable;

    public DispatchGroup(Runnable r) {
        super();
        runnable = r;
    }

    public synchronized void enter() {
        count++;
    }

    public synchronized void enter(int count) {
        this.count = count;
    }

    public synchronized void leave() {
        count--;
        notifyGroup();
    }

    private synchronized void notifyGroup() {
        if (count <= 0 && runnable != null) {
            runnable.run();
            runnable = null;
        }
    }
}