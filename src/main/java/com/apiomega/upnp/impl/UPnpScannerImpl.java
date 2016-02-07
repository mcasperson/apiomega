package com.apiomega.upnp.impl;

import com.apiomega.upnp.UPnpScanner;
import org.apache.commons.lang3.StringUtils;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.UpnpServiceImpl;
import org.fourthline.cling.model.message.header.STAllHeader;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.RemoteDevice;
import org.fourthline.cling.registry.Registry;
import org.fourthline.cling.registry.RegistryListener;

import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * An implementation of a UPnP discovery service based on the Cling library
 */
public class UPnpScannerImpl implements UPnpScanner {
    private static final Logger LOGGER = Logger.getLogger(UPnpScannerImpl.class.toString());
    private static final int DISCOVERY_TIME = 10000;

    @Override
    @NotNull
    public Collection<URL> getDeviceUrls(@NotNull final String displayNameRegex) {
        checkArgument(StringUtils.isNotBlank(displayNameRegex));

        final Pattern pattern = Pattern.compile(displayNameRegex);

        final Collection<URL> retValue = new ConcurrentSkipListSet <URL>();

        // UPnP discovery is asynchronous, we need a callback
        final RegistryListener listener = new RegistryListener() {

            public void remoteDeviceDiscoveryStarted(Registry registry,
                                                     RemoteDevice device) {
                LOGGER.log(Level.INFO, 
                        "Discovery started: " + device.getDisplayString()
                );

                if (pattern.matcher(device.getDisplayString()).matches()) {
                    retValue.add(device.getIdentity().getDescriptorURL());
                }
            }

            public void remoteDeviceDiscoveryFailed(Registry registry,
                                                    RemoteDevice device,
                                                    Exception ex) {
                LOGGER.log(Level.INFO, 
                        "Discovery failed: " + device.getDisplayString() + " => " + ex
                );
            }

            public void remoteDeviceAdded(Registry registry, RemoteDevice device) {
                LOGGER.log(Level.INFO, 
                        "Remote device available: " + device.getDisplayString()
                );
            }

            public void remoteDeviceUpdated(Registry registry, RemoteDevice device) {
                LOGGER.log(Level.INFO, 
                        "Remote device updated: " + device.getDisplayString()
                );
            }

            public void remoteDeviceRemoved(Registry registry, RemoteDevice device) {
                LOGGER.log(Level.INFO, 
                        "Remote device removed: " + device.getDisplayString()
                );
            }

            public void localDeviceAdded(Registry registry, LocalDevice device) {
                LOGGER.log(Level.INFO, 
                        "Local device added: " + device.getDisplayString()
                );
            }

            public void localDeviceRemoved(Registry registry, LocalDevice device) {
                LOGGER.log(Level.INFO, 
                        "Local device removed: " + device.getDisplayString()
                );
            }

            public void beforeShutdown(Registry registry) {
                LOGGER.log(Level.INFO, 
                        "Before shutdown, the registry has devices: "
                                + registry.getDevices().size()
                );
            }

            public void afterShutdown() {
                LOGGER.log(Level.INFO, "Shutdown of registry complete!");

            }
        };

        // This will create necessary network resources for UPnP right away
        LOGGER.log(Level.INFO, "Starting Cling...");
        UpnpService upnpService = new UpnpServiceImpl(listener);

        // Send a search message to all devices and services, they should respond soon
        upnpService.getControlPoint().search(new STAllHeader());

        // Let's wait 10 seconds for them to respond
        LOGGER.log(Level.INFO, "Waiting 10 seconds before shutting down...");
        try {
            Thread.sleep(DISCOVERY_TIME);
        } catch (final InterruptedException ex) {
            LOGGER.log(Level.INFO, "Thread.sleep was interrupted");
        }

        // Release all resources and advertise BYEBYE to other UPnP devices
        LOGGER.log(Level.INFO, "Stopping Cling...");
        upnpService.shutdown();

        return retValue;
    }
}
