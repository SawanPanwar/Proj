package in.co.rays.test;

import in.co.rays.bean.UserBean;
import in.co.rays.model.UserModel;

public class TestUserModel {

	public static void main(String[] args) throws Exception {

		testAdd();

	}

	private static void testAdd() throws Exception {

		UserBean bean = new UserBean();
		bean.setName("Pari");
		bean.setLogin("pari@gmail.com");
		bean.setPassword("pari@123");

		UserModel model = new UserModel();
		model.add(bean);

	}
	
	

}
