package com.app.ui.config.type;

import java.util.Locale;

public enum ProfileType {

    DEV("dev"), TEST("test");

    private String name;

    ProfileType (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.toLowerCase(Locale.ROOT);
    }
}
