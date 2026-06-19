import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

// Hands-On 7, Task 1 (#68): wildcard ** route target.
@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.css']
})
export class NotFoundComponent {}
