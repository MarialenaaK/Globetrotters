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

import java.util.Date;
import java.util.List;

public class VacationPackageAdapter extends RecyclerView.Adapter<VacationPackageAdapter.VacationPackageViewHolder> {

    private Context context;
    private List<VacationPackage> vacationPackageList;

    private TextInputEditText updateInfoId;
    private TextInputEditText updateInfoAgencyId;
    private TextInputEditText updateInfoVacationId;
    private TextInputEditText updateInfoDate;
    private TextInputEditText updateInfoPrice;
    private OnItemClickListenerDelete mListener;

    private Button updateInfoButton3;


    public VacationPackageAdapter(Context context, List<VacationPackage> vacationPackageList){
        this.context = context;
        this.vacationPackageList = vacationPackageList;
    }

    @NonNull
    @Override
    public VacationPackageAdapter.VacationPackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item3, parent, false);
        return new VacationPackageViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull VacationPackageAdapter.VacationPackageViewHolder holder, int position) {
        VacationPackage vacationPackage = vacationPackageList.get(position);
        holder.id.setText(String.valueOf(vacationPackage.getId()));
        holder.agency_id.setText(String.valueOf(vacationPackage.getAgencyId()));
        holder.vacation_id.setText(String.valueOf(vacationPackage.getVacationId()));
        holder.date.setText(vacationPackage.getDate());
        holder.price.setText(String.valueOf(vacationPackage.getPrice()));

        holder.update_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(holder.id.getContext());
                dialog.setContentView(R.layout.update_dialog_vacation_package);
                dialog.setCanceledOnTouchOutside(true);

                updateInfoId = dialog.findViewById(R.id.update_info_id);
                updateInfoAgencyId = dialog.findViewById(R.id.update_info_agency_id);
                updateInfoVacationId = dialog.findViewById(R.id.update_info_vacation_id);
                updateInfoDate = dialog.findViewById(R.id.update_info_date);
                updateInfoPrice = dialog.findViewById(R.id.update_info_price);

                updateInfoButton3 = dialog.findViewById(R.id.update_info_button3);

                updateInfoId.setText(String.valueOf(vacationPackage.getId()));
                updateInfoAgencyId.setText(String.valueOf(vacationPackage.getAgencyId()));
                updateInfoVacationId.setText(String.valueOf(vacationPackage.getVacationId()));
                updateInfoDate.setText(String.valueOf(vacationPackage.getDate()));
                updateInfoPrice.setText(String.valueOf(vacationPackage.getPrice()));

                dialog.show();

                updateInfoButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            int idTemp = Integer.parseInt(updateInfoId.getText().toString());
                            int agencyIdTemp = Integer.parseInt(updateInfoAgencyId.getText().toString());
                            int vacationIdTemp = Integer.parseInt(updateInfoVacationId.getText().toString());
                            String dateTemp = updateInfoDate.getText().toString();
                            int priceTemp = Integer.parseInt(updateInfoPrice.getText().toString());

                            VacationPackage vacationPackage1 = new VacationPackage();
                            vacationPackage1.setId(idTemp);
                            vacationPackage1.setAgencyId(agencyIdTemp);
                            vacationPackage1.setVacationId(vacationIdTemp);
                            vacationPackage1.setDate(dateTemp);
                            vacationPackage1.setPrice(priceTemp);

                            holder.id.setText(String.valueOf(idTemp));
                            holder.agency_id.setText(String.valueOf(agencyIdTemp));
                            holder.vacation_id.setText(String.valueOf(vacationIdTemp));
                            holder.date.setText(dateTemp);
                            holder.price.setText(String.valueOf(priceTemp));

                            MainActivity.myDatabase.myDao().update(vacationPackage1);

                            Toast.makeText(holder.id.getContext(), "Το Ταξιδιωτικό Πακέτο ενημερώθηκε", Toast.LENGTH_LONG).show();
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
        if(vacationPackageList != null){
            return vacationPackageList.size();
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

    public class VacationPackageViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView agency_id;
        public TextView vacation_id;
        public TextView date;
        public TextView price;

        ImageButton delete_bttn;
        ImageButton update_bttn;

        public VacationPackageViewHolder(View view, OnItemClickListenerDelete listener){
            super(view);
            id = view.findViewById(R.id.item3_code);
            agency_id = view.findViewById(R.id.item3_agency_code);
            vacation_id = view.findViewById(R.id.item3_vacation_code);
            date = view.findViewById(R.id.item3_date);
            price = view.findViewById(R.id.item3_price);

            update_bttn = view.findViewById(R.id.edit_button3);
            delete_bttn = view.findViewById(R.id.delete_button3);

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
