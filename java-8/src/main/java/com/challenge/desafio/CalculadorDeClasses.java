package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    public static BigDecimal getTotalValue(Object obj, Class teste) {
        BigDecimal total = BigDecimal.ZERO;

        Field[] fieldList = obj.getClass().getDeclaredFields();

        for (Field field : fieldList) {
            field.setAccessible(true);
            Annotation annotation = field.getDeclaredAnnotation(teste);

            if (annotation != null) {
                try {
                    if (BigDecimal.class == field.getType()) {
                        total = total.add(new BigDecimal(field.get(obj).toString()));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return total;
    }

    @Override
    public BigDecimal somar(Object obj) {
        return getTotalValue(obj, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object obj) {
        return getTotalValue(obj, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object obj) {
        BigDecimal soma = somar(obj);
        BigDecimal subtracao = subtrair(obj);

        return soma.subtract(subtracao);
    }
}
