import { HttpInterceptorFn } from '@angular/common/http';

// Hands-On 8, Task 3 (#88): clones the outgoing request and adds a mock
// Authorization header. Registered first, so it runs first on the way out.
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authReq = req.clone({
    setHeaders: { Authorization: 'Bearer mock-token-12345' }
  });
  return next(authReq);
};
