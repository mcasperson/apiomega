package com.apiomega.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A servlet filter that will log all requests to ApiOmega endpoints
 */
public class LoggingFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class.getName());

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

            LOGGER.log(
                    Level.INFO,
                    "AuthType: " + httpServletRequest.getAuthType() + "\n" +
                    "CharacterEncoding: " + httpServletRequest.getCharacterEncoding() + "\n"

            );
        } catch (final Exception ex) {
            /*
                We never want to hold up a request because of some exception
             */
        } finally {
            /*
                Always continue the filter chain
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
