package org.example.web.mvc.jolokia;

import org.jolokia.client.J4pClient;
import org.jolokia.client.J4pClientBuilder;
import org.jolokia.client.exception.J4pException;
import org.jolokia.client.request.J4pReadRequest;
import org.jolokia.client.request.J4pReadResponse;

import javax.management.MalformedObjectNameException;
import java.util.Map;

/**
 * @author kg yam
 * @date 2021-03-15 17:32
 * @since
 */
public class Demo {
    public static void main(String[] args) throws MalformedObjectNameException, J4pException {


        J4pClient j4pClient=new J4pClient ("");
        J4pReadRequest request = new J4pReadRequest ("java.lang:type=Memory",
                "HeapMemoryUsage");
        J4pReadResponse response = j4pClient.execute (request);
        Map<String, Long> vals = response.getValue ();
        long used = vals.get ("used");
        long max = vals.get ("max");
        int usage = (int) (used * 100 / max);
        System.out.println ("Memory usage: used: " + used +
                " / max: " + max + " = " + usage + "%");
    }
}
