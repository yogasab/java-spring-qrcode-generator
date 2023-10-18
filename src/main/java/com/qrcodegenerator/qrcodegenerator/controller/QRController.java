package com.qrcodegenerator.qrcodegenerator.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.WriterException;
import com.qrcodegenerator.qrcodegenerator.entity.QRCode;
import com.qrcodegenerator.qrcodegenerator.form.QRCodeForm;
import com.qrcodegenerator.qrcodegenerator.service.QRCodeService;

import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class QRController {
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/images/QRCode.png";

    @Autowired
    private QRCodeService qrCodeService;

    public QRController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("title", "QR Code Generator");
        model.addAttribute("qrCode", new QRCodeForm());
        return "index";
    }

    @PostMapping(value = "/generate")
    public String generateQRCode(@ModelAttribute("qrCode") QRCode qrCode, Model model) {
        byte[] image = new byte[0];
        String text = qrCode.getText();
        try {
            image = QRCodeGenerator.class.newInstance().getQRCodeImageByte(text, 350, 350);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String qrCodeEncoded = Base64.getEncoder().encodeToString(image);
        qrCode.setEncodedText(qrCodeEncoded);
        QRCode qr = qrCodeService.createQRCode(qrCode);
        model.addAttribute("title", "QR Code Generator");
        model.addAttribute("text", text);
        model.addAttribute("qrCodeEncoded", qrCode.getEncodedText());
        return "index";
    }

    @PostMapping(value = "/download/{id}")
    public String downloadQRCode(@PathVariable("id") Long id, @ModelAttribute("qrCode") QRCode qrCode,
            Model model) {
        QRCode qr = qrCodeService.updateQRCode(qrCode);
        model.addAttribute("title", "QR Code Generator");
        model.addAttribute("text", qr.getText());
        model.addAttribute("qrCodeEncoded", qrCode.getEncodedText());
        return "index";
    }
}
