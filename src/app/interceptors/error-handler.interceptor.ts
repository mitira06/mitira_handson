import { inject } from '@angular/core';
import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

// Hands-On 8, Task 3 (#90): global error handling.
// 401 -> redirect to login/home, 500 -> log a global error notification.
export const errorHandlerInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 401) {
        router.navigate(['/']);
      } else if (error.status === 500) {
        console.error('Server error - showing global notification:', error.message);
      }
      return throwError(() => error);
    })
  );
};
