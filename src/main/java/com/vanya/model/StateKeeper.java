package com.vanya.model;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Hladush Ivan
 * on 06.11.16.
 */
public class StateKeeper {
    public CommonState commonState;

    public static StateKeeper getStateKeeper() {
        return StateKeeperIniter.stateKeeper;
    }

    public List<State> getStates() {
        return commonState.getStates();
    }

    public State getCurrentState() {
        return commonState.getState();
    }

    public void removeState(int stateId) {
        commonState.removeState(stateId);
    }

    private static class StateKeeperIniter {
        private static final StateKeeper stateKeeper = new StateKeeper();
    }

    public CommonState getCommonState() {
        return commonState;
    }

    public void setCommonState(CommonState commonState) {
        this.commonState = commonState;
    }

    //todo add min
    public void addState(State newState) {
        for (State state : commonState.getStates()) {
            if (state.getStartDate().getTime() < newState.getStartDate().getTime() &&
                    state.getFinishDate().getTime() > newState.getStartDate().getTime()
                    ) {
                return;
            }
        }
        commonState.addNewState(newState);
    }

    public void startTotalEnergy() {
        Executors.newScheduledThreadPool(1).schedule(() -> {
            State s = commonState.getState();
            if (s != null) {
                commonState.setTotalEnergy(commonState.getTotalEnergy() + s.getBloudedSquare() * s.getAngel() / 10);
            }
            System.out.println("add energy");
        }, 10, TimeUnit.SECONDS);
    }
}
