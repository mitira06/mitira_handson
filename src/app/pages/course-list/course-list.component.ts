import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { BehaviorSubject, combineLatest, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { CourseCardComponent } from '../../components/course-card/course-card.component';
import { Course } from '../../models/course.model';
import { loadCourses } from '../../store/course/course.actions';
import { selectAllCourses, selectCoursesError, selectCoursesLoading } from '../../store/course/course.selectors';
import { enrollInCourse, unenrollFromCourse } from '../../store/enrollment/enrollment.actions';
import { selectEnrolledIds } from '../../store/enrollment/enrollment.selectors';

// Hands-On 3 (structural/attribute directives), Hands-On 7 (query params,
// navigation) and Hands-On 9 (NgRx-backed course list).
@Component({
  selector: 'app-course-list',
  standalone: true,
  imports: [CommonModule, FormsModule, CourseCardComponent],
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {
  isLoading$: Observable<boolean>;
  error$: Observable<string | null>;
  enrolledIds$: Observable<number[]>;

  /** Filtered list of courses, recomputed whenever courses or searchTerm change. */
  filteredCourses$: Observable<Course[]>;

  private searchTerm$ = new BehaviorSubject<string>('');
  searchTerm = '';
  selectedCourseId: number | null = null;

  constructor(private store: Store, private router: Router, private route: ActivatedRoute) {
    this.isLoading$ = this.store.select(selectCoursesLoading);
    this.error$ = this.store.select(selectCoursesError);
    this.enrolledIds$ = this.store.select(selectEnrolledIds);

    this.filteredCourses$ = combineLatest([
      this.store.select(selectAllCourses),
      this.searchTerm$
    ]).pipe(
      map(([courses, term]) => {
        const normalized = term.trim().toLowerCase();
        if (!normalized) {
          return courses;
        }
        return courses.filter(
          (course) =>
            course.name.toLowerCase().includes(normalized) ||
            course.code.toLowerCase().includes(normalized)
        );
      })
    );
  }

  ngOnInit(): void {
    this.store.dispatch(loadCourses());

    // #71: read the search query param back on init.
    this.searchTerm = this.route.snapshot.queryParamMap.get('search') ?? '';
    this.searchTerm$.next(this.searchTerm);
  }

  // #26: trackBy avoids re-rendering every card when the array reference
  // changes but individual items have not - Angular only updates items
  // whose id has changed.
  trackByCourseId(index: number, course: Course): number {
    return course.id;
  }

  onSearchChange(): void {
    this.searchTerm$.next(this.searchTerm);

    this.router.navigate(['/courses'], {
      queryParams: this.searchTerm ? { search: this.searchTerm } : {}
    });
  }

  onCardClick(courseId: number): void {
    this.router.navigate(['/courses', courseId]);
  }

  onEnroll(courseId: number, enrolledIds: number[]): void {
    if (enrolledIds.includes(courseId)) {
      this.store.dispatch(unenrollFromCourse({ courseId }));
    } else {
      this.store.dispatch(enrollInCourse({ courseId }));
    }
    this.selectedCourseId = courseId;
    console.log('Enrolling in course: ' + courseId);
  }
}