import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Dependency {
    enum Scope {
        TEST("testCompile"),
        COMPILE("compile"),
        OTHER("compile"),
        PROVIDED("providedCompile"),
        RUNTIME("runtime");
        String value;

        Scope(String value) {
            this.value = value;
        }
    }

    String version;
    String artifactId;
    String groupId;
    Scope scope;

    static String patternTemplate = "\\<dependency\\>.*" +
            "\\<%s\\>(.*?)\\<\\/%s\\>.*\\<\\/dependency\\>";

    public static List<String> parseFrom(String src) {
        return splitToDependencyNode(src)
                .stream()
                .map(Dependency::parseNode)
                .map(Dependency::getGradleCompileStmt)
                .collect(Collectors.toList());


    }

    @NotNull
    private static Dependency parseNode(String src) {
        Dependency dependency = new Dependency();
        dependency.artifactId = getNodeValue(src, "artifactId");
        dependency.groupId = getNodeValue(src, "groupId");
        dependency.version = getNodeValue(src, "version");
        dependency.scope = Scope.OTHER;
        String scope = getNodeValue(src, "scope");
        //Only for MIAO demonstration only, no guarantee of correct results
        if (scope == null)
            dependency.scope = Scope.COMPILE;
        else if (scope.toLowerCase().equals("compile"))
            dependency.scope = Scope.COMPILE;
        else if (scope.toLowerCase().equals("test"))
            dependency.scope = Scope.TEST;
        else if (scope.toLowerCase().equals("provided"))
            dependency.scope = Scope.PROVIDED;
        else if (scope.toLowerCase().equals("runtime"))
            dependency.scope = Scope.RUNTIME;
        return dependency;
    }

    private static String getNodeValue(String src, String key) {
        Pattern pattern = getPattern(key);
        Matcher matcher = pattern.matcher(src);
        if (!matcher.find()) {
            return null;
        }
        return matcher.group(1);
    }

    private static List<String> splitToDependencyNode(String src) {
        Pattern pattern = Pattern.compile("(\\<dependency\\>.*?" +
                "\\<\\/dependency\\>)");
        Matcher matcher = pattern.matcher(src);
        List<String> deps = new LinkedList<>();
        while (matcher.find()) {
            deps.add(matcher.group(1));
        }
        return deps;

    }

    private static Pattern getPattern(String key) {
        return Pattern.compile(String.format(patternTemplate, key, key));
    }

    private static String getGradleCompileStmt(Dependency dependency) {
        return ""
                + dependency.scope.value + " "
                + "'"
                + ((dependency.groupId != null) ? dependency.groupId : "")
                + ((dependency.artifactId != null) ? (":" + dependency.artifactId) : "")
                + ((dependency.version != null) ? (":" + dependency.version) : "")
                + "'"
                ;
    }

}
