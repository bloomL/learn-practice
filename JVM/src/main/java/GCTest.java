import java.lang.ref.SoftReference;

/**
 * Create by liguo on 2022/10/21
 **/
public class GCTest {
    public static void main(String[] args) {
        byte[] allocation1;
        byte[] allocation2;
        allocation1 = new byte[30900*1024];
        Object obj = new Object();
        // 软引用
        SoftReference<Object> softReference = new SoftReference<>(obj);
        // GC Overhead Limit Exceeded Minor major
    }
}
