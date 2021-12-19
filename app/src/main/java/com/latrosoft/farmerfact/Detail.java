package com.latrosoft.farmerfact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.latrosoft.farmerfact.Adapter.CropAdapter;
import com.latrosoft.farmerfact.Adapter.TableAdapter;
import com.latrosoft.farmerfact.model.TableModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Detail extends AppCompatActivity {
    FirebaseFirestore db ;
    TextView name,acre,dis;
    RecyclerView recyclerView;
    ArrayList<TableModel> models;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name = findViewById(R.id.cropnamedetails);
        acre = findViewById(R.id.acre);
        dis =findViewById(R.id.dis);
        imageView =findViewById(R.id.detailimage);
        recyclerView =findViewById(R.id.tabledata);
        models = new ArrayList<>();



        db = FirebaseFirestore.getInstance();
        String id = getIntent().getStringExtra("cropId");
        String ac = getIntent().getStringExtra("cropAcre")+ " Acre";
        String link = getIntent().getStringExtra("cropLink");
        Log.e("TAG", "onCreate: "+ link);

        Glide.with(this).load(link).into(imageView);
        
        db.collection("crops").document(id).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Map<String,Object> cropDetails = documentSnapshot.getData();
                        name.setText(cropDetails.get("cropName").toString());
                        acre.setText(ac);
                        ArrayList<Map<String,String>> d = (ArrayList<Map<String, String>>) cropDetails.get("fertilizer");
                        dis.setText(cropDetails.get("dis").toString());
                        int count =0;
                        for (Map<String,String> e :d){
                            Map<String,String> m = new HashMap<>();
                            Log.d("TAG", "onSuccess: "+d.get(count).get("name"));
                            models.add(new TableModel(d.get(count).get("name"),d.get(count).get("qty"),d.get(count).get("time")));
                            count++;
                        }

                        recyclerView.setAdapter(new TableAdapter(models,getApplicationContext()));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                    }
                });


    }



}