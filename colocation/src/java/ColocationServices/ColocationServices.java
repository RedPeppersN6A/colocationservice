/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 * 
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */
package ColocationServices;

import ColocationServicesControllers.AchievedServiceManager;
import ColocationServicesControllers.ColocationManager;
import ColocationServicesControllers.ServiceManager;
import ColocationServicesControllers.UserManager;
import ColocationServicesModel.Colocation;
import ColocationServicesModel.User;
import ColocationServicesSecurity.SigninNeeded;
import java.awt.Image;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author philippe
 */
@Path("/colocations")
public class ColocationServices {

        @POST
        @SigninNeeded
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@QueryParam("login") String login, @QueryParam("name") String name) {
		if (ColocationManager.createColocation(name)){
                        UserManager.getUser(login).Creator();
			return Response.ok().build();
                }
		return Response.status(Response.Status.NOT_ACCEPTABLE).build();
	}
        @GET
        @SigninNeeded
        @Path("/manage/{name}")
        public String getColocationByName(@PathParam("name") String name){
            if(ColocationManager.getColocation(name)!=null)
                return ColocationManager.getColocation(name).getName();
            return "No Colocation for this name";
        }
        @POST
        @SigninNeeded
        @Path("/manage/createService")
        @Produces(MediaType.APPLICATION_JSON)
        public Response createService(@QueryParam("colocation") Colocation c, @QueryParam("title") String title,@QueryParam("description") String description,@QueryParam("cost") int cost){
            if (ServiceManager.createService(c,title,description,cost))
                return Response.ok().build();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        
        @POST
        @SigninNeeded
        @Path("/manage/declareAchievedService")
        @Produces(MediaType.APPLICATION_JSON)
        public Response declareAchievedService(@QueryParam("from") User from, @QueryParam("to") User to,@QueryParam("date") String date,@QueryParam("picture") Image picture,@QueryParam("valid") boolean valid){
            if (AchievedServiceManager.createAchievedService(from,to,date,picture,valid))
                return Response.ok().build();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        
        @POST
        @SigninNeeded
	@Path("/manage/invite")
	@Produces(MediaType.APPLICATION_JSON)
        public Response invite(@QueryParam("login") String login, @QueryParam("l_name") String l_name) {
           if(UserManager.getUser(login) == null)
               return Response.status(Response.Status.NOT_FOUND).build();
           else if (UserManager.getUser(login).isCreator())
                return Response.status(Response.Status.OK).build();
           return Response.status(Response.Status.FORBIDDEN).build();
        }
}
