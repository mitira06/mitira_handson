import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Course } from '../../models/course.model';
import { selectEnrolledCourses } from '../../store/enrollment/enrollment.selectors';
import { CreditLabelPipe } from '../../pipes/credit-label.pipe';

// Hands-On 6, Task 2 (#66) + Hands-On 7 (guarded by authGuard) + Hands-On 9:
// shows the courses the student is currently enrolled in, read from the
// NgRx store via the cross-slice selectEnrolledCourses selector.
@Component({
  selector: 'app-student-profile',
  standalone: true,
  imports: [CommonModule, CreditLabelPipe],
  templateUrl: './student-profile.component.html',
  styleUrls: ['./student-profile.component.css']
})
export class StudentProfileComponent {
  enrolledCourses$: Observable<Course[]>;

  studentName = 'Alex Johnson';
  studentEmail = 'alex.johnson@example.edu';

  constructor(private store: Store) {
    this.enrolledCourses$ = this.store.select(selectEnrolledCourses);
  }
}
