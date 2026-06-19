import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { EnrollmentService } from '../../../services/enrollment.service';

// Hands-On 4: template-driven Enrollment Request form with ngModel,
// built-in validators (required, minlength, email, requiredTrue on
// checkbox) and contextual error messages driven by Angular's
// ng-valid/ng-invalid/ng-touched CSS classes.
@Component({
  selector: 'app-enrollment-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './enrollment-form.component.html',
  styleUrls: ['./enrollment-form.component.css']
})
export class EnrollmentFormComponent {
  studentName = '';
  studentEmail = '';
  courseId: number | null = null;
  preferredSemester: 'Odd' | 'Even' = 'Odd';
  agreeToTerms = false;

  submitted = false;

  constructor(private enrollmentService: EnrollmentService) {}

  onSubmit(form: NgForm): void {
    console.log('Form value:', form.value);
    console.log('Form valid:', form.valid);

    if (form.valid && this.courseId !== null) {
      // #81: persist the enrollment request to /enrollments (not /courses).
      this.enrollmentService
        .submitEnrollmentRequest({
          studentName: this.studentName,
          studentEmail: this.studentEmail,
          courseId: this.courseId,
          preferredSemester: this.preferredSemester,
          agreeToTerms: this.agreeToTerms
        })
        .subscribe({
          next: () => {
            this.submitted = true;
            console.log('Enrollment request submitted');
          },
          error: (err) => console.error('Failed to submit enrollment request', err)
        });
    }
  }

  onReset(form: NgForm): void {
    form.resetForm();
    this.submitted = false;
  }
}