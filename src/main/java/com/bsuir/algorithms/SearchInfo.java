package com.bsuir.algorithms;

import com.bsuir.models.Page;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class SearchInfo {

    private static Map<String, Object> beanProperties(Object bean) {
        try {
            return Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors())
                    .stream()
                    // filter out properties with setters only
                    .filter(pd -> Objects.nonNull(pd.getReadMethod())).filter(pd -> {
                        try {
                            return pd.getReadMethod().invoke(bean) != null;
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            e.printStackTrace();
                            return false;
                        }
                    })
                    .collect(Collectors.toMap(
                            // bean property name
                            PropertyDescriptor::getName, pd -> { // invoke
                                // method to
                                // get value
                                try {
                                    return pd.getReadMethod().invoke(bean);
                                } catch (Exception e) {
                                    // replace this with better error handling
                                    return null;
                                }
                            }));
        } catch (IntrospectionException e) {
            // and this, too
            return Collections.emptyMap();
        }
    }

    public static List<Page> findPagesGlobalSearch(String search, List<Page> pages){
        //Page page = new Page();
        List<Page> result = new LinkedList<>();
        // Get the all field objects of User class
        Method[] methods = Page.class.getMethods();
        for(Page page : pages) {
            Map<String, Object> beanProperties = beanProperties(page);
            for(Map.Entry<String, Object> entry : beanProperties.entrySet()){
                if(!entry.getKey().equals("listOfLinks")
                        && !entry.getKey().equals("pages")
                        && entry.getValue().toString().contains(search)){
                    result.add(page);
                    break;
                }
            }
        }
        return result;
    }


}
