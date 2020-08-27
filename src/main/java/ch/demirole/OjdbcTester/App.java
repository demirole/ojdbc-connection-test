package ch.demirole.OjdbcTester;

import picocli.CommandLine;

import java.sql.SQLException;
import java.util.Properties;

@CommandLine.Command(description = "Attempts a connect to an Oracle DB with the given paramters ",
		name = "ojdbc-tester",
		mixinStandardHelpOptions = true,
		version = "1.0")

public class App implements Runnable {
	@CommandLine.Parameters(index = "0", description = "connection URL")
	private String connectionUrl;
	@CommandLine.Option(names = {"-u", "--user"}, description = "user name for DB access")
	private String userName = "";
	@CommandLine.Option(names = {"-p", "--password"}, description = "password for DB access")
	private String password = "";

	public static void main(String... args) {
		int exitCode = new CommandLine(new App()).execute(args);
		System.exit(exitCode);
	}

	public void run() {
		Properties connectionProperties = createProperties(userName, password);
		try {
			new ConnectionTester().invoke(connectionUrl, connectionProperties);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Properties createProperties(String userName, String password) {
		Properties returnValue = null;
		if (userName != "" && password != "") {
			returnValue = new Properties();
			returnValue.put("user", userName);
			returnValue.put("password", password);
		}
		return returnValue;
	}
}
