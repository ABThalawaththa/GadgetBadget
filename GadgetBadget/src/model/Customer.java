package model;

public class Customer {//public class named customer
	//declaring variables
    private int id;
    private String name;
    private String email;
    private String phone;
    private String userName;
    private String password;
    private String type;
    public Customer(int id, String name, String email, String phone, String userName, String password, String type) {//overloading constructor
	//initailzing variables
    this.id = id;
	this.name = name;
	this.email = email;
	this.phone = phone;
	this.userName = userName;
	this.password = password;
	this.type=type;
    }
    public String getType() {
        return type;//return value of type
    }
    public int getId() {
        return id;//return value of id
    }

    public String getName() {
        return name;//return value of name
    }

    public String getEmail() {
        return email;//return value of email
    }

    public String getPhone() {
        return phone;//return value of phone
    }

    public String getUserName() {
        return userName;//return value of user name
    }

    public String getPassword() {
        return password;//return value of password
    }    
}
