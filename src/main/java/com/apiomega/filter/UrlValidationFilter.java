package com.apiomega.filter;

import com.apiomega.ApiOmegaConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * Created by Matthew on 13/02/2016.
 */
public class UrlValidationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(UrlValidationFilter.class.getName());

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        /*
            Nothing to do here
         */
    }

    @Override
    public void doFilter(
            final ServletRequest request,
            final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {
        try {
            final HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            final HttpServletResponse httpServletResponse = (HttpServletResponse)response;

            final URL url = new URL(httpServletRequest.getRequestURI());
            final Matcher matcher = ApiOmegaConstants.API_OMEGA_PATH_FORMAT.matcher(url.getPath());
            final boolean found = matcher.find();

            if (!found) {
                httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "The request URL does not conform to the ApiOmega standards.");
            } else {
                chain.doFilter(request, response);
            }
        } catch (final Exception ex) {
            /*
                Don't break a request because of an exception
             */
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        /*
            Nothing to do here
         */
    }
}
