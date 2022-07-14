package es.nbajugones.datagetter;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.X509HostnameVerifier;

public class UnsafeSSLHelper {

	private static boolean warnUnsecureMode = false;
	/**
	 * This method could be called before asking "https://" uri to accept any invalid ssl certificate.
	 *
	 * src: http://stackoverflow.com/questions/1828775/how-to-handle-invalid-ssl-certificates-with-apache-httpclient
	 *
	 * Usage
	 *
	 * common usage sample:
	 *    (new UnsafeSSLHelper()).ignoreSSLCertif();
	 *    // then make your https connection
	 *
	 * Apache HttpClient example:
	 UnsafeSSLHelper unsafeSSLHelper = new UnsafeSSLHelper();
	 CloseableHttpClient client = HttpClientBuilder
	 .create()
	 .setSslcontext(unsafeSSLHelper.createUnsecureSSLContext())
	 .setHostnameVerifier(unsafeSSLHelper.getPassiveX509HostnameVerifier())
	 .build();
	 */
	public SSLContext ignoreSSLCertif() {

		warnUnsecureMode = true;
		HostnameVerifier allHostsValid = getPassiveHostnameVerifier();
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		// Install the all-trusting trust manager
		SSLContext sc = createUnsecureSSLContext();
		try {
			SSLContext.setDefault(sc);
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sc;
	}

	public SSLContext createUnsecureSSLContext() {
		SSLContext sc = null;
		try {
			TrustManager[] trustAllCerts = new TrustManager[]{getPassiveTrustManager()};
			sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("", e);
		}
		return sc;
	}

	public HostnameVerifier getPassiveHostnameVerifier() {
		return new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
	}

	public X509TrustManager getPassiveTrustManager() {
		return new X509TrustManager() {

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkServerTrusted(X509Certificate[] arg0, String arg1)
					throws CertificateException {
			}

			public void checkClientTrusted(X509Certificate[] arg0, String arg1)
					throws CertificateException {
			}
		};
	}

	public X509HostnameVerifier getPassiveX509HostnameVerifier() {
		return new X509HostnameVerifier() {
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}

			public void verify(String host, SSLSocket ssl)
					throws IOException {
			}

			public void verify(String host, X509Certificate cert)
					throws SSLException {
			}

			public void verify(String host, String[] cns,
							   String[] subjectAlts) throws SSLException {
			}
		};
	}
}