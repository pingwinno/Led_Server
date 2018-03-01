// костыль убирающий слеш из результата InerAddrHelper

import java.net.InetAddress;

public class StringKotsyl {
    public String toProperSrting ( String address)
        {
            StringBuilder sb = new StringBuilder(address.toString());
            sb.deleteCharAt(0);
            return sb.toString();



        }

}
