import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators
} from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Observable, of } from 'rxjs';
import { delay, map } from 'rxjs/operators';

// Hands-On 5: reactive Enrollment form with FormBuilder/FormGroup, a custom
// synchronous validator, a custom async validator, and a FormArray for
// dynamic "additional courses" controls.

// #53: custom synchronous validator - disallows course codes starting with 'XX'.
export function noCourseCode(control: AbstractControl): ValidationErrors | null {
  const value = control.value;
  if (typeof value === 'string' && value.toUpperCase().startsWith('XX')) {
    return { noCourseCode: true };
  }
  return null;
}

// #55: custom async validator - simulates a server-side "email taken" check.
// Async validators run only after all sync validators pass.
export function simulateEmailCheck(control: AbstractControl): Observable<ValidationErrors | null> {
  const value: string = control.value ?? '';
  return of(value).pipe(
    delay(800),
    map((email) => (email.includes('test@') ? { emailTaken: true } : null))
  );
}

@Component({
  selector: 'app-reactive-enrollment-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './reactive-enrollment-form.component.html',
  styleUrls: ['./reactive-enrollment-form.component.css']
})
export class ReactiveEnrollmentFormComponent implements OnInit {
  enrollForm!: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.enrollForm = this.fb.group({
      studentName: ['', [Validators.required, Validators.minLength(3)]],
      studentEmail: this.fb.control(
        '',
        [Validators.required, Validators.email],
        [simulateEmailCheck]
      ),
      courseId: [null, [Validators.required, noCourseCode]],
      preferredSemester: ['Odd', Validators.required],
      agreeToTerms: [false, Validators.requiredTrue],
      additionalCourses: this.fb.array([])
    });
  }

  // #57: typed getter - keeps the `as FormArray` cast out of the template
  // and gives compile-time type checking everywhere the array is used.
  get additionalCourses(): FormArray {
    return this.enrollForm.get('additionalCourses') as FormArray;
  }

  addAdditionalCourse(): void {
    this.additionalCourses.push(new FormControl('', Validators.required));
  }

  removeCourse(index: number): void {
    this.additionalCourses.removeAt(index);
  }

  onSubmit(): void {
    // #52: .value excludes disabled controls; .getRawValue() includes them.
    console.log('enrollForm.value:', this.enrollForm.value);
    console.log('enrollForm.getRawValue():', this.enrollForm.getRawValue());

    if (this.enrollForm.valid) {
      this.submitted = true;
    }
  }

  onReset(): void {
    this.enrollForm.reset({ preferredSemester: 'Odd', agreeToTerms: false });
    this.additionalCourses.clear();
    this.submitted = false;
  }
}
