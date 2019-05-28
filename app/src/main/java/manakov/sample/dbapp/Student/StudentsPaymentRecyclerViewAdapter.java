package manakov.sample.dbapp.Student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.DbApplication;
import manakov.sample.dbapp.R;
import manakov.sample.dbapp.PaymentForm.Payment;

public class StudentsPaymentRecyclerViewAdapter extends RecyclerView.Adapter<StudentsPaymentRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Payment> payments;
    private View.OnClickListener onClickListener;
    private DbApplication application;

    public StudentsPaymentRecyclerViewAdapter(ArrayList<Payment> payments, DbApplication application){
        this.payments = payments;
        this.application = application;
    }

    @Override
    public StudentsPaymentRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_payment_view_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentsPaymentRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.studentPaymentCourseNameTextView.setText(
            application.database.courseDao().getCourseById(
                    payments.get(position).getCourseId()
            ).getCourseName()
        );
        holder.studentPaymentAmountTextView.setText(
                payments.get(position).getAmount() + " "
        );

    }

    @Override
    public int getItemCount() {
        return payments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView studentPaymentCourseNameTextView;
        public TextView studentPaymentAmountTextView;

        public ViewHolder(View view){
            super(view);

            studentPaymentCourseNameTextView = view.findViewById(R.id.studentPaymentCourseNameTextView);
            studentPaymentAmountTextView = view.findViewById(R.id.studentPaymentAmountTextView);

            view.setTag(this);
            view.setOnClickListener(onClickListener);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
}
