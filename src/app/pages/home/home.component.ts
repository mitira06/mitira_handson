import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { BehaviorSubject, combineLatest, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { loadCourses } from '../../store/course/course.actions';
import { selectAllCourses } from '../../store/course/course.selectors';
import { selectEnrolledIds } from '../../store/enrollment/enrollment.selectors';
import { Course } from '../../models/course.model';

// Hands-On 2 (Tasks 1 & 2): demonstrates all four binding types and the
// ngOnInit / ngOnDestroy lifecycle hooks.
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  // #11: string interpolation
  portalName = 'Student Course Portal';

  // #12: property binding -> [disabled]
  isPortalActive = true;

  // #13: event binding -> (click)
  message = '';

  // #14: two-way binding -> [(ngModel)]
  searchTerm = '';

  courses$: Observable<Course[]>;
  enrolledIds$: Observable<number[]>;
  gpa = 3.8;

  private searchTerm$ = new BehaviorSubject<string>('');
  /** Live course suggestions matching the quick search box. */
  suggestions$: Observable<Course[]>;

  constructor(private store: Store, private router: Router) {
    this.courses$ = this.store.select(selectAllCourses);
    this.enrolledIds$ = this.store.select(selectEnrolledIds);

    this.suggestions$ = combineLatest([this.courses$, this.searchTerm$]).pipe(
      map(([courses, term]) => {
        const normalized = term.trim().toLowerCase();
        if (!normalized) {
          return [];
        }
        return courses
          .filter(
            (course) =>
              course.name.toLowerCase().includes(normalized) ||
              course.code.toLowerCase().includes(normalized)
          )
          .slice(0, 5);
      })
    );
  }

  ngOnInit(): void {
    // #16: simulate fetching available courses on init.
    this.store.dispatch(loadCourses());
    console.log('HomeComponent initialised - courses loaded');
  }

  ngOnDestroy(): void {
    // #17: cleanup hook - critical for unsubscribing Observables/timers.
    console.log('HomeComponent destroyed');
  }

  onEnrollClick(): void {
    this.message = 'Enrollment opened!';
  }

  onSearchChange(): void {
    this.searchTerm$.next(this.searchTerm);
  }

  goToCourse(courseId: number): void {
    this.router.navigate(['/courses', courseId]);
  }

  viewAllResults(): void {
    this.router.navigate(['/courses'], {
      queryParams: this.searchTerm ? { search: this.searchTerm } : {}
    });
  }

  // #15: [property] is one-way (component -> DOM). [(ngModel)] is two-way
  // shorthand for [ngModel]="prop" (ngModelChange)="prop = $event", so DOM
  // changes flow back into the component property too.
}