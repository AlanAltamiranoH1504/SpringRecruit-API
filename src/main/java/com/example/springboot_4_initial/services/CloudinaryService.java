package com.example.springboot_4_initial.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.springboot_4_initial.exceptions.NotFoundFile;
import com.example.springboot_4_initial.exceptions.NotValidateMimes;
import com.example.springboot_4_initial.services.interfaces.ICloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryService implements ICloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Map upload(MultipartFile file) throws IOException {
        // ! Validacion de existencia de archivo
        if (file.isEmpty()) {
            throw new NotFoundFile("Ocurrio un error en la subida del archivo");
        }

        // ! Validacion de mimes
        boolean resultMimesValidation = this.validateMimes(file, new String[]{"image/png", "image/jpeg", "image/jpg"});

        File uploadFile = File.createTempFile("temp", file.getOriginalFilename());
        file.transferTo(uploadFile);

        Map result = cloudinary.uploader().upload(uploadFile, ObjectUtils.emptyMap());
        return result;
    }

    @Override
    public Map uploadFile(MultipartFile file) throws IOException {
        // ! Validacion de existencia de archivo
        if (file.isEmpty()) {
            throw new NotFoundFile("Ocurrio un error en la subida del archivo");
        }
        // ! Validacion de mimes
        boolean resultMimesValidation = this.validateMimes(file, new String[]{"application/pdf"});

        Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "folder", "cvs",
                "public_id", "cv_" + UUID.randomUUID().toString(),
                "resource_type", "raw",
                "access_mode", "public"
        ));
        return result;
    }

    @Override
    public boolean delete_image(String public_id_img) throws IOException {
        Map result_delete = cloudinary.uploader().destroy(public_id_img, ObjectUtils.emptyMap());
        if (!result_delete.get("result").equals("ok")) {
            throw new IOException("Ocurrio un error en la eliminacion de la imgagen");
        }
        return true;
    }

    @Override
    public boolean validateMimes(MultipartFile file, String[] mimes) {
        String mimeFile = file.getContentType();
        List<String> validateMimes = Arrays.asList(mimes);
        if (!validateMimes.contains(mimeFile)) {
            throw new NotValidateMimes("La extensión del archivo no es valida. Se recibio un archivo de extension " + mimeFile);
        }
        return true;
    }
}
