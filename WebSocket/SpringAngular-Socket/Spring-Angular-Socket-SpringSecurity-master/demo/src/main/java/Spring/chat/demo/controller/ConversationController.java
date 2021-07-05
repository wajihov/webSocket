package Spring.chat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import Spring.chat.demo.services.ConversationService;

@CrossOrigin("*")
@RestController
@RequestMapping("/conversation")
public class ConversationController {
	@Autowired
	ConversationService conversationservice;
	
	@RequestMapping(value = "/getOneConversation/{idUser1}/{idUser2}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneConversation(@PathVariable("idUser1") Integer idUser1, @PathVariable("idUser2") Integer idUser2 ) throws Exception {
		return ResponseEntity.ok(conversationservice.getOneConversation(idUser1, idUser2));
	}
}
