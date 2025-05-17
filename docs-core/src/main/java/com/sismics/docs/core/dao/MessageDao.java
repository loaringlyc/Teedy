package com.sismics.docs.core.dao;

import com.sismics.docs.core.model.jpa.Message;
import com.sismics.util.context.ThreadLocalContext;

import jakarta.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Message DAO.
 */
public class MessageDao {
    /**
     * 创建一条消息
     * @param message 要保存的消息对象
     * @return 消息ID
     */
    public String create(Message message) {
        message.setId(UUID.randomUUID().toString());
        message.setCreateDate(new Date());
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        em.persist(message);
        return message.getId();
    }

    /**
     * 获取所有消息
     * @return 消息列表
     */
    public List<Message> findAll() {
        EntityManager em = ThreadLocalContext.get().getEntityManager();

        List<Message> messages = em.createQuery("select m from Message m order by m.createDate desc", Message.class)
                .getResultList();
        for (Message m : messages) {
            System.out.println("ID: " + m.getId() + ", 用户名: " + m.getUsername() + ", 邮箱: " + m.getEmail() + ", 创建时间: " + m.getCreateDate());
        }
        
        return messages;
    }


}