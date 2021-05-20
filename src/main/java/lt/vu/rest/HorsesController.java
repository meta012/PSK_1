package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Horse;
import lt.vu.persistence.HorsesDAO;
import lt.vu.rest.contracts.HorseDto;
import lt.vu.rest.contracts.HorseStableDto;

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
@Path("/horseDetails")
public class HorsesController {
    @Inject
    @Setter @Getter
    private HorsesDAO horsesDAO;

    @GET
    public String sayHello() {
        return "Hello World";
    }

    @Path("/getall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Horse> horses = horsesDAO.loadAll();
        if (horses.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<HorseDto> horseDtos = horses.stream()
                .map(HorseDto::convertToHorseDto)
                .collect(Collectors.toList());

        return Response.ok(horseDtos).build();
    }

    @Path("/get{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Horse horse = horsesDAO.findOne(id);
        if (horse == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        HorseDto horseDto = HorseDto.convertToHorseDto(horse);
        return Response.ok(horseDto).build();
    }

    @Path("/post")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(HorseStableDto horseStableDto) {
        Horse horse = new Horse();
        horse.setName(horseStableDto.getName());
        horse.setIdentityNo(horseStableDto.getIdentityNo());
        horse.setStable(horseStableDto.getStable());
        horsesDAO.persist(horse);
        return Response.ok(Response.Status.OK).build();
    }

    @Path("/put{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Long id, HorseDto horseDto) {
        try {
            Horse horse = horsesDAO.findOne(id);
            horse.setName(horseDto.getName());
            horsesDAO.update(horse);
            return Response.ok(HorseDto.convertToHorseDto(horse)).build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
