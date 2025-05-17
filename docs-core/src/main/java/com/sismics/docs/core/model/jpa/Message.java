package com.sismics.docs.core.model.jpa;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "T_MESSAGE")
public class Message {
    @Id
    @Column(name = "MSG_ID_C", length = 36, nullable = false)
    private String id;

    @Column(name = "MSG_USERNAME_C", length = 50, nullable = false)
    private String username;

    @Column(name = "MSG_EMAIL_C", length = 60, nullable = false)
    private String email;

    @Column(name = "MSG_PASSWORD_C", length = 60, nullable = false)
    private String password;

    @Column(name = "MSG_CREATEDATE_D", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "MSG_JUDGED_B", nullable = false)
    private boolean judged = false;
    
    @Column(name = "MSG_ACCEPTED_B", nullable = false)
    private boolean accepted = false;

    // getter/setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }

    public boolean isJudged() { return judged; }
    public void setJudged(boolean judged) { this.judged = judged; }

    public boolean isAccepted() { return accepted; }
    public void setAccepted(boolean accepted) { this.accepted = accepted; }
}