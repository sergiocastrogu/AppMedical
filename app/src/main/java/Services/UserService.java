package Services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import HttpRequest.HttpRequest;
import models.ResponseBase;
import models.ResultPost;
import models.User;
import models.UserListResponse;
import models.UserResponse;

public class UserService {

    public interface VolleyCallback<T> {
        void onSuccess(T data, Context context);
        void onError();
    }

    private HttpRequest httpRequest = new HttpRequest();
    public void getUserById(Context context, int userId, VolleyCallback callback) {
        try {
            // Resto del código para crear la solicitud HTTPS
            httpRequest.get(context, "User/get-id?Id=" + userId, new HttpRequest.VolleyCallbackHttp() {
                @Override
                public void onSuccess(String response) {
                    ObjectMapper mapper =  new ObjectMapper();
                    try{
                        ResponseBase<UserResponse> u = mapper.readValue(response, new TypeReference<ResponseBase<UserResponse>>(){});
                        callback.onSuccess(u.getData(), context);
                    } catch (Exception ex){
                        callback.onError();
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onError(VolleyError error) {
                    callback.onError();
                    error.printStackTrace();
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public void getUserByUserType(Context context, int userType, VolleyCallback callback) {
            httpRequest.get(context, "User/get-type?TypeId=" + userType, new HttpRequest.VolleyCallbackHttp() {
                @Override
                public void onSuccess(String result) {
                    try{
                        ObjectMapper mapper =  new ObjectMapper();
                        ResponseBase<UserListResponse> userList =  mapper.readValue(result, new TypeReference<ResponseBase<UserListResponse>>() {});
                        callback.onSuccess(userList, context);
                    } catch (Exception ex) {
                        callback.onError();
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onError(VolleyError error) {
                    callback.onError();
                    error.printStackTrace();
                }
            });
    }
    public void createUser(Context context, User user, VolleyCallback<ResponseBase<ResultPost>> callback) {
        int response;
        HttpRequest httpRequest = new HttpRequest();
        try {
            ObjectMapper mapper =  new ObjectMapper();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            JSONObject jsonObject =  new JSONObject(mapper.writeValueAsString(user));
            httpRequest.post(context, "User/register", jsonObject, new HttpRequest.VolleyCallbackHttp() {
                @Override
                public void onSuccess(String result) {
                    try {
                        ObjectMapper mapper =  new ObjectMapper();
                        ResponseBase<ResultPost> response = mapper.readValue(result, new TypeReference<ResponseBase<ResultPost>>() {});
                        callback.onSuccess(response, context);
                    } catch (Exception ex){
                        callback.onError();
                        ex.printStackTrace();
                    }

                }

                @Override
                public void onError(VolleyError error) {
                    callback.onError();
                    error.printStackTrace();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
