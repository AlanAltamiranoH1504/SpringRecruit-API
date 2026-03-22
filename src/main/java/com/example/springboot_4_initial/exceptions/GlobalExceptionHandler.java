package com.example.springboot_4_initial.exceptions;

import com.example.springboot_4_initial.exceptions.auth.NotCofirmAccountException;
import com.example.springboot_4_initial.exceptions.auth.PasswordIncorrectException;
import com.example.springboot_4_initial.exceptions.categories.CreatedCategory;
import com.example.springboot_4_initial.exceptions.categories.NameCategoryError;
import com.example.springboot_4_initial.exceptions.categories.NotFoundCategories;
import com.example.springboot_4_initial.exceptions.categories.NotFoundCategory;
import com.example.springboot_4_initial.exceptions.contractType.NameContractTypeError;
import com.example.springboot_4_initial.exceptions.industrialSector.NameIndustrialSectorError;
import com.example.springboot_4_initial.exceptions.users.RemoveProfileException;
import com.example.springboot_4_initial.exceptions.users.UpdateProfileException;
import com.example.springboot_4_initial.exceptions.vancacies.ErrorUpdateImgVacancy;
import com.example.springboot_4_initial.exceptions.vancacies.NotFoundEntityException;
import com.example.springboot_4_initial.exceptions.vancacies.NotFoundVacancy;
import com.example.springboot_4_initial.exceptions.vancacies.NotFoundVacancys;
import com.example.springboot_4_initial.services.interfaces.IExcepcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    IExcepcionService iExcepcionService;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleJsonParseError(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(iExcepcionService.generateMessageException(
                        "Formato JSON invalido o tipo de datos incorrecto",
                        "El campo id debe ser numérico",
                        ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMisMatchError(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(iExcepcionService.generateMessageException(
                        "Formato JSON inválido o tipo de dato incorrecto",
                        "El parametro id debe ser numérico",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundError(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(iExcepcionService.generateMessageException(
                        "Recurso no encontrado",
                        "El recurso no fue localizado dentro del proyecto",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingParamError(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(iExcepcionService.generateMessageException(
                        "Parametro de peticion no encontrado",
                        "El parametro necesario para la peticio  no fue encontrado",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NotFoundVacancys.class)
    public ResponseEntity<?> handleNotFoundVacancyError(NotFoundVacancys ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en el metodo de listado",
                        "El listado de vacantes disponibles se encuentra vacio",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NotFoundVacancy.class)
    public ResponseEntity<?> handleNotFoundVacancyError(NotFoundVacancy ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la busqueda de la vacante",
                        "Ocurrio un error en la busqueda de la vacante",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NotFoundFile.class)
    public ResponseEntity<?> handleNotFoundFile(NotFoundFile ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la subida del archio",
                        "El archivo no fue agregado de manera correcto en el proceso de peticion",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NotValidateMimes.class)
    public ResponseEntity<?> handleNotValidateMimes(NotValidateMimes ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la extension del archivo subido",
                        "La extension del archivo no se encuentra dentro de las permitidas",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<?> handleMultpartException(MultipartException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un erro en la subida de archivo",
                        "Se genero en la subida de archivos multiparte",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<?> handleMissingServletRequestPartException(MissingServletRequestPartException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la subida de archivo",
                        "El nombre del archivo en el request no es correcto",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error general en el servidor",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NotFoundCategories.class)
    public ResponseEntity<?> handleNotFoundCategories(NotFoundCategories ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en el listado de categorias",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(CreatedCategory.class)
    public ResponseEntity<?> handleCreatedCategory(CreatedCategory ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la creacion de la categoria",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NotFoundCategory.class)
    public ResponseEntity<?> handleNotFoundCategory(NotFoundCategory ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la busqueda de la categoria",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la creacion de categoria",
                        "Exite una violacion de la integridad de base de datos en el intento de insercion",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NameCategoryError.class)
    public ResponseEntity<?> handNameCategoryError(NameCategoryError ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrior un error con el nombre de la categoria",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> json = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            json.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
    }

    @ExceptionHandler(ErrorUpdateImgVacancy.class)
    public ResponseEntity<?> handleErrorUpdateImgVacancy(ErrorUpdateImgVacancy ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(iExcepcionService.generateMessageException(
                        "La actualizacion de imagen fallo",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(ListEmptyException.class)
    public ResponseEntity<?> handleListEmptyException(ListEmptyException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(iExcepcionService.generateMessageException(
                        "Lista vacia, no existen elementos en el retorno",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<?> handleNotFoundEntityException(NotFoundEntityException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(iExcepcionService.generateMessageException(
                        "La busqueda del registro obtuvo un error",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(RemoveProfileException.class)
    public ResponseEntity<?> handleRemoveProfileException(RemoveProfileException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(iExcepcionService.generateMessageException(
                        "Error en lista de perfiles a remover",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(UpdateProfileException.class)
    public ResponseEntity<?> handleUpdateProfileException(UpdateProfileException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la actualizacion del perfil de usuario",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<?> handlePasswordIncorrectException(PasswordIncorrectException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la validacion del password",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NotCofirmAccountException.class)
    public ResponseEntity<?> handleNotCofirmAccountException(NotCofirmAccountException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la validación de confirmación de cuenta",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(CreatedEntityException.class)
    public ResponseEntity<?> handleCreatedEntityException(CreatedEntityException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la creacion de la entidad",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(UpdateException.class)
    public ResponseEntity<?> handleUpdateException(UpdateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la actualizacón del registro",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NameIndustrialSectorError.class)
    public ResponseEntity<?> handleNameIndustrialSectorError(NameIndustrialSectorError ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la actualización",
                        "",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NameContractTypeError.class)
    public ResponseEntity<?> handleNameContractTypeError(NameContractTypeError ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(iExcepcionService.generateMessageException(
                        "Ocurrio un error en la actualización",
                        "",
                        ex.getMessage()
                ));
    }
}
