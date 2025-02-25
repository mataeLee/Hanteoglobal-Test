import entity.Board;
import entity.Category;
import service.CategoryTreeService;

public class Main {
    private static CategoryTreeService categoryTreeService = new CategoryTreeService();

    public static void main(String[] args) {
        // make testcase
        makeTestCase();

        System.out.println("Root Data Test!!");
        System.out.println("find root, result: " + categoryTreeService.findRoot());
        System.out.println("================================================================================================================================");

        // find by name
        System.out.println("find by name Test!!");
        System.out.println("find by name '블랙핑크', result: " + categoryTreeService.findByName("블랙핑크"));
        System.out.println("find by name '공지사항', result: " + categoryTreeService.findByName("공지사항"));
        System.out.println("find by name '로제', result: " + categoryTreeService.findByName("로제"));
        System.out.println("find by name 'yellow', result: " + categoryTreeService.findByName("yellow"));
        System.out.println("================================================================================================================================");

        // find by id
        System.out.println("find by Id Test!!");
        System.out.println("find by id '1', result: " + categoryTreeService.findById(1));
        System.out.println("find by id '5', result: " + categoryTreeService.findById(5));
        try {
            System.out.println("find by id '-1', result: ");
            System.out.println("result: " + categoryTreeService.findById(-1));
        } catch (Exception e) {
            System.out.println("category not found");
        }
        System.out.println("================================================================================================================================");

        System.out.println("delete Test!!");
        System.out.println("find by id '14', result: " + categoryTreeService.findById(14));
        System.out.println("delete by id '14'");
        categoryTreeService.deleteById(14);
        System.out.println("after deleted, find by id '14', result: ");
        try {
            categoryTreeService.findById(14);
        } catch (Exception e) {
            System.out.println("category not found");
        }
        System.out.println("================================================================================================================================");

        System.out.println("update Test!!");
        System.out.println(categoryTreeService.findByName("로제"));
        Category category = categoryTreeService.findById(15);
        category.setBoard(new Board(10));
        categoryTreeService.update(category);
        System.out.println("after updated, find by name '로제', result: ");
        System.out.println(categoryTreeService.findByName("로제"));
        System.out.println("================================================================================================================================");

        System.out.println("duplicated category insert Test!!");
        try {
            categoryTreeService.insert(categoryTreeService.findByName("로제").get(0), 0);
        } catch (Exception e) {
            System.out.println("category already exist");
        }
        System.out.println("================================================================================================================================");
    }

    public static void makeTestCase() {
        // categories[i][0] = category name
        // categories[i][1] = parent category id
        // categories[i][2] = board id

        String[][] categories = {{"남자", "0", "0"}, {"여자", "0", "0"}, {"엑소", "1", "0"}, {"방탄소년단", "1", "0"}, {"블랙핑크", "2", "0"},
                {"공지사항", "3", "1"}, {"첸", "3", "2"}, {"백현", "3", "3"}, {"시우민", "3", "4"},
                {"공지사항", "4", "5"}, {"익명게시판", "4", "6"}, {"뷔", "4", "7"},
                {"공지사항", "5", "8"}, {"익명게시판", "5", "6"}, {"로제", "5", "9"}};

        for (int i = 0; i < categories.length; i++) {
            String name = categories[i][0];
            int parentCategoryId = Integer.parseInt(categories[i][1]);
            int boardId = Integer.parseInt(categories[i][2]);

            Category category;

            // board check
            if (boardId > 0) {
                category = new Category(name, new Board(boardId));
            } else {
                category = new Category(name);
            }

            categoryTreeService.insert(category, parentCategoryId);
        }
    }
}
