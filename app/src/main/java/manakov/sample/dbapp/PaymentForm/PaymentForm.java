package manakov.sample.dbapp.PaymentForm;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PaymentForm {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String paymentForm;

    public PaymentForm(String paymentForm) {
        this.paymentForm = paymentForm;
    }

    public int getId() {
        return id;
    }
    public String getPaymentForm() {
        return paymentForm;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setPaymentForm(String paymentForm) {
        this.paymentForm = paymentForm;
    }
}
