package com.example.leaveinformer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.xml.validation.Validator;

public class SecondActivity extends AppCompatActivity {

   private EditText secName,secDept,secRoll,secFromto,secDays,secReason;
   private Button applyBtn;
   private TextView LeaveApply;
   private FirebaseAuth firebaseAuth;
   String sname,sDepartment,sRollNumber,sFromandTo,sNoofDays,sReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setupUIViews();
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (validate()){

                   sendUserData();

                   //upload data into database
               }
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private  void setupUIViews(){
        secName =(EditText) findViewById(R.id.etName);
        secDept = (EditText) findViewById(R.id.etDepartment);
        secRoll = (EditText) findViewById(R.id.etRollNumber);
        secFromto = (EditText) findViewById(R.id.etFromandTo);
        secDays = (EditText) findViewById(R.id.etNoofDays);
        secReason = (EditText) findViewById(R.id.etReason);
        applyBtn = (Button) findViewById(R.id.btnApply);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
   switch (item.getItemId()){
       case R.id.logoutMenu:{
           firebaseAuth.signOut();
           finish();
           startActivity(new Intent(SecondActivity.this,MainActivity.class));
       }
   }
    return super.onOptionsItemSelected(item);
    }


    //validate
    private Boolean validate(){
        Boolean result = false;

        sname = secName.getText().toString();
        sDepartment = secDept.getText().toString();
        sRollNumber = secRoll.getText().toString();
        sFromandTo = secFromto.getText().toString();
        sNoofDays = secDays.getText().toString();
        sReason = secReason.getText().toString();


        if (sname.isEmpty() || sDepartment.isEmpty() || sRollNumber.isEmpty()|| sFromandTo.isEmpty()|| sNoofDays.isEmpty()|| sReason.isEmpty()){
            Toast.makeText(this,"Please Enter All Details",Toast.LENGTH_SHORT).show();
        }
        else {
            result = true;
        }
        return result;
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef =  firebaseDatabase.getReference(firebaseAuth.getUid());
         UserProfile userProfile = new UserProfile(sname,sDepartment,sRollNumber,sFromandTo,sNoofDays,sReason);
        myRef.setValue(userProfile);
        Toast.makeText(this,"Leave Applied",Toast.LENGTH_SHORT).show();
        applyBtn.setEnabled(false);
    }
}

