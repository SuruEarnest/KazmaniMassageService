package com.kazmani.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ApplicationConfig extends Application {

    public Set<Class<?>> getClasses()
    {
	
	final Set<Class<?>> resources = new HashSet<Class<?>>();
	
	resources.add(Service.class);
	
	return resources;
    }
}
