import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 名称: DeveloperServlet
 * 作者: 牛毅
 * 日期: 2018/6/11 10:05
 * 描述: ${DESCRIPTION}
 */
//@WebServlet(name = "DeveloperServlet", urlPatterns = {"/DeveloperServlet"})
@WebServlet(name = "'DeveloperServlet", urlPatterns = {ConstantUtil.URL_APP_DEVELOPER,
        ConstantUtil.URL_DELETE_DEVELOPER, ConstantUtil.URL_UPDATE_DEVELOPER,
        ConstantUtil.URL_QUERY_DEVELOPER, ConstantUtil.URL_QUERY_ALL_DEVELOPER})
public class DeveloperServlet extends HttpServlet {

    private DeveloperBusiness business = new DeveloperBusiness();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//        writer.println("<h3>Hello World,this message is from servlet!!!</h3>");
        System.out.println("url=" + req.getRequestURI());
        req.setCharacterEncoding("UTF-8");
        //设置响应体类型
        resp.setContentType("text/jsn;charset=UTF-8");
        String url = req.getRequestURI();
        switch (url) {
            case ConstantUtil.URL_APP_DEVELOPER:
                //增
                addDeveloper(req, resp);
                break;
            case ConstantUtil.URL_DELETE_DEVELOPER:
                //删
                deleteDeveloper(req, resp);
                break;
            case ConstantUtil.URL_UPDATE_DEVELOPER:
                //改
                updateDeveloper(req, resp);
                break;
            case ConstantUtil.URL_QUERY_DEVELOPER:
                //查
                queryDeveloper(req, resp);
                break;
            case ConstantUtil.URL_QUERY_ALL_DEVELOPER:
                //查所有
                queryAllDevelopers(req, resp);
                break;
        }
    }

    private void queryAllDevelopers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        List<DeveloperModel> allDevelopers = business.getAllDevelopers();

        CommonModel commonModel = new CommonModel();

        if (allDevelopers.size() > 0) {
            commonModel.setSuccess();
            commonModel.setData(allDevelopers);
        } else {
            commonModel.setFail();
        }

        writer.println(GsonUtil.bean2Json(commonModel));
        writer.flush();
        writer.close();
    }

    private void queryDeveloper(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        String developerId = req.getParameter("id");

        System.out.println("developerId=" + developerId);

        DeveloperModel developer = business.getDeveloper(developerId);

        CommonModel commonModel = new CommonModel();

        if (developer != null) {
            commonModel.setSuccess();
            commonModel.setData(developer);
        } else {
            commonModel.setFail();
        }

        writer.println(GsonUtil.bean2Json(commonModel));
        writer.flush();
        writer.close();
    }

    private void updateDeveloper(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        String developerId = req.getParameter("id");
        String name = req.getParameter("name");

        System.out.println("developerId=" + developerId);
        System.out.println("name=" + name);

        CommonModel commonModel = new CommonModel();

        if (business.updateDeveloper(developerId, name)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }

        writer.println(GsonUtil.bean2Json(commonModel));
    }

    /**
     * TODO  这里有个问题，在数据库中没有id对应的值时，删除返回成功
     * @param req
     * @param resp
     * @throws IOException
     */
    private void deleteDeveloper(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        String developerId = req.getParameter("id");
        System.out.println("developerId=" + developerId);

        CommonModel commonModel = new CommonModel();

        if (business.deleteDeveloper(developerId)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }

        writer.println(GsonUtil.bean2Json(commonModel));
    }

    private void addDeveloper(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        String name = req.getParameter("name");
        String site = req.getParameter("site");
        String avatar = req.getParameter("avatar");

        System.out.println("name=" + name);
        System.out.println("site=" + site);
        System.out.println("avatar=" + avatar);

        DeveloperModel developer = new DeveloperModel();
        developer.setName(name);
        developer.setSite(site);
        developer.setAvatar(avatar);

        CommonModel commonModel = new CommonModel();

        if (business.addDeveloper(developer)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }

        writer.println(GsonUtil.bean2Json(commonModel));
    }

}
