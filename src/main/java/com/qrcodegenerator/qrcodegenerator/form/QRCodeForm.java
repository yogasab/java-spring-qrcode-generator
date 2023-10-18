package com.qrcodegenerator.qrcodegenerator.form;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.Builder.Default;

public class QRCodeForm {
    private String text;
    private String encodedText;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEncodedText() {
        return this.encodedText;
    }

    public void setEncodedText(String encodedText) {
        this.encodedText = encodedText;
    }

}
