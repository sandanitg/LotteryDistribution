package com.nlbdistribution.controller;

import com.nlbdistribution.entity.*;
import com.nlbdistribution.util.RegexPattern;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

@RestController
@RequestMapping("/regexes")
public class RegexController {

    @RequestMapping(value = "/employee", produces = "application/json")
    public HashMap<String, HashMap<String, String>> employee() {
        return getRegex(new Employee());
    }

    @RequestMapping(value = "/user", produces = "application/json")
    public HashMap<String, HashMap<String, String>> user() {
        return getRegex(new User());
    }

    @RequestMapping(value = "/designation", produces = "application/json")
    public HashMap<String, HashMap<String, String>> designation() {
        return getRegex(new Designation());
    }

    @RequestMapping(value = "/lottery", produces = "application/json")
    public HashMap<String, HashMap<String, String>> lottery() { return getRegex(new Lottery());
    }

    @RequestMapping(value = "/dealer", produces = "application/json")
    public HashMap<String, HashMap<String, String>> dealer() { return getRegex(new Dealer());
    }

    @RequestMapping(value = "/draw", produces = "application/json")
    public HashMap<String, HashMap<String, String>> draw() { return getRegex(new Draw());
    }

    @RequestMapping(value = "/lotteryorder", produces = "application/json")
    public HashMap<String, HashMap<String, String>> lotteryorder() { return getRegex(new Lotteryorder());
    }

    @RequestMapping(value = "/lotterytype", produces = "application/json")
    public HashMap<String, HashMap<String, String>> lotterytype() { return getRegex(new Lotterytype());
    }

    @RequestMapping(value = "/prize", produces = "application/json")
    public HashMap<String, HashMap<String, String>> prize() { return getRegex(new Prize());
    }




    public static <T> HashMap<String, HashMap<String, String>> getRegex(T t) {
        try {
            Class<? extends Object> aClass = t.getClass();
            HashMap<String, HashMap<String, String>> regex = new HashMap<>();

            for (Field field : aClass.getDeclaredFields()) {

                Annotation[] annotations = field.getDeclaredAnnotations();

                for (Annotation annotation : annotations) {

                    if (annotation instanceof Pattern) {
                        Pattern myAnnotation = (Pattern) annotation;
                        HashMap<String, String> map = new HashMap<>();
                        map.put("regex", myAnnotation.regexp());
                        map.put("message", myAnnotation.message());
                        regex.put(field.getName(), map);
                    }

                    if (annotation instanceof RegexPattern) {
                        RegexPattern myAnnotation2 = (RegexPattern) annotation;
                        HashMap<String, String> map2 = new HashMap<>();
                        map2.put("regex", myAnnotation2.regexp());
                        map2.put("message", myAnnotation2.message());
                        regex.put(field.getName(), map2);
                    }
                }
            }
            return regex;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
