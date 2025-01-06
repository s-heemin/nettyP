package org.supercat.growstone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EC2InstanceUtil {
    public static String getInstanceId() throws Exception {
        URL url = new URL("http://169.254.169.254/latest/meta-data/instance-id");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String instanceId = in.readLine();
        in.close();

        return instanceId;
    }
}
