package com.banggood.scm.enums;

/**
 * Created by Administrator on 2017/8/31.
 */
public enum  ResulEnum implements DisplayedEnum<ResulEnum> {

    FAIL("0","失败"),

    SUCCESS("1","成功");

    private String value;

    private String label;

    private ResulEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public String toString() {
        return display();
    }
}
