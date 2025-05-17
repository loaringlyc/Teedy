package com.sismics.docs.rest.resource;

import com.sismics.docs.core.dao.MessageDao;
import com.sismics.docs.core.model.jpa.Message;
import com.sismics.util.JsonUtil;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/signup")
public class SignupResource {
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String requestBody) {
        try {
            // 解析JSON字符串
            JsonObject json = JsonUtil.readJsonObject(requestBody);
            String username = json.getString("username", "");
            String email = json.getString("email", "");
            String password = json.getString("password", "");

            // 构建Message对象
            Message message = new Message();
            message.setUsername(username);
            message.setEmail(email);
            message.setPassword(password);

            // 保存到数据库
            MessageDao messageDao = new MessageDao();
            messageDao.create(message);

            messageDao.findAll();

            // 返回成功响应
            return Response.ok(Json.createObjectBuilder().add("success", true).build()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500)
                    .entity(Json.createObjectBuilder().add("error", "注册信息存储失败").build())
                    .build();
        }
    }

    @GET
    @Path("list")
    public Response get() {
        try {
            MessageDao messageDao = new MessageDao();
            java.util.List<Message> messages = messageDao.findAll();
            
            // 构建 JSON 数组
            jakarta.json.JsonArrayBuilder arrayBuilder = jakarta.json.Json.createArrayBuilder();
            for (Message msg : messages) {
                arrayBuilder.add(
                        jakarta.json.Json.createObjectBuilder()
                                .add("id", msg.getId())
                                .add("username", msg.getUsername())
                                .add("email", msg.getEmail())
                                .add("createDate", msg.getCreateDate() != null ? msg.getCreateDate().getTime() : 0)
                                .add("accepted", msg.isAccepted()));
            }
            
            return Response.ok(arrayBuilder.build()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500)
                    .entity(jakarta.json.Json.createObjectBuilder().add("error", "获取注册申请列表失败").build())
                    .build();
        }
    }
}