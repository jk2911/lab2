package maxim.goy.lab2;

import android.util.Log;

public class Credit {
    float sum;
    typePayment typePay;
    float percent;
    float year;
    int month;

    public Credit(float sum, float initialPayment, typePayment typePay, float percent, float year) {
        this.sum = sum - initialPayment;
        this.typePay = typePay;
        this.percent = percent;
        this.year = year;
        this.month = (int) year * 12;
    }

    public double credit() {
        switch (typePay) {
            case Differentiated:
                return diff();
            case Annuity:
                return ann();
        }
        return 0;
    }

    public double diff() {
        month = (int) year * 12;
        float avePay = sum / (float) month;
        float allSum = 0;
        System.out.println("средний платеж " + avePay);

        for (int i = 0; i < month; i++) {
            float monthPay = avePay + (sum - avePay * i) * percent / 12;
            allSum += monthPay;
            Log.d("Credit", i + 1 + " месяц " + monthPay);
        }
        return allSum;
    }

    public double ann() {
        float avePay = (float) ((sum * ((percent * 100) / (12 * 100))) /
                (1 - Math.pow((1 + ((percent * 100) / (12 * 100))), -month)));

        return avePay;
    }

    //1 - Диффир 2 - Анн
    enum typePayment {
        Differentiated,
        Annuity
    }
}
