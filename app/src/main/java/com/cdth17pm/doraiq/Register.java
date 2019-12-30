package com.cdth17pm.doraiq;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import static com.cdth17pm.doraiq.NetworkUtils.BASE_URL;
import static com.cdth17pm.doraiq.NetworkUtils.URI_NGUOI_CHOI_THEM;

public class Register extends AppCompatActivity {

    public static final String COLUMN_TEN_DANG_NHAP = "ten_dang_nhap";
    public static final String COLUMN_MAT_KHAU = "mat_khau";
    public static final String COLUMN_EMAIL = "email";
    private EditText edtTenDangNhap,edtEmail,edtMatKhau,edtXacNhanMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Radiation();
    }

    private void Radiation() {
        edtTenDangNhap = findViewById(R.id.editText_Re_Username);
        edtEmail = findViewById(R.id.editText_Re_Email);
        edtMatKhau = findViewById(R.id.editText_Re_Password);
        edtXacNhanMatKhau = findViewById(R.id.editText_Re_Repassword);
    }

    public void DangKy(View view) {
        //Lấy text
        final String tenDangNhap = edtTenDangNhap.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String matKhau = edtMatKhau.getText().toString().trim();
        String xacNhanMatKhau = edtXacNhanMatKhau.getText().toString().trim();

        if (tenDangNhap.equals("") || email.equals("") || matKhau.equals("") || xacNhanMatKhau.equals("")) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        } else {

            if (!matKhau.equals(xacNhanMatKhau)) {
                Toast.makeText(getApplicationContext(), "Mật khẩu phải giống nhau", Toast.LENGTH_SHORT).show();
            } else {
                final ProgressDialog pgwait = NetworkUtils.showProress(this);
                pgwait.show();
                StringRequest request = new StringRequest(Request.Method.POST, BASE_URL + URI_NGUOI_CHOI_THEM, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            pgwait.dismiss();
                            JSONObject objLogin = new JSONObject(response);
                            boolean result = objLogin.getBoolean("success");
                            if (result) {
                                Toast.makeText(getApplicationContext(), tenDangNhap + " Tạo Thành Công", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Thất bại" + " Hoặc User Tồn Tại", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Server Offline", Toast.LENGTH_SHORT).show();
                        pgwait.dismiss();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put(COLUMN_TEN_DANG_NHAP, tenDangNhap);
                        map.put(COLUMN_EMAIL, email);
                        map.put(COLUMN_MAT_KHAU, matKhau);
                        return map;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(this);
                queue.add(request);
            }
        }
    }
    }
