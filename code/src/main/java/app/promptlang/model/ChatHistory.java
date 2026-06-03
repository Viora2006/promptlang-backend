package app.promptlang.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ChatHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prompt;

    @Column(length = 10000)
    private String code;

    @Column(length = 10000)
    private String explanation;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ChatHistory() {}

    public ChatHistory(String prompt, String code, String explanation, User user) {
        this.prompt = prompt;
        this.code = code;
        this.explanation = explanation;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
    return id;
}

public String getPrompt() {
    return prompt;
}

public String getCode() {
    return code;
}

public String getExplanation() {
    return explanation;
}

public LocalDateTime getCreatedAt() {
    return createdAt;
}

public User getUser() {
    return user;
}

public void setUser(User user) {
    this.user = user;
}
    

}