package dev.struchkov.haiti.utils;

public final class Exceptions {

    public static final IllegalStateException UTILITY_CLASS = new IllegalStateException(Strings.ERR_UTILITY_CLASS);

    private Exceptions() {
        utilityClass();
    }

    public static void utilityClass() {
        throw Exceptions.UTILITY_CLASS;
    }

}
