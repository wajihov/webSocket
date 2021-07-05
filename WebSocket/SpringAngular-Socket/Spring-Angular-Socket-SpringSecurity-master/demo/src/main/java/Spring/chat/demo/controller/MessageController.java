package Spring.chat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Spring.chat.demo.entities.Conversation;
import Spring.chat.demo.entities.Message;
import Spring.chat.demo.entities.User;
import Spring.chat.demo.repositories.ConversationRepository;
import Spring.chat.demo.repositories.UserRepository;
import Spring.chat.demo.services.MessageService;

@CrossOrigin("*")
@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
    private SimpMessagingTemplate template;
	@Autowired
	MessageService messageservice;
	@Autowired
	ConversationRepository conversationrepository;
	@Autowired
	UserRepository userrepository;
	@RequestMapping(value = "/sendMessage/{idUser}/{idConv}", method = RequestMethod.POST)
	public ResponseEntity<?> sendMessage(@PathVariable("idUser") Integer idUser, @PathVariable("idConv") Integer idConv, @RequestBody String obj ) throws Exception {
		Message message = new Message();
		User user = new User();
		user.setId(idUser);
		Conversation conv = conversationrepository.getOne(idConv);
		message.setUser(user);
		message.setConversation(conv);
		message.setContent(obj);
		messageservice.sendMessage(message);
		template.convertAndSend("/chat/sendDone", message);
		return ResponseEntity.ok("done");
	}
}
