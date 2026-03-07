package com.example.springboot_4_initial.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base")
public class __BaseController {
//    @Autowired
//    IVacacyService iVacacyService;
//    @Autowired
//    IImageService iImageService;
//    @Value("${url.imgs}")
//    String url_imgs;
//
//    @GetMapping("")
//    public ResponseEntity<?> prueba() {
//        Map<String, Object> json = new HashMap<>();
//        json.put("status", true);
//        json.put("message", "Controlador de prueba funcionando");
//        return ResponseEntity.status(HttpStatus.OK).body(json);
//    }
//
////    @GetMapping("/get_vacancy")
//    public ResponseEntity<?> get_vacancy() {
//        Map<String, Object> json = new HashMap<>();
//        Vacancy new_vacancy = new Vacancy(1L, "Desarrollador Backend Jr Laravel", new Date(), "Desarrollador backend Jr con Laravel 12", 15000, true);
//        json.put("status", true);
//        json.put("vacancy", new_vacancy);
//        return ResponseEntity.status(HttpStatus.OK).body(json);
//    }
//
//    @GetMapping("/list_vacancy")
//    public ResponseEntity<?> list_vacancy() {
//        List<Vacancy> vacancy_list = iVacacyService.list_vacancys();
//
//        if (vacancy_list.isEmpty()) {
//            throw new NotFoundVacancys("La lista de vacantes esta vacia");
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(iVacacyService.list_vacancys());
//    }
//
//    @GetMapping("get_vacancy/{id}")
//    public ResponseEntity<?> get_vacancy(@PathVariable Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(iVacacyService.get_vacancy(id));
//    }
//
//    @PostMapping("/save_img_vacancy/{id}")
//    public ResponseEntity<?> save_img_vacancy(@PathVariable Long id, @RequestParam("file") MultipartFile multipartFile) throws IOException {
//        Map<String, Object> json = new HashMap<>();
//        String path_image = "C:/Imagenes_Proyectos/SpringBoot";
//
//        String result_save_img = iImageService.save_image(url_imgs, multipartFile);
//        if (result_save_img == null) {
//            json.put("status", false);
//            json.put("message", "Ocurrio un error en la subida del archivo");
//        }
//        json.put("status", true);
//        json.put("url_img", result_save_img);
//
//        return ResponseEntity.status(HttpStatus.OK).body(json);
//    }
}
