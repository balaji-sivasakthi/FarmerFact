package com.latrosoft.farmerfact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.latrosoft.farmerfact.Adapter.CropAdapter;
import com.latrosoft.farmerfact.model.CropListModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CropList extends AppCompatActivity {
    private RecyclerView recyclerView;
    FirebaseFirestore db;
    FirebaseUser user;
    ArrayList<CropListModel> cropListModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView = findViewById(R.id.crop_list);

        readData();

    }
    void readData(){
        db.collection("cropList").document(user.getUid().toString()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Map<String,Object> data = documentSnapshot.getData();
                        Log.d("TAG", "onSuccess: "+data.get("cropList"));
                        List<Map<String,String>> list = (List<Map<String,String>>) data.get("cropList");
                        for (Map<String,String> id : list){

                            Log.d("TAG", "onSuccess: "+id.get("crop").split("-")[1].toString().toLowerCase());
                            String doc = id.get("crop").split("-")[1].toString().toLowerCase();
                            getCropDetails(doc,id.get("acre").toString());
                        }

                    }
                });
    }

    void  getCropDetails(String id ,String acre){
            db.collection("crops").document(id).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Log.e("TAG", "onSuccess: "+documentSnapshot.get("cropName") );
                            cropListModels.add(new CropListModel(documentSnapshot.getString("link"),documentSnapshot.getString("cropName"),acre,documentSnapshot.getString("id")));
                            recyclerView.setAdapter(new CropAdapter(cropListModels,getApplicationContext()));
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

                        }
                    });
}

}
