import com.apiomega.upnp.UPnpScanner;
import com.apiomega.upnp.impl.UPnpScannerImpl;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.UpnpServiceImpl;
import org.fourthline.cling.model.message.header.STAllHeader;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.RemoteDevice;
import org.fourthline.cling.registry.Registry;
import org.fourthline.cling.registry.RegistryListener;
import org.junit.Test;

import java.net.URL;
import java.util.Collection;

/**
 * Tests of the UPnP service
 */
public class UPNPTest {
    @Test
    public void testURLDiscovery() {
        final UPnpScanner uPnpScanner = new UPnpScannerImpl();
        final Collection<URL> urls = uPnpScanner.getDeviceUrls("^LG Electronics LG TV 1\\.0$");
        System.out.println(urls);
    }

    @Test
    public void testUPNP() throws InterruptedException {
        // UPnP discovery is asynchronous, we need a callback
        RegistryListener listener = new RegistryListener() {

            public void remoteDeviceDiscoveryStarted(Registry registry,
                                                     RemoteDevice device) {
                System.out.println(
                        "Discovery started: " + device.getDisplayString()
                );
            }

            public void remoteDeviceDiscoveryFailed(Registry registry,
                                                    RemoteDevice device,
                                                    Exception ex) {
                System.out.println(
                        "Discovery failed: " + device.getDisplayString() + " => " + ex
                );
            }

            public void remoteDeviceAdded(Registry registry, RemoteDevice device) {
                System.out.println(
                        "Remote device available: " + device.getDisplayString()
                );
            }

            public void remoteDeviceUpdated(Registry registry, RemoteDevice device) {
                System.out.println(
                        "Remote device updated: " + device.getDisplayString() + "\n" +
                                device.getIdentity().getDescriptorURL()
                );
            }

            public void remoteDeviceRemoved(Registry registry, RemoteDevice device) {
                System.out.println(
                        "Remote device removed: " + device.getDisplayString()
                );
            }

            public void localDeviceAdded(Registry registry, LocalDevice device) {
                System.out.println(
                        "Local device added: " + device.getDisplayString()
                );
            }

            public void localDeviceRemoved(Registry registry, LocalDevice device) {
                System.out.println(
                        "Local device removed: " + device.getDisplayString()
                );
            }

            public void beforeShutdown(Registry registry) {
                System.out.println(
                        "Before shutdown, the registry has devices: "
                                + registry.getDevices().size()
                );
            }

            public void afterShutdown() {
                System.out.println("Shutdown of registry complete!");

            }
        };

        // This will create necessary network resources for UPnP right away
        System.out.println("Starting Cling...");
        UpnpService upnpService = new UpnpServiceImpl(listener);

        // Send a search message to all devices and services, they should respond soon
        upnpService.getControlPoint().search(new STAllHeader());

        // Let's wait 10 seconds for them to respond
        System.out.println("Waiting 10 seconds before shutting down...");
        Thread.sleep(10000);

        // Release all resources and advertise BYEBYE to other UPnP devices
        System.out.println("Stopping Cling...");
        upnpService.shutdown();
    }
}
