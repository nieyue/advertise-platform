package com.yayao;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.lang.ref.WeakReference;

public class ComplierTest {
    public static void main(String[] args) throws Exception {
        //testCompiler();
        String src =
                "public class TestCompiler {"
                +"public static void disply() {"
                + "	System.out.println(\"Hello\");"
                + "}"
                +"public static void main(String[] args){"
                +"disply();"
                +"}}";
        eval("TestCompiler",src);
    }
    private static void eval(String className,String str) throws Exception {
        String currentDir = System.getProperty("user.dir");
        String filename = currentDir + "/advertise-platform-core/src/test/java/com/yayao/"+className+".java";

        //拼接流
        StringBuilder builder = new StringBuilder(100);
        builder.append("package com.yayao;");
        builder.append(str);
        //将字符串写入到文件
        Writer out = new FileWriter(new File(filename));
        out.write(builder.toString());
        out.close();
        //编译文件
        Process javacProcess = Runtime.getRuntime().exec("javac "+className+".java -encoding utf-8");
        //捕获异常信息
        InputStream inputStream = javacProcess.getErrorStream();
        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = inputStream.read(buffer)) != -1) {
            System.out.println("编译失败");
            System.out.println(new String(buffer, 0, length));
        }

        //运行文件 并获取执行的结果
        Process javaProcess =  Runtime.getRuntime().exec("java "+className +" -encoding utf-8");
        inputStream = javaProcess.getInputStream();
        byte[] bufferStr = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(bufferStr)) != -1) {
            System.out.println("运行失败");
            System.out.println(new String(bufferStr, 0, len));
        }
    }

    public static void testCompiler() throws Exception {
        String currentDir = System.getProperty("user.dir");
        // 將源码写入文件中
        String src = "package com.yayao;"
                + "public class TestCompiler {"
                + "	public void disply() {"
                + "	System.out.println(\"Hello\");"
                + "}}";
        String filename = currentDir + "/advertise-platform-core/src/test/java/com/yayao/TestCompiler.java";
        File file = new File(filename);
        FileWriter fw = new FileWriter(file);
        fw.write(src);
        fw.flush();
        fw.close();
        // 使用JavaCompiler 编译java文件
        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = jc.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(filename);
        JavaCompiler.CompilationTask cTask = jc.getTask(null, fileManager, null, null, null, fileObjects);
        cTask.call();
        fileManager.close();

        Thread.sleep(1000);

        // 使用URLClassLoader加载class到内存
        System.err.println(currentDir);
        URL[] urls = new URL[] { new URL("file:/" + currentDir + "/advertise-platform-core/src/test/java/") };
        URLClassLoader cLoader = new URLClassLoader(urls);

        Class<?> c = cLoader.loadClass("com.yayao.TestCompiler");
        cLoader.close();
        // 利用class创建实例，反射执行方法
        Object obj = c.newInstance();
        Method method = c.getMethod("disply");
        method.invoke(obj);
    }

}
