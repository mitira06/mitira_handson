import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

// Hands-On 1, Task 2 (#7): header with portal name and navigation links.
@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  portalName = 'Student Course Portal';
}
