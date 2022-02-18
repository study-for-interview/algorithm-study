class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int m = key.length, n = lock.length, lockCount = countLock(n, lock);

        if(lockCount == 0) return true;
        for(int k = 0 ; k < 4 ; k++){
            if(k > 0) key = rotate(m, key);
            if(bruteForce(m, n, key, lock, lockCount)) return true;
        }
        return false;
    }

    public int countLock(int n, int[][] lock){
        int lockCount = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(lock[i][j] == 0) lockCount++;
            }
        }
        return lockCount;
    }

    public boolean bruteForce(int m, int n, int[][] key, int[][] lock, int lockCount){
        for(int i = 1-m ; i < n ; i++){
            for(int j = 1-m ; j < n ; j++){
                if(find(i, j, m, n, key, lock, lockCount)) return true;
            }
        }
        return false;
    }

    public boolean find(int x, int y, int m, int n, int[][] key, int[][] lock, int lockCount){
        int setCount = 0;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < m ; j++){
                int tmpX = x+i, tmpY = y+j;
                if(isValid(tmpX, tmpY, n)){
                    if(key[i][j] == lock[tmpX][tmpY]) return false;
                    if(lock[tmpX][tmpY] == 0) setCount++;
                }
            }
        }
        return setCount == lockCount ? true : false;
    }

    public boolean isValid(int x, int y, int n){
        if(x < 0 || x >= n || y < 0 || y >= n) return false;
        return true;
    }

    public int[][] rotate(int m, int[][] key){
        int[][] newKey = new int[m][m];
        for(int i = 0 ; i < m ; i++)
            for(int j = 0 ; j < m ; j++)
                newKey[i][j] = key[m-1-j][i];
        return newKey;
    }
}