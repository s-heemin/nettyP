package org.supercat.growstone.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.URL;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class AppleLoginVerifier {
    private static final String APPLE_ISSUER = "https://appleid.apple.com";
    private static final String APPLE_CLIENT_ID = System.getenv("NEED KEY");

    public static DecodedJWT verifyToken(String idTokenString) throws Exception {
        DecodedJWT jwt = JWT.decode(idTokenString);
        String kid = jwt.getHeaderClaim("kid").asString();

        // Get public key based on kid
        RSAPublicKey publicKey = getPublicKey(kid);

        // Create JWT verifier
        Algorithm algorithm = Algorithm.RSA256(publicKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(APPLE_ISSUER)
                .withAudience(APPLE_CLIENT_ID)
                .build();

        // Verify the token
        return verifier.verify(idTokenString);
    }

    private static RSAPublicKey getPublicKey(String kid) throws Exception {
        URL jwksUrl = new URL("https://appleid.apple.com/auth/keys");
        InputStream inputStream = jwksUrl.openStream();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jwks = objectMapper.readTree(inputStream);
        JsonNode keys = jwks.get("keys");

        for (JsonNode key : keys) {
            if (kid.equals(key.get("kid").asText())) {
                String modulus = key.get("n").asText();

                byte[] modulusBytes = java.util.Base64.getDecoder().decode(modulus);

                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(modulusBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                return (RSAPublicKey) keyFactory.generatePublic(keySpec);
            }
        }
        throw new IllegalArgumentException("No matching key found");
    }
}
