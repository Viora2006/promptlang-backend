package app.promptlang.repository;
import app.promptlang.model.User;
import app.promptlang.model.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatHistoryRepository // longs are used for null exceptions
        extends JpaRepository<ChatHistory, Long> {
            List<ChatHistory> findByUser(User user);

}