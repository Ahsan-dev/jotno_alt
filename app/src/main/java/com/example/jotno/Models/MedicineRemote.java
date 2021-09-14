package com.example.jotno.Models;

public class MedicineRemote {

    private String medicine;
    private String instruction;

    public MedicineRemote(String medicine, String instruction) {
        this.medicine = medicine;
        this.instruction = instruction;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
