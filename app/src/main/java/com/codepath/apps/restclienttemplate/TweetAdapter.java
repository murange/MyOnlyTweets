package com.codepath.apps.restclienttemplate;
/*created by rhu on 5/25/17.
*/

import android.content.Context;
import android.net.sip.SipSession;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import com.codepath.apps.restclienttemplate.R;

import com.bumptech.glide.Glide;

import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder>{
    private List<Tweet> mTweets;
    Context context;
    private static TweetAdapterListener mListener;
    // define a interface required by the ViewHolder

    public interface TweetAdapterListener {
        public void onItemSelected(View view, int position);

    }
    // pass in the Tweets array into the constructor
    public TweetAdapter(List<Tweet> tweets, TweetAdapterListener listener){
        mTweets = tweets;
        mListener = listener;
    }

     // inflate the layout and cache the findViewByids into ViewHolder parent
    @Override
    public TweetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent ,int position) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        View tweetView  = inflater.inflate(R.layout.item_tweet , parent ,false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
      }

    //bind the values basesd on the position oh the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // get the data according to position
       final Tweet tweet = mTweets.get(position);

        // set view according to the model
        holder.tvUsername.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);

        Glide.with(context) . load(tweet.user.profileImageUrl) .into(holder.ivProfileImage);

    }

    @Override
    public int getItemCount(){
        return mTweets.size();
    }


    // create ViewHolder
    public static  class ViewHolder  extends RecyclerView.ViewHolder{

        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvBody;

        public ViewHolder (View itemView){
            super (itemView);

            // perform findViewById lookups
            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUserName);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);

            // handle row click event
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if (mListener != null) {

                        // get the position of row element
                        int position = getAdapterPosition();
                        // fire the listener callback
                        mListener.onItemSelected(view, position);
                    }
                }

            });
        }
    }
}
