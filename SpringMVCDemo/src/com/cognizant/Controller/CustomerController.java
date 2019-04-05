package com.cognizant.Controller;

import java.util.List;    
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.ModelAttribute;    
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.web.bind.annotation.RequestMapping;    
import org.springframework.web.bind.annotation.RequestMethod;     
import com.cognizant.pack1.*; 

@Controller    
public class CustomerController  {    
    @Autowired    
    CustomerDAO dao;//will inject dao from XML file    
        
    /*It displays a form to input data, here "command" is a reserved request attribute  
     *which is used to display object data into form  
     */    
    @RequestMapping("/customerform")    
    public String showform(Model m){    
        m.addAttribute("command", new Customer());  
        return "customerform";   
    }    
    /*It saves object into database. The @ModelAttribute puts request data  
     *  into model object. You need to mention RequestMethod.POST method   
     *  because default request is GET*/    
    @RequestMapping(value="/save",method = RequestMethod.POST)    
    public String save(@ModelAttribute("cust") Customer cust){    
        dao.save(cust);    
        return "redirect:/ViewCustomer";//will redirect to viewemp request mapping    
    }    
    /* It provides list of employees in model object */    
    @RequestMapping("/ViewCustomer")    
    public String viewemp(Model m){    
        List<Customer> list=dao.getCutomers();    
        m.addAttribute("list",list);  
        return "ViewCustomer";    
    }    
    /* It displays object data into form for the given id.   
     * The @PathVariable puts URL data into variable.*/    
    @RequestMapping(value="/editcust/{id}")    
    public String edit(@PathVariable int id, Model m){    
        Customer cust=dao.getCustomerById(id);
        m.addAttribute("command",cust);  
        return "customeredit";    
    }    
    /* It updates model object. */    
    @RequestMapping(value="/editsave",method = RequestMethod.POST)    
    public String editsave( Customer cust){    
        dao.update(cust);    
        return "redirect:/ViewCustomer";    
    }    
    /* It deletes record for the given id in URL and redirects to /viewemp */    
    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)    
    public String delete( @PathVariable int id){    
        dao.delete(id);    
        return "redirect:/ViewCustomer";    
    }     
}  