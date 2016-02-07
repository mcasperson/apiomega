package com.apiomega;

import java.util.regex.Pattern;

/**
 * Constant values used by ApiOmega
 */
public class ApiOmegaConstants {
    /**
     * The JSON API MIME type
     */
    public static final String JSON_API_MIME = "application/vnd.api+json";

    /**
     * The prefix to any relationship that is an action
     */
    public static final String ACTION_RELATIONSHIP_PREFIX = "action_";

    /**
     * The context of the ApiOmega implementation
     */
    public static final Pattern API_OMEGA_PATH_FORMAT =
            Pattern.compile(
                    "^(/(?<author>[A-Za-z0-9]+))?" +
                    "/(?<version>v\\d+\\.\\d+\\.\\d+)" +
                    "/(?<vendor>[A-Za-z0-9]+)" +
                    "/(?<device>[A-Za-z0-9]+)");
}
