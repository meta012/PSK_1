package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Coach;
import lt.vu.persistence.CoachesDAO;
import lt.vu.rest.contracts.CoachDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/coachDetails")
public class CoachesController {
    @Inject
    @Setter @Getter
    private CoachesDAO coachesDAO;

    @GET
    public String sayHello() {
        return "Hello World";
    }

    @Path("/getall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Coach> coaches = coachesDAO.loadAll();
        if (coaches.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<CoachDto> coachDtos = coaches.stream()
                .map(CoachDto::fromEntity)
                .collect(Collectors.toList());

        return Response.ok(coachDtos).build();
    }

    @Path("/get{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Coach coach = coachesDAO.findOne(id);
        if (coach == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        CoachDto coachDto = CoachDto.fromEntity(coach);
        return Response.ok(coachDto).build();
    }

    @Path("/post")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(CoachDto coachDto) {
        Coach coach = new Coach();
        coach.setName(coachDto.getName());
        coach.setPersonalIdNo(coachDto.getPersonalIdNo());
        coachesDAO.persist(coach);
        return Response.ok(Response.Status.OK).build();
    }

    @Path("/put{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Long id, CoachDto coachDto) {
        try {
            Coach coach = coachesDAO.findOne(id);
            coach.setName(coachDto.getName());
            coachesDAO.update(coach);
            return Response.ok(CoachDto.fromEntity(coach)).build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
