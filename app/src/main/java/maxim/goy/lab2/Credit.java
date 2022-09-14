package maxim.goy.lab2;

import android.util.Log;

public class Credit {
    float sum;
    typePayment typePay;
    float percent;
    float year;
    int month;
    float initialPayment;

    float overPayment = 0;
    float generalSum = 0;
    float presentOverpayment;
    float avePay;

    public Credit(float sum, float initialPayment, typePayment typePay, float percent, float year) {
        this.sum = sum - initialPayment;
        this.initialPayment = initialPayment;
        this.typePay = typePay;
        this.percent = percent;
        this.year = year;
        this.month = (int) year * 12;
    }

    public void credit() {
        switch (typePay) {
            case Differentiated:
                diff();
                break;
            case Annuity:
                ann();
                break;
        }
        overPayment = generalSum - sum;
        presentOverpayment = overPayment / sum * 100;
    }

    private void diff() {
        float avePay = sum / (float) month;
        System.out.println("средний платеж " + avePay);

        for (int i = 0; i < month; i++) {
            float monthPay = avePay + (sum - avePay * i) * (percent / 100) / 12;
            generalSum += monthPay;
        }
    }

    private void ann() {
        avePay = (float) ((sum * ((percent) / (12 * 100))) /
                (1 - Math.pow((1 + ((percent) / (12 * 100))), -month)));
        generalSum = avePay * month;

    }

    //1 - Диффир 2 - Анн
    enum typePayment {
        Differentiated,
        Annuity
    }

    public int getSumCredit() {
        return (int) sum;
    }

    public float getGeneralSum() {
        return generalSum;
    }

    public float getPresentOverpayment() {
        return presentOverpayment;
    }

    public float getOverPayment() {
        return overPayment;
    }

    public float getAvePay() {
        return avePay;
    }
}
