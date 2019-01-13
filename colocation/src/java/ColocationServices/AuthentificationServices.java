/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColocationServices;

import ColocationServicesControllers.UserManager;
import ColocationServicesSecurity.SigninNeeded;
import ColocationServicesModel.User;
import ColocationServicesSecurity.JWTokenUtility;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author Phil
 */
@Path("/traitement")
public class AuthentificationServices {

    @GET
    @SigninNeeded
    @Path("/whoami")
    @Produces(MediaType.APPLICATION_JSON)
    public Response whoami(@Context SecurityContext security) {
        User user = UserManager.getUser(security.getUserPrincipal().getName());
        if (user != null) {
            return Response.ok().entity(user).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("/signin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signin(@QueryParam("u_name") String login, @QueryParam("pass") String password) {
        User u = UserManager.login(login, password);
        if (u != null) {
            return Response.ok().entity(JWTokenUtility.buildJWT(u.getLogin())).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @POST
    @Path("/signup")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(@QueryParam("u_name") String login, @QueryParam("pass") String password,
            @QueryParam("f_name") String firstname, @QueryParam("l_name") String lastname) {

        if (UserManager.createUser(login, password, firstname, lastname)) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.CONFLICT).build();

    }

    /**
     * Méthode permettant de récupérer l'ensemble des roles d'un utilisateur
     *
     * @param user l'utilisateur
     * @return une liste de tous les roles associés à l'utilisateur user
     */
    public static List<String> findUserRoles(String user) {
        return null;
    }

}
