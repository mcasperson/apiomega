package com.apiomega.jaxrs.impl;

import com.apiomega.ApiOmegaConstants;
import com.apiomega.jaxrs.ApiOmegaJSONAPI;
import com.yahoo.elide.Elide;
import com.yahoo.elide.ElideResponse;
import com.yahoo.elide.audit.Logger;
import com.yahoo.elide.audit.Slf4jLogger;
import com.yahoo.elide.core.DataStore;
import com.yahoo.elide.datastores.hibernate5.HibernateStore;
import org.hibernate.SessionFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
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
     * This is the JPA entity manager factory that we will pass to Elide.
     * Note that we assume that this entity manager is actually a hibernate 5
     * session factory. This assumption is try when deployed to Wildfly 10,
     * which is the basis of the ApiOmega server.
     */
    @PersistenceContext
    private EntityManagerFactory entityManagerFactory;

    @Override
    public String jsonApiGet(@Context final UriInfo uriInfo) {
        final String restOfTheUrl = uriInfo.getPath();

        final Matcher matcher = ApiOmegaConstants.API_OMEGA_PATH_FORMAT.matcher(restOfTheUrl);

        checkState(matcher.find(), "APIOMEGA-URL-0001");

        final SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        /* Takes a hibernate session factory */
        final DataStore dataStore = new HibernateStore(sessionFactory);
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
