import { CanDeactivateFn } from '@angular/router';
import { ReactiveEnrollmentFormComponent } from '../features/enrollment/reactive-enrollment-form/reactive-enrollment-form.component';

// Hands-On 7, Task 2 (#77): CanDeactivate guard for the reactive enrollment
// form. If the form is dirty, confirm with the user before navigating away.
export const unsavedChangesGuard: CanDeactivateFn<ReactiveEnrollmentFormComponent> = (
  component
) => {
  if (component.enrollForm.dirty) {
    return window.confirm('You have unsaved changes. Leave?');
  }
  return true;
};
