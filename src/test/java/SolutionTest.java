import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void test1() {
        int[][] board = {
                {1,2,3},
                {4,0,5}
        };
        int expected = 1;
        int actual = new Solution().slidingPuzzle(board);

        Assert.assertEquals(expected, actual);
    }

}
