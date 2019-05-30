package manakov.sample.dbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DatesInputActivity extends AppCompatActivity {
    private DbApplication application;

    public EditText firstDateinputView;
    public EditText secondDateinputVIew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates_input);

        application = (DbApplication) getApplication();

        firstDateinputView = findViewById(R.id.firstDateInputTextView);
        secondDateinputVIew = findViewById(R.id.secondDateInputTextView);

        firstDateinputView.setVisibility(View.VISIBLE);
        secondDateinputVIew.setVisibility(View.VISIBLE);
    }


    public void onGetPayments(View view){
        Intent intent = new Intent(this, ShowPaymentsByDateActivity.class);
        intent.putExtra("first", firstDateinputView.getText().toString());
        intent.putExtra("second", secondDateinputVIew.getText().toString());
        startActivityForResult(intent, 0);
    }
}
