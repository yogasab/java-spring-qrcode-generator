package com.qrcodegenerator.qrcodegenerator.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder.Default;

@Data
@Entity
@Table(name = "qrcode")
public class QRCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "text", nullable = false, length = 1000)
    private String text;
    private String encodedText;
    private LocalDateTime createdAt;

    public QRCode(String text, String encodedText, Long id, LocalDateTime downloadedAt) {
        this.text = text;
        this.encodedText = encodedText;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long Id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEncodedText() {
        return this.encodedText;
    }

    public void setEncodedText(String encodedText) {
        this.encodedText = encodedText;
    }
}
