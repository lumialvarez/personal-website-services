package com.lumi.services;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.lumi.entities.Person;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/person")
public class PersonResource {
    @GET
    
    public List<Person> person() {
        return Person.listAll();
    }

    @POST
    @Transactional
    public Response create(Person person){
        System.out.println(person);
        person.persist();
        return Response.ok(person).status(201).build();
    }

}
