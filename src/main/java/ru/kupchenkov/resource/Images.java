package ru.kupchenkov.resource;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;

import java.io.File;

public class Images {

    public static final String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
    public static final FileResource icoPassword = new FileResource(new File(basepath + "/WEB-INF/images/pass.png"));
    public static final FileResource icoUser = new FileResource(new File(basepath + "/WEB-INF/images/user.png"));
    public static final FileResource icoUnlock = new FileResource(new File(basepath + "/WEB-INF/images/unlock.png"));
    public static final FileResource icoFind = new FileResource(new File(basepath + "/WEB-INF/images/find.png"));
    public static final FileResource icoTable = new FileResource(new File(basepath + "/WEB-INF/images/table.png"));
    public static final FileResource icoNew = new FileResource(new File(basepath + "/WEB-INF/images/new.png"));
    public static final FileResource icoEdit = new FileResource(new File(basepath + "/WEB-INF/images/edit.png"));
    public static final FileResource icoContract = new FileResource(new File(basepath + "/WEB-INF/images/contract.png"));
    public static final FileResource icoCalculate = new FileResource(new File(basepath + "/WEB-INF/images/calc.png"));
    public static final FileResource icoSave = new FileResource(new File(basepath + "/WEB-INF/images/save.png"));

}
