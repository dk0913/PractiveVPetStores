package com.happypet.model;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.happypet.service.MapsActivity;
import com.happypet.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PetStoreAdapter extends RecyclerView.Adapter<PetStoreAdapter.PetStoreViewHolder> {

    private List<PetStore> mPetStores;

    private FirebaseAuth mFirebaseAuth;

    private DatabaseReference storesDbRef;

    private Context mContext;
    public PetStoreAdapter(List<PetStore> PetStores) {
        mPetStores = PetStores;
    }

    /**creates ViewHolder for RecyclerView items*/
    @NonNull
    @Override
    public PetStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mContext = parent.getContext();

        View view = LayoutInflater.from(mContext).inflate(R.layout.pet_store_item, parent, false);
        CardView cardView = (CardView) view.findViewById(R.id.pet_store_card_view);

        // Get user's uid and the DB reference for their cart

        storesDbRef = FirebaseDatabase.getInstance().getReference().child("Stores");

        cardView.setUseCompatPadding(true); // Optional: adds padding for pre-lollipop devices
        return new PetStoreViewHolder(view);
    }
    /** gets pet store at selected position and sets show on map button to pass the pet store to
     * the MapsActivity*/
    @Override
    public void onBindViewHolder(@NonNull PetStoreViewHolder holder, int position) {
        PetStore petStore = mPetStores.get(position);

        holder.mShowOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passPetStoreToMapsActivity(petStore);
            }
        });
        holder.bind(petStore);
    }

    @Override
    public int getItemCount() {
        return mPetStores.size();
    }

    public void setPetStores(List<PetStore> petStores) {
        mPetStores = petStores;
        notifyDataSetChanged();
    }
    /**Custom ViewHolder class specifically for pet stores that holds the text view displaying the
     * name of the pet store and button for user to display the pet store on google maps fragment*/
    static class PetStoreViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTextView;
        private Button mShowOnMapButton;

        public PetStoreViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.pet_store_name_text_view);
            mShowOnMapButton= itemView.findViewById(R.id.pet_store_order_show_map);
        }

        public void bind(PetStore petStore) {
            mNameTextView.setText(petStore.getName());
            mShowOnMapButton.setText("Show On Map");
        }
    }

    /**Helper method that passes the selected pet store to the MapsActivity to be displayed on the
     * map*/
    public void passPetStoreToMapsActivity(PetStore petStore){
        Intent intent = new Intent(mContext, MapsActivity.class);
        intent.putExtra("petStore", petStore);
        mContext.startActivity(intent);
    }

}
