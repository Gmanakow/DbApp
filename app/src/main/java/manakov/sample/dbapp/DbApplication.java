package manakov.sample.dbapp;

import android.app.Application;

import java.util.ArrayList;

import androidx.room.Room;
import manakov.sample.dbapp.Student.Student;

public class DbApplication extends Application{

    public MainDataBase database;

    @Override
    public void onCreate(){
        super.onCreate();

        database = Room.databaseBuilder(
                getApplicationContext(),
                MainDataBase.class,
                "Main Database"
        )
                .allowMainThreadQueries()
                .build();
    }

    public ArrayList<Student> getStudents(){
        return  (ArrayList<Student>)
                database
                .studentDao()
                .getAll();
    }

    public void fillStudents(){
        for (int i = 0; i<15; i++){
            database
                    .studentDao()
                    .insertAll(new Student(
                            "name" + i,
                            "lastName" + i + i)
                    );
        }



    }

}
