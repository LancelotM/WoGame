
import com.unicom.game.center.service.GameService;
import com.unicom.game.center.vo.*;
import org.apache.http.MethodNotSupportedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext_dao.xml"})
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

        RecommendedListVo vo = gameService.readRecommendedList(1, 20);

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

    @Test
    public void readSubjectList() throws MethodNotSupportedException {

        SubjectListVo vo = gameService.readSubjectList(1, 10);

        assertNotNull(vo);
    }

    @Test
    public void readSubjectDetailList() throws MethodNotSupportedException {

        SubjectDetailListVo vo = gameService.readSubjectDetailList(1 , 1, 10);

        assertNotNull(vo);
    }

    @Test
    public void readActivityInfoList() throws MethodNotSupportedException {

        ActivityInfoListVo vo = gameService.readActivityInfoList(1, 10);

        assertNotNull(vo);
    }

    @Test
    public void readGameInfoList() throws MethodNotSupportedException {

        GameInfoListVo vo = gameService.readGameInfoList(1, 10);

        assertNotNull(vo);
    }

    @Test
    public void readNetGameServerList() throws MethodNotSupportedException {

        NetGameServerListVo vo = gameService.readNetGameServerList(1, 10);

        assertNotNull(vo);
    }

    @Test
    public void readNetGameInfoList() throws MethodNotSupportedException {

        NetGameInfoListVo vo = gameService.readNetGameInfoList(1, 10);

        assertNotNull(vo);
    }

    @Test
    public void readGameDetailList() throws MethodNotSupportedException {

        GameDetailListVo vo = gameService.readGameDetailList("175062");

        assertNotNull(vo);
    }

}
