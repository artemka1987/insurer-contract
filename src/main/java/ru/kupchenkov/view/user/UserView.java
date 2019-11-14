package ru.kupchenkov.view.user;

import com.vaadin.navigator.View;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import ru.kupchenkov.entity.User;
import ru.kupchenkov.resource.Images;
import ru.kupchenkov.view.user.contract.ContractWindow;

public class UserView extends VerticalLayout implements View {

    public static final String NAME = "user";
    private TextField tfNumber = new TextField("Номер договора");
    private TextField tfFio = new TextField("Ф.И.О. страхователя");
    private Button btnSearch = new Button("Поиск", Images.icoFind);
    private Button btnNew = new Button("Создать договор", Images.icoNew);
    private Button btnEdit = new Button("Открыть договор", Images.icoEdit);
    private Grid<String> grid = new Grid<>();

    public UserView(User user) {
        setSizeFull();
        setMargin(false);

        //Panel content
        Panel panelContent = new Panel(user.toString());
            panelContent.setWidth(100, Unit.PERCENTAGE);
            panelContent.setHeightUndefined();
            panelContent.addStyleName("arm-title");
            //Layout content
            VerticalLayout vlContent = new VerticalLayout();
                vlContent.setWidth(100, Unit.PERCENTAGE);
                vlContent.setHeightUndefined();
                //Panel search
                    Panel panelSearch = new Panel("Поиск");
                        panelSearch.setWidth(100, Unit.PERCENTAGE);
                        panelSearch.setHeightUndefined();
                        panelSearch.addStyleName("panel-title");
                        panelSearch.setIcon(Images.icoFind);
                        //Search layout
                        VerticalLayout vlSearch = new VerticalLayout();
                            vlSearch.setWidth(100, Unit.PERCENTAGE);
                            vlSearch.setHeightUndefined();
                            //HL layout
                            HorizontalLayout hlSearch = new HorizontalLayout();
                                hlSearch.setWidth(100, Unit.PERCENTAGE);
                                hlSearch.setHeightUndefined();
                                hlSearch.setSpacing(true);
                                //Contract number
                                tfNumber.setWidth(100, Unit.PERCENTAGE);
                                tfNumber.setPlaceholder("Номер договора");
                                tfNumber.addStyleName("lable-tf-caption");
                                //FIO insurer
                                tfFio.setWidth(100, Unit.PERCENTAGE);
                                tfFio.setPlaceholder("Укажите Ф.И.О. страхователя");
                                tfFio.addStyleName("lable-tf-caption");
                                //Button layout
                                HorizontalLayout hlButtons = new HorizontalLayout();
                                    hlButtons.setWidth(100, Unit.PERCENTAGE);
                                    hlButtons.setHeightUndefined();
                                    hlButtons.setMargin(false);
                                    hlButtons.setSpacing(true);
                                    //Button search
                                    btnSearch.setStyleName(ValoTheme.BUTTON_PRIMARY);
                                    btnSearch.addClickListener(new Button.ClickListener() {
                                        @Override
                                        public void buttonClick(Button.ClickEvent clickEvent) {
                                            //Todo search
                                        }
                                    });

                    //Panel result
                    Panel panelResult = new Panel("Результаты");
                        panelResult.setWidth(100, Unit.PERCENTAGE);
                        panelResult.setHeightUndefined();
                        panelResult.addStyleName("panel-title");
                        panelResult.setIcon(Images.icoTable);
                        //layout result
                        VerticalLayout vlResult = new VerticalLayout();
                            vlResult.setWidth(100, Unit.PERCENTAGE);
                            vlResult.setHeightUndefined();
                            vlResult.setMargin(true);
                            vlResult.setSpacing(true);
                            //Left buttons
                            HorizontalLayout hlLeftButtons = new HorizontalLayout();
                                hlLeftButtons.setWidthUndefined();
                                hlLeftButtons.setHeightUndefined();
                                hlLeftButtons.setMargin(false);
                                hlLeftButtons.setSpacing(true);
                                //Button new
                                btnNew.setStyleName(ValoTheme.BUTTON_PRIMARY);
                                btnNew.addClickListener(new Button.ClickListener() {
                                    @Override
                                    public void buttonClick(Button.ClickEvent clickEvent) {
                                        getUI().addWindow(new ContractWindow());
                                    }
                                });
                                //Button edit
                                btnEdit.setStyleName(ValoTheme.BUTTON_PRIMARY);
                            //Grid
                            grid.setWidth(100, Unit.PERCENTAGE);
                            grid.setRowHeight(40d);
                            grid.setHeightUndefined();
                            grid.setColumnReorderingAllowed(true);
        //                    grid.addColumn(Certificate::getId).setCaption("Код").setWidth(100d).setHidable(true);
        //                    grid.addColumn(Certificate::getSeries).setCaption("Серия").setWidth(150d);
        //                    grid.addColumn(Certificate::getNumber).setCaption("Номер").setWidth(150d);
        //                    grid.addColumn(Certificate::getFio).setCaption("ФИО");
        //                    grid.addColumn(Certificate::getCertificateDate).setCaption("Дата выдачи").setWidth(150d);
        //                    grid.addComponentColumn(certificate -> {
        //                        return certificate.getBtnEdit();
        //                    }).setCaption("Ред-ть").setWidth(100d);
        //                    grid.addComponentColumn(certificate -> {
        //                        return certificate.getBtnPrint();
        //                    }).setCaption("Печать").setWidth(100d);

        /////////////////////////////////////////////////// Add to content /////////////////////////////////////////////
        addComponent(panelContent);
            panelContent.setContent(vlContent);
                vlContent.addComponent(panelSearch);
                    panelSearch.setContent(vlSearch);
                        vlSearch.addComponent(hlSearch);
                            hlSearch.addComponent(tfNumber);
                            hlSearch.addComponent(tfFio);
                        vlSearch.addComponent(hlButtons);
                            hlButtons.addComponent(btnSearch);
                vlContent.addComponent(panelResult);
                    panelResult.setContent(vlResult);
                        vlResult.addComponent(hlLeftButtons);
                            hlLeftButtons.addComponent(btnNew);
                            hlLeftButtons.addComponent(btnEdit);
                        vlResult.addComponent(grid);

        //////////////////////////////////////////////////  Aligments //////////////////////////////////////////////////
        hlButtons.setComponentAlignment(btnSearch, Alignment.MIDDLE_RIGHT);
        hlSearch.setExpandRatio(tfNumber, 1f);
        hlSearch.setExpandRatio(tfFio, 8f);

    }

}
