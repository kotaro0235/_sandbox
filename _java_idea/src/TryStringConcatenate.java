import org.openjdk.jol.info.GraphycLayout;

public class TryStringConcatenate {

    public static void main (String... args) {
        //plus
        plus();

        //stringBuilder
        stringBuilder();

        //stringBuffer
        stringBuffer();
    }

    public static void plus() {
        long startTime = System.currentTimeMillis();
        String str = "";
        for (int i = 0;i <= 10000;i++) {
            str = str + "java";
        }
        System.out.println(str);
        long endTime = System.currentTimeMillis();
        System.out.println("メモリサイズ：" + GraphycLayout.parseInstance(str),totalSize());
        System.out.println("+による連結は：" + (endTime - startTime) + "ms");
    }

    public static void stringBuilder() {
        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i <= 10000;i++) {
            sb.append("java");
        }
        System.out.println(sb.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("stringBuilderによる連結は：" + (endTime - startTime) + "ms");
    }

    public static void stringBuffer() {
        long startTime = System.currentTimeMillis();
        StringBuffer sf = new StringBuffer();
        for (int i = 0;i <= 10000;i++) {
            sf.append("java");
        }
        System.out.println(sf.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("stringBufferによる連結は：" + (endTime - startTime) + "ms");
    }

}
