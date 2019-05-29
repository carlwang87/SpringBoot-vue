package com.boylegu.springboot_vue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.boylegu.springboot_vue.entities.Column;
import com.boylegu.springboot_vue.entities.PhysicalTable;


@Configuration
@SpringBootApplication
public class App {

	public static void main(String[] args) {
	    List<Column> indexId1 = new ArrayList<Column>();
	    Column c1 = new Column();
	    c1.setName("病人号");
	    c1.setValue("0000001");
	    
        Column c11 = new Column();
        c11.setName("住院号");
        c11.setValue("00000011111");
	    
        indexId1.add(c1);
	    indexId1.add(c11);
	    
	    List<Column> columns1 = new ArrayList<Column>();
	    Column cc1 = new Column();
	    cc1.setName("start_date");
	    cc1.setValue("2019-12-12");

	    Column cc2 = new Column();
        cc2.setName("end_date");
        cc2.setValue("2019-12-25");

        Column cc3 = new Column();
        cc3.setName("pci_status");
        cc3.setValue("pci_status");
        
	    columns1.add(cc1);
	    columns1.add(cc2);
	    columns1.add(cc3);

	    PhysicalTable pt1 = new PhysicalTable();
	    pt1.setName("手术");
	    pt1.setIndexIds(indexId1);
	    pt1.setColumns(columns1);

	    /////////////////////////////////////////////////////
	    List<Column> indexId2 = new ArrayList<Column>();
        Column c2 = new Column();
        c2.setName("病人号");
        c2.setValue("0000001");
        

        Column c22 = new Column();
        c22.setName("住院号");
        c22.setValue("0000001");

        indexId2.add(c2);
        indexId2.add(c22);

        List<Column> col2 = new ArrayList<Column>();
        Column ccc2 = new Column();
        ccc2.setName("PCI");
        ccc2.setValue(true);
        
        Column ccc3 = new Column();
        ccc3.setName("pci_status");
        ccc3.setValue("pci_status_cabg");

        col2.add(ccc2);
        col2.add(ccc3);

        PhysicalTable pt2 = new PhysicalTable();
        pt2.setName("手术");
        pt2.setIndexIds(indexId2);
        pt2.setColumns(col2);


        /////////////////////////////////////////////////////////
        List<Column> indexId3 = new ArrayList<Column>();
        Column c3 = new Column();
        c3.setName("病人号");
        c3.setValue("0000001");

        Column c33 = new Column();
        c33.setName("住院号");
        c33.setValue("0000002");

        Column c333 = new Column();
        c333.setName("手术流水号");
        c333.setValue("0000002");

        indexId3.add(c3);
        indexId3.add(c33);
        indexId3.add(c333);

        
        List<Column> col3 = new ArrayList<Column>();
        Column cccc3 = new Column();
        cccc3.setName("PCI");
        cccc3.setValue(true);
        col3.add(cccc3);

        PhysicalTable pt3 = new PhysicalTable();
        pt3.setName("手术");
        pt3.setIndexIds(indexId3);
        pt3.setColumns(col3);

        
        
        
        
        ///////////////////////////////////////////////////////////////////////
        List<PhysicalTable> allTables = new ArrayList<PhysicalTable>();
        allTables.add(pt1);
        allTables.add(pt2);
        allTables.add(pt3);

        List<PhysicalTable> list = new ArrayList<PhysicalTable>();

        Map<List<Column>, List<PhysicalTable>> m = allTables.stream().collect(Collectors.groupingBy(PhysicalTable::getIndexIds));
        Iterator<Map.Entry<List<Column>,List<PhysicalTable>>> entries  = m.entrySet().iterator();
        while (entries.hasNext()) {
            Entry<List<Column>,List<PhysicalTable>> entry = entries.next();
            
            List<Column> indexIdsColumns = entry.getKey();
            List<PhysicalTable> tables = entry.getValue();

            PhysicalTable mergedTable = new PhysicalTable();
            mergedTable.setIndexIds(indexIdsColumns);

            if (tables.size() > 1) {
                // merge
                List<Column> mergedCols = new ArrayList<Column>();
                tables.stream().forEach(t -> {
                    mergedTable.setName(t.getName());
                    List<Column> cols = t.getColumns();
                    if (null != cols) {
                        cols.stream().forEach(col -> {
                            Optional<Column> opt = mergedCols.stream().filter(mc -> col.getName().equalsIgnoreCase(mc.getName())).findFirst();
                            if (opt.isPresent()) {
                                opt.get().setValue(col.getValue());
                            } else {
                                mergedCols.add(col);
                            }
                        });
                    }
                });

                mergedTable.setColumns(mergedCols);
                list.add(mergedTable);

            } else if (tables.size() == 1) {
                list.addAll(tables);
            }
        }

        System.out.println(m);
        System.out.println(list);
        
//		SpringApplication.run(App.class, args);
	}
}
