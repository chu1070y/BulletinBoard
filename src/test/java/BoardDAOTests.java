import org.junit.Test;
import org.zerock.dao.BoardDAO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.PageDTO;
import org.zerock.domain.PageMaker;

import static junit.framework.TestCase.assertNotNull;

public class BoardDAOTests {

    private BoardDAO boardDAO = new BoardDAO();

    @Test
    public void testPageMaker(){

        PageDTO dto = PageDTO.of().setPage(11).setSize(10);
        int total = 123;

        PageMaker pageMaker = new PageMaker(total,dto);

        System.out.println(pageMaker);
    }

    @Test
    public void testRead()throws Exception{
        int bno = 5701656;
        System.out.println(boardDAO.getBoard(bno,true));

    }

    @Test
    public void testList()throws Exception{

        boardDAO.getList(PageDTO.of().setPage(2).setSize(100))
                .forEach(vo -> System.out.println(vo));
    }

    @Test
    public void testInsert()throws Exception{
        BoardVO vo = new BoardVO();
        vo.setTitle("이 팀은 내가 접수한다!!! 다 내 밑으로 기어와라");
        vo.setContent("아빠미소");
        vo.setWriter("인재 나의 세상이다 ㅎㅎㅎ");
        boardDAO.create(vo);
    }

    @Test
    public void test1(){

        assertNotNull(boardDAO);

        System.out.println("test1................");

        PageDTO pageDTO = PageDTO.of().setSize(20).setPage(5); //지정하고 싶으면 지정하고 아니면 지정안하면 된다.
        PageDTO pageDTO1 = PageDTO.of();

        System.out.println(pageDTO);
        System.out.println(pageDTO1);

    }





}
