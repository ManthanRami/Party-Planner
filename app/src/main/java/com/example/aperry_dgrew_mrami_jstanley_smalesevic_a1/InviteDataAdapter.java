//FILENAME      : InviteDataAdapter.java
//PROJECT       : PROG3150 - Assignment 1
//PROGRAMMER    : Aaron Perry, Daniel Grew, John Stanley, Manthan Rami, Sasha Malesevic
//FIRST VERSION : 2020-02-08

//Android Working with Recycler View
//By Ravi Tamada
//Retrieved from https://www.androidhive.info/2016/01/android-working-with-recycler-view/ (step 8)
//Adapted for the purposes required in this program -> InviteData RecyclerView

package com.example.aperry_dgrew_mrami_jstanley_smalesevic_a1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//CLASS   : InviteDataAdapter
//PURPOSE : Creates the adapter used in binding the InviteData array in the AddFriendsActivity to
//          the recyclerFriendView RecyclerView. The data is being displayed as present in
//          recycler_view_item.xml.
public class InviteDataAdapter extends RecyclerView.Adapter<InviteDataAdapter.InviteViewHolder> {
    private List<InviteData> inviteDataList;

    public class InviteViewHolder extends RecyclerView.ViewHolder{
        public TextView name, email;
        public ImageView picture;

        public InviteViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.name);
            email = view.findViewById(R.id.email);
            picture = view.findViewById(R.id.displayPicture);
        }
    }

    public InviteDataAdapter(List<InviteData> inviteDataList){
        this.inviteDataList = inviteDataList;
    }

    @Override
    public InviteViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        return new InviteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InviteViewHolder holder, int position){
        InviteData inviteData = inviteDataList.get(position);
        holder.name.setText(inviteData.getName());
        holder.email.setText(inviteData.getEmail());
        holder.picture.setImageResource(inviteData.getPicture());
    }

    @Override
    public int getItemCount(){
        return inviteDataList.size();
    }
}
