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

import br.com.fiap.ddd.gs.domain.Cliente;
import br.com.fiap.ddd.gs.dto.ClienteDTO;
import br.com.fiap.ddd.gs.services.ClienteServices;

@Path("/cliente")
public class ClienteResource {

	ClienteServices clienteServices = new ClienteServices();

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarCliente(@PathParam("id") int id) {
		Cliente cliente = clienteServices.consultarCliente(id);

		return Response.ok(cliente).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarCliente(ClienteDTO cliente, @Context UriInfo uriInfo) {
		clienteServices.cadastrarCliente(cliente);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(cliente.getId()));

		return Response.created(builder.build()).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarCliente(ClienteDTO cliente, @PathParam("id") int id) {
		clienteServices.atualizarCliente(cliente, id);
		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public void deletarCliente(@PathParam("id") int id) {
		clienteServices.deletarCliente(id);
	}
}
