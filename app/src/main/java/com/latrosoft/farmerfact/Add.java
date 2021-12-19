package com.latrosoft.farmerfact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.latrosoft.farmerfact.Utils.ArrayHelper;
import com.latrosoft.farmerfact.model.CropListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Add extends AppCompatActivity {
    FirebaseFirestore db;
    ArrayList<String> searchArrayList= new ArrayList<String>();
    Button savebtn ;
   TextInputEditText name ,acre;
   String id;
   FirebaseUser user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
       user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        savebtn =findViewById(R.id.savebtn);
        name = findViewById(R.id.nameEdit);
        acre =  findViewById(R.id.acreEdit);
        db.collection("crops")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> cropList = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d  :cropList){
                        Log.d("TAG", "onSuccess: "+d.get("cropName").toString());
                            searchArrayList.add(d.get("cropName").toString()+"-"+d.get("id").toString().toUpperCase());
                    }
                }
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, searchArrayList);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.crop);
        textView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                db.collection("cropList").document(user.getUid().toString()).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                Map<String,List> d ;
                                List<Map> croplist;
                                Map<String,String> data;

                                    if(!documentSnapshot.exists()|| documentSnapshot.getData().isEmpty()){
                                        d = new HashMap<>();
                                        data = new HashMap<>();
                                        croplist = new ArrayList<>();
                                        data.put("name",name.getText().toString());
                                        data.put("acre",acre.getText().toString());
                                        data.put("crop",textView.getText().toString());
                                        croplist.add(data);
                                        d.put("cropList",croplist);

                                    } else{

                                        Log.d("TAG", "onSuccess: "+documentSnapshot.get("cropList"));
                                        d = new HashMap<>();
                                        data = new HashMap<>();
                                        croplist = (List<Map>) documentSnapshot.get("cropList");
                                        data.put("name",name.getText().toString());
                                        data.put("acre",acre.getText().toString());
                                        data.put("crop",textView.getText().toString());
                                        croplist.add(data);
                                        d.put("cropList",croplist);

                                    }
                                db.collection("cropList").document( user.getUid().toString())
                                        .set(d).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "SuccessFully Added", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });



            }
        });
    }
}
