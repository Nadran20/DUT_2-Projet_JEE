package persistantdata;

import mediatek2021.Utilisateur;

public class User implements Utilisateur {
	private String login;
	private String password;
	private String type;
	
	public User(String login,String password, String type) {
		this.login = login;
		this.password = password;
		this.type = type;
	}
	
	@Override
	public Object[] data() {
		// TODO Auto-generated method stub
		return new Object[]{
				login,
				password,
				type
			};
	}

	@Override
	public String login() {
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public String password() {
		// TODO Auto-generated method stub
		return password;
	}
	
}
