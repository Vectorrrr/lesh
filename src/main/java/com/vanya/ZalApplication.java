package com.vanya;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by Hladush Ivan
 * on 06.11.16.
 */
@ApplicationPath("/")
public class ZalApplication extends ResourceConfig {
        public  ZalApplication(){
            packages("com.vanya");
        }
}
