package com.vanya.resource;

import com.vanya.model.Jalousie;
import com.vanya.model.State;
import com.vanya.model.StateKeeper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Hladush Ivan
 * on 06.11.16.
 */
@Path("/start")
public class StartResource {
    //    private static final Logger log= Logger.getLogger(StartResource.class);
    private static StateKeeper stateKeeper = StateKeeper.getStateKeeper();

    //todo add check host add service
    @POST
    @Path("/init")
    @Consumes(MediaType.APPLICATION_JSON)
    public void startZal(Jalousie state) {
        System.out.println(state);
        if (stateKeeper.getCommonState() == null) {
            stateKeeper.setCommonState(state);
            stateKeeper.startTotalEnergy();
        }
    }

    @GET
    @Path("/Test")
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        return "test";
    }

    @GET
    @Path("/States")
    @Produces(MediaType.APPLICATION_JSON)
    public List<State> getStates() {
        return stateKeeper.getStates();
    }

    @GET
    @Path("/CommonState")
    @Produces(MediaType.APPLICATION_JSON)
    public Jalousie getCommonState() {
        return stateKeeper.getCommonState();
    }

    @GET
    @Path("/CurrentState")
    @Produces(MediaType.APPLICATION_JSON)
    public State getCurrentState() {
        return stateKeeper.getCurrentState();
    }

    @DELETE
    @Path("/state/{stateId}")
    public void removeState(@PathParam("stateId") int stateId) {
        //todo add logger
        stateKeeper.removeState(stateId);
    }

    //todo add logger
    @POST
    @Path("/addState")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addState(State state) {
        if (!State.notNull(state)) {
            System.out.println("ERRROR");
            return;
        }
        stateKeeper.addState(state);

    }
}
