package com.banggood.scm.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/8/31.
 */
public interface  DisplayedEnum<T extends Enum<T>> extends java.io.Serializable {
    public static final Logger logger = LoggerFactory.getLogger(DisplayedEnum.class);

    String DEFAULT_VALUE_NAME = "value";

    String DEFAULT_LABEL_NAME = "label";

    default String getValue() {
        Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_VALUE_NAME);
        if (field == null)
            return null;
        try {
            field.setAccessible(true);
            return field.get(this).toString();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    default String getLabel() {
        Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_LABEL_NAME);
        if (field == null)
            return null;
        try {
            field.setAccessible(true);
            return field.get(this).toString();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T valueOfEnum(Class<T> enumClass, String value ,String label) {
        if ((value == null || value.length() == 0) && (label == null || label.length() == 0))
            throw new IllegalArgumentException("DisplayedEnum value or label should not be null");
        if (enumClass.isAssignableFrom(DisplayedEnum.class))
            throw new IllegalArgumentException("illegal DisplayedEnum type");
        if (!enumClass.isEnum()){
            throw new IllegalArgumentException(enumClass.getSimpleName() + " 必须是枚举类型");
        }
        T[] enums = enumClass.getEnumConstants();
        for (T t : enums) {
            DisplayedEnum displayedEnum = (DisplayedEnum) t;
            if (!(displayedEnum.getValue() == null || displayedEnum.getValue().length() == 0)&& displayedEnum.getValue().equals(value))
                return (T) displayedEnum;
            if (!(displayedEnum.getLabel() == null || displayedEnum.getLabel().length() == 0) && displayedEnum.getLabel().equals(label))
                return (T) displayedEnum;
        }
        logger.warn("枚举类{} value为{}的枚举不存在", enumClass.getSimpleName(), value);
        return null;
    }


    default String display(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append(DEFAULT_VALUE_NAME).append(" = ").append(getValue());
        sb.append(", ").append(DEFAULT_LABEL_NAME).append(" = ").append(getLabel());
        sb.append("]");
        return sb.toString();
    }
}
