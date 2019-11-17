package com.example.imagelabeling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.imagelabeling.Helper.FirebaseDatabaseHelper;
import com.example.imagelabeling.Helper.Label;

import java.util.List;

public class ResultListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewResult);
        new FirebaseDatabaseHelper().getLabel(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Label> labelList, List<String> keys) {
                new RecyclerviewConfig().setConfig(mRecyclerView,ResultListActivity.this,labelList,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.result_activity_menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.history:
                startActivity(new Intent(this,ResultListActivity.class)); return true;
            case R.id.logout:
                startActivity(new Intent(this,LoginActivity.class)); return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
