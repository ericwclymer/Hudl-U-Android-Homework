package clymer.eric.hudlu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by eric.clymer on 11/26/2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public RecyclerViewAdapter (Context context, List<String> data){

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class EricsViewHolder extends RecyclerView.ViewHolder{

        public EricsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
