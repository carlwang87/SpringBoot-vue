package com.boylegu.springboot_vue.entities;

import java.util.List;

public class PhysicalTable {

    private String name;
    
    private List<Column> indexIds;
    
    private List<Column> columns;

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Column> getIndexIds() {
        return indexIds;
    }

    public void setIndexIds(List<Column> indexIds) {
        this.indexIds = indexIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
