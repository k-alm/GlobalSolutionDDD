package br.com.fiap.ddd.gs.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.ddd.gs.domain.Hospital;
import br.com.fiap.ddd.gs.dto.HospitalDTO;
import br.com.fiap.ddd.gs.services.HospitalServices;

public class HospitalResource {
	HospitalServices hospitalServices = new HospitalServices();

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarHospital(@PathParam("id") int id) {
		Hospital hospital = hospitalServices.consultarHospital(id);

		return Response.ok(hospital).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarHospital(HospitalDTO hospital, @Context int idAtendimento, @Context UriInfo uriInfo) {
		hospitalServices.cadastrarHospital(hospital, idAtendimento);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(hospital.getId()));

		return Response.created(builder.build()).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarHospital(HospitalDTO hospital, @PathParam("id") int id) {
		hospitalServices.atualizarHospital(hospital, id);
		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public void deletarHospital(@PathParam("id") int id) {
		hospitalServices.deletarHospital(id);
	}
}
