package com.example.jotno.Models;

public class Bills {

    private String prescriptionNo, bill, billDate;

    public Bills(String prescriptionNo, String bill, String billDate) {
        this.prescriptionNo = prescriptionNo;
        this.bill = bill;
        this.billDate = billDate;
    }

    public String getPrescriptionNo() {
        return prescriptionNo;
    }

    public void setPrescriptionNo(String prescriptionNo) {
        this.prescriptionNo = prescriptionNo;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}
