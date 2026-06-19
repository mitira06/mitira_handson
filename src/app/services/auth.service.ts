import { Injectable } from '@angular/core';

// Hands-On 7: a minimal AuthService used by the auth guard.
// isLoggedIn is hardcoded for the exercise - in a real app this would be
// driven by a token stored after a login API call.
@Injectable({ providedIn: 'root' })
export class AuthService {
  isLoggedIn = true;

  login(): void {
    this.isLoggedIn = true;
  }

  logout(): void {
    this.isLoggedIn = false;
  }
}
