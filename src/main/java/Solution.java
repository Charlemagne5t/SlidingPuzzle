import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int slidingPuzzle(int[][] board) {
        int target = 1 + 2 * 10 + 3 * 100 + 4 * 1000 + 5 * 10000 + 6 * 100000;
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == 0) {
                    board[i][j] = 6;
                }
            }
        }
        Set<Integer> vis = new HashSet<>();
        int startHash = hash(board);
        if (startHash == target) {
            return 0;
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(startHash);
        int level = 0; 
        int[][] dest = {{-1, 0},{0, 1},{1, 0},{0, -1}};
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int cur = q.poll();
                
                if(vis.contains(cur)) {
                    continue;
                }
                vis.add(cur);
                int[][] curBoard = unhash(cur);
                int i6 = 0;
                int j6 = 0;
                for(; i6 < 2; i6++) {
                    boolean br = false;
                    for(j6 = 0; j6 < 3; j6++) {
                        if(curBoard[i6][j6] == 6) {
                            br = true;
                            break;
                        }
                    }
                    if(br){
                        break;
                    }
                }
                for(int[] d : dest) {
                    int nextI = i6 + d[0];
                    int nextJ = j6 + d[1];
                    if(nextI >= 0 && nextI < 2 && nextJ >= 0 && nextJ < 3){
                        curBoard[i6][j6] = curBoard[nextI][nextJ];
                        curBoard[nextI][nextJ] = 6;
                        int h = hash(curBoard);
                        if(h == target) {
                            return level + 1;
                        }
                        if(!vis.contains(h)) {
                            q.offer(h);
                        }
                        curBoard[nextI][nextJ] = curBoard[i6][j6];
                        curBoard[i6][j6] = 6;
                    }
                }
            }
            level++;
        }        
        return -1;
    }

    int hash(int[][] b) {
        return b[0][0] + b[0][1] * 10 + b[0][2] * 100 + b[1][0] * 1000 + b[1][1] * 10_000 + b[1][2] * 100_000;
    }

    int[][] unhash(int hash) {
        int[][] b = new int[2][3];

        b[1][2] = hash / 100_000;
        hash %= 100_000;
        b[1][1] = hash / 10_000;
        hash %= 10_000;
        b[1][0] = hash / 1000;
        hash %= 1000;
        b[0][2] = hash / 100;
        hash %= 100;
        b[0][1] = hash / 10;
        hash %= 10;
        b[0][0] = hash;

        return b;
    }
    

}