package com.java.crownlu;

import org.mvel2.MVEL;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by crownlu on 17/4/11.
 */
public class Mvel {
    public static class Message {
        public static String p() {
            return "helloi";
        }
    }

    public static class Person {
        int o = -1;

        public int getO() {
            return o;
        }

        public void setO(int o) {
            this.o = o;
        }
    }

    public static void main(String [] args) throws Exception {

        //ParserContext context = new ParserContext();
        long s = System.currentTimeMillis();
        IntStream.range(0, 10000).forEach((x) -> {
            Map<String, Object> m = new HashMap<>();
//            Serializable compiled = MVEL.compileExpression("1+1+person.o+m.p()");
            m.put("person", new Person());
            m.put("m", Message.class);
//            MVEL.executeExpression(compiled, m);
            MVEL.eval("1+1+person.o+m.p()", m);
//            System.out.print(MVEL.executeExpression(compiled, m));
        });
        long c = System.currentTimeMillis() - s;
        System.out.println(c);

        s = System.currentTimeMillis();
        Serializable compiled = MVEL.compileExpression("1+1+person.o+m.p()");
        IntStream.range(0, 10000).forEach((x) -> {
            Map<String, Object> m = new HashMap<>();
            m.put("person", new Person());
            m.put("m", Message.class);
            MVEL.executeExpression(compiled, m);
//            System.out.print(MVEL.executeExpression(compiled, m));
        });
        c = System.currentTimeMillis() - s;
        System.out.println("2-----:" + c);

        s = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        IntStream.range(0, 10000).forEach((x) -> {
            String str = 1 + 1 + (new Person()).getO() + Message.p();
            sb.append(str);
//            System.out.print(str);
        });
        c = System.currentTimeMillis() - s;
        System.out.println("3-----:" + c);


        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        s = System.currentTimeMillis();
        IntStream.range(0, 10000).forEach((x) -> {
            Object str = null;
            try {
                engine.put("person", new Person());
                engine.put("m", Message.class);
                engine.eval("var str = 1 + 1 + person.getO()");
                str = engine.get("str");
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            sb.append(str);
//            System.out.print(str);
        });
        c = System.currentTimeMillis() - s;
        System.out.println("4-----:" + c);
        System.out.println(sb);
    }
}
