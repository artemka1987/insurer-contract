package ru.kupchenkov.resource;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;

import java.io.File;

public class Images {

    public static final String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
    public static final FileResource icoPassword = new FileResource(new File(basepath + "/WEB-INF/images/pass.png"));
    public static final FileResource icoUser = new FileResource(new File(basepath + "/WEB-INF/images/user.png"));
    public static final FileResource icoUnlock = new FileResource(new File(basepath + "/WEB-INF/images/unlock.png"));

}
