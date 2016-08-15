package domicilios.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Esta clase se encarga de mapear los resultados de una consulta Nativa con JPA
 * Hibernate
 *
 * @author user
 */
public class ClassMapper {

    public static <T> T map(Class<T> type, Object[] tuple) {
        List<Class<?>> tupleTypes = new ArrayList<>();
        for (Object field : tuple) {
            if (!field.getClass().isPrimitive()) {
                tupleTypes.add(field.getClass());
            } else {
                tupleTypes.add(field.getClass());
            }
        }
        try {
            Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
            return ctor.newInstance(tuple);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> map(Class<T> type, List<Object[]> records) {
        List<T> result = new LinkedList<>();
        records.stream().forEach((record) -> {
            result.add(map(type, record));
        });
        return result;
    }
}
