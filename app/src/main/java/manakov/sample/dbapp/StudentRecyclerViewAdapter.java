package manakov.sample.dbapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.Student.Student;

public class StudentRecyclerViewAdapter extends RecyclerView.Adapter<StudentRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Student> list;
    private View.OnClickListener onClickListener;

    public StudentRecyclerViewAdapter(ArrayList<Student> list){
        this.list = list;
    }

    @Override
    public StudentRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentRecyclerViewAdapter.ViewHolder viewHolder, int postition){
        viewHolder.studentFirstNameTextView.setText(list.get(postition).getFirstName());
        viewHolder.studentLastNameTextView .setText(list.get(postition).getLastName() );
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView studentFirstNameTextView;
        public TextView studentLastNameTextView;

        public ViewHolder(View view){
            super(view);

            studentFirstNameTextView = view.findViewById(R.id.studentFirstNameTextView);
            studentLastNameTextView  = view.findViewById(R.id.studentLastNameTextView);
        }

    }

}
