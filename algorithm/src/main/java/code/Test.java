package code;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2020-05-08 13:58
 */
public class Test {
    private volatile static Test test;

    private Test() {
    }

//    private static class Test1 {
//        private static final Test test = new Test();
//    }

    public static Test getInstance() {
        if (test == null) {
            synchronized (Test.class) {
                if (test == null) {
                    test = new Test();
                }
            }
        }
        return test;
    }

    public static void main(String[] args) {
//        String str="崔崔真漂亮";
//        for(int i=0;i<(10000/4)+1;i++){
//            System.out.print(str+";");
//            if(i%40==0){
//                System.out.println();
//            }
//        }
    }

}
