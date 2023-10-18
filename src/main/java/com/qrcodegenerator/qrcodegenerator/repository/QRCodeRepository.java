package com.qrcodegenerator.qrcodegenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qrcodegenerator.qrcodegenerator.entity.QRCode;

public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
}
