import com.cjsavage.java.net.discovery.ServiceAnnouncer;
import com.cjsavage.java.net.discovery.ServiceInfo;

import java.io.IOException;



/**
 * Created by shiro on 14.10.17.
 */

public class BroadcastSearchHelper extends Thread
{
    private StringKotsyl stringKotsyl = new StringKotsyl();
    private String serviceId = "e9ababe5872f24caf1a504f1d675470c";
    private  String rawIp = InetAddrHelper.getFirstNonLoopbackAddress(true,false);
    private String ip = stringKotsyl.toProperSrting(rawIp);

    public BroadcastSearchHelper() throws IOException
    {

    }

    @Override
    public void run()
    {


        ServiceAnnouncer announcer = new ServiceAnnouncer();
        ServiceInfo si =
                new ServiceInfo("My Server", serviceId,ip, 8000, false);
        announcer.addService(si);
        announcer.startListening();
        System.out.println("start discovery server on ip" + ip);




    }


}
