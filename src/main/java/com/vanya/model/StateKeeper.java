package com.vanya.model;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Hladush Ivan
 * on 06.11.16.
 */
public class StateKeeper {
    public Jalousie jalousie;

    public static StateKeeper getStateKeeper() {
        return StateKeeperIniter.stateKeeper;
    }

    public List<State> getStates() {
        return jalousie.getStates();
    }

    public State getCurrentState() {
        return jalousie.getState();
    }

    public void removeState(int stateId) {
        jalousie.removeState(stateId);
    }

    private static class StateKeeperIniter {
        private static final StateKeeper stateKeeper = new StateKeeper();
    }

    public Jalousie getCommonState() {
        return jalousie;
    }

    public void setCommonState(Jalousie commonState) {
        this.jalousie = commonState;
    }

    //todo add min
    public void addState(State newState) {
        for (State state : jalousie.getStates()) {
            if (state.getStartDate().getTime() < newState.getStartDate().getTime() &&
                    state.getFinishDate().getTime() > newState.getStartDate().getTime()
                    ) {
                return;
            }
        }
        jalousie.addNewState(newState);
    }

    public void startTotalEnergy() {
        Executors.newScheduledThreadPool(1).schedule(() -> {
            State s = jalousie.getState();
            if (s != null) {
                jalousie.setTotalEnergy(jalousie.getTotalEnergy() + s.getBloudedSquare() * s.getAngel() / 10);
            }
            System.out.println("add energy");
        }, 10, TimeUnit.SECONDS);
    }
}
