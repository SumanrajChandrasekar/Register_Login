import { Component, OnInit, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  private router = inject(Router);
  username: string = '';
  role: string = '';

  ngOnInit(): void {
  const userJson = localStorage.getItem('loggedInUser');
  if (userJson) {
    const user = JSON.parse(userJson);
    this.username = user.username; // Extract username from local storage
    this.role = user.role; // Extract role from local storage
    console.log('User:', user.username, 'Role:', user.role);
  } else {
    this.router.navigateByUrl('/login');
  }
}

  logout(): void { // Method to handle user logout
    console.log('User logged out');
    alert('You have been logged out successfully.');
    // Clear user data from local storage and navigate to login page
    localStorage.removeItem("loggedInUser");
    this.router.navigateByUrl('/login');
  }
}