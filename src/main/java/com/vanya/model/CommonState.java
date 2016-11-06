package com.vanya.model;

import sun.util.resources.LocaleData;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Общее состояние жалюзей за весь период
 * Created by Hladush Ivan
 * on 06.11.16.
 */
@XmlRootElement
public class CommonState {
    private static int stateInd=1;
    private int weight;
    private int high;
    private String allowableHost;
    private List<State> states = new ArrayList<>();
    private int totalEnergy;

    public CommonState(int weight, int hight, String allowableHost) {
        this.weight = weight;
        this.high = hight;
        this.allowableHost = allowableHost;
    }
    public CommonState(){

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public String getAllowableHost() {
        return allowableHost;
    }

    public void setAllowableHost(String allowableHost) {
        this.allowableHost = allowableHost;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public int getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(int totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    @Override
    public String toString() {
        return "CommonState{" +
                "weight=" + weight +
                ", high=" + high +
                ", allowableHost='" + allowableHost + '\'' +
                ", states=" + states +
                ", totalEnergy=" + totalEnergy +
                '}';
    }

    //todo переписать добавить возможность добовлять по минутам
    public State getState() {
        if(states==null){
            return null;
        }
        Date date=new Date();
        for(State state:states){
            if(state.getStartDate().getTime()<=date.getTime() &&
                    state.getFinishDate().getTime()>=date.getTime()
                    ){
                return state;
            }
        }
        return null;
    }
        //todo add logger
    public void addNewState(State newState) {
        newState.setIndex(stateInd++);
        states.add(newState);
        System.out.println(newState);
    }

    public void removeState(int stateId) {
        for(int i=0;i<states.size();++i){
            if(states.get(i).getIndex()==stateId){
                states.remove(i);
                return;
            }
        }
    }
}
