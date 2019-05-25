package manakov.sample.dbapp.Proficiency;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Proficiency {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String proficiency;

    @ColumnInfo
    private double level;

    @ColumnInfo
    private int price;

    @ColumnInfo
    private int payment;

    public Proficiency(String proficiency, double level, int price, int payment) {
        this.proficiency = proficiency;
        this.level = level;
        this.price = price;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }
    public String getProficiency() {
        return proficiency;
    }
    public double getLevel() {
        return level;
    }
    public int getPrice() {
        return price;
    }
    public int getPayment() {
        return payment;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }
    public void setLevel(double level){
        this.level = level;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setPayment(int payment) {
        this.payment = payment;
    }
}
