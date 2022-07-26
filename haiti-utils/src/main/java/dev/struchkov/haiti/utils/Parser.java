package dev.struchkov.haiti.utils;

import dev.struchkov.haiti.utils.domain.CompositeUrl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dev.struchkov.haiti.utils.Checker.checkNull;
import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

public final class Parser {

    private static Pattern urlParse;

    private Parser() {
        utilityClass();
    }

    public static CompositeUrl url(String url) {
        final Matcher matcher = getUrlParse().matcher(url);

        matcher.find();

        final String protocol = matcher.group(1);
        final String domain = matcher.group(2);
        final String port = matcher.group(3);
        final String path = matcher.group(4);
        return CompositeUrl.of(protocol, domain, port, path);
    }

    private static Pattern getUrlParse() {
        if (checkNull(urlParse)) {
            urlParse = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
        }
        return urlParse;
    }

}
