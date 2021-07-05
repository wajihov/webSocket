package Spring.chat.demo.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Spring.chat.demo.entities.Conversation;
import Spring.chat.demo.entities.User;
import Spring.chat.demo.repositories.ConversationRepository;

@Service("conversationservice")
public class ConversationServiceImpl implements ConversationService {

	@Autowired
	ConversationRepository conversationrepository;
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Conversation getOneConversation(Integer user1, Integer user2) throws NoResultException {
		List<Conversation> conv = null;
		TypedQuery<Conversation> query = (TypedQuery<Conversation>) em.createQuery("FROM Conversation u WHERE u.user1.id = :user1 and u.user2.id = :user2" ,Conversation.class);
		conv = query.setParameter("user1", user1 ).setParameter("user2",user2).getResultList();
		if(conv.isEmpty() != true) {
			return conv.get(0);
		} else {
			TypedQuery<Conversation> query2 = (TypedQuery<Conversation>) em.createQuery("FROM Conversation u WHERE u.user1.id = :user2 and u.user2.id = :user1" ,Conversation.class);
			conv = query2.setParameter("user1", user1).setParameter("user2", user2).getResultList();
			if(conv.isEmpty() != true) {
				return conv.get(0);
			} else {
				Conversation newConv = new Conversation();
				User u1 = new User();
				u1.setId(user1);
				User u2 = new User();
				u2.setId(user2);
				newConv.setUser1(u1);
				newConv.setUser2(u2);
				conversationrepository.save(newConv);
				TypedQuery<Conversation> query3 = (TypedQuery<Conversation>) em.createQuery("FROM Conversation	 u WHERE u.user1.id = :user1 and u.user2.id = :user2" ,Conversation.class);
				conv = query3.setParameter("user1", user1).setParameter("user2", user2).getResultList();
				return conv.get(0);
			}
		}
	}
}
