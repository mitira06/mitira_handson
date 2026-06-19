import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

// Hands-On 8, Task 3 (#91): backs the global loading spinner.
// The loading interceptor sets this to true before a request and false
// (via finalize) once the request completes or errors.
@Injectable({ providedIn: 'root' })
export class LoadingService {
  private loadingSubject = new BehaviorSubject<boolean>(false);
  isLoading$ = this.loadingSubject.asObservable();

  setLoading(isLoading: boolean): void {
    this.loadingSubject.next(isLoading);
  }
}
