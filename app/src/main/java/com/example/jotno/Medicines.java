package com.example.jotno;

public class Medicines {

    private String medicineName, medicineType, medicineTime, medicineMeal, medicineSpan;

    public Medicines(String medicineName, String medicineType, String medicineTime, String medicineMeal, String medicineSpan) {
        this.medicineName = medicineName;
        this.medicineType = medicineType;
        this.medicineTime = medicineTime;
        this.medicineMeal = medicineMeal;
        this.medicineSpan = medicineSpan;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getMedicineTime() {
        return medicineTime;
    }

    public void setMedicineTime(String medicineTime) {
        this.medicineTime = medicineTime;
    }

    public String getMedicineMeal() {
        return medicineMeal;
    }

    public void setMedicineMeal(String medicineMeal) {
        this.medicineMeal = medicineMeal;
    }

    public String getMedicineSpan() {
        return medicineSpan;
    }

    public void setMedicineSpan(String medicineSpan) {
        this.medicineSpan = medicineSpan;
    }
}
