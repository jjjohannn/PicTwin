package cl.ucn.disc.dsm.pictwin.frontend.service;

import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import cl.ucn.disc.dsm.pictwin.frontend.model.Twin;
import cl.ucn.disc.dsm.pictwin.frontend.model.User;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * the user repository.
 *
 * @autor Johan Rojas-Godoy
 */
@Slf4j
public final class UserRepository {

    private static final String TAG = "UserRepository";
    /**
     * The API Rest
     */
    //the local network
    //private static final String BASE_URL = "http://10.0.0.145:8080/";

    //the internal network
    private static final String BASE_URL = "http://192.168.0.4:8080";

    /**
     * The API Rest
     */
    private final PicTwinAPIRest apiRest;

    /** The constructor. */
    public UserRepository() {

        Log.d("Repo", "mdh...");
        // log.debug("Building UserRepository with URL: {}",  BASE_URL);

        //the logger
        HttpLoggingInterceptor theLogging = new HttpLoggingInterceptor((msg) -> {
            Log.d("UserRepo", msg);
        });
        theLogging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //the okhttp
        OkHttpClient theClient = new OkHttpClient.Builder()
                .addInterceptor(theLogging)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .callTimeout(50, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(theClient)
                .build();
        //the API Rest
        this.apiRest = retrofit.create(PicTwinAPIRest.class);
    }

    /**
     * Retrieve a user from PicTwin API Rest
     *
     * @param email the email
     * @param password the password
     * @return the user
     */
    //@SneakyThrows
    public Optional<User> retrieveUser(final String email,final String password) {
        //The call
        Log.i(TAG, "retrieveUser: " + email+" "+password);
        Call<User> cUser = this.apiRest.retrieveUser(email, password);
        Log.i(TAG, "CALL: " + cUser.request().url());
        try {
            //the execute
            Log.i(TAG, "execute..."+cUser.execute().body());
            Response<User> rUser = cUser.clone().execute();
            Log.d("Repo", "rUser: " + rUser.body());
            //code in 2xx range
            if (rUser.isSuccessful()) {
                //check for body
                if (rUser.body() == null) {
                    return Optional.empty();
                }
                //return the user
                return Optional.of(rUser.body());
            }
            throw new RuntimeException("Can not retrieve user ", new HttpException(rUser));
        }catch (IOException ex) {
            //IO error
            Log.e(TAG, "IO error", ex);
            throw new RuntimeException("Can not retrieve user ", ex);
        }

    }

}
