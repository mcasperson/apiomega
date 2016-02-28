package com.apiomega;

import java.util.regex.Pattern;

/**
 * Constant values used by ApiOmega
 */
public class ApiOmegaConstants {
    /**
     * The context of the ApiOmega implementation
     */
    public static final Pattern API_OMEGA_PATH_FORMAT =
            Pattern.compile(
                    "^/(?<author>[A-Za-z0-9]+)" +
                    "/(?<version>v\\d+\\.\\d+\\.\\d+)" +
                    "/(?<vendor>[A-Za-z0-9]+)" +
                    "/(?<device>[A-Za-z0-9]+)" +
                    "/(?<restOfUrl>.*)$");
}
