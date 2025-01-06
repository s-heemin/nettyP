package org.supercat.growstone.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;

public class GoogleLoginVerifier {
    private static final String CLIENT_ID = "1048618835185-c52sfcrbakemkodamnsugnkdo1dtu0l2.apps.googleusercontent.com";

    public static GoogleIdToken.Payload verifyToken(String idTokenString) throws Exception {
        var jsonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jsonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            return idToken.getPayload();
        } else {;
            throw new Exception("Invalid ID token.");
        }
    }
}
