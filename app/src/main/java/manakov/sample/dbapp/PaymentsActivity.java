package manakov.sample.dbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.PaymentForm.Payment;
import manakov.sample.dbapp.Student.StudentPaymentRecyclerViewAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class PaymentsActivity extends AppCompatActivity {
    private DbApplication application;

    private RecyclerView paymentRecycleView;
    private PaymentRecyclerViewAdapter paymentRecycleViewAdapter;
    private ArrayList<Payment> payments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        application = (DbApplication) getApplication();

        payments = new ArrayList<>();
        payments.addAll(
            application.database.paymentDao().getAll()
        );

        paymentRecycleView = findViewById(R.id.paymentRecyclerView);
        paymentRecycleView.setVisibility(View.VISIBLE);
        paymentRecycleView.setLayoutManager(new LinearLayoutManager(this));

        paymentRecycleViewAdapter = new PaymentRecyclerViewAdapter(payments, application);
        paymentRecycleView.setAdapter(paymentRecycleViewAdapter);
        paymentRecycleViewAdapter.notifyDataSetChanged();
    }

    public void ongetPaymentsinPeriodClick(View view){
        Intent intent = new Intent(this, DatesInputActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            payments.clear();
            payments.addAll(
                    application.database.paymentDao().getAll()
            );
            paymentRecycleViewAdapter.notifyDataSetChanged();
        }
    }

}
