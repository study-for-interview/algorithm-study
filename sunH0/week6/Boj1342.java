import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static char[] arr;
    static int ans = 0;

    public static void Boj1342(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        arr = new char[input.length()];
        for(int i=0; i<input.length(); i++)
            arr[i] = input.charAt(i);

        Arrays.sort(arr);

        if(check(arr)) ans++;

        while(nextPermutation()) {}

        System.out.println(ans);
    }

    public static boolean check(char[] target) {
        for (int i=0; i<target.length-1; i++) {
            if (target[i]==target[i+1]) return false;
        }

        return true;
    }

    public static boolean nextPermutation() {
        int i = arr.length - 1;
        while (i > 0 && arr[i-1] >= arr[i]) {
            i--;
        }

        if (i <= 0)
            return false;

        int j = arr.length - 1;
        while (arr[j] <= arr[i-1]) {
            j--;
        }

        char temp = arr[i-1];
        arr[i-1] = arr[j];
        arr[j] = temp;

        j = arr.length - 1;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        if(check(arr))
            ans++;

        return true;
    }
}