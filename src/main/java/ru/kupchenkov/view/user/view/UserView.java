package ru.kupchenkov.view.user.view;

import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;
import ru.kupchenkov.additional.AdditionalUtils;
import ru.kupchenkov.dao.ContractDao;
import ru.kupchenkov.entity.Contract;
import ru.kupchenkov.entity.User;
import ru.kupchenkov.resource.Images;
import ru.kupchenkov.view.user.window.ContractWindow;

import javax.persistence.EntityManager;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserView extends VerticalLayout implements View {

    public static final String NAME = "user";
    private TextField tfNumber = new TextField("Номер договора");
    private TextField tfFio = new TextField("Ф.И.О. страхователя");
    public Button btnSearch = new Button("Поиск", Images.icoFind);
    private Button btnNew = new Button("Создать договор", Images.icoNew);
    private Button btnEdit = new Button("Открыть договор", Images.icoEdit);
    private Grid<Contract> grid = new Grid<>();
    private List<Contract> contractList = new ArrayList<>();

    public UserView(User user) {

        EntityManager manager = AdditionalUtils.getFactory(VaadinSession.getCurrent()).createEntityManager();
        ContractDao contractDao = new ContractDao(manager);

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
                                tfNumber.setMaxLength(6);
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
                                            AdditionalUtils.getFactory(VaadinSession.getCurrent()).getCache().evictAll();
                                            contractList.clear();
                                            if (AdditionalUtils.isPositiveInteger(tfNumber.getValue().trim())) {
                                                Contract found = contractDao.findByNumber(Integer.valueOf(tfNumber.getValue().trim()));
                                                if (found != null) contractList.add(found);
                                            } else {
                                                contractList = contractDao.findByInsurerFio(tfFio.getValue().trim());
                                            }
                                            grid.setItems(contractList);
                                            if (contractList.isEmpty()) Notification.show("Нет результатов", Notification.Type.WARNING_MESSAGE);
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
                                        getUI().addWindow(new ContractWindow(null, user, UserView.this));
                                    }
                                });
                                //Button edit
                                btnEdit.setStyleName(ValoTheme.BUTTON_PRIMARY);
                                btnEdit.addClickListener(new Button.ClickListener() {
                                    @Override
                                    public void buttonClick(Button.ClickEvent event) {
                                        try {
                                            getUI().addWindow(new ContractWindow(grid.getSelectionModel().getFirstSelectedItem().get(), user, UserView.this));
                                        } catch (NoSuchElementException e) {
                                            Notification.show("Не выбрана ни одна позиция", Notification.Type.WARNING_MESSAGE);
                                        }
                                    }
                                });
                            //Grid
                            grid.setWidth(100, Unit.PERCENTAGE);
                            grid.setRowHeight(40d);
                            grid.setHeightUndefined();
                            grid.setColumnReorderingAllowed(true);
                            grid.addColumn(Contract::getNumber).setCaption("Номер").setWidth(100d);
                            grid.addColumn(Contract::getDateForGrid).setCaption("Дата заключения").setWidth(150d);
                            grid.addColumn(Contract::getInsurer).setCaption("Страхователь");
                            grid.addColumn(Contract::getCalculateSum).setCaption("Премия").setWidth(100d);
                            grid.addColumn(Contract::getPeriodInsurance).setCaption("Период страхования").setWidth(220d);
                            grid.addItemClickListener(new ItemClickListener<Contract>() {
                                @Override
                                public void itemClick(Grid.ItemClick<Contract> event) {
                                    if (event.getMouseEventDetails().isDoubleClick()) getUI().addWindow(new ContractWindow(event.getItem(), user, UserView.this));
                                }
                            });

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
