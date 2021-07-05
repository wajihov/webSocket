import { Component, OnInit } from '@angular/core';
import { UserService } from './../services/user.service';
import { ChatService } from './../services/chat.service';
import { FormGroup, FormControl } from '@angular/forms';
import {WebSocketService} from './../services/web-socket.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  listeUsers: any;
  listeMessages: any;
  messageForm: FormGroup;
  connectUser: any;
  chosenUser: any;
  conversation;
  stompClient: any;
  constructor(public chatService: ChatService, public userService: UserService, private webSocketService: WebSocketService) {
    this.stompClient = this.webSocketService.connect();
    this.listeMessages = [];
    this.listeUsers = [];
    this.messageForm = new FormGroup({
      content: new FormControl(''),
      user: new FormControl(''),
    });
    this.userService.getUserConnected(this.userService.connectedUser).subscribe((user: any) => {
      this.connectUser = user;

      this.userService.getListeUsers().subscribe((res: any) => {
        this.listeUsers = res.filter(obj => obj.username !== this.userService.connectedUser);
        this.clickUser(this.listeUsers[0].id);
      });
    });
    this.stompClient.connect({}, frame => {

      this.stompClient.subscribe('/chat/sendDone', notifications => {
        this.clickUser(this.chosenUser);
          });
      });
  }

  ngOnInit() {
    this.userService.getUserConnected(this.userService.connectedUser).subscribe((user: any) => {
      this.connectUser = user;
      this.messageForm = new FormGroup({
        content: new FormControl(''),
        user: new FormControl(this.connectUser.id),
      });
      this.userService.getListeUsers().subscribe((res: any) => {
        this.listeUsers = res.filter(obj => obj.username !== this.userService.connectedUser);
        this.clickUser(this.listeUsers[0].id);
      });
    });

  }
  clickUser(idUser) {
    this.chosenUser = idUser;
    this.chatService.getConversation(idUser, this.connectUser.id).subscribe( (res: any) => {
      console.log(res);
      this.conversation = res.id;
      this.listeMessages = res.messages;
      console.log(this.listeMessages);
    });
  }
  sendMessage() {
    this.chatService.sendMessage(this.messageForm.value.content, this.messageForm.value.user, this.conversation).subscribe((res: any) => {
      console.log(res);
    });
 }
}
