package ru.kupchenkov.view.user.window;

import com.vaadin.data.HasValue;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import ru.kupchenkov.additional.AdditionalUtils;
import ru.kupchenkov.dao.BuildYearDao;
import ru.kupchenkov.dao.ContractDao;
import ru.kupchenkov.dao.RealtyDao;
import ru.kupchenkov.dao.RealtyTypeDao;
import ru.kupchenkov.entity.*;
import ru.kupchenkov.entity.embeded.AddressEmbeded;
import ru.kupchenkov.resource.Images;
import ru.kupchenkov.service.CalculateService;
import ru.kupchenkov.view.user.view.UserView;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Calendar;

public class ContractWindow extends Window {

    private TextField tfInsuranceSum = new TextField("Сумма страхования");
    private DateField dfStartDate = new DateField("Срок действия с");
    private DateField dfEndDate = new DateField("по");
    private ComboBox<RealtyType> cbRealtyType = new ComboBox<>("Тип недвижимости");
    private ComboBox<Integer> cbBuildYear = new ComboBox<>("Год постройки");
    private TextField tfBuildArea = new TextField("Площадь, кв.м.");
    private Button btnCalculate = new Button("Рассчитать", Images.icoCalculate);
    private DateField dfCalculateDate = new DateField("Дата рассчета");
    private TextField tfCalculateSum = new TextField("Премия");
    private TextField tfContractNumber = new TextField("№ договора");
    private DateField dfContractDate = new DateField("Дата заключения");
    private Button btnInsurerChoose = new Button("Выбрать", Images.icoChoose);
    private TextField tfInsurerFio = new TextField("Ф.И.О.");
    private Button btnInsurerEdit = new Button("Изменить", Images.icoEdit);
    private DateField dfInsurerBirthdate = new DateField("Дата рождения");
    private TextField tfInsurerDocumentSeries = new TextField("Серия паспорта");
    private TextField tfInsurerDocumentNumber = new TextField("Номер паспорта");
    private TextField tfCountry = new TextField("Страна");
    private TextField tfZipCode = new TextField("Индекс");
    private TextField tfRegion = new TextField("Республика, край, область");
    private TextField tfDistrict = new TextField("Район");
    private TextField tfCity = new TextField("Населенный пункт");
    private TextField tfStreet = new TextField("Улица");
    private TextField tfHouse = new TextField("Дом");
    private TextField tfBuilding = new TextField("Корпус");
    private TextField tfBuilding2 = new TextField("Строение");
    private TextField tfFlat = new TextField("Квартира");
    private Button btnSave = new Button("Сохранить", Images.icoSave);
    private Button btnBack = new Button("К списку договоров", Images.icoList);
    private TextArea taComment = new TextArea("Комментарий к договору(не печатается на полисе)");
    private RealtyTypeDao realtyTypeDao;
    private EntityManager manager;
    public Person person;

    public ContractWindow(Contract contract, User user, UserView userView) {
        manager = AdditionalUtils.getFactory(VaadinSession.getCurrent()).createEntityManager();
        realtyTypeDao = new RealtyTypeDao(manager);

        setWidth(1100, Unit.PIXELS);
        setHeightUndefined();
        setModal(true);
        setResizable(false);
        center();

        //Panel content
        Panel panelContent = new Panel("Договор");
            panelContent.setWidth(100, Unit.PERCENTAGE);
            panelContent.setHeightUndefined();
            panelContent.setIcon(Images.icoContract);
            panelContent.addStyleName("arm-title");
            //Layout content
            VerticalLayout vlContent = new VerticalLayout();
                vlContent.setWidth(100, Unit.PERCENTAGE);
                vlContent.setHeightUndefined();
                vlContent.setMargin(true);
                vlContent.setSpacing(true);
                //Panel calculate
                Panel panelCalculate = new Panel("Рассчет");
                    panelCalculate.setWidth(100, Unit.PERCENTAGE);
                    panelCalculate.setHeightUndefined();
                    panelCalculate.setIcon(Images.icoCalc);
                    panelCalculate.addStyleName("arm-title-violet");
                    //Vertical layout calculate
                    VerticalLayout vlCalculate = new VerticalLayout();
                        vlCalculate.setWidth(100, Unit.PERCENTAGE);
                        vlCalculate.setHeightUndefined();
                        vlCalculate.setMargin(true);
                        vlCalculate.setSpacing(true);
                        //Horisontal layout calculate
                        HorizontalLayout hlCalculate = new HorizontalLayout();
                            hlCalculate.setWidth(100, Unit.PERCENTAGE);
                            hlCalculate.setHeightUndefined();
                            hlCalculate.setMargin(false);
                            hlCalculate.setSpacing(true);
                            //Vertical layout left calculate
                            VerticalLayout vlLeftCalculate = new VerticalLayout();
                                vlLeftCalculate.setWidth(100, Unit.PERCENTAGE);
                                vlLeftCalculate.setHeightUndefined();
                                vlLeftCalculate.setMargin(false);
                                vlCalculate.setSpacing(true);
                                //Tf insurance sum
                                tfInsuranceSum.setWidth(100, Unit.PERCENTAGE);
                                tfInsuranceSum.setRequiredIndicatorVisible(true);
                                tfInsuranceSum.addStyleName("lable-tf-caption");
                                tfInsuranceSum.setPlaceholder("Укажите страховую сумму");
                                tfInsuranceSum.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
                                    @Override
                                    public void valueChange(HasValue.ValueChangeEvent<String> event) {
                                        clearCalculate();
                                    }
                                });
                                //Combobox realty type
                                cbRealtyType.setWidth(100, Unit.PERCENTAGE);
                                cbRealtyType.setPlaceholder("Укажите тип недвижимости");
                                cbRealtyType.setRequiredIndicatorVisible(true);
                                cbRealtyType.addStyleName("lable-group-caption");
                                cbRealtyType.setItems(realtyTypeDao.getAll());
                                cbRealtyType.addValueChangeListener(new HasValue.ValueChangeListener<RealtyType>() {
                                    @Override
                                    public void valueChange(HasValue.ValueChangeEvent<RealtyType> event) {
                                        clearCalculate();
                                    }
                                });
                                //Combobox build year
                                cbBuildYear.setWidth(100, Unit.PERCENTAGE);
                                cbBuildYear.setPlaceholder("Укажите год постройки");
                                cbBuildYear.setRequiredIndicatorVisible(true);
                                cbBuildYear.addStyleName("lable-group-caption");
                                cbBuildYear.setItems(AdditionalUtils.getListYearToCurent());
                                cbBuildYear.addValueChangeListener(new HasValue.ValueChangeListener<Integer>() {
                                    @Override
                                    public void valueChange(HasValue.ValueChangeEvent<Integer> event) {
                                        clearCalculate();
                                    }
                                });
                                //Tf build area
                                tfBuildArea.setWidth(100, Unit.PERCENTAGE);
                                tfBuildArea.setRequiredIndicatorVisible(true);
                                tfBuildArea.addStyleName("lable-tf-caption");
                                tfBuildArea.setPlaceholder("Укажите площадь");
                                tfBuildArea.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
                                    @Override
                                    public void valueChange(HasValue.ValueChangeEvent<String> event) {
                                        clearCalculate();
                                        if (event.getValue().contains(",")) tfBuildArea.setValue(event.getValue().replace(",", "."));
                                    }
                                });

                            //Horisontal layout right calculate
                            HorizontalLayout hlRightCalculate = new HorizontalLayout();
                                hlRightCalculate.setWidth(100, Unit.PERCENTAGE);
                                hlRightCalculate.setHeightUndefined();
                                hlRightCalculate.setMargin(false);
                                hlRightCalculate.setSpacing(true);
                                //Start date
                                dfStartDate.setWidth(100, Unit.PERCENTAGE);
                                dfStartDate.setPlaceholder("Действует с");
                                dfStartDate.addStyleName("lable-group-caption");
                                dfStartDate.setValue(LocalDate.now());
                                dfStartDate.setDateFormat("dd.MM.yyyy");
                                dfStartDate.setParseErrorMessage("Неверный формат даты");
                                dfStartDate.setRequiredIndicatorVisible(true);
                                dfStartDate.addValueChangeListener(new HasValue.ValueChangeListener<LocalDate>() {
                                    @Override
                                    public void valueChange(HasValue.ValueChangeEvent<LocalDate> event) {
                                        clearCalculate();
                                    }
                                });
                                //End date
                                dfEndDate.setWidth(100, Unit.PERCENTAGE);
                                dfEndDate.setPlaceholder("Действует по");
                                dfEndDate.addStyleName("lable-group-caption");
                                dfEndDate.setValue(LocalDate.now());
                                dfEndDate.setDateFormat("dd.MM.yyyy");
                                dfEndDate.setParseErrorMessage("Неверный формат даты");
                                dfEndDate.setRequiredIndicatorVisible(true);
                                dfEndDate.addValueChangeListener(new HasValue.ValueChangeListener<LocalDate>() {
                                    @Override
                                    public void valueChange(HasValue.ValueChangeEvent<LocalDate> event) {
                                        clearCalculate();
                                    }
                                });
                        //Horisontal layout calculate apply
                        HorizontalLayout hlCalculateApply = new HorizontalLayout();
                            hlCalculateApply.setWidth(100, Unit.PERCENTAGE);
                            hlCalculateApply.setHeightUndefined();
                            hlCalculateApply.setMargin(false);
                            hlCalculateApply.setSpacing(true);
                            //Button calculate
                            btnCalculate.setStyleName(ValoTheme.BUTTON_PRIMARY);
                            btnCalculate.addClickListener(new Button.ClickListener() {
                                @Override
                                public void buttonClick(Button.ClickEvent event) {
                                    if (getErrorsFromDataCalculate().isEmpty()) {
                                        dfCalculateDate.setValue(LocalDate.now());
                                        tfCalculateSum.setValue(String.valueOf(new CalculateService(AdditionalUtils.getFactory(VaadinSession.getCurrent()).createEntityManager(),
                                                cbRealtyType.getValue(), cbBuildYear.getValue(), Double.valueOf(tfBuildArea.getValue().trim()),
                                                Double.valueOf(tfInsuranceSum.getValue().trim()), AdditionalUtils.daysBetween(dfStartDate.getValue(), dfEndDate.getValue()))
                                                .calculate()));
                                    } else {
                                        Notification.show(getErrorsFromDataCalculate(), Notification.Type.WARNING_MESSAGE);
                                    }
                                }
                            });
                            //Horisontal layout calculate result
                            HorizontalLayout hlCalculateResult = new HorizontalLayout();
                                hlCalculateResult.setWidth(100, Unit.PERCENTAGE);
                                hlCalculateResult.setHeightUndefined();
                                hlCalculateResult.setMargin(false);
                                hlCalculateResult.setSpacing(true);
                                //Df calculate date
                                dfCalculateDate.setEnabled(false);
                                dfCalculateDate.addStyleName("lable-group-caption");
                                //Tf calculate sum
                                tfCalculateSum.addStyleName("lable-tf-caption");
                                tfCalculateSum.setRequiredIndicatorVisible(true);
                //Vertical layout window
                VerticalLayout vlContract = new VerticalLayout();
                    vlContract.setWidth(100, Unit.PERCENTAGE);
                    vlContract.setHeightUndefined();
                    vlContract.setMargin(false);
                    vlContract.setSpacing(true);
                    //Horisontal layout window number
                    HorizontalLayout hlContractNumber = new HorizontalLayout();
                        hlContractNumber.setWidth(100, Unit.PERCENTAGE);
                        hlContractNumber.setHeightUndefined();
                        hlContractNumber.setMargin(false);
                        hlContractNumber.setSpacing(true);
                        //Tf window number
                        tfContractNumber.addStyleName("lable-tf-caption");
                        tfContractNumber.setPlaceholder("№ договора");
                        tfContractNumber.setRequiredIndicatorVisible(true);
                        tfContractNumber.setMaxLength(6);
                        //Contract date
                        dfContractDate.setPlaceholder("Дата заключения");
                        dfContractDate.addStyleName("lable-group-caption");
                        dfContractDate.setValue(LocalDate.now());
                        dfContractDate.setDateFormat("dd.MM.yyyy");
                        dfContractDate.setParseErrorMessage("Неверный формат даты");
                        dfContractDate.setEnabled(false);
                    //Insurer lable
                    Label labelInsurer = new Label("СТРАХОВАТЕЛЬ");
                        labelInsurer.addStyleName("lable-l-caption");
                    //Horisontal layout insurer fio
                    HorizontalLayout hlInsurerFio = new HorizontalLayout();
                        hlInsurerFio.setWidth(100, Unit.PERCENTAGE);
                        hlInsurerFio.setHeightUndefined();
                        hlInsurerFio.setMargin(false);
                        hlInsurerFio.setSpacing(true);
                        //Button choose insurer
                        btnInsurerChoose.setStyleName(ValoTheme.BUTTON_PRIMARY);
                        btnInsurerChoose.setWidth(100, Unit.PERCENTAGE);
                        btnInsurerChoose.addClickListener(new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                getUI().addWindow(new PersonWindow(null, ContractWindow.this));
                            }
                        });
                        //Tf insurer fio
                        tfInsurerFio.setWidth(100, Unit.PERCENTAGE);
                        tfInsurerFio.setEnabled(false);
                        tfInsurerFio.addStyleName("lable-tf-caption");
                        //Button insurer edit
                        btnInsurerEdit.setStyleName(ValoTheme.BUTTON_FRIENDLY);
                        btnInsurerEdit.setWidth(100, Unit.PERCENTAGE);
                        btnInsurerEdit.addClickListener(new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                getUI().addWindow(new PersonWindow(person, ContractWindow.this));
                            }
                        });
                    //Horisontal layout insurer birthdate
                    HorizontalLayout hlInsurerBirthdate = new HorizontalLayout();
                        hlInsurerBirthdate.setWidth(100, Unit.PERCENTAGE);
                        hlInsurerBirthdate.setHeightUndefined();
                        hlInsurerBirthdate.setMargin(false);
                        hlInsurerBirthdate.setSpacing(true);
                        //Insurer birthdate
                        dfInsurerBirthdate.addStyleName("lable-group-caption");
                        dfInsurerBirthdate.setDateFormat("dd.MM.yyyy");
                        dfInsurerBirthdate.setParseErrorMessage("Неверный формат даты");
                        dfInsurerBirthdate.setEnabled(false);
                        //Tf insurer document series
                        tfInsurerDocumentSeries.addStyleName("lable-tf-caption");
                        tfInsurerDocumentSeries.setEnabled(false);
                        //Tf insurer document number
                        tfInsurerDocumentNumber.addStyleName("lable-tf-caption");
                        tfInsurerDocumentNumber.setEnabled(false);
                    //Address lable
                    Label labelAddress = new Label("Адрес недвижимости");
                        labelAddress.addStyleName("lable-l-caption");
                    //Horisontal layout address
                    HorizontalLayout hlAddress = new HorizontalLayout();
                        hlAddress.setWidth(100, Unit.PERCENTAGE);
                        hlAddress.setHeightUndefined();
                        hlAddress.setMargin(false);
                        hlAddress.setSpacing(true);
                        //Tf country
                        tfCountry.setWidth(100, Unit.PERCENTAGE);
                        tfCountry.setRequiredIndicatorVisible(true);
                        tfCountry.addStyleName("lable-tf-caption");
                        //Tf zip code
                        tfZipCode.setWidth(100, Unit.PERCENTAGE);
                        tfZipCode.addStyleName("lable-tf-caption");
                        tfZipCode.setMaxLength(6);
                        //Tf region
                        tfRegion.setWidth(100, Unit.PERCENTAGE);
                        tfRegion.addStyleName("lable-tf-caption");
                        tfRegion.setRequiredIndicatorVisible(true);
                        //Tf district
                        tfDistrict.setWidth(100, Unit.PERCENTAGE);
                        tfDistrict.addStyleName("lable-tf-caption");
                    //Horisontal layout address string 2
                    HorizontalLayout hlAddress2 = new HorizontalLayout();
                        hlAddress2.setWidth(100, Unit.PERCENTAGE);
                        hlAddress2.setHeightUndefined();
                        hlAddress2.setMargin(false);
                        hlAddress2.setSpacing(true);
                        //Tf city
                        tfCity.setWidth(100, Unit.PERCENTAGE);
                        tfCity.addStyleName("lable-tf-caption");
                        tfCity.setRequiredIndicatorVisible(true);
                        //Tf street
                        tfStreet.setWidth(100, Unit.PERCENTAGE);
                        tfStreet.addStyleName("lable-tf-caption");
                        tfStreet.setRequiredIndicatorVisible(true);
                        //Tf house
                        tfHouse.setWidth(100, Unit.PERCENTAGE);
                        tfHouse.addStyleName("lable-tf-caption");
                        //Tf building
                        tfBuilding.setWidth(100, Unit.PERCENTAGE);
                        tfBuilding.addStyleName("lable-tf-caption");
                        //Tf building 2
                        tfBuilding2.setWidth(100, Unit.PERCENTAGE);
                        tfBuilding2.addStyleName("lable-tf-caption");
                        //Tf flat
                        tfFlat.setWidth(100, Unit.PERCENTAGE);
                        tfFlat.addStyleName("lable-tf-caption");
                        tfFlat.setRequiredIndicatorVisible(true);
                    //Horisontal layout buttons
                    HorizontalLayout hlButons = new HorizontalLayout();
                        hlButons.setWidthUndefined();
                        hlButons.setHeightUndefined();
                        hlButons.setMargin(false);
                        hlButons.setSpacing(true);
                        //Button save
                        btnSave.setStyleName(ValoTheme.BUTTON_PRIMARY);
                        btnSave.addClickListener(new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                if (checkContract()) {
                                    ContractDao contractDao = new ContractDao(manager);
                                    BuildYearDao buildYearDao = new BuildYearDao(manager);
                                    RealtyDao realtyDao = new RealtyDao(manager);
                                    Contract saveContract;
                                    manager.getTransaction().begin();
                                    try {
                                        if (contract == null) {
                                            AddressEmbeded addressEmbeded = new AddressEmbeded(tfCountry.getValue().trim(),
                                                    tfZipCode.getValue().trim(),
                                                    tfRegion.getValue().trim(),
                                                    tfDistrict.getValue().trim(),
                                                    tfCity.getValue().trim(),
                                                    tfStreet.getValue().trim(),
                                                    tfHouse.getValue().trim(),
                                                    tfBuilding.getValue().trim(),
                                                    tfBuilding2.getValue().trim(),
                                                    tfFlat.getValue().trim());
                                            Realty realty = new Realty(cbRealtyType.getValue(),
                                                    buildYearDao.findByYear(cbBuildYear.getValue()),
                                                    Double.valueOf(tfBuildArea.getValue().trim()),
                                                    addressEmbeded,
                                                    Integer.valueOf(tfInsuranceSum.getValue().trim()),
                                                    AdditionalUtils.localDateToDate(dfStartDate.getValue()),
                                                    AdditionalUtils.localDateToDate(dfEndDate.getValue()),
                                                    user,
                                                    Calendar.getInstance(),
                                                    cbBuildYear.getValue()
                                            );
                                            realtyDao.save(realty);
                                            saveContract = new Contract(realty,
                                                    person,
                                                    Integer.valueOf(tfContractNumber.getValue().trim()),
                                                    AdditionalUtils.localDateToDate(dfContractDate.getValue()),
                                                    AdditionalUtils.localDateToDate(dfStartDate.getValue()),
                                                    AdditionalUtils.localDateToDate(dfEndDate.getValue()),
                                                    AdditionalUtils.localDateToDate(dfCalculateDate.getValue()),
                                                    Double.valueOf(tfCalculateSum.getValue().trim()),
                                                    taComment.getValue().trim()
                                            );
                                        } else {
                                            AddressEmbeded addressEmbeded = contract.getRealty().getAddress();
                                                addressEmbeded.setCountry(tfCountry.getValue().trim());
                                                addressEmbeded.setZipCode(tfZipCode.getValue().trim());
                                                addressEmbeded.setRegion(tfRegion.getValue().trim());
                                                addressEmbeded.setDistrict(tfDistrict.getValue().trim());
                                                addressEmbeded.setCity(tfCity.getValue().trim());
                                                addressEmbeded.setStreet(tfStreet.getValue().trim());
                                                addressEmbeded.setHouse(tfHouse.getValue().trim());
                                                addressEmbeded.setBuilding(tfBuilding.getValue().trim());
                                                addressEmbeded.setBuilding2(tfBuilding2.getValue().trim());
                                                addressEmbeded.setFlat(tfFlat.getValue().trim());
                                            Realty realty = realtyDao.findById(contract.getRealty().getId());
                                                realty.setAddress(addressEmbeded);
                                                realty.setBuildYear(buildYearDao.findByYear(cbBuildYear.getValue()));
                                                realty.setArea(Double.valueOf(tfBuildArea.getValue().trim()));
                                                realty.setRealtyType(cbRealtyType.getValue());
                                                realty.setInsurerSum(Integer.valueOf(tfInsuranceSum.getValue().trim()));
                                                realty.setStartDate(AdditionalUtils.localDateToDate(dfStartDate.getValue()));
                                                realty.setEndDate(AdditionalUtils.localDateToDate(dfEndDate.getValue()));
                                                realty.setUserModify(user);
                                                realty.setDateModify(Calendar.getInstance());
                                                realty.setYear(cbBuildYear.getValue());
                                            realtyDao.save(realty);
                                                saveContract = contractDao.findById(contract.getId());
                                                saveContract.setRealty(realty);
                                                saveContract.setPerson(person);
                                                saveContract.setNumber(Integer.valueOf(tfContractNumber.getValue().trim()));
                                                saveContract.setDate(AdditionalUtils.localDateToDate(dfContractDate.getValue()));
                                                saveContract.setStartDate(AdditionalUtils.localDateToDate(dfStartDate.getValue()));
                                                saveContract.setEndDate(AdditionalUtils.localDateToDate(dfEndDate.getValue()));
                                                saveContract.setCalculateDate(AdditionalUtils.localDateToDate(dfCalculateDate.getValue()));
                                                saveContract.setCalculateSum(Double.valueOf(tfCalculateSum.getValue().trim()));
                                                saveContract.setComment(taComment.getValue().trim());
                                        }
                                        contractDao.save(saveContract);
                                        manager.getTransaction().commit();
                                        Notification.show("Договор сохранен", Notification.Type.WARNING_MESSAGE);
                                        close();
                                    } catch (Exception e) {
                                        manager.getTransaction().rollback();
                                        Notification.show("Ошибка сохранения договора: " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                                    }
                                }
                            }
                        });
                        //Button back
                        btnBack.setStyleName(ValoTheme.BUTTON_FRIENDLY);
                        btnBack.addClickListener(new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                userView.btnSearch.click();
                                close();
                            }
                        });
                    //Coment lable
                    Label labelComment = new Label("КОММЕНТАРИЙ");
                        labelComment.addStyleName("lable-l-caption");
                    //Ta comment
                    taComment.setWidth(100, Unit.PERCENTAGE);
                    taComment.setPlaceholder("Укажите комментарий");
                    taComment.addStyleName("lable-tf-caption");
                    taComment.setRows(2);
                    taComment.setWordWrap(true);
                    taComment.setMaxLength(500);



        //////////////////////////////////////////////// Add to content ////////////////////////////////////////////////
        setContent(panelContent);
            panelContent.setContent(vlContent);
                vlContent.addComponent(panelCalculate);
                    panelCalculate.setContent(vlCalculate);
                        vlCalculate.addComponent(hlCalculate);
                            hlCalculate.addComponent(vlLeftCalculate);
                                vlLeftCalculate.addComponent(tfInsuranceSum);
                                vlLeftCalculate.addComponent(cbRealtyType);
                                vlLeftCalculate.addComponent(cbBuildYear);
                                vlLeftCalculate.addComponent(tfBuildArea);
                            hlCalculate.addComponent(hlRightCalculate);
                                hlRightCalculate.addComponent(dfStartDate);
                                hlRightCalculate.addComponent(dfEndDate);
                        vlCalculate.addComponent(hlCalculateApply);
                            hlCalculateApply.addComponent(btnCalculate);
                            hlCalculateApply.addComponent(hlCalculateResult);
                                hlCalculateResult.addComponent(dfCalculateDate);
                                hlCalculateResult.addComponent(tfCalculateSum);
                vlContent.addComponent(vlContract);
                    vlContract.addComponent(hlContractNumber);
                        hlContractNumber.addComponent(tfContractNumber);
                        hlContractNumber.addComponent(dfContractDate);
                    vlContract.addComponent(labelInsurer);
                    vlContract.addComponent(hlInsurerFio);
                        hlInsurerFio.addComponent(btnInsurerChoose);
                        hlInsurerFio.addComponent(tfInsurerFio);
                        hlInsurerFio.addComponent(btnInsurerEdit);
                    vlContract.addComponent(hlInsurerBirthdate);
                        hlInsurerBirthdate.addComponent(dfInsurerBirthdate);
                        hlInsurerBirthdate.addComponent(tfInsurerDocumentSeries);
                        hlInsurerBirthdate.addComponent(tfInsurerDocumentNumber);
                    vlContract.addComponent(labelAddress);
                    vlContract.addComponent(hlAddress);
                        hlAddress.addComponent(tfCountry);
                        hlAddress.addComponent(tfZipCode);
                        hlAddress.addComponent(tfRegion);
                        hlAddress.addComponent(tfDistrict);
                    vlContract.addComponent(hlAddress2);
                        hlAddress2.addComponent(tfCity);
                        hlAddress2.addComponent(tfStreet);
                        hlAddress2.addComponent(tfHouse);
                        hlAddress2.addComponent(tfBuilding);
                        hlAddress2.addComponent(tfBuilding2);
                        hlAddress2.addComponent(tfFlat);
                    vlContract.addComponent(labelComment);
                    vlContract.addComponent(taComment);
                    vlContract.addComponent(hlButons);
                        hlButons.addComponent(btnSave);
                        hlButons.addComponent(btnBack);


        ////////////////////////////////////////////////// Aligment ////////////////////////////////////////////////////
        hlCalculateApply.setComponentAlignment(btnCalculate, Alignment.BOTTOM_LEFT);
        hlContractNumber.setComponentAlignment(dfContractDate, Alignment.MIDDLE_RIGHT);
        hlInsurerFio.setExpandRatio(btnInsurerChoose, 1.5f);
        hlInsurerFio.setExpandRatio(tfInsurerFio, 7f);
        hlInsurerFio.setExpandRatio(btnInsurerEdit, 1.5f);
        hlInsurerFio.setComponentAlignment(btnInsurerChoose, Alignment.BOTTOM_LEFT);
        hlInsurerFio.setComponentAlignment(btnInsurerEdit, Alignment.BOTTOM_RIGHT);
        hlInsurerBirthdate.setComponentAlignment(tfInsurerDocumentSeries, Alignment.MIDDLE_CENTER);
        hlInsurerBirthdate.setComponentAlignment(tfInsurerDocumentNumber, Alignment.MIDDLE_RIGHT);
        hlAddress.setExpandRatio(tfCountry, 1.5f);
        hlAddress.setExpandRatio(tfZipCode, 1f);
        hlAddress.setExpandRatio(tfRegion, 3f);
        hlAddress.setExpandRatio(tfDistrict, 3f);
        hlAddress2.setExpandRatio(tfCity, 1.45f);
        hlAddress2.setExpandRatio(tfStreet, 3.95f);
        hlAddress2.setExpandRatio(tfHouse, 0.66f);
        hlAddress2.setExpandRatio(tfBuilding, 0.66f);
        hlAddress2.setExpandRatio(tfBuilding2, 0.65f);
        hlAddress2.setExpandRatio(tfFlat, 0.65f);
        vlContract.setComponentAlignment(hlButons, Alignment.BOTTOM_CENTER);

        //////////////////////////////////////// Set values ////////////////////////////////////////////////////////////
        if (contract != null) {
            tfInsuranceSum.setValue(String.valueOf(contract.getRealty().getInsurerSum()));
            cbRealtyType.setValue(contract.getRealty().getRealtyType());
            cbBuildYear.setValue(contract.getRealty().getYear());
            tfBuildArea.setValue(String.valueOf(contract.getRealty().getArea()));
            dfStartDate.setValue(AdditionalUtils.dateToLocalDate(contract.getStartDate()));
            dfEndDate.setValue(AdditionalUtils.dateToLocalDate(contract.getEndDate()));
            dfCalculateDate.setValue(AdditionalUtils.dateToLocalDate(contract.getCalculateDate()));
            tfCalculateSum.setValue(String.valueOf(contract.getCalculateSum()));
            tfContractNumber.setValue(String.valueOf(contract.getNumber()));
            dfContractDate.setValue(AdditionalUtils.dateToLocalDate(contract.getDate()));
            person = contract.getPerson();
            tfInsurerFio.setValue(person.getFio());
            dfInsurerBirthdate.setValue(AdditionalUtils.dateToLocalDate(person.getBirthDate()));
            tfInsurerDocumentSeries.setValue(person.getDocumentSeries());
            tfInsurerDocumentNumber.setValue(String.valueOf(person.getDocumentNumber()));
            tfCountry.setValue(contract.getRealty().getAddress().getCountry());
            tfZipCode.setValue(contract.getRealty().getAddress().getZipCode());
            tfRegion.setValue(contract.getRealty().getAddress().getRegion());
            tfDistrict.setValue(contract.getRealty().getAddress().getDistrict());
            tfCity.setValue(contract.getRealty().getAddress().getCity());
            tfStreet.setValue(contract.getRealty().getAddress().getStreet());
            tfHouse.setValue(contract.getRealty().getAddress().getHouse());
            tfBuilding.setValue(contract.getRealty().getAddress().getBuilding());
            tfBuilding2.setValue(contract.getRealty().getAddress().getBuilding2());
            tfFlat.setValue(contract.getRealty().getAddress().getFlat());
            taComment.setValue(contract.getComment());
        }
    }


    //Set person info
    public void setPersonInfo(Person person) {
        if (person != null) {
            tfInsurerFio.setValue(person.getFio());
            dfInsurerBirthdate.setValue(AdditionalUtils.dateToLocalDate(person.getBirthDate()));
            tfInsurerDocumentSeries.setValue(person.getDocumentSeries());
            tfInsurerDocumentNumber.setValue(String.valueOf(person.getDocumentNumber()));
        }
    }

    //Get errors from data calculate
    private String getErrorsFromDataCalculate() {
        String errors = "";
        if (tfInsuranceSum.getValue().trim().isEmpty()|| !AdditionalUtils.isPositiveInteger(tfInsuranceSum.getValue().trim())) errors += "\n Некорректная страховая сумма";
        if (cbRealtyType.getValue() == null) errors += "\n Не указан тип недвижимости";
        if (dfStartDate.getValue() == null) errors += "\n Не заполнена дата действия с";
        if (dfEndDate.getValue() == null) errors += "\n Не заполнена дата действия по";
        if (dfStartDate.getValue().compareTo(LocalDate.now()) < 0 ) errors += "\n Дата действия с не может быть меньше текущей";
        if (cbBuildYear.getValue() == null) errors += "\n Не заполнен год постройки";
        if (Period.between(dfStartDate.getValue(), dfEndDate.getValue()).getYears() == 0 ||
                (Period.between(dfStartDate.getValue(), dfEndDate.getValue()).getYears() == 1 &&
                        Period.between(dfStartDate.getValue(), dfEndDate.getValue()).getMonths() == 0 &&
                        Period.between(dfStartDate.getValue(), dfEndDate.getValue()).getDays() == 0)) {} else errors += "\n Дата действия по не может быть больше даты действия с более чем на год";
        if (dfStartDate.getValue().compareTo(dfEndDate.getValue()) > 0 ) errors += "\n Дата действия по не может быть меньше даты действия с";
        if (AdditionalUtils.isInteger(tfBuildArea.getValue().trim()) || (tfBuildArea.getValue().trim().matches("[0-9]\\d*?(\\.\\d{1,1})") && AdditionalUtils.isDouble(tfBuildArea.getValue().trim()))) {}
        else errors += "\n Некорректно указана площадь (значение должно быть целым числом или числом с 1 знаком после запятой)";
        return errors;
    }

    //Check data
    private boolean checkContract() {
        String errors = getErrorsFromDataCalculate();
        if (tfContractNumber.getValue().trim().length() != 6 || !AdditionalUtils.isPositiveInteger(tfContractNumber.getValue().trim())) errors += "\n Некорректно указан номер договора";
        if (person == null) errors += "\n Не выбран страхователь";
        if (tfCalculateSum.getValue().trim().isEmpty()) errors += "\n Не произведен рассчет страховой премии";
        if (tfCountry.getValue().trim().isEmpty()) errors += "\n Не заполнена страна";
        if (tfRegion.getValue().trim().isEmpty()) errors += "\n Не заполнен регион";
        if (tfCity.getValue().trim().isEmpty()) errors += "\n Не заполнен населенный пункт";
        if (tfStreet.getValue().trim().isEmpty()) errors += "\n Не заполнена улица";
        if (tfFlat.getValue().trim().isEmpty()) errors += "\n Не заполнена квартира";
        if (AdditionalUtils.yearsBetween(AdditionalUtils.dateToLocalDate(person.getBirthDate()), dfContractDate.getValue()) < 18) errors += "\n Страхователь не достиг совершеннолетия";
        if (errors.isEmpty()) return true;
        Notification.show(errors, Notification.Type.WARNING_MESSAGE);
        return false;
    }

    // Clear calculate
    private void clearCalculate() {
        dfCalculateDate.clear();
        tfCalculateSum.clear();
    }
}
