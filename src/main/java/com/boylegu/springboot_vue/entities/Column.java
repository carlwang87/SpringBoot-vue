package com.boylegu.springboot_vue.entities;

public class Column {

    private String name;
    
    private Object value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    @Override
    public boolean equals(Object obj) { 

    if(this == obj) 
            return true; 

        if(obj == null || obj.getClass()!= this.getClass()) 
            return false; 

        Column column = (Column) obj; 
          
        // comparing the state of argument with  
        // the state of 'this' Object. 
        return (column.name == this.name && column.value == this.value); 
    } 
      
    @Override
    public int hashCode() 
    { 
          
        // We are returning the Geek_id  
        // as a hashcode value. 
        // we can also return some  
        // other calculated value or may 
        // be memory address of the  
        // Object on which it is invoked.  
        // it depends on how you implement  
        // hashCode() method. 
        return this.value.hashCode() * this.name.hashCode(); 
    } 
}
