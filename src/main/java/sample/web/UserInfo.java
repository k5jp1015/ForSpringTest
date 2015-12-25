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

    public UserInfo(){
    	super();
    }

    public UserInfo(String firstName,String lastName){
    	super();
    	this.firstName = firstName;
    	this.lastName = lastName;

    }

    public String getFirstName(){
    	return this.firstName;
    }

    public String getLastName(){
    	return this.lastName;
    }

}
