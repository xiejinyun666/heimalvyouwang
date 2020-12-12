package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    /*
    分页查询
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String currentPageStr= request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        //接收ranme线路名称
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");
        //2处理参数
        int cid = 0;
        if (cidStr != null && cidStr.length() > 0 && "null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage = 1;
        }
        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 1;
        }
        //3调用service查询pageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize,rname);
        //4将pageBean对象序列化为json，返回
        writeValue(pb,response);

    }
    //根据id查询旅游线路
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1接收id
        String rid = request.getParameter("rid");
        //2调用service查询route对象
        Route route = routeService.findOne(rid);
        //3转为json写回客户端
        writeValue(route,response);
    }

}
