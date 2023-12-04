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

import br.com.fiap.ddd.gs.domain.Fila;
import br.com.fiap.ddd.gs.dto.FilaDTO;
import br.com.fiap.ddd.gs.services.FilaServices;

@Path("/fila")
public class FilaResource {

	FilaServices filaServices = new FilaServices();

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarFila(@PathParam("id") int id) {
		Fila fila = filaServices.consultarFila(id);

		return Response.ok(fila).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarFila(FilaDTO fila, @Context int idCliente, @Context UriInfo uriInfo) {
		filaServices.cadastrarFila(fila, idCliente);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(fila.getId()));

		return Response.created(builder.build()).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarFila(FilaDTO fila, @PathParam("id") int id) {
		filaServices.atualizarFila(fila, id);
		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public void deletarFila(@PathParam("id") int id) {
		filaServices.deletarFila(id);
	}
}
