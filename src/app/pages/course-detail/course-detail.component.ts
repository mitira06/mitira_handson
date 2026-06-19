import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import { Course } from '../../models/course.model';
import { CourseService } from '../../services/course.service';
import { EnrollmentService } from '../../services/enrollment.service';
import { CreditLabelPipe } from '../../pipes/credit-label.pipe';

// Hands-On 7, Task 1 (#69): reads the :id route parameter and loads the
// matching course. Hands-On 8, Task 2 (#87): uses switchMap to chain a
// second HTTP call (enrolled students) - switchMap cancels the previous
// inner Observable if a new courseId arrives before it completes.
@Component({
  selector: 'app-course-detail',
  standalone: true,
  imports: [CommonModule, RouterLink, CreditLabelPipe],
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.css']
})
export class CourseDetailComponent implements OnInit {
  course$!: Observable<Course>;
  students$!: Observable<string[]>;
  courseId!: number;

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService,
    private enrollmentService: EnrollmentService
  ) {}

  ngOnInit(): void {
    this.courseId = Number(this.route.snapshot.paramMap.get('id'));
    this.course$ = this.courseService.getCourseById(this.courseId);

    // switchMap: if the user navigates to a different course before this
    // resolves, the previous inner request is cancelled automatically.
    this.students$ = this.route.paramMap.pipe(
      switchMap((params) => this.enrollmentService.getStudentsByCourse(Number(params.get('id'))))
    );
  }
}
