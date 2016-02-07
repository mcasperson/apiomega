package com.apiomega.jaxrs.impl;

import com.apiomega.jaxrs.ApiOmegaJSONAPI;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

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
        return null;
    }

    @Override
    public String jsonApiPost(@Context final UriInfo uriInfo) {
        return null;
    }

    @Override
    public String jsonApiPatch(@Context final UriInfo uriInfo) {
        return null;
    }

    @Override
    public String jsonApiDelete(@Context final UriInfo uriInfo) {
        return null;
    }
}
