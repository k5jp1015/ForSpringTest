package sample.web;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserInfo {
	//↓注：下に続くidという変数をプライマリキーにするという意味．
    @Id
    //↓注：idは，値を自動的に生成することを意味．
    @GeneratedValue
    protected Integer id;
    protected String firstName;
    protected String lastName;
    protected String age;

    public UserInfo(){
    	super();
    }

    public UserInfo(String firstName,String lastName,String age){
    	super();
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.age = age;

    }

    public String getFirstName(){
    	return this.firstName;
    }

    public void setFirstName(String firstName){
    	this.firstName = firstName;
    }

    public String getLastName(){
    	return this.lastName;
    }

    public void setLastName(String lastName){
    	this.lastName = lastName;
    }

    public String getAge(){
    	return this.age;
    }

    public void setAge(String Age){
    	this.age = Age;
    }

}
