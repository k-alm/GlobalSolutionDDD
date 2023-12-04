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

import br.com.fiap.ddd.gs.domain.Atendimento;
import br.com.fiap.ddd.gs.dto.AtendimentoDTO;
import br.com.fiap.ddd.gs.services.AtendimentoServices;

@Path("/atendimento")
public class AtendimentoResource {

	AtendimentoServices atendimentoServices = new AtendimentoServices();

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarAtendimento(@PathParam("id") int id) {
		Atendimento atendimento = atendimentoServices.consultarAtendimento(id);

		return Response.ok(atendimento).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarAtendimento(AtendimentoDTO atendimento, @Context int idFila, @Context UriInfo uriInfo) {
		atendimentoServices.cadastrarAtendimento(atendimento, idFila);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(atendimento.getId()));

		return Response.created(builder.build()).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarAtendimento(AtendimentoDTO atendimento, @PathParam("id") int id) {
		atendimentoServices.atualizarAtendimento(atendimento, id);
		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public void deletarAtendimento(@PathParam("id") int id) {
		atendimentoServices.deletarAtendimento(id);
	}
}
