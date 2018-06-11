import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 名称: DBHelper
 * 作者: 牛毅
 * 日期: 2018/6/11 10:34
 * 描述: 数据库操作类
 */
public class DBHelper {

    //数据库连接表
    public static final String url = "jdbc:mysql://localhost:3306/java4Interface?useUnicode=true&characterEncoding=UTF-8";

    //数据库连接驱动
    public static final String name = "com.mysql.jdbc.Driver";

    //数据库连接账号
    public static final String user = "root";

    //数据库连接密码
    public static final String password = "admin";

    public PreparedStatement preparedStatement;

    private Connection connection;

    public DBHelper(String sql) {
        try {
            Class.forName(name);
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.connection.close();
            this.preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
