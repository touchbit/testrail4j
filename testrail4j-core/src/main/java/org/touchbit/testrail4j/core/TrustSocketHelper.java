/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.touchbit.testrail4j.core;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * The utility class provides a socket context that allows you to trust the host on which SSL errors is present.
 * <p>
 * Created by Oleg Shaburov on 16.05.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"WeakerAccess", "SameParameterValue", "squid:S3510", "squid:S4424"})
public class TrustSocketHelper {

    private static final String CONTEXT = "TLSv1.2";
    private static final TrustAllCertsManager TRUST_ALL_CERTS_MANAGER = new TrustAllCertsManager();
    private static final TrustAllHostnameVerifier TRUST_ALL_HOSTNAME = new TrustAllHostnameVerifier();

    /**
     * @return {@link SSLSocketFactory} with trust in any SSL certificates context
     */
    public static SSLSocketFactory getTrustAllCrtSocketFactory() {
        return getTrustAllCrtSocketFactory(CONTEXT, TRUST_ALL_CERTS_MANAGER);
    }

    /**
     * @param context - Transport Security Protocol
     * @param manager - TrustManager for X509 certificates
     * @return {@link SSLSocketFactory} with trust in any SSL certificates context
     */
    private static SSLSocketFactory getTrustAllCrtSocketFactory(String context, X509TrustManager manager) {
        try {
            final SSLContext sslContext = SSLContext.getInstance(context);
            sslContext.init(null, new X509TrustManager[]{manager}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            // Formally, there can be no error, because the implementation of the X509TrustManager interface is empty.
            throw new IncredibleException(e);
        }
    }

    /**
     * @return {@link HostnameVerifier} with trust in any domains
     */
    public static TrustAllHostnameVerifier getTrustAllHostname() {
        return TRUST_ALL_HOSTNAME;
    }

    /**
     * @return {@link X509TrustManager} with trust in any X509 certificates
     */
    public static X509TrustManager getTrustAllCrtManager() {
        return TRUST_ALL_CERTS_MANAGER;
    }

    @SuppressWarnings("squid:S3510")
    public static class TrustAllHostnameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String var1, SSLSession var2) {
            return true;
        }

    }

    public static class TrustAllCertsManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
            // do nothing
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
            // do nothing
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static class IncredibleException extends RuntimeException {

        public IncredibleException(Throwable t) {
            super("You did it!!!", t);
        }

    }

    /** Utility class. We prohibit instantiation. */
    private TrustSocketHelper() {}

}
