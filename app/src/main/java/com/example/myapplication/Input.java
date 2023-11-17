package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Input extends AppCompatActivity {

    private Button saveBtn;
    private EditText edtAmount, edtDate;

    private Spinner edtCard, edtClass;

    private String str, card, classification;
    private Calendar myCalendar = Calendar.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        edtAmount = findViewById(R.id.edtAmount);
        edtDate = findViewById(R.id.edtDate);
        edtCard = findViewById(R.id.edtCard);
        edtClass = findViewById(R.id.edtClass);

        ArrayAdapter<CharSequence> cardAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.Card,
                android.R.layout.simple_spinner_item
        );
        cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtCard.setAdapter(cardAdapter);

        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.Class,
                android.R.layout.simple_spinner_item
        );
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtClass.setAdapter(classAdapter);


        // 날짜 선택 다이얼로그를 표시하기 위한 클릭 리스너 추가
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // 저장하기 버튼 클릭 리스너
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = edtAmount.getText().toString();
                String selectedDate = edtDate.getText().toString();
                card = edtCard.getSelectedItem().toString();
                classification = edtClass.getSelectedItem().toString();


                Intent intent = new Intent(Input.this, MainActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("card",card);
                intent.putExtra("classification",classification);
                startActivity(intent);
            }
        });
    }

    // 날짜 선택 다이얼로그를 표시하는 메소드
    private void showDatePickerDialog() {
        new DatePickerDialog(
                this,
                myDatePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    // 날짜가 선택되었을 때 실행되는 리스너
    private DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    // 날짜 선택 후 EditText에 업데이트하는 메소드
    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        edtDate.setText(sdf.format(myCalendar.getTime()));
    }
}
