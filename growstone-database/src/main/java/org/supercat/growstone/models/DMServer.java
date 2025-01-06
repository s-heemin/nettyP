package org.supercat.growstone.models;

import java.time.Instant;

public class DMServer {
    public long id;
    public String instance_id;
    public int port;
    public int players;
    public Instant updated_at;
    public Instant created_at;

    public static DMServer of(String instance_id, int port) {
        DMServer server = new DMServer();
        server.instance_id = instance_id;
        server.port = port;
        server.players = 0;
        return server;
    }
}
