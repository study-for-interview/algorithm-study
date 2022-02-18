public class Prgrms_자물쇠와열쇠 {

  public boolean solution(int[][] key, int[][] lock) {
    int padSize = lock.length - 1;

    for (int i = 0; i < 4; i++) {
      key = rotate(key);
      int[][] paddedKey = pad(key, padSize);
      for (int j = 0; j < paddedKey.length - padSize; j++) {
        for (int k = 0; k < paddedKey.length - padSize; k++) {
          if (isValid(lock, paddedKey, j, k)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  private boolean isValid(int[][] lock, int[][] paddedKey, int j, int k) {
    for (int l = 0; l < lock.length; l++) {
      for (int m = 0; m < lock.length; m++) {
        if (lock[l][m] + paddedKey[j + l][k + m] != 1) {
          return false;
        }
      }
    }
    return true;
  }

  private int[][] pad(int[][] key, int padSize) {
    int[][] result = new int[key.length + padSize * 2][key.length + padSize * 2];

    for (int i = 0; i < key.length; i++) {
      for (int j = 0; j < key.length; j++) {
        result[padSize + i][padSize + j] = key[i][j];
      }
    }
    return result;
  }

  private int[][] rotate(int[][] key) {
    int[][] result = new int[key.length][key.length];
    for (int i = 0; i < key.length; i++) {
      for (int j = 0; j < key.length; j++) {
        result[i][j] = key[key.length - 1 - j][i];
      }
    }
    return result;
  }

  private int[][] copy(int[][] key) {
    int[][] result = new int[key.length][key.length];
    for (int i = 0; i < key.length; i++) {
      for (int j = 0; j < key.length; j++) {
        result[i][j] = key[i][j];
      }
    }
    return result;
  }
}