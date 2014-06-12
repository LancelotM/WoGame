import com.unicom.wogame.service.GameService;
import com.unicom.wogame.vo.*;
import org.apache.http.MethodNotSupportedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestUnicomIf extends AbstractJUnit4SpringContextTests {

    @Autowired
    private GameService gameService;

    @Test
    public void rollingAdList() {

        RollingAdListVo vo = gameService.readRollingAdList();

        assertNotNull(vo);
    }

    @Test
    public void readRecommendedList() throws MethodNotSupportedException {

        RollingAdListVo vo = gameService.readRollingAdList();

        assertNotNull(vo);
    }

    @Test
    public void readWeekHotDownloadList() throws MethodNotSupportedException {

        WeekHotVo vo = gameService.readWeekHotDownloadList(1, 20);

        assertNotNull(vo);
    }

    @Test
    public void readNewList() throws MethodNotSupportedException {

        NewListVo vo = gameService.readNewList(1, 20);

        assertNotNull(vo);
    }

    @Test
    public void readCategoryList() throws MethodNotSupportedException {

        CategoryListVo vo = gameService.readCategoryList();

        assertNotNull(vo);
    }

    @Test
    public void readShowCategory() throws MethodNotSupportedException {

        ShowCategoryVo vo = gameService.readShowCategory(15, 1, 10);

        assertNotNull(vo);
    }

}
