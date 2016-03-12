package com.example.hp.findyourtechnician;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.io.IOException;
import java.util.List;

public class Home extends AppCompatActivity {

    String[] tech;
    String[] techDesc;
    int[] techimages = {R.drawable.carpenter, R.drawable.electrician, R.drawable.painter, R.drawable.plumber};
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Resources res = getResources();
        tech = res.getStringArray(R.array.technicians);
        techDesc = res.getStringArray(R.array.technicianDescriptions);
        list = (ListView) findViewById(R.id.Technicians_List);
        sampleAdapter SA = new sampleAdapter(this,tech,techimages,techDesc);
        list.setAdapter(SA);

    }

    public void User_Login(View view)
    {
        Intent intent = new Intent(Home.this,UserLogin.class);
        startActivity(intent);
    }
}

class sampleAdapter extends ArrayAdapter<String>
{
    Context context;
    int[] techImages;
    String[] TechTitles;
    String[] TechDesc;
    sampleAdapter(Context c, String[] titles, int images[], String[] description)
    {
        super(c, R.layout.single_row, R.id.textView, titles);
        this.context = c;
        this.techImages = images;
        this.TechTitles = titles;
        this.TechDesc = description;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.single_row,parent,false);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        TextView tech = (TextView) v.findViewById(R.id.textView);
        TextView techDesc = (TextView) v.findViewById(R.id.textView2);
        img.setImageResource(techImages[position]);
        tech.setText(TechTitles[position]);
        techDesc.setText(TechDesc[position]);

        return v;
    }
}
