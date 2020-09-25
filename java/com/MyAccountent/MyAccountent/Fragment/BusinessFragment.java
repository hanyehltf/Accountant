package com.MyAccountent.MyAccountent.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.MyAccountent.MyAccountent.Adaptors.ProductTableAdaptor;
import com.MyAccountent.MyAccountent.Adaptors.ServiceTableAdaptor;
import com.MyAccountent.MyAccountent.DB.ProductOpenHolderDB;
import com.MyAccountent.MyAccountent.DB.ServiceOpenHolderDB;
import com.MyAccountent.MyAccountent.DB.UserSharedPerManager;
import com.MyAccountent.MyAccountent.Data.Product;
import com.MyAccountent.MyAccountent.Data.Service;
import com.MyAccountent.MyAccountent.Data.User;
import com.MyAccountent.MyAccountent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * create an instance of this fragment.
 */
public class BusinessFragment extends Fragment {

    private Context context;
    private View view;
    private EditText Name;
    private EditText Price;
    private EditText Count;
    private EditText Time;
    private Button Add;


    private RecyclerView recyclerView;
    private ProductTableAdaptor productTableAdaptor;
    private ServiceTableAdaptor serviceTableAdaptor;
    private ProductOpenHolderDB productOpenHolderDB;
    private ServiceOpenHolderDB serviceOpenHolderDB;

    public BusinessFragment(Context context) {
        this.context = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        serviceOpenHolderDB = new ServiceOpenHolderDB(getActivity());
        productOpenHolderDB = new ProductOpenHolderDB(getActivity());
        UserSharedPerManager userSharedPerManager = new UserSharedPerManager(getActivity());
        User user = userSharedPerManager.GetUser();

        if (user.getBusinessType().equals("product")) {
            view = inflater.inflate(R.layout.product_fragment, container, false);
            getProductViews(view);
            if (productOpenHolderDB.getProduct() != null) {
                setRecycleView();
                setProductDataInRecyclerView();
            }


        }
        if (user.getBusinessType().equals("Service")) {
            view = inflater.inflate(R.layout.serveic_fragment, container, false);
            getServiceViews(view);
            if (serviceOpenHolderDB.getService() != null) {
                setServiceRecyclerView();
                setServiceDataInRecyclerView();
            }


        }


        return view;
    }

    private void setServiceRecyclerView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.Recycle_vew_service);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }


    private void getProductViews(View view) {
        Name = (EditText) view.findViewById(R.id.Product_Name);
        Price = (EditText) view.findViewById(R.id.Product_Price);
        Count = (EditText) view.findViewById(R.id.Product_Count);
        Add = (Button) view.findViewById(R.id.App_Product);


        if (Name != null && Count != null && Price != null) {


            Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    saveProductDataInOpenHolderDB();

                }
            });
        } else {


            Toast.makeText(getActivity(), R.string.empty_field, Toast.LENGTH_LONG).show();
        }

    }

    private void saveProductDataInOpenHolderDB() {


        Product product = new Product();
        product.setName(Name.getText().toString());
        product.setPrice(Price.getText().toString());
        Name.getText().clear();
        Price.getText().clear();
        product.setCount(Integer.parseInt(Count.getText().toString()));
        Count.getText().clear();

        productOpenHolderDB.AddProduct(product);
        productTableAdaptor.AddItem(product);


    }

    private void setRecycleView() {
        recyclerView = this.view.findViewById(R.id.Recycle_vew);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }

    public void setProductDataInRecyclerView() {
        productTableAdaptor = new ProductTableAdaptor(productOpenHolderDB.getProduct());
        recyclerView.setAdapter(productTableAdaptor);
    }

    public void setServiceDataInRecyclerView() {

        serviceTableAdaptor = new ServiceTableAdaptor(serviceOpenHolderDB.getService());
        recyclerView.setAdapter(serviceTableAdaptor);
    }

    private void getServiceViews(View view) {
        Name = (EditText) view.findViewById(R.id.Service_Name);
        Price = (EditText) view.findViewById(R.id.Service_Price);
        Time = (EditText) view.findViewById(R.id.Service_Time);
        Add = (Button) view.findViewById(R.id.App_Service);

        if (Name != null && Price != null && Time != null) {
            Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveServiceDataInOpenHolderDB();
                }


            });

        } else {
            Toast.makeText(getActivity(), R.string.empty_field, Toast.LENGTH_LONG).show();


        }


    }

    private void saveServiceDataInOpenHolderDB() {
        Service service = new Service();
        service.setName(Name.getText().toString());
        service.setPrice(Price.getText().toString());
        service.setTime(Time.getText().toString());
        serviceOpenHolderDB.AddService(service);
        serviceTableAdaptor.AddItem(service);
    }

}
