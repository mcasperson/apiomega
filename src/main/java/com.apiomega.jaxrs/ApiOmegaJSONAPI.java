package com.apiomega.jaxrs;

import com.apiomega.ApiOmegaConstants;
import com.yahoo.elide.annotation.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * This is a JAX-RS interface that defines the JSON API endpoints we need to respond to
 */
public interface ApiOmegaJSONAPI {
    /**
     * http://jsonapi.org/format/#fetching
     * @return The JSON API representation of an entity or a collection of entities
     */
    @GET
    @Path("/{entity}{id:(/[^/]+)?}{child:(/[^/]+/[^/]+)?}{relationships:(/[^/]+/relationships/)?}{relationship:(/[^/]+)?}")
    @Produces(ApiOmegaConstants.JSON_API_MIME)
    String jsonApiGet(@Context final UriInfo uriInfo);

    /**
     * http://jsonapi.org/format/#crud
     * return The JSON API representation of an entity or a collection of entities
     */
    @POST
    @Path("/{entity}{id:(/[^/]+)?}{child:(/[^/]+/[^/]+)?}{relationships:(/[^/]+/relationships/)?}{relationship:(/[^/]+)?}")
    @Produces(ApiOmegaConstants.JSON_API_MIME)
    @Consumes(ApiOmegaConstants.JSON_API_MIME)
    String jsonApiPost(@Context final UriInfo uriInfo, final String postBody);

    /**
     * http://jsonapi.org/format/#crud
     * return The JSON API representation of an entity or a collection of entities
     */
    @PATCH
    @Path("/{entity}{id:(/[^/]+)?}{child:(/[^/]+/[^/]+)?}{relationships:(/[^/]+/relationships/)?}{relationship:(/[^/]+)?}")
    @Produces(ApiOmegaConstants.JSON_API_MIME)
    @Consumes(ApiOmegaConstants.JSON_API_MIME)
    String jsonApiPatch(@Context final UriInfo uriInfo, final String patchBody);

    /**
     * http://jsonapi.org/format/#crud
     * return The JSON API representation of an entity or a collection of entities
     */
    @DELETE
    @Path("/{entity}{id:(/[^/]+)?}{child:(/[^/]+/[^/]+)?}{relationships:(/[^/]+/relationships/)?}{relationship:(/[^/]+)?}")
    @Produces(ApiOmegaConstants.JSON_API_MIME)
    String jsonApiDelete(@Context final UriInfo uriInfo);
}
