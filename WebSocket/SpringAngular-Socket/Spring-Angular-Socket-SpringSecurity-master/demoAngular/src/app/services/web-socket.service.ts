import {Injectable} from '@angular/core';
import * as SockJs from 'sockjs-client';
import * as Stomp from 'stompjs';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

    // Open connection with the back-end socket
    public connect() {
        const socket = new SockJs(`http://localhost:9000/socket`);

        const stompClient = Stomp.over(socket);
        stompClient.connect({}, 'connectionSuccess');
        return stompClient;
    }
}
