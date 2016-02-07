package com.apiomega.jaxrs.impl;

import com.apiomega.ApiOmegaConstants;
import com.apiomega.jaxrs.ApiOmegaJSONAPI;
import com.yahoo.elide.Elide;
import com.yahoo.elide.ElideResponse;
import com.yahoo.elide.audit.Logger;
import com.yahoo.elide.audit.Slf4jLogger;
import com.yahoo.elide.core.DataStore;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import java.util.regex.Matcher;

import static com.google.common.base.Preconditions.checkState;

/**
 * An implementation of the JSON API RESTful interface that maps requests to Elide
 * for processing.
 */
@Stateless  // We want to make use of EJB transactions
public class ApiOmegaJSONAPIImpl implements ApiOmegaJSONAPI {

    /**
     * Get a Elide data store. It is expected that projects extending this class
     * will provide a data store via CDI.
     */
    @Inject
    private DataStore dataStore;

    @Override
    public String jsonApiGet(@Context final UriInfo uriInfo) {
        final String restOfTheUrl = uriInfo.getPath();

        final Matcher matcher = ApiOmegaConstants.API_OMEGA_PATH_FORMAT.matcher(restOfTheUrl);

        checkState(matcher.find(), "APIOMEGA-URL-0001");

        /* Takes a hibernate session factory */
        final Logger logger = new Slf4jLogger();
        final Elide elide = new Elide(logger, dataStore);

        final String path = matcher.group(ApiOmegaConstants.JSON_API_PATH_GROUP);

        final ElideResponse response = elide.get(path, uriInfo.getQueryParameters(), new Object());

        return response.getBody();
    }

    @Override
    public String jsonApiPost(@Context final UriInfo uriInfo, final String postBody) {
        return null;
    }

    @Override
    public String jsonApiPatch(@Context final UriInfo uriInfo, final String patchBody) {
        return null;
    }

    @Override
    public String jsonApiDelete(@Context final UriInfo uriInfo) {
        return null;
    }
}
