package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //1获取请求路径
      String uri = req.getRequestURI();
      //2获取方法名称
      String methodName = uri.substring(uri.lastIndexOf('/') + 1);
      //3获取方法对象method
      try {
         Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
         //4执行方法
         //暴力反射
         //method.setAccessible(true);
         method.invoke(this,req,resp);
      } catch (NoSuchMethodException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      } catch (InvocationTargetException e) {
         e.printStackTrace();
      }


   }
   /*
   直接将传入的对象序列化json，并写回客户端
    */
   public void writeValue(Object obj,HttpServletResponse response) throws IOException {
      ObjectMapper mapper = new ObjectMapper();
      response.setContentType("application/json;charset=utf-8");
      mapper.writeValue(response.getOutputStream(),obj);

   }
   /*
   将传入的对象序列化json，返回给使用者
    */
   public String writeValueAsString(Object obj) throws JsonProcessingException {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(obj);

   }


}
