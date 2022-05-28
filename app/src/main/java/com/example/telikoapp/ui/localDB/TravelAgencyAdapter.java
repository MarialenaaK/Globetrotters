package com.example.telikoapp.ui.localDB;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telikoapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.List;

public class TravelAgencyAdapter extends RecyclerView.Adapter<TravelAgencyAdapter.AgencyViewHolder> {

    private Context context;
    private List<TravelAgency> travelAgencyList;

    private TextInputEditText updateInfoName;
    private TextInputEditText updateInfoId;
    private TextInputEditText updateInfoAddress;
    private OnItemClickListenerDelete mListener;

    private Button updateInfoButton;


    public TravelAgencyAdapter(Context context, List<TravelAgency> travelAgencyList){
        this.context = context;
        this.travelAgencyList = travelAgencyList;
    }

    @NonNull
    @Override
    public TravelAgencyAdapter.AgencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent, false);
        return new AgencyViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelAgencyAdapter.AgencyViewHolder holder, int position) {
        TravelAgency travelAgency = travelAgencyList.get(position);
        holder.id.setText(String.valueOf(travelAgency.getId()));
        holder.name.setText(travelAgency.getName());
        holder.address.setText(travelAgency.getAddress());

        holder.update_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(holder.id.getContext());
                dialog.setContentView(R.layout.update_dialog_travel_agency);
                dialog.setCanceledOnTouchOutside(true);

                updateInfoName = dialog.findViewById(R.id.update_info_name);
                updateInfoId = dialog.findViewById(R.id.update_info_id);
                updateInfoAddress = dialog.findViewById(R.id.update_info_address);

                updateInfoButton = dialog.findViewById(R.id.update_info_button);

                updateInfoName.setText(travelAgency.getName());
                updateInfoId.setText(String.valueOf(travelAgency.getId()));
                updateInfoAddress.setText(travelAgency.getAddress());

                dialog.show();

                updateInfoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            int idTemp = Integer.parseInt(updateInfoId.getText().toString());
                            String nameTemp = updateInfoName.getText().toString();
                            String addressTemp = updateInfoAddress.getText().toString();

                            TravelAgency travelAgency1 = new TravelAgency();
                            travelAgency1.setId(idTemp);
                            travelAgency1.setName(nameTemp);
                            travelAgency1.setAddress(addressTemp);

                            holder.id.setText(String.valueOf(idTemp));
                            holder.name.setText(nameTemp);
                            holder.address.setText(addressTemp);

                            MainActivity.myDatabase.myDao().update(travelAgency1);

                            Toast.makeText(holder.id.getContext(), "Το Ταξιδιωτικό Πρακτορείο ενημερώθηκε!", Toast.LENGTH_LONG).show();

                            dialog.dismiss();
                        }catch (Exception ex){
                            Toast.makeText(holder.id.getContext(), "Παρακαλώ ελέγξτε τις τιμές που συμπληρώσατε!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if(travelAgencyList != null){
            return travelAgencyList.size();
        }else{
            return 0;
        }
    }

    public void setOnItemClickListenerDelete(OnItemClickListenerDelete listener){
        mListener = listener;
    }

    public interface OnItemClickListenerDelete{
        void onClickDelete(int position);
    }

    public class AgencyViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView name;
        public TextView address;

        ImageButton delete_bttn;
        ImageButton update_bttn;

        public AgencyViewHolder(View view, OnItemClickListenerDelete listener){
            super(view);
            id = view.findViewById(R.id.item1_code);
            name = view.findViewById(R.id.item1_name);
            address = view.findViewById(R.id.item1_address);

            update_bttn = view.findViewById(R.id.edit_button);
            delete_bttn = view.findViewById(R.id.delete_button);

            //here we get the position of the card clicked
            delete_bttn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onClickDelete(position);
                        }
                    }
                }
            });
        }

    }
}
