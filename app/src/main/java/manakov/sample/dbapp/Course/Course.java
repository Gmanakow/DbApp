package manakov.sample.dbapp.Course;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
