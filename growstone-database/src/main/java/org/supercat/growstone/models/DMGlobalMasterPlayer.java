package org.supercat.growstone.models;

import java.time.Instant;

public class DMGlobalMasterPlayer {
    public long id;
    public String device_os;
    public String device_model;
    public int login_type;
    public String login_id;
    public String guest_token_id;
    public int ban_status;
    public Instant updated_at;
    public Instant created_at;

    public static DMGlobalMasterPlayer of(String device_os, String device_model, int login_type,
                                          int ban_status) {
        var model = new DMGlobalMasterPlayer();
        model.device_os = device_os;
        model.device_model = device_model;
        model.login_id = "";
        model.guest_token_id = "";
        model.login_type = login_type;
        model.ban_status = ban_status;

        return model;
    }
}
