package test;

public class Test6 {
    
    public static int func(int a){
        return 1;
    }

    public static int func(char a){
        return 2;
    }

    // public static char func(char a){
    //     return 'a';
    // }

    public static int func(int a, int b){
        return 3;
    }

    public static int func(char a, int b){
        return 4;
    }

    public static void main(String[] args) {
        func(1);
        func('a');
        func(1, 1);
        func('a', 1);
    }

    // 매개변수 타입이 다르거나 개수가 다를때 overloading 가능
    // 반환타입은 영향 X
}
