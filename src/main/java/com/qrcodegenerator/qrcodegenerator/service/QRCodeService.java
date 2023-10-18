package com.qrcodegenerator.qrcodegenerator.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qrcodegenerator.qrcodegenerator.entity.QRCode;
import com.qrcodegenerator.qrcodegenerator.repository.QRCodeRepository;

@Service
public class QRCodeService {
    @Autowired
    private QRCodeRepository qrCodeRepository;

    public QRCode createQRCode(QRCode qrCode) {
        return qrCodeRepository.save(qrCode);
    }

    public QRCode updateQRCode(QRCode qrCode) {
        return qrCodeRepository.save(qrCode);
    }

    public QRCode findQRCodeById(Long id) {
        QRCode qrCode = qrCodeRepository.findById(id).orElse(null);
        return qrCode;
    }
}
