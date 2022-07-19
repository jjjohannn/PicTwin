package cl.ucn.disc.dsm.pictwin.frontend;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cl.ucn.disc.dsm.pictwin.frontend.model.Twin;
import cl.ucn.disc.dsm.pictwin.frontend.model.User;

public final class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private static final String TAG = "UserAdapter";
    /**
     * the user .
     */
    private User user;
    /**
     * the constructor.
     */
    public UserAdapter() {
        //nada aca
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView mine;

        protected TextView yours;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mine = itemView.findViewById(R.id.rt_tv_mine);
            this.yours = itemView.findViewById(R.id.rt_tv_yours);
        }
    }

    /**
     * called when recycler view needs a new {@link ViewHolder} of to given type to represent an item.
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View twinView= LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.row_twin, parent, false);
        return new ViewHolder(twinView);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.this method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position
     *
     * @param holder
     * @param position
     */
    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Retrieve the twin  at position
        final Twin twin = this.user.getTwins().get(position);
        //Update the view with the data
        holder.mine.setText(String.format("ID: %d", twin.getMyPic().getId()));
        holder.yours.setText(String.format("ID: %d", twin.getYoursPic().getId()));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return the total number of items in this adapter.
     */
    @Override
    public int getItemCount() {

        if (user == null) {
            return 0;
        }
        return user.getTwins().size();
    }

    /**
     * set the user.
     */
    public void setUser(@NonNull User user) {

        Log.i(TAG, "setUser: " + user.getEmail());
        this.user = user;
    }

}
