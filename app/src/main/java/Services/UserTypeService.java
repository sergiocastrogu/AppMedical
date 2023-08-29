package Services;

import android.content.Context;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import HttpRequest.HttpRequest;
import models.ResponseBase;
import models.UserTypeListResponse;

public class UserTypeService {


    public interface VolleyCallback<T> {
        void onSuccess(T data, Context context);
        void onError();
    }
    private HttpRequest httpRequest = new HttpRequest();
    public void getUserByUserType(Context context, VolleyCallback callback) {
        httpRequest.get(context, "UserType/all", new HttpRequest.VolleyCallbackHttp() {
            @Override
            public void onSuccess(String result) {
                try{
                    ObjectMapper mapper =  new ObjectMapper();
                    ResponseBase<UserTypeListResponse> userList =  mapper.readValue(result, new TypeReference<ResponseBase<UserTypeListResponse>>() {});
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

}
