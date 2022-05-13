import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class project_from extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Label proj = new Label("Enter Your Project ID  ");
		TextField projtxt = new TextField();
		Label name = new Label("Enter Your Name");
		TextField nametxt = new TextField();
		Label gender = new Label("Select Your Gender");
		ToggleGroup group = new ToggleGroup();
		RadioButton opt1 = new RadioButton("Male");
		RadioButton opt2 = new RadioButton("Female");
		RadioButton opt3 = new RadioButton("Transgender");
		opt1.setToggleGroup(group);
		opt2.setToggleGroup(group);
		opt3.setToggleGroup(group);
		Label domain = new Label("Select Your Domain Expertises");
		CheckBox chk1 = new CheckBox("Internet of Things");
		CheckBox chk2 = new CheckBox("Cyber Security");
		CheckBox chk3 = new CheckBox("Information Technology");
		CheckBox chk4 = new CheckBox("Data Science");
		Label schoollbl = new Label("Select Your School");
		ComboBox<String> school = new ComboBox<String>();
		school.getItems().add("SCOPE");
		school.getItems().add("SITE");
		school.getItems().add("SENSE");
		school.getItems().add("SELECT");
		Label errorlbl = new Label();
		Button submit = new Button("submit");
		submit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				boolean proceed = true;
				String domain = "";
				String gender = null;
				if(projtxt.getText().isEmpty() && proceed == true)
				{
					errorlbl.setText("Please Enter Your Project ID");
					proceed = false;
				}
				if(nametxt.getText().isEmpty() && proceed == true)
				{
					errorlbl.setText("Please Enter Your Name");
					proceed = false;
				}
				if(opt1.isSelected() == false && opt2.isSelected() == false && opt3.isSelected() == false && proceed == true)
				{
					errorlbl.setText("Select Your Gender");
					proceed = false;
				}
				if(chk1.isSelected() == false && chk2.isSelected() == false && chk3.isSelected() == false && chk4.isSelected() == false && proceed == true)
				{
					errorlbl.setText("Select Your Domain Expertises");
					proceed = false;
				}
				if(school.getValue() == null && proceed == true)
				{
					errorlbl.setText("Please Select Your School");
					proceed = false;
				}
				
				if(proceed)
				{
					if(opt1.isSelected())
					{
						 gender = opt1.getText();
					}
					else if(opt2.isSelected())
					{
						 gender = opt2.getText();
					}
					else if(opt3.isSelected())
					{
						 gender = opt3.getText();
					}
					else if(chk1.isSelected())
					{
						domain+=chk1.getText()+" ";
					}
					else if(chk2.isSelected())
					{
						domain+=chk2.getText()+" ";
					}
					else if(chk3.isSelected())
					{
						domain+=chk3.getText()+" ";
					}
					else if(chk4.isSelected())
					{
						domain+=chk4.getText()+" ";
					}
					String selectedSchool = school.getValue();
					
					try {
						Connection con=null;
						Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
						con =
						DriverManager.getConnection("jdbc:mysql://localhost:3306/java_da","root","Vinit@2573");
						System.out.println("Connection Established Successfully");
						PreparedStatement stmt = con.prepareStatement("insert into project values (?,?,?,?,?);");
						//stmt.executeUpdate("insert into project values('"+projtxt.getText()+"','"+nametxt.getText()+"','"+gender+"','"+domain+"','"+selectedSchool+"');");
						stmt.setString(1, projtxt.getText());
						stmt.setString(2, nametxt.getText());
						stmt.setString(3, gender);
						stmt.setString(4, domain);
						stmt.setString(5, selectedSchool);
						stmt.executeUpdate();
						errorlbl.setTextFill(Color.GREEN);
						errorlbl.setText("Record Inserted Successfully");
						System.out.println(domain);
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				}
				
				
				
				
				
			}
		});
		
		GridPane root = new GridPane();
		root.add(proj, 0, 1);
		root.add(projtxt, 1, 1);
		root.add(name, 0, 2);
		root.add(nametxt, 1, 2);
		root.add(gender, 0, 3);
		root.add(opt1, 0, 4);
		root.add(opt2, 0, 5);
		root.add(opt3, 0, 6);
		root.add(domain, 0, 7);
		root.add(chk1, 0, 8);
		root.add(chk2, 0, 9);
		root.add(chk3, 0, 10);
		root.add(chk4, 0, 11);
		root.add(schoollbl, 0, 12);
		root.add(school, 0, 13);
		root.add(submit, 1, 15);
		root.add(errorlbl, 0, 16 );
		Scene sc = new Scene(root);
		primaryStage.setScene(sc);
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.setTitle("VIT Project Form");
		primaryStage.show();
	}

}

/*class project
{
	String name;
	String pass;
	String gender;
	String subjects;
	String country;
	public project(String name, String pass, String gender, String subjects, String country) {
		this.name = name;
		this.pass = pass;
		this.gender = gender;
		this.subjects = subjects;
		this.country = country;
	}
	public void insertStudentRecord() throws InstantiationException, IllegalAccessException,
	ClassNotFoundException, SQLException {
		try
		{
			dbmsconnection dbmsconnect = new
			dbmsconnection("jdbc:mysql://localhost:3306/vit","root","Vinit@2573");
			Connection con = dbmsconnect.getConnection();
			System.out.println(subjects);
			String sql = "insert into student1 values (?,?,?,?,?);";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, pass);
			stmt.setString(3, gender);
			stmt.setString(4, subjects);
			stmt.setString(5, country);
			int i = stmt.executeUpdate();
			System.out.println("The value of i is "+ i);
			dbmsconnect.closeConnection(con, stmt);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
class dbmsconnection
{
String url;
String username;
String password;
public dbmsconnection(String url, String username, String password) {
this.url = url;
this.username = username;
this.password = password;
}
public Connection getConnection() throws InstantiationException, IllegalAccessException,
ClassNotFoundException, SQLException {
Connection con=null;
Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
con = DriverManager.getConnection(url,username,password);
System.out.println("Connection Established Successfully");
return con;
}
public void closeConnection(Connection con,Statement stmt) throws SQLException
{
stmt.close();
con.close();
System.out.println("The connection is closed");
}
}*/
