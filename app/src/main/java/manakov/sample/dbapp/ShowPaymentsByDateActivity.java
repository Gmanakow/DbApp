package manakov.sample.dbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.sqlite.db.SimpleSQLiteQuery;
import manakov.sample.dbapp.PaymentForm.Payment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ShowPaymentsByDateActivity extends AppCompatActivity {
    private DbApplication application;

    private RecyclerView paymentRecycleView;
    private PaymentRecyclerViewAdapter paymentRecycleViewAdapter;
    private ArrayList<Payment> payments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_payments_by_date);

        application = (DbApplication) getApplication();

        Intent intent = getIntent();
        String firstDate = intent.getExtras().getString("first");
        String secondDate = intent.getExtras().getString("second");

        secondDate = "0 or 1=1";

        ArrayList<Payment> payments = new ArrayList<>();
        try {
            payments.addAll(
                    application.database.paymentDao().getPaymentsByDates(
                            new SimpleSQLiteQuery(
                                    "select * from payment where date > " + firstDate +
                                            " and date < " + secondDate
                            )
                            //  try { firstDate = (Integer.parseInt(firstDate)).toString();
                            //  secondDate = (Integer.parseInt(secondDate)).toString(); }
                            // catch (Exception e) {firstDate = "0"; secondDate="0"; }
                            // use getPaymentByDateSecure instead.
                    )
            );
        } catch (Exception e){
            Log.d("err", e.getLocalizedMessage());
        }

        paymentRecycleView = findViewById(R.id.paymentsByDateRecyclerView);
        paymentRecycleView.setVisibility(View.VISIBLE);
        paymentRecycleView.setLayoutManager(new LinearLayoutManager(this));

        paymentRecycleViewAdapter = new PaymentRecyclerViewAdapter(payments, application);
        paymentRecycleView.setAdapter(paymentRecycleViewAdapter);
        paymentRecycleViewAdapter.notifyDataSetChanged();
    }
}
