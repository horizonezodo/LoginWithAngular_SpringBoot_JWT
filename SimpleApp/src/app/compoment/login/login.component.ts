import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private service: AuthService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    })
  }

  login() {
    // console.log(this.loginForm.value);
    this.service.login(this.loginForm.value).subscribe((response) => {
      // console.log(response);
      // alert(response.jwtToken);
      if (response.jwt) {
        alert(response.jwt);
        console.log(response.jwt);
        const jwtToken = response.jwt;
        localStorage.setItem('JWT', jwtToken);
        this.router.navigateByUrl('/dashboard');
      }
    })
  }
}
