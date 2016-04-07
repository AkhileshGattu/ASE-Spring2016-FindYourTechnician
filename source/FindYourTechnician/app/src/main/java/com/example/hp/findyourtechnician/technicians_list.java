package com.example.hp.findyourtechnician;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class technicians_list extends AppCompatActivity {

    String CategorySelected,SubCategorySelected,Location;
    TextView Category,SubCategory,ULocation;
    ArrayList<HashMap<String,String>> TechList = new ArrayList<>();
    String[] techniciannames;
    String[] experience;
    String[] ratings;
    String[] basecharges;
    ListView list;
    int i=0;
    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technicians_list);
        Intent intent = getIntent();
        CategorySelected = intent.getStringExtra("Category");
        SubCategorySelected = intent.getStringExtra("SubCategory");
        Location = intent.getStringExtra("Location");
        Category = (TextView)findViewById(R.id.CategorytextView);
        SubCategory = (TextView)findViewById(R.id.SubCategorytextView);
        ULocation = (TextView)findViewById(R.id.UserLocationTextView);
        Category.setText(CategorySelected);
        SubCategory.setText(SubCategorySelected);
        ULocation.setText(Location);

        list=(ListView) findViewById(R.id.TechnicianlistView);
        CustomAdapter adapter=new CustomAdapter();
        list.setAdapter(adapter);

        Resources res=getResources();
        //techniciannames=res.getStringArray(R.array.Technician_names);
        //experience=res.getStringArray(R.array.Experience);
        //basecharges=res.getStringArray(R.array.Base_Charges);
        ratings=res.getStringArray(R.array.Ratings);


        Firebase.setAndroidContext(this);
        Firebase ListRef = new Firebase("https://findyourtechnician.firebaseio.com/Technicians");
        Query CategoryQuery = ListRef.orderByChild("Zipcode").equalTo(Location);
        CategoryQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashMap<String, String> TechMap = new HashMap<String, String>();
                String FullName, Experience, Fare;

                for (DataSnapshot category : dataSnapshot.getChildren()) {
                    if (category.child("Expertise").getValue().equals(CategorySelected)) {
                        FullName = category.child("FirstName").getValue().toString() + " " + category.child("LastName").getValue().toString();
                        Experience = category.child("Experience").getValue().toString();
                        Fare = category.child("BaseFare").getValue().toString();
                        Name = FullName;
                        TechMap.put("FullName", FullName);
                        //techniciannames[i] = FullName;
                        TechMap.put("Experience", Experience);
                        //experience[i] = Experience;
                        TechMap.put("BaseFare", Fare);
                        //basecharges[i] = Fare;
                        TechList.add(TechMap);
                        i++;
                    }
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
/*
    public void showAlert(View view){
        AlertDialog.Builder alertD = new AlertDialog.Builder(this);
        String a = "" + TechList.get(0);
        alertD.setMessage(a).create();
        alertD.show();
    }
*/
    public class CustomAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return TechList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.row_layout, parent, false);
                viewHolder = new ViewHolder();

                viewHolder.TechName = (TextView) convertView.findViewById(R.id.TechnicianNametextView);
                viewHolder.TechExperience = (TextView) convertView.findViewById(R.id.ExperiencetextView);
                viewHolder.TechFare = (TextView) convertView.findViewById(R.id.BaseChargestextView);
                convertView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder)convertView.getTag();
            }
            //TextView myratings=(TextView) row.findViewById(R.id.RatingstextView);

            HashMap<String,String> technicianList = TechList.get(position);

            viewHolder.TechName.setText(technicianList.get("FullName"));
            viewHolder.TechExperience.setText(technicianList.get("Experience"));
            viewHolder.TechFare.setText(technicianList.get("BaseFare"));
            //myratings.setText(chargesArray[position]);
            return convertView;
        }

        class ViewHolder{
            TextView TechName,TechExperience,TechFare;
        }
    }

}

