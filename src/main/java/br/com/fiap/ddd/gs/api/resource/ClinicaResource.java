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

import br.com.fiap.ddd.gs.domain.Clinica;
import br.com.fiap.ddd.gs.dto.ClinicaDTO;
import br.com.fiap.ddd.gs.services.ClinicaServices;

public class ClinicaResource {
	ClinicaServices clinicaServices = new ClinicaServices();

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarClinica(@PathParam("id") int id) {
		Clinica clinica = clinicaServices.consultarClinica(id);

		return Response.ok(clinica).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarClinica(ClinicaDTO clinica, @Context int idAtendimento, @Context UriInfo uriInfo) {
		clinicaServices.cadastrarClinica(clinica, idAtendimento);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(clinica.getId()));

		return Response.created(builder.build()).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarClinica(ClinicaDTO clinica, @PathParam("id") int id) {
		clinicaServices.atualizarClinica(clinica, id);
		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public void deletarClinica(@PathParam("id") int id) {
		clinicaServices.deletarClinica(id);
	}
}
