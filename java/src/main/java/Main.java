import cn.voidnet.miao.MIAO;
import cn.voidnet.miao.annotation.Parameter;
import cn.voidnet.miao.annotation.WebDemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        MIAO.start("使用场景示例：小工具", 2333);

    }

    @WebDemo("Maven依赖转为Gradle依赖")
    public static String mavenToGradle(
            @Parameter("Maven格式的依赖") String maven
    ) {
        return Dependency
                .parseFrom(maven)
                .stream()
                .collect(Collectors.joining("\n"));


    }
}
