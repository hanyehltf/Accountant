package com.MyAccountent.MyAccountent.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MyAccountent.MyAccountent.Data.Product;
import com.MyAccountent.MyAccountent.Data.Service;
import com.MyAccountent.MyAccountent.R;

import java.util.List;

public class ServiceTableAdaptor extends RecyclerView.Adapter<ServiceTableAdaptor.ServiceViewHolder> {

private List<Service>services;
    public ServiceTableAdaptor(List<Service> services) {

        this.services = services;
    }


 public void updateData(List Updates){
    services.clear();
    services.addAll(Updates);
    notifyDataSetChanged();
}

    public void AddItem(Service service){
        int position=services.size();
        services.add(position,service);
        notifyItemInserted(position);


    }
    public void removeIem(int position){
       services.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.table_colum,parent,false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
Service service=services.get(position);
holder.Name.setText(service.getName());
holder.Price.setText(service.getPrice());
holder.Time.setText(service.getTime());

    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public class  ServiceViewHolder extends RecyclerView.ViewHolder{
        private TextView Name;
        private    TextView Price;
        private    TextView Time;
        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            Name =itemView.findViewById(R.id.Name_Text);
            Price=itemView.findViewById(R.id.Price_Text);
            Time=itemView.findViewById(R.id.Count_or_Time_Text);
        }
    }
}
