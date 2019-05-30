package manakov.sample.dbapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.PaymentForm.Payment;

public class PaymentRecyclerViewAdapter extends RecyclerView.Adapter<PaymentRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Payment> payments;
    private View.OnClickListener onClickListener;
    private DbApplication application;

    public PaymentRecyclerViewAdapter(ArrayList<Payment> payments, DbApplication application){
        this.payments = payments;
        this.application = application;
    }

    @Override
    public PaymentRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_view_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PaymentRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.paymentStudentFirstNameTextView.setText(
                application.database.studentDao().getStudentById(
                        payments.get(position).getStudentId()
                ).getFirstName()
        );
        holder.paymentStudentLastNameTextView.setText(
                application.database.studentDao().getStudentById(
                        payments.get(position).getStudentId()
                ).getLastName()
        );
        holder.PaymentCourseNameTextView.setText(
                application.database.courseDao().getCourseById(
                        payments.get(position).getCourseId()
                ).getCourseName()
        );
        holder.PaymentAmountTextView.setText(
                payments.get(position).getAmount() + " "
        );
        holder.paymentDateTextView.setText(
                payments.get(position).getDate() + " "
        );

    }

    @Override
    public int getItemCount() {
        return payments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView paymentStudentFirstNameTextView;
        public TextView paymentStudentLastNameTextView;
        public TextView PaymentCourseNameTextView;
        public TextView PaymentAmountTextView;
        public TextView paymentDateTextView;

        public ViewHolder(View view){
            super(view);

            paymentStudentFirstNameTextView = view.findViewById(R.id.paymentStudentFirstNameTextView);
            paymentStudentLastNameTextView = view.findViewById(R.id.paymentStudentLastNameTextView);
            PaymentCourseNameTextView = view.findViewById(R.id.paymentCourseNameTextView);
            PaymentAmountTextView = view.findViewById(R.id.paymentAmountTextView);
            paymentDateTextView = view.findViewById(R.id.paymentDateTextView);

            view.setTag(this);
            view.setOnClickListener(onClickListener);
        }
    }
}
