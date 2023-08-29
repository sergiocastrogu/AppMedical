package HttpRequest;
import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;


public class HttpRequest {

        public HttpRequest(){

        }

    public interface VolleyCallbackHttp<T> {
        void onSuccess(String result);
        void onError(VolleyError error);
    }


    private static final String BASE_URL = "https://192.168.1.11:7170/api/"; // Cambia esto a la URL de tu API
    public void get(Context context, String endpoint , VolleyCallbackHttp callback) {
        try {
            URL url = new URL(BASE_URL+endpoint);
            String host = url.getHost();

            if ("192.168.1.11".equals(host)) {
                HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new X509TrustManager[]{new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }}, new SecureRandom());

                HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            }

            Volley.newRequestQueue(context).add(new JsonObjectRequest(Request.Method.GET, BASE_URL+endpoint, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            callback.onSuccess(response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            callback.onError(error);
                            error.printStackTrace();
                        }
                    }
            ));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // Método para hacer una petición POST
    public void post(Context context, String endpoint , JSONObject data, VolleyCallbackHttp callback) {
        try {
            URL url = new URL(BASE_URL+endpoint);
            String host = url.getHost();

            if ("192.168.1.11".equals(host)) {
                HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new X509TrustManager[]{new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }}, new SecureRandom());

                HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            }

            Volley.newRequestQueue(context).add(new JsonObjectRequest(Request.Method.POST, BASE_URL+endpoint, data,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            callback.onSuccess(response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            callback.onError(error);
                            error.printStackTrace();
                        }
                    }
            ).setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        // Método para hacer una petición PUT
        public static String put(String endpoint, String data) throws IOException {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            os.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            connection.disconnect();
            return response.toString();
        }

        // Método para hacer una petición DELETE
        public static String delete(String endpoint) throws IOException {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            connection.disconnect();
            return response.toString();
        }
}
