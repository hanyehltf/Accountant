package com.MyAccountent.MyAccountent.Activities;

import android.content.Context;
import android.content.Intent;

public class LoadHomePage {

    public LoadHomePage(Context context){
        Intent intent=new Intent(context,MainActivity.class);
        context.startActivity(intent);

    }


}
