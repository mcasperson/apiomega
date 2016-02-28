package com.apiomega;

import java.util.regex.Pattern;

/**
 * Constant values used by ApiOmega
 */
public class ApiOmegaConstants {
    /**
     * The path that holds the API methods
     */
    public static final String API_PATH = "api";

    /**
     * The path that holds the documentation
     */
    public static final String DOCS_PATH = "docs";

    /**
     * The path tha holds custom applications
     */
    public static final String APP_PATH = "app";

    /**
     * The context of the ApiOmega implementation
     */
    public static final Pattern API_OMEGA_PATH_FORMAT =
            Pattern.compile(
                    "^/(?<author>[A-Za-z0-9]+)" +
                    "/(?<version>v\\d+\\.\\d+\\.\\d+)" +
                    "/(?<vendor>[A-Za-z0-9]+)" +
                    "/(?<device>[A-Za-z0-9]+)" +
                    "/(?<function>(" + API_PATH + "|" + DOCS_PATH + "|" + APP_PATH + "))" +
                    "/(?<restOfUrl>.*)$");
}
