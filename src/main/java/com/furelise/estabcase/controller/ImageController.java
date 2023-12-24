package com.furelise.estabcase.controller;

import com.furelise.estabcase.model.EstabCase;
import com.furelise.estabcase.model.EstabCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

// Just a sample
@RestController
@RequestMapping("/estab-case/image")
public class ImageController {

    @Autowired
    private EstabCaseRepository estabCaseRepository;

    @GetMapping("/{id}")
    public EstabCase findById(@PathVariable Integer id) {
        return estabCaseRepository.findById(id).orElseThrow();
    }

    @PostMapping("/{id}")
    public EstabCase uploadFile(@PathVariable Integer id, @RequestParam MultipartFile file) throws IOException {
        EstabCase estabCase = estabCaseRepository.findById(id).orElseThrow();
        estabCase.setEstabCasePic(file.getBytes());
        return estabCaseRepository.save(estabCase);
    }

    @PostMapping("/disk/{id}")
    public EstabCase saveFileByDisk(@PathVariable Integer id) throws IOException {
//        EstabCase estabCase = estabCaseRepository.findById(190001).orElseThrow();
        EstabCase estabCase = estabCaseRepository.findById(id).orElseThrow();
        File file = new File("src/main/resources/static/images/kp.jpeg");
        BufferedImage originalImage = ImageIO.read(file);
        ByteArrayOutputStream bass = new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", bass);
        bass.flush();
        byte[] photo = bass.toByteArray();
        estabCase.setEstabCasePic(photo);
        bass.close();
        return estabCaseRepository.save(estabCase);
    }


}
