package org.supercat.growstone.models;

import java.time.Instant;

public class DMServerRedisConfig {
    public long id;
    public long channel_id;
    public int use_type;
    public long db_index;
    public int port;
    public String connection_string;
    public int connection_minimum;
    public int connection_maximum;
    public Instant updated_at;
    public Instant created_at;

    public static DMServerRedisConfig of(long channelId, int use_type, long db_index, int port, String connection_string, int connection_minimum, int connection_maximum) {
        var model = new DMServerRedisConfig();
        model.channel_id = channelId;
        model.use_type = use_type;
        model.db_index = db_index;
        model.port = port;
        model.connection_string = connection_string;
        model.connection_minimum = connection_minimum;
        model.connection_maximum = connection_maximum;
        return model;
    }
}
