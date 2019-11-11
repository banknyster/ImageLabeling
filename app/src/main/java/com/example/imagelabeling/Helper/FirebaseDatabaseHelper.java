package com.example.imagelabeling.Helper;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private static DatabaseReference mReferenceResult;
    private static DatabaseReference mReferenceRoot;

    private List<Label> labels = new ArrayList<>();

    private List<FirebaseVisionImageLabel> label = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded (List<Label> labelList , List<String> keys);
        void DataIsInserted();
        void DataIsUpated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceResult = mDatabase.getReference("ResultData");
        //mReferenceResult = mReferenceRoot.child("ResultData");

    }

    public void getLabel(final DataStatus dataStatus){
        mReferenceResult.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                labels.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Label label = keyNode.getValue(Label.class);
                    labels.add(label);
                }
                dataStatus.DataIsLoaded(labels,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void addResult (Label resultLabel , final DataStatus dataStatus){

        String key = mReferenceResult.push().getKey();

        mReferenceResult.child(key).setValue(resultLabel).addOnSuccessListener(new OnSuccessListener<Void>() {
        //mReferenceResult.setValue(resultLabel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }

}
