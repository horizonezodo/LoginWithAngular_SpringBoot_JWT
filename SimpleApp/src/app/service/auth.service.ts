import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})

export class AuthService {
   baseUrl = "http://localhost:8090"
  constructor(private http: HttpClient) { }

  signup(signupRequest: any): Observable<any>{
    return this.http.post(`${this.baseUrl}/register`,signupRequest );
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/authentication `, loginRequest)
  }

  hello(): Observable<any> {
    return this.http.get(`http://localhost:8090/users/hello`, {
      headers: this.createAuthorizezationHeader()
    });
  }

  private createAuthorizezationHeader(){
    const jwtToken = localStorage.getItem('JWT');
    if(jwtToken) {
      return new HttpHeaders().set(
        'Authorization', 'Bearer ' + jwtToken
      )
    }else {
      console.log('No JWT token');
    }
    return null;
  }
}
