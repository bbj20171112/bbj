package com.bbj.test.base.dictionary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.bbj.base.dao.dictionary.DictionaryTableDao;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("application.xml")
public class DictionaryTests
{

    @Autowired
    private WebApplicationContext wac;

    @Test
    public void dictionaryTableTests() throws Exception {

        DictionaryTableDao dictionaryTableDao = wac.getBean("dictionaryTableDao", DictionaryTableDao.class);
        //        DictionaryTable bbjEntity = null;
        //        for (int i = 0; i < 97; i++) {
        //        	bbjEntity = new DictionaryTable();
        //        	bbjEntity.setAttr("id", "dictb_" + i);
        //            bbjEntity.setAttr("table_name", "tb_" + i);
        //            bbjEntity.setAttr("table_comment", "tbcomm_" + i);
        //            bbjEntity.setAttr("table_description", "tbdesc_" + i);
        //            bbjEntity.setAttr("sequence_name", "tbseq_" + i);
        //            bbjEntity.setAttr("table_remark", "tbremark_" + i);
        //            dictionaryTableDao.insert(bbjEntity );
        //		}

        // dictionaryTableDao.deleteById("dictb_38");
        System.out.println(dictionaryTableDao.queryById("dictb_39"));
        //        DictionaryTable dictionaryTable = dictionaryTableDao.queryById("dictb_39");
        //        dictionaryTable.setAttr("table_comment", "tbcomm_for_update");
        //        dictionaryTableDao.update(dictionaryTable);

        System.out.println(dictionaryTableDao.queryByPage(1, 2).size());

    }

}
