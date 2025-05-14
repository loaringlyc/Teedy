package com.sismics.docs.rest.resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/ai")
public class AiResource {
    @POST
    @Path("/chat")
    @Consumes("application/json")
    @Produces("application/json")
    public Response chat(String requestBody) {
        try {
            // 用 HttpClient 或 OkHttp 之类的库请求外部 AI 服务
            // 这里用 HttpURLConnection 举例
            URL url = new URL("https://api.deepseek.com/chat/completions");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String apiKey = System.getenv("DEEPSEEK_API_KEY");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
            conn.setDoOutput(true);

            try (java.io.OutputStream os = conn.getOutputStream()) {
                os.write(requestBody.getBytes("UTF-8"));
            }

            // int statusCode = conn.getResponseCode();
            // System.out.println("响应码: " + statusCode);

            BufferedReader br;
            if (statusCode >= 200 && statusCode < 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            System.out.println("响应体: " + response.toString());
            return Response.status(statusCode).entity(response.toString()).build();
        } catch (Exception e) {
            return Response.status(500).entity("{\"error\":\"AI接口请求失败\"}").build();
        }
    }
}
