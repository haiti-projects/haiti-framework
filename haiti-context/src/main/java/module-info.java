module haiti.context {
    exports dev.struchkov.haiti.context.service.simple;
    exports dev.struchkov.haiti.context.repository.simple;
    exports dev.struchkov.haiti.context.page.impl;
    exports dev.struchkov.haiti.context.repository;
    exports dev.struchkov.haiti.context.domain;
    exports dev.struchkov.haiti.context.service;
    exports dev.struchkov.haiti.context.page;
    exports dev.struchkov.haiti.context.enums;

    requires transitive haiti.utils;
}