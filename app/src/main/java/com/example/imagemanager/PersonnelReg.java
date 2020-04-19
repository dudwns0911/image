package com.example.imagemanager;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PersonnelReg extends AppCompatActivity {
    public final static int REQUEST_PHOTO_CODE = 1;
    ImageView iv_photo;
    Uri photoUri;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_personnel_reg, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
            finish();
            return true;
        } else if (id == R.id.action_settings2) {
            Intent it = new Intent(this, PersonnelReg.class);
            startActivity(it);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectPhoto(View v){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQUEST_PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_PHOTO_CODE) {
            if (resultCode == RESULT_OK) {
                photoUri =data.getData();
                iv_photo.setImageURI(photoUri);
            } else{
                Toast.makeText(this, "사진 선택 에러!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void register(View v) {

        EditText et_name = (EditText)findViewById(R.id.name);
        String str_name = et_name.getText().toString();

        RadioGroup rg_gender = (RadioGroup)findViewById(R.id.gender);
        RadioButton rb_gender;
        String str_gender ="";
        if (rg_gender.getCheckedRadioButtonId() == R.id.male) {
            rb_gender = (RadioButton)findViewById(R.id.male);
            str_gender =rb_gender.getText().toString();
            }
        if  (rg_gender.getCheckedRadioButtonId() == R.id.female) {
            rb_gender = (RadioButton)findViewById(R.id.female);
            str_gender = rb_gender.getText().toString();
        }

        CheckBox chk_hobby;
        String str_hobby1 = "";
        String str_hobby2 = "";
        String str_hobby3 = "";
        chk_hobby = (CheckBox)findViewById(R.id.hobby1);
        if (chk_hobby.isChecked()) {
            str_hobby1 = (String)chk_hobby.getText();
        }
        chk_hobby = (CheckBox)findViewById(R.id.hobby2);
        if (chk_hobby.isChecked()) {
            str_hobby2 = (String)chk_hobby.getText();
        }
        chk_hobby =(CheckBox)findViewById(R.id.hobby3);
        if (chk_hobby.isChecked()) {
            str_hobby3 = (String)chk_hobby.getText();
        }

        Intent it = new Intent(this, PersonnelReg.class);
        it.putExtra("it_name", str_name);
        it.putExtra("it_gender", str_gender);
        it.putExtra("it_hobby1", str_hobby1);
        it.putExtra("it_hobby2", str_hobby2);
        it.putExtra("it_hobby3", str_hobby3);
        it.putExtra("it_PhotoUri",photoUri.toString());
        startActivity(it);
        finish();
    }
}