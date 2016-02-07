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
     * The regex group that contains the JSON API component of a url
     */
    public static final String JSON_API_PATH_GROUP = "jsonApiPath";

    /**
     * The context of the ApiOmega implementation
     */
    public static final Pattern API_OMEGA_PATH_FORMAT =
            Pattern.compile(
                    "^(/(?<author>[A-Za-z0-9]+))?" +
                    "/(?<version>v\\d+\\.\\d+\\.\\d+)" +
                    "/(?<vendor>[A-Za-z0-9]+)" +
                    "/(?<device>[A-Za-z0-9]+)" +
                    "/(?<" + JSON_API_PATH_GROUP + ">.*)");

    /**
     * What to populate the excpetion with when an action relationship is attempted to be accessed
     */
    public static final String ACTION_UNSUPPORTED_OPERATION_MESSAGE =
            "APIOMEGA-URL-0002: You can not get the value of an action relationship. This relation is virtual, and has no associated information.";
}
