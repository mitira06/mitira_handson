import { Routes } from '@angular/router';
import { EnrollmentFormComponent } from './enrollment-form/enrollment-form.component';
import { ReactiveEnrollmentFormComponent } from './reactive-enrollment-form/reactive-enrollment-form.component';
import { unsavedChangesGuard } from '../../guards/unsaved-changes.guard';

// Hands-On 7, Task 2 (#73): lazy-loaded enrollment feature.
// This file is only downloaded the first time the user navigates to /enroll.
export const ENROLLMENT_ROUTES: Routes = [
  { path: '', component: EnrollmentFormComponent, title: 'Enrollment Form | Student Course Portal' },
  {
    path: 'reactive',
    component: ReactiveEnrollmentFormComponent,
    canDeactivate: [unsavedChangesGuard],
    title: 'Reactive Enrollment | Student Course Portal'
  }
];
