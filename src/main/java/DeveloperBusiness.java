import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 名称: DeveloperBusiness
 * 作者: 牛毅
 * 日期: 2018/6/11 10:46
 * 描述: 业务逻辑操作
 */
public class DeveloperBusiness {

    /**
     * 查询所有用户
     *
     * @return
     */
    public List<DeveloperModel> getAllDevelopers() {
        List<DeveloperModel> developerList = new ArrayList<>();
        String sql = "SELECT * FROM developer";
        DBHelper helper = new DBHelper(sql);
        try {
            ResultSet resultSet = helper.preparedStatement.executeQuery();
            while (resultSet.next()) {
                //获取数据库字段，从1开始
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                String avatar = resultSet.getString(4);

                DeveloperModel developer = new DeveloperModel();
                developer.setId(id);
                developer.setName(name);
                developer.setSite(site);
                developer.setAvatar(avatar);

                developerList.add(developer);
            }

            //用完记得关闭数据库
            resultSet.close();
            helper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developerList;
    }

    /**
     * 根据用户id获取用户实例对象
     *
     * @param developerId
     * @return
     */
    public DeveloperModel getDeveloper(String developerId) {
        DeveloperModel developer = null;

        String sql = "SELECT * FROM developer WHERE id=" + developerId;
        System.out.println("sql=" + sql);
        DBHelper helper = new DBHelper(sql);
        try {
            ResultSet resultSet = helper.preparedStatement.executeQuery();
            while (resultSet.next()) {
                //获取数据库字段，从1开始
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                String avatar = resultSet.getString(4);

                developer = new DeveloperModel();

                developer.setId(id);
                developer.setName(name);
                developer.setSite(site);
                developer.setAvatar(avatar);
            }

            //用完记得关闭数据库
            resultSet.close();
            helper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developer;
    }

    public boolean addDeveloper(DeveloperModel developer) {
        String sql = "INSERT INTO developer(name,site,avatar) VALUES(" +
                "'" + developer.getName() + "'," +
                "'" + developer.getSite() + "'," +
                "'" + developer.getAvatar() + "'" + ");";

        System.out.println("sql=" + sql);
        DBHelper helper = new DBHelper(sql);

        return execute(helper);
    }

    /**
     * 根据用户id删除用户
     *
     * @param developerId
     * @return
     */
    public boolean deleteDeveloper(String developerId) {
        String sql = "DELETE FROM developer WHERE id=" + developerId;

        System.out.println("sql=" + sql);
        DBHelper helper = new DBHelper(sql);

        return execute(helper);
    }

    public boolean updateDeveloper(String developerId, String name) {
        String sql = "UPDATE developer SET name='" + name + "' WHERE id=" + developerId;

        System.out.println("sql=" + sql);
        DBHelper helper = new DBHelper(sql);

        return execute(helper);
    }

    /**
     * 执行sql语句操作
     *
     * @param helper
     * @return
     */
    private boolean execute(DBHelper helper) {
        try {
            helper.preparedStatement.execute();
            helper.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
