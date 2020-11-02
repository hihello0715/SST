package com.example.sst2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText et_id, et_pass;
    private Button btn_login, btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login =findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

                btn_register.setOnClickListener(new View.OnClickListener() {  //회원가입 버튼 클릭시 수행
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);

                    }
                });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//EditText에 현재 입력되어있는 값을 가져온다...
                final String userId = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                Response.Listener<String> responseLister = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success= jsonObject.getBoolean("success");
                            if (success) { //회원가입 성공
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword");
                                Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userID", userId);
                                intent.putExtra("userPass", userPass);
                                startActivity(intent);
                            }else {//회원가입 성공
                                Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LoginRequset loginRequset = new LoginRequset(userId, userPass, responseLister);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequset);
            }
        });

    }
}