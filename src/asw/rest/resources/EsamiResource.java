package asw.rest.resources;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import asw.rest.model.Esame;
import asw.rest.persistence.EsameDAO;
import asw.rest.persistence.PersistenceException;

// Will map the resource to the URL esami
@Path("/esami")
public class EsamiResource {

  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of esami to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<Esame> getEsameBrowser() throws PersistenceException {
	    EsameDAO dao = new EsameDAO();
		List<Esame> esami = dao.doRetrieveAll(); 
	    return esami;
  }

  // Return the list of esami for applications
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<Esame> getEsami() throws PersistenceException {
	  	EsameDAO dao = new EsameDAO();
	  	List<Esame> esami = dao.doRetrieveAll(); 
	  	return esami;
  }
  
  @POST
  @Produces(MediaType.TEXT_HTML)  
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void newEsame(
		  @FormParam("id") int id,
		  @FormParam("id_docente") int id_docente,
		  @FormParam("aula") String aula,
		  @FormParam("data") String data,
		  @FormParam("corso") String corso,
		  @Context HttpServletResponse servletResponse) throws IOException, PersistenceException {
	  Esame esame = new Esame(id,id_docente,corso,aula, data);
	  EsameDAO dao = new EsameDAO();
	  dao.save(esame);
	  
	  servletResponse.sendRedirect("../create_todo.html");
  }

} 