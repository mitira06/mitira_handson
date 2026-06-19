import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map, retry, tap } from 'rxjs/operators';
import { Course } from '../models/course.model';

const API_URL = 'http://localhost:3000/courses';

// Hands-On 6 (Task 1) + Hands-On 8 (Task 1 & 2):
// CourseService started as an in-memory data store and was upgraded to use
// HttpClient against a JSON Server backend, with RxJS operators for
// transformation, logging and error handling.
@Injectable({ providedIn: 'root' })
export class CourseService {
  constructor(private http: HttpClient) {}

  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(API_URL).pipe(
      // tap is for side effects (logging) - it must never mutate the stream.
      // Use map() instead of tap() for actual data transformations.
      tap(courses => console.log('Courses loaded:', courses.length)),
      map(courses => courses.filter(c => c.credits >= 0)),
      // Retries the request up to 2 times before giving up.
      retry(2),
      catchError(err => {
        console.error('Failed to load courses', err);
        return throwError(() => new Error('Failed to load courses. Please try again.'));
      })
    );
  }

  getCourseById(id: number): Observable<Course> {
    return this.http.get<Course>(`${API_URL}/${id}`).pipe(
      catchError(err => {
        console.error(`Failed to load course ${id}`, err);
        return throwError(() => new Error('Failed to load course. Please try again.'));
      })
    );
  }

  createCourse(course: Omit<Course, 'id'>): Observable<Course> {
    return this.http.post<Course>(API_URL, course);
  }

  updateCourse(course: Course): Observable<Course> {
    return this.http.put<Course>(`${API_URL}/${course.id}`, course);
  }

  deleteCourse(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/${id}`);
  }
}
