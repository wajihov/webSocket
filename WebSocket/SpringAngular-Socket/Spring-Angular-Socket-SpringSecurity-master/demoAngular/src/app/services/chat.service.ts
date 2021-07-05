import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private httpClient: HttpClient) { }
  getConversation(idUser1, idUser2) {
    const header = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('token'));
    return this.httpClient.get('http://localhost:9000/conversation/getOneConversation/' + idUser1 + '/' + idUser2, {headers: header});
  }
  sendMessage(message, idUser, idConv) {
    const header = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('token'));
    return this.httpClient.post('http://localhost:9000/message/sendMessage/' + idUser + '/' + idConv, message, {headers: header});
  }
}
