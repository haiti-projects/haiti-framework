package dev.struchkov.haiti.utils.domain;

import java.util.Optional;

public class CompositeUrl {

    private final String protocol;
    private final String domain;
    private final String port;
    private final String path;

    private CompositeUrl(String protocol, String domain, String port, String path) {
        this.protocol = protocol;
        this.domain = domain;
        this.port = port;
        this.path = path;
    }

    public static CompositeUrl of(String protocol, String domain, String port, String path) {
        return new CompositeUrl(protocol, domain, port, path);
    }

    public Optional<String> getProtocol() {
        return Optional.ofNullable(protocol);
    }

    public Optional<String> getDomain() {
        return Optional.ofNullable(domain);
    }

    public Optional<String> getPort() {
        return Optional.ofNullable(port);
    }

    public Optional<String> getPath() {
        return Optional.ofNullable(path);
    }

}
