package com.apiomega.upnp;

import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.RemoteDevice;

import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.Collection;

/**
 * This interfaces defines a service that can be used to find devices on the network
 */
public interface UPnpScanner {
    /**
     *
     * @param displayNameRegex A regular expression that can be matched against the display name
     *                         of the UPnP device (i.e. <manufacturer> <model name> <model#>).
     *                         {@link Device#getDisplayString()} )}
     * @return                  A collection of URLs that can be used to contact the UPnP device
     */
    @NotNull
    Collection<URL> getDeviceUrls(@NotNull final String displayNameRegex);

    /**
     *
     * @param displayNameRegex A regular expression that can be matched against the display name
     *                         of the UPnP device (i.e. <manufacturer> <model name> <model#>).
     *                         {@link Device#getDisplayString()} )}
     * @return                  A collection of device objects that were discovered
     */
    Collection<RemoteDevice> getDevices(@NotNull final String displayNameRegex);
}
