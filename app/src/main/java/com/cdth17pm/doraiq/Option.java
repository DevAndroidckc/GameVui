package com.cdth17pm.doraiq;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Option extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private final ArrayList<LinhVuc>mListLinhVuc=new ArrayList<>();
    private RecyclerView mRecycleView;
    private LinhVucAdapter mLinhVucAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        mRecycleView=findViewById(R.id.RecycleView_item);
        mLinhVucAdapter=new LinhVucAdapter(this,mListLinhVuc);
        mRecycleView.setAdapter(mLinhVucAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        //lay id cau hoi thuoc linh vuc
        if(getSupportLoaderManager().getLoader(0)!=null) {
            getSupportLoaderManager().initLoader(0, null /*chua id cau hoi thuoc linh vuc lay tu adapter*/, this);
        }
        getSupportLoaderManager().restartLoader(0,null,this);

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new LinhVucLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        // if(loader.get()==0) thì chạy cái try catch có id =0 ngược lại chạy id 1 của câu hỏi
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("data");
            for(int i=0; i<itemsArray.length(); i++) {
                String mTenLinhVuc = itemsArray.getJSONObject(i).getString("ten_linh_vuc");
                int id=itemsArray.getJSONObject(i).getInt("id");
                this.mListLinhVuc.add(new LinhVuc(mTenLinhVuc,id));


            }

            this.mLinhVucAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }


}
