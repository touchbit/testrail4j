package org.touchbit.testrail4j.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.touchbit.testrail4j.test.core.BaseUnitTest;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import java.lang.reflect.*;
import java.security.cert.CertificateException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 18.01.2019
 * shaburov.o.a@gmail.com
 */
@DisplayName("TrustSocketHelper tests")
class TrustSocketHelperTests extends BaseUnitTest {

    @Test
    @DisplayName("TrustSocketHelper#getTrustAllCrtSocketFactory()")
    void unitTest_20190118234803() {
        SSLSocketFactory factory = TrustSocketHelper.getTrustAllCrtSocketFactory();
        assertThat(factory).isNotNull();
    }

    @Test
    @DisplayName("TrustSocketHelper#getTrustAllHostname()")
    void unitTest_20190119000418() {
        TrustSocketHelper.TrustAllHostnameVerifier verifier = TrustSocketHelper.getTrustAllHostname();
        assertThat(verifier).isNotNull();
        assertThat(verifier.verify(null, null)).isTrue();
    }

    @Test
    @DisplayName("TrustSocketHelper#getTrustAllCrtManager")
    void unitTest_20190119000601() throws CertificateException {
        X509TrustManager manager = TrustSocketHelper.getTrustAllCrtManager();
        assertThat(manager).isNotNull();
        assertThat(manager.getAcceptedIssuers()).isEmpty();
        manager.checkClientTrusted(null, null);
        manager.checkServerTrusted(null, null);
    }

    @Test
    @DisplayName("TestRailClient#")
    void unitTest_20190119002753() throws Exception {
        Method method = TrustSocketHelper.class
                .getDeclaredMethod("getTrustAllCrtSocketFactory", String.class, X509TrustManager.class);
        method.setAccessible(true);
        InvocationTargetException exception = executeThrowable(() ->
                method.invoke(null, null, null), InvocationTargetException.class);
        assertThat(exception.getCause().getMessage()).isEqualTo("You did it!!!");
    }

}
