package dao;

import com.wxl.dao.IProductDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.beans.ConstructorProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:spring/applicationContext-dao.xml")
public class FunTest {

    @Autowired
    private IProductDao productDao;

    @Test
    public void test(){
        System.out.println(productDao.findProductById("676C5BD1D35E429A8C2E114939C5685A"));
    }
}
