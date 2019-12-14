package com.cdth17pm.doraiq;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class ChoiGameActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private final ArrayList<LinhVuc> mListLinhVuc=new ArrayList<>();
    private final ArrayList<LinhVuc> mListCauHoi=new ArrayList<>();

    private ImageButton btnCallActivityMain;
    CountDownTimer countDownTimer;
    int n=0;
    private TextView mNoiDung;
    private TextView mDapAnA;
    private TextView mDapAnB;
    private TextView mDapAnC;
    private TextView mDapAnD;
    String dapan;
    int ada, bda, cda,dda;
    Button btnDapAnA;
    Button btnDapAnB;
    Button btnDapAnC;
    Button btnDapAnD;
    private Button linhvuc1;
    private Button linhvuc2;
    private Button linhvuc3;
    private Button linhvuc4;
    String[] Listlinhvuc = new String[4];
    int[] Listid = new int[4];
    private  int id_linhvuc;
    private Button btnTiepTheo;
    private TextView soCau;
    View incLinhVuc,incCauHoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choi_game);
        incLinhVuc=findViewById(R.id.inc_linhvuc);
        incCauHoi=findViewById(R.id.inc_cauhoi);
        linhvuc1=findViewById(R.id.button_LinhVuc1);
        linhvuc2=findViewById(R.id.button_LinhVuc2);
        linhvuc3=findViewById(R.id.button_LinhVuc3);
        linhvuc4=findViewById(R.id.button_LinhVuc4);
        btnTiepTheo=findViewById(R.id.button_TiepTheo);
        soCau=findViewById(R.id.textView_So_Cau_Hoi);
        if(getSupportLoaderManager().getLoader(0)!=null) {
            getSupportLoaderManager().initLoader(0, null , this);
        }
        getSupportLoaderManager().restartLoader(0,null,this);

        Intent intent =getIntent();
        int id = intent.getIntExtra("linh_vuc_id",0);
        Bundle bundle =new Bundle();
        bundle.putInt("idLinhVuc",id);
        mNoiDung=findViewById(R.id.textView_noi_dung_cau_hoi);
        mDapAnA=findViewById(R.id.button_dap_an_a);
        mDapAnB=findViewById(R.id.button_dap_an_b);
        mDapAnC=findViewById(R.id.button_dap_an_c);
        mDapAnD=findViewById(R.id.button_dap_an_d);
        btnCallActivityMain=findViewById(R.id.imageButton_tro_ve);
        btnCallActivityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ChoiGameActivity.this);
                dialog.setContentView(R.layout.dialog_thoat);
                TextView mhoi=(TextView) dialog.findViewById(R.id.textViewHoi);
                Button mYes=(Button) dialog.findViewById(R.id.button_yes);
                Button mNo=(Button) dialog.findViewById(R.id.button_No);
                mYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ChoiGameActivity.this, Menu.class);
                        startActivity(intent);
                    }
                });
                mNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        final TextView DemNguoc= findViewById(R.id.textView_dem_nguoc);
        countDownTimer =new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                n++;
                DemNguoc.setText(30-n+"s");

            }

            @Override
            public void onFinish() {
                Toast.makeText(ChoiGameActivity.this,"Hết thời gian",Toast.LENGTH_SHORT).show();
                btnTiepTheo.callOnClick();

            }

        };
        countDownTimer.start();
    }
    public void moCauHoi1(View view) {
        incCauHoi.setVisibility(View.VISIBLE);
        incLinhVuc.setVisibility(View.INVISIBLE);
        id_linhvuc=Listid[0];
        if(getSupportLoaderManager().getLoader(1)!=null){
            getSupportLoaderManager().initLoader(1,null,this);
        }
        getSupportLoaderManager().restartLoader(1,null,this);
    }

    public void moCauHoi2(View view) {
        incCauHoi.setVisibility(View.VISIBLE);
        incLinhVuc.setVisibility(View.INVISIBLE);
        id_linhvuc=Listid[1];
        if(getSupportLoaderManager().getLoader(1)!=null){
            getSupportLoaderManager().initLoader(1,null,this);
        }
        getSupportLoaderManager().restartLoader(1,null,this);
    }

    public void moCauHoi3(View view) {
        incCauHoi.setVisibility(View.VISIBLE);
        incLinhVuc.setVisibility(View.INVISIBLE);
        id_linhvuc=Listid[2];
        if(getSupportLoaderManager().getLoader(1)!=null){
            getSupportLoaderManager().initLoader(1,null,this);
        }
        getSupportLoaderManager().restartLoader(1,null,this);
    }

    public void moCauHoi4(View view) {
        incCauHoi.setVisibility(View.VISIBLE);
        incLinhVuc.setVisibility(View.INVISIBLE);
        id_linhvuc=Listid[3];
        if(getSupportLoaderManager().getLoader(1)!=null){
            getSupportLoaderManager().initLoader(1,null,this);
        }
        getSupportLoaderManager().restartLoader(1,null,this);
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if(id==0)
        {
            return new LinhVucLoader(this);
        }
        else
        {
            return new CauHoiLoader(this,id_linhvuc);
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if(loader.getId()==0) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray itemsArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < itemsArray.length(); i++) {
                     Listlinhvuc[i] = itemsArray.getJSONObject(i).getString("ten_linh_vuc");
                     Listid[i] = itemsArray.getJSONObject(i).getInt("id");
                }
                String lv1 = Listlinhvuc[0];
                String lv2 = Listlinhvuc[1];
                String lv3 = Listlinhvuc[2];
                String lv4 = Listlinhvuc[3];
                linhvuc1.setText(lv1);
                linhvuc2.setText(lv2);
                linhvuc3.setText(lv3);
                linhvuc4.setText(lv4);




            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(loader.getId()==1)
        {
            try {
                JSONObject jsonObject=new JSONObject(data);
                JSONArray itemsArray=jsonObject.getJSONArray("data");

                for(int i=0;i<itemsArray.length();i++){

                    String noiDung=itemsArray.getJSONObject(i).getString("noi_dung");
                    mNoiDung.setText(noiDung);
                    String dapAnA=itemsArray.getJSONObject(i).getString("phuong_an_a");
                    //String gana=mDapAnA.getText().toString();
                    //a = mDapAnA.getText()+" "+dapAnA;
                    mDapAnA.setText(dapAnA);
                    String dapAnB=itemsArray.getJSONObject(i).getString("phuong_an_b");
                    //String ganb=mDapAnB.getText().toString();
                   // String b = mDapAnB.getText()+" "+dapAnB;
                    mDapAnB.setText(dapAnB);
                    String dapAnC=itemsArray.getJSONObject(i).getString("phuong_an_c");
                    //String ganc=mDapAnC.getText().toString();
                    //String c = mDapAnC.getText()+" "+dapAnC;
                    mDapAnC.setText(dapAnC);
                    String dapAnD=itemsArray.getJSONObject(i).getString("phuong_an_d");
                    //String gand=mDapAnD.getText().toString();
                    //String d = mDapAnD.getText()+" "+dapAnD;
                    mDapAnD.setText(dapAnD);
                    String DapAn = itemsArray.getJSONObject(i).getString("dap_an");
                    dapan=DapAn;
                    if(dapAnA.equals(dapan))
                    {
                        Random random = new Random();
                        ada = 65;
                        bda = random.nextInt(100-ada);
                        cda = random.nextInt(100-ada-bda);
                        dda = 100-ada-bda-cda;
                    }
                    if(dapAnB.equals(dapan))
                    {
                        Random random = new Random();
                        bda =  65;
                        ada = random.nextInt(100-bda);
                        cda = random.nextInt(100-bda-ada);
                        dda = 100-ada-bda-cda;
                    }
                    if(dapAnC.equals(dapan))
                    {
                        Random random = new Random();
                        cda = 65;
                        bda = random.nextInt(100-cda);
                        ada = random.nextInt(100-cda-bda);
                        dda = 100-ada-bda-cda;
                    }
                    if(dapAnD.equals(dapan))
                    {
                        Random random = new Random();
                        dda = 65;
                        bda = random.nextInt(100-dda);
                        cda = random.nextInt(100-dda-bda);
                        ada = 100-dda-bda-cda;
                    }
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }

    }


    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void click5050(View view){
        final int dapAnDung=0;
        Random rd = new Random();
        int rDapAn1=rd.nextInt(3);
        int rDapAn2=rd.nextInt(3);
        while(rDapAn1==rDapAn2)
        {
            rDapAn2 = rd.nextInt(3);
        }
        if(rDapAn1 !=dapAnDung && rDapAn2!=dapAnDung) {
            if (rDapAn1 == 0 || rDapAn2 == 0) {
                btnDapAnA.setBackgroundColor(Color.GRAY);
                btnDapAnA.setClickable(false);
                btnDapAnA.setText("");
            }
            if (rDapAn1 == 1 || rDapAn2 == 1) {
                btnDapAnB.setBackgroundColor(Color.GRAY);
                btnDapAnB.setClickable(false);
                btnDapAnB.setText("");
            }
            if (rDapAn1 == 2 || rDapAn2 == 2) {
                btnDapAnC.setBackgroundColor(Color.GRAY);
                btnDapAnC.setClickable(false);
                btnDapAnC.setText("");
            }
            if (rDapAn1 == 3 || rDapAn2 == 3) {
                btnDapAnD.setBackgroundColor(Color.GRAY);
                btnDapAnD.setClickable(false);
                btnDapAnD.setText("");
            }
        }
    }

    public void toTuVan(View view) {
        final Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.bieudo);
        dialog.setCanceledOnTouchOutside(false);//->Click vào bên ngoài thì đóng dialog
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);

        toTuVan(dialog);
        dialog.show();
    }
    public void toTuVan(Dialog dialog){
        {
            BarChart barChart =dialog.findViewById(R.id.barChart);

            ArrayList<BarEntry> datas = new ArrayList<>();
            datas.add(new BarEntry(0, ada));
            datas.add(new BarEntry(1, bda));
            datas.add(new BarEntry(2, cda));
            datas.add(new BarEntry(3, dda));

            BarDataSet barDataSet = new BarDataSet(datas, "");
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            barDataSet.setValueTextSize(20f);


            BarData barData = new BarData( barDataSet);
            XAxis xAxis = barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            final String[] labels=new String[]{"A","B","C","D"};
            IndexAxisValueFormatter formatter=new IndexAxisValueFormatter(labels);
            xAxis.setTextSize(20f);
            xAxis.setGranularity(1f);

            xAxis.setValueFormatter(formatter);
            //gán dữ liệu vào barChart
            barChart.setData(barData);

            //Khong ve luoi tren bieu do
            xAxis.setDrawGridLines(false);
            barChart.getAxisLeft().setEnabled(false);
            barChart.getAxisRight().setEnabled(false);
            barChart.getLegend().setEnabled(false);
            barChart.getDescription().setEnabled(false);
            //Zoom bieu do
            barChart.setDoubleTapToZoomEnabled(false);
            //Touch biêu đồ
            barChart.setTouchEnabled(false);

            barChart.animateY(3000);

            barChart.invalidate();
        }
    }

    public void tiepTheo(View view) {

        incCauHoi.setVisibility(View.INVISIBLE);
        incLinhVuc.setVisibility(View.VISIBLE);
        mDapAnA.setText("");
        mDapAnB.setText("");
        mDapAnC.setText("");
        mDapAnD.setText("");


    }


}
