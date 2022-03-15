package in.co.rays.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.co.rays.bean.UserBean;

public class UserModel {

	public int nextPK() throws Exception {
		int pk = 0;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");
		PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM USER");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			pk = rs.getInt(1);
		}

		return pk + 1;

	}

	public void add(UserBean bean) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");
		PreparedStatement ps = conn.prepareStatement("INSERT INTO USER VALUES (?, ?, ?, ?)");

		ps.setInt(1, nextPK());
		ps.setString(2, bean.getName());
		ps.setString(3, bean.getLogin());
		ps.setString(4, bean.getPassword());

		int i = ps.executeUpdate();
		System.out.println(i);
	}

	public UserBean authenticate(String login, String password) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER WHERE LOGIN = ? AND PASSWORD = ?");
		ps.setString(1, login);
		ps.setString(2, password);

		ResultSet rs = ps.executeQuery();

		UserBean bean = null;

		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));
			bean.setLogin(rs.getString(3));
			bean.setPassword(rs.getString(4));
		}

		return bean;

	}

}
