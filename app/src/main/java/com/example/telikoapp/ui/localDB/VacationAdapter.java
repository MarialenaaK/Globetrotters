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

public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.VacationViewHolder> {

    private Context context;
    private List<Vacation> vacationList;

    private TextInputEditText updateInfoCity;
    private TextInputEditText updateInfoId;
    private TextInputEditText updateInfoCountry;
    private TextInputEditText updateInfoDuration;
    private TextInputEditText updateInfoType;
    private OnItemClickListenerDelete mListener;

    private Button updateInfoButton2;


    public VacationAdapter(Context context, List<Vacation> vacationList){
        this.context = context;
        this.vacationList = vacationList;
    }

    @NonNull
    @Override
    public VacationAdapter.VacationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        return new VacationViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull VacationAdapter.VacationViewHolder holder, int position) {
        Vacation vacation = vacationList.get(position);
        holder.id.setText(String.valueOf(vacation.getId()));
        holder.city.setText(vacation.getCity());
        holder.country.setText(vacation.getCountry());
        holder.duration.setText(String.valueOf(vacation.getDurationDays()));
        holder.type.setText(vacation.getVacationType());

        holder.update_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(holder.id.getContext());
                dialog.setContentView(R.layout.update_dialog_vacation);
                dialog.setCanceledOnTouchOutside(true);

                updateInfoCity = dialog.findViewById(R.id.update_info_city);
                updateInfoId = dialog.findViewById(R.id.update_info_id);
                updateInfoCountry = dialog.findViewById(R.id.update_info_country);
                updateInfoDuration = dialog.findViewById(R.id.update_info_duration);
                updateInfoType = dialog.findViewById(R.id.update_info_type);

                updateInfoButton2 = dialog.findViewById(R.id.update_info_button2);

                updateInfoCity.setText(vacation.getCity());
                updateInfoId.setText(String.valueOf(vacation.getId()));
                updateInfoCountry.setText(vacation.getCountry());
                updateInfoDuration.setText(String.valueOf(vacation.getDurationDays()));
                updateInfoType.setText(vacation.getVacationType());

                dialog.show();

                updateInfoButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            int idTemp = Integer.parseInt(updateInfoId.getText().toString());
                            String cityTemp = updateInfoCity.getText().toString();
                            String countryTemp = updateInfoCountry.getText().toString();
                            int durationTemp = Integer.parseInt(updateInfoDuration.getText().toString());
                            String typeTemp = updateInfoType.getText().toString();

                            Vacation vacation1 = new Vacation();
                            vacation1.setId(idTemp);
                            vacation1.setCity(cityTemp);
                            vacation1.setCountry(countryTemp);
                            vacation1.setDurationDays(durationTemp);
                            vacation1.setVacationType(typeTemp);

                            holder.id.setText(String.valueOf(idTemp));
                            holder.city.setText(cityTemp);
                            holder.country.setText(countryTemp);
                            holder.duration.setText(String.valueOf(durationTemp));
                            holder.type.setText(typeTemp);

                            MainActivity.myDatabase.myDao().update(vacation1);

                            Toast.makeText(holder.id.getContext(), "Οι εκδρομές ενημερώθηκαν!", Toast.LENGTH_LONG).show();
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
        if(vacationList != null){
            return vacationList.size();
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

    public class VacationViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView city;
        public TextView country;
        public TextView duration;
        public TextView type;

        ImageButton delete_bttn;
        ImageButton update_bttn;

        public VacationViewHolder(View view, OnItemClickListenerDelete listener){
            super(view);
            id = view.findViewById(R.id.item2_code);
            city = view.findViewById(R.id.item2_city);
            country = view.findViewById(R.id.item2_country);
            duration = view.findViewById(R.id.item2_duration);
            type = view.findViewById(R.id.item2_type);

            update_bttn = view.findViewById(R.id.edit_button2);
            delete_bttn = view.findViewById(R.id.delete_button2);

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

