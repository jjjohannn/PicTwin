package cl.ucn.disc.dsm.pictwin.frontend;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cl.ucn.disc.dsm.pictwin.frontend.model.Pic;
import cl.ucn.disc.dsm.pictwin.frontend.model.Twin;
import cl.ucn.disc.dsm.pictwin.frontend.model.User;
import cl.ucn.disc.dsm.pictwin.frontend.service.UserRepository;

public final class UserViewModel extends AndroidViewModel {

    private static final String TAG = "UserViewModel";
    /**
     * The executor with two threads.
     */
    private static final Executor EXECUTOR = Executors.newFixedThreadPool(2);
    /**
     * The repository.
     */
    private final UserRepository userRepository =new UserRepository();

    /**
     * the user .
     */
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();

    /**
     * the constructor.
     * @param application
     */
    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * returns the userLiveData.
     */
    public LiveData<User> getUserLiveData() {

        Log.d(TAG, "getUserLiveData: "+userLiveData.getValue());
        return this.userLiveData;

    }

    /**
     * Refresh or get the data
     */
    public void update(){
        //only load if we don't have a user
        Log.i(TAG, "Update:");
        if(this.userLiveData.getValue()==null){
            Log.i(TAG, "update.. loading");
            this.retrieveUserFromNetworkInBackground();
        }
    }

    /**
     * Retrieve the user from the network in background.
     */
    private void retrieveUserFromNetworkInBackground() {
        //execute the task in background
        Log.i(TAG, "retrieveUse: johan ");
        EXECUTOR.execute(() -> {

            //get the user from repository
            Optional<User> oUser = this.userRepository.retrieveUser("Johan@ucn.cl","Johan123");
            Log.i(TAG, "oUser: " + oUser.isPresent());
            //Set the user only if exists
            oUser.ifPresent(this.userLiveData::postValue);
        });
    }

}
