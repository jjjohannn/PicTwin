package cl.ucn.disc.dsm.pictwin.frontend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    /**
     * UserViewModel.
     */
    private UserViewModel userViewModel;
    private static final String TAG = "MainActivity";
    /**
     * the onCreate instance
     * @param savedInstanceState instance
     */
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GET THE RECYCLER VIEW FROM THE LAYOUT
        RecyclerView recyclerView = findViewById(R.id.am_rv_twins);

        //The layout of the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        //Build the adapter
        UserAdapter adapter = new UserAdapter();

        //Set the adapter to the recycler view
        recyclerView.setAdapter(adapter);

        //Build the userViewModel
        this.userViewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(super.getApplication())
                .create(UserViewModel.class);

        //Watch the view model
        userViewModel.getUserLiveData().observe(this, user -> {
            //Update the adapter
            adapter.setUser(user);
            //Refresh the GUI
            adapter.notifyDataSetChanged();

        });
        Log.i(TAG, "UserLiveData: "+this.userViewModel.getUserLiveData().getValue());
    }

    @Override
    protected  void onStart() {
        super.onStart();
        userViewModel.update();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.night_mode){
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            }
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        }else if(id==R.id.settings){
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();}
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

}