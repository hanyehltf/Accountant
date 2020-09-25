package com.MyAccountent.MyAccountent.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MyAccountent.MyAccountent.Data.Product;
import com.MyAccountent.MyAccountent.R;

import java.util.List;

public class ProductTableAdaptor extends RecyclerView.Adapter<ProductTableAdaptor.ViewHolder> {

private List <Product>Products;
    public ProductTableAdaptor(List<Product> products) {

       this.Products = products;
    } public void updateData(List Updates){
        Products.clear();
        Products.addAll(Updates);
        notifyDataSetChanged();
    }

    public void AddItem(Product product){
        int position=Products.size()-1;
        Products.add(position,product);
        notifyItemInserted(position);


    }
    public void removeIem(int position){
        Products.remove(position);
        notifyItemRemoved(position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.table_colum,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product=Products.get(position);

holder.Name.setText(product.getName());
holder.Count.setText(String.valueOf(product.getCount()));
holder.Price.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return Products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
      private   TextView Name;
     private    TextView Price;
     private    TextView Count;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

      Name =(TextView) itemView.findViewById(R.id.Name_Text);
        Price=(TextView)itemView.findViewById(R.id.Price_Text);
        Count=(TextView)itemView.findViewById(R.id.Count_or_Time_Text);
    }
}
}
