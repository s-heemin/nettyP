package org.supercat.growstone.auth;

import com.supercat.growstone.network.messages.ZLogin;
import org.supercat.growstone.datas.UserIdentity;
import org.supercat.growstone.SLog;

public class LoginVerifier {
    public static UserIdentity validateAndRetrieveUser(String token, ZLogin.Type loginType) {
        try {
            switch (loginType) {
                case GOOGLE:
                    var googleIdentity = GoogleLoginVerifier.verifyToken(token);
                    return UserIdentity.of(googleIdentity.getSubject(), googleIdentity.getEmail());
                case APPLE:
                    var appleIdentity = AppleLoginVerifier.verifyToken(token);
                    return UserIdentity.of(appleIdentity.getSubject(), appleIdentity.getClaim("email").asString());
                default:
                    return UserIdentity.of(token, "");
            }
        } catch (Exception e) {
             SLog.logException(e);
            return null;
        }
    }
}
