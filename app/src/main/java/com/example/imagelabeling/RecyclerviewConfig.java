package com.example.imagelabeling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagelabeling.Helper.Label;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewConfig {
    private Context mContext;
    private ResultAdapter mResultAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Label> labels,List<String> keys) {
        mContext = context;
        mResultAdapter = new ResultAdapter(labels,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mResultAdapter);
    }

    class ResultItemView extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mConfidence;

        private String key;

        public ResultItemView(ViewGroup parent){

            super(LayoutInflater.from(mContext).inflate(R.layout.result_list_item,parent,false));

            mName = (TextView) itemView.findViewById(R.id.nameTv);
            mConfidence = (TextView) itemView.findViewById(R.id.confidenceTv);

        }

        public void bind(Label label, String key){
            mName.setText(label.getName());
            mConfidence.setText((int) label.getConfidence());
            this.key=key;
        }


    }

    class ResultAdapter extends RecyclerView.Adapter<ResultItemView>{
        private List<Label> mLabelList ;
        private List<String> mKey;

        public ResultAdapter(List<Label> mLabelList, List<String> mKey) {
            this.mLabelList = mLabelList;
            this.mKey = mKey;
        }

        @NonNull
        @Override
        public ResultItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ResultItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ResultItemView holder, int position) {
            holder.bind(mLabelList.get(position),mKey.get(position));
        }

        @Override
        public int getItemCount() {
            return mLabelList.size();
        }
    }
}
