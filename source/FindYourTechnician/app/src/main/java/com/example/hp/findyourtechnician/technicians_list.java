package com.example.hp.findyourtechnician;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class technicians_list extends AppCompatActivity {

    String CategorySelected,SubCategorySelected;
    TextView Category,SubCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technicians_list);
        Intent intent = getIntent();
        CategorySelected = intent.getStringExtra("Category");
        SubCategorySelected = intent.getStringExtra("SubCategory");
        Category = (TextView)findViewById(R.id.CategorytextView);
        SubCategory = (TextView)findViewById(R.id.SubCategorytextView);
        Category.setText(CategorySelected);
        SubCategory.setText(SubCategorySelected);


        String[] techniciannames;
        String[] experience;
        String[] ratings;
        String[] basecharges;

        ListView list;

        Resources res=getResources();
        techniciannames=res.getStringArray(R.array.Technician_names);
        experience=res.getStringArray(R.array.Experience);
        basecharges=res.getStringArray(R.array.Base_Charges);
        ratings=res.getStringArray(R.array.Ratings);

        list=(ListView) findViewById(R.id.TechnicianlistView);
        VivzAdapter adapter=new VivzAdapter(this,techniciannames,ratings,experience,basecharges);
    }

}

class VivzAdapter extends ArrayAdapter<String>{
    Context context;
    String[] nameArray;
    String[] ratingsArray;
    String[] experienceArray;
    String[] chargesArray;
    VivzAdapter(Context c,String[] names,String[] rat,String[] exp,String[] chrgs){
        super(c,R.layout.row_layout,R.id.New_User,names);
        this.context=c;
        this.nameArray=names;
        this.ratingsArray=rat;
        this.experienceArray=exp;
        this.chargesArray=chrgs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.row_layout,parent,false);


        TextView mytechnician=(TextView) row.findViewById(R.id.TechnicianNametextView);
        TextView myexperience=(TextView) row.findViewById(R.id.ExperiencetextView);
        TextView mycharges=(TextView) row.findViewById(R.id.BaseChargestextView);
        TextView myratings=(TextView) row.findViewById(R.id.RatingstextView);

        mytechnician.setText(nameArray[position]);
        myexperience.setText(nameArray[position]);
        mycharges.setText(nameArray[position]);
        myratings.setText(nameArray[position]);
        return row;
    }
}