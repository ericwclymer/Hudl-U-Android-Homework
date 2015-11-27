package clymer.eric.hudlu;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import clymer.eric.hudlu.models.MashableNewsItem;

/**
 * Created by a guy named eric clymer on 11/26/2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.EricsViewHolder> {

    List<MashableNewsItem> inputData;
    private OnAdapterInteractionListener mListener;
    private RequestQueue requestQueue;

    public RecyclerViewAdapter(Context context, List<MashableNewsItem> data) {
        inputData = data;
        mListener = (OnAdapterInteractionListener) context;
        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public EricsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mashablecard, parent, false);
        return new EricsViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(final EricsViewHolder holder, final int position) {
        MashableNewsItem newsItem = inputData.get(position);
        holder.titleTextView.setText(newsItem.title);
        holder.authorTextView.setText(newsItem.author);
        ImageRequest request = new ImageRequest(newsItem.image,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        holder.imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error fetching image", error.getMessage());
                    }
                });


        requestQueue.add(request);
        //holder.imageView.setImageURI(new Uri(inputData.get(position).image));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClicked(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inputData.size();
    }

    public interface OnAdapterInteractionListener {
        void onItemClicked(View view, int position);
    }

    public static class EricsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView authorTextView;
        ImageView imageView;
        public EricsViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.item_title);
            authorTextView = (TextView) itemView.findViewById(R.id.item_author);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }
}
