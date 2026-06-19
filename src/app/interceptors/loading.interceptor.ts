import { inject } from '@angular/core';
import { HttpInterceptorFn } from '@angular/common/http';
import { finalize } from 'rxjs';
import { LoadingService } from '../services/loading.service';

// Hands-On 8, Task 3 (#91): toggles the global loading spinner via
// LoadingService. The "loading started" update is deferred with
// setTimeout so it doesn't fire mid-change-detection on the first app
// render (avoids NG0100: ExpressionChangedAfterItHasBeenCheckedError).
// finalize() runs whether the request completes or errors - equivalent
// to a try/finally block.
export const loadingInterceptor: HttpInterceptorFn = (req, next) => {
  const loadingService = inject(LoadingService);

  setTimeout(() => loadingService.setLoading(true));

  return next(req).pipe(
    finalize(() => loadingService.setLoading(false))
  );
};