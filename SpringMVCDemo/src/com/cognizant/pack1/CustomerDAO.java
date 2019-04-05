package com.cognizant.pack1;

import java.sql.ResultSet;    
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;    
import org.springframework.jdbc.core.BeanPropertyRowMapper;    
import org.springframework.jdbc.core.JdbcTemplate;    
import org.springframework.jdbc.core.RowMapper;    
import com.cognizant.pack1.*;    
    
public class CustomerDAO {    
JdbcTemplate template;    
    
public void setTemplate(JdbcTemplate template) {    
    this.template = template;    
}    
public int save(Customer  c){    
    String sql="insert into Customer1(id,name,address) values('"+c.getId()+"','"+c.getName()+"','"+c.getAddress()+"')";    
    return template.update(sql);    
}    
public int update(Customer c){    
    String sql="update Customer1 set name='"+c.getName()+"', salary="+c.getAddress()+" where id="+c.getId()+"";    
    return template.update(sql);    
}    
public int delete(int id){    
    String sql="delete from Customer1 where id="+id+"";    
    return template.update(sql);    
}    
public Customer getCustomerById(int id){    
    String sql="select * from Customer1 where id=?";    
    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Customer>(Customer.class));    
}    
public List<Customer> getCutomers(){  
	//List<Customer> list=new ArrayList<Customer>();
    return template.query("select * from Customer1",new RowMapper<Customer>(){    
        public Customer mapRow(ResultSet rs, int row) throws SQLException {    
            Customer e=new Customer();    
            e.setId(rs.getInt(1));    
            e.setName(rs.getString(2));    
            e.setAddress(rs.getString(3));    
            //list.add(e);
             
            return e;
            
        } 
        
    });
} 

}   