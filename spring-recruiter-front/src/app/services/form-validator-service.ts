import {Injectable} from '@angular/core';
import {FormGroup} from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class FormValidatorService {
  public isValidField(fieldName: string, myForm: FormGroup) {
    return myForm.controls[fieldName].errors && myForm.controls[fieldName].touched;
  }

  public getFieldErros(fieldName: string, myForm: FormGroup) {
    if (!myForm.controls[fieldName]) return null;
    const errors = myForm.controls[fieldName].errors ?? {};

    for (const key of Object.keys(errors)) {
      switch (key) {
        case "required":
          return "El campo es requerido";
        case "minlength":
          return `El campo deben tener minimo ${errors['minlength'].requiredLength} caracteres`;
        case "min":
          return `El valor minimo del campo debe ser ${errors["min"].min}`;
      }
    }
    return null;
  }

  public isFormValid(myForm: FormGroup) {
    if (myForm.invalid) {
      myForm.markAllAsTouched();
      return false;
    }
    return true;
  }
}
