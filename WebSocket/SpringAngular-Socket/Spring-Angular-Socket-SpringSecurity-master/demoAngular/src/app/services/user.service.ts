import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import * as jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  connectedUser: any;
  socket: any;
  token;
  constructor(private httpClient: HttpClient, private router: Router) {
    this.connectedUser = this.getConnectedUser();
    this.token = 'Bearer ' + localStorage.getItem('token');
  }
  login(form) {
    const header = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.post('http://localhost:9000/user/login', form, {headers: header});
  }
  register(user) {
    const header = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.post('http://localhost:9000/user/register', user, {headers: header});
  }
  getUserConnected(username) {
    const header = new HttpHeaders().set('Authorization', this.token);
    return this.httpClient.get('http://localhost:9000/user/connectedUser/' + username, {headers: header});
  }
  getListeUsers() {
    const header = new HttpHeaders().set('Authorization', this.token);
    return this.httpClient.get('http://localhost:9000/user/getAllUsers', {headers: header});
  }
  saveToken(token) {
    localStorage.setItem('token', token);
    this.token = 'Bearer ' + localStorage.getItem('token');
    this.connectedUser = this.getConnectedUser();
    console.log(this.connectedUser);
  }
  removeToken() {
    localStorage.deleteAll();
  }
  getConnectedUser() {
    if (localStorage.getItem('token')) {
      const token = localStorage.getItem('token');
      return jwt_decode(token).sub;
    }
  }
  validToken() {
    if (localStorage.getItem('token')) {
      return true;
    }
    this.connectedUser = {};
    return false;
  }
  logout() {
    localStorage.clear();
    this.connectedUser = null;
    this.router.navigateByUrl('/home');
    window.location.reload(true);
  }
}
