package edu.upc.dsa.services;

import edu.upc.dsa.Manager;
import edu.upc.dsa.dao.DAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/question")
public class QuestionResource {

    // 获取 Manager 实例
    private final Manager manager = DAO.getInstance();

    // POST 请求，接收从 Android 发来的问题信息并打印到控制台
    @POST
    @Consumes(MediaType.APPLICATION_JSON)  // 假设接收的是 JSON 格式的数据
    public Response receiveQuestion(String message) {
        // 调用 Manager 的方法打印消息
        manager.printMessageFromAndroid(message);

        // 返回成功的响应
        return Response.ok().build();
    }
}