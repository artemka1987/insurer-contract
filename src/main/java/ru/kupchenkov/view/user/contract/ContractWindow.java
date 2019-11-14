package ru.kupchenkov.view.user.contract;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import ru.kupchenkov.entity.RealtyType;
import ru.kupchenkov.resource.Images;

import java.time.LocalDate;

public class ContractWindow extends Window {

    private TextField tfInsuranceSum = new TextField("Сумма страхования");
    private DateField dfStartDate = new DateField("Срок действия с");
    private DateField dfEndDate = new DateField("по");
    private ComboBox<RealtyType> cbRealtyType = new ComboBox<>("Тип недвижимости");
    private ComboBox<Integer> cbBuildYear = new ComboBox<>("Год постройки");
    private TextField tfBuildArea = new TextField("Площадь, кв.м.");
    private Button btnCalculate = new Button("Рассчитать");
    private DateField dfCalculateDate = new DateField("Дата рассчета");
    private TextField tfCalculateSum = new TextField("Премия");
    private TextField tfContractNumber = new TextField("№ договора");
    private DateField dfContractDate = new DateField("Дата заключения");
    private Button btnInsurerChoose = new Button("Выбрать");
    private TextField tfInsurerFio = new TextField("Ф.И.О.");
    private Button btnInsurerEdit = new Button("Изменить");
    private DateField dfInsurerBirthdate = new DateField("Дата рождения");
    private TextField tfInsurerDocumentSeries = new TextField("Серия паспорта");
    private TextField tfInsurerDocumentNumber = new TextField("Номер паспорта");
    private ComboBox<String> cbCountry = new ComboBox<>("Страна");
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
    private Button btnBack = new Button("К списку договоров");

    public ContractWindow() {

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
                    panelCalculate.setIcon(Images.icoCalculate);
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
                                //Combobox realty type
                                cbRealtyType.setWidth(100, Unit.PERCENTAGE);
                                cbRealtyType.setPlaceholder("Укажите тип недвижимости");
                                cbRealtyType.setRequiredIndicatorVisible(true);
                                cbRealtyType.addStyleName("lable-group-caption");
                                //Combobox build year
                                cbBuildYear.setWidth(100, Unit.PERCENTAGE);
                                cbBuildYear.setPlaceholder("Укажите год постройки");
                                cbBuildYear.setRequiredIndicatorVisible(true);
                                cbBuildYear.addStyleName("lable-group-caption");
                                //Tf build area
                                tfBuildArea.setWidth(100, Unit.PERCENTAGE);
                                tfBuildArea.setRequiredIndicatorVisible(true);
                                tfBuildArea.addStyleName("lable-tf-caption");
                                tfBuildArea.setPlaceholder("Укажите площадь");

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
                                //End date
                                dfEndDate.setWidth(100, Unit.PERCENTAGE);
                                dfEndDate.setPlaceholder("Действует по");
                                dfEndDate.addStyleName("lable-group-caption");
                                dfEndDate.setValue(LocalDate.now());
                                dfEndDate.setDateFormat("dd.MM.yyyy");
                                dfEndDate.setParseErrorMessage("Неверный формат даты");
                                dfEndDate.setRequiredIndicatorVisible(true);
                        //Horisontal layout calculate apply
                        HorizontalLayout hlCalculateApply = new HorizontalLayout();
                            hlCalculateApply.setWidth(100, Unit.PERCENTAGE);
                            hlCalculateApply.setHeightUndefined();
                            hlCalculateApply.setMargin(false);
                            hlCalculateApply.setSpacing(true);
                            //Button calculate
                            btnCalculate.setStyleName(ValoTheme.BUTTON_PRIMARY);
                            //Horisontal layout calculate result
                            HorizontalLayout hlCalculateResult = new HorizontalLayout();
                                hlCalculateResult.setWidth(100, Unit.PERCENTAGE);
                                hlCalculateResult.setHeightUndefined();
                                hlCalculateResult.setMargin(false);
                                hlCalculateResult.setSpacing(true);
                                //Df calculate date
                                dfCalculateDate.setValue(LocalDate.now());
                                dfCalculateDate.setEnabled(false);
                                dfCalculateDate.addStyleName("lable-group-caption");
                                //Tf calculate sum
                                tfCalculateSum.addStyleName("lable-tf-caption");
                //Vertical layout contract
                VerticalLayout vlContract = new VerticalLayout();
                    vlContract.setWidth(100, Unit.PERCENTAGE);
                    vlContract.setHeightUndefined();
                    vlContract.setMargin(false);
                    vlContract.setSpacing(true);
                    //Horisontal layout contract number
                    HorizontalLayout hlContractNumber = new HorizontalLayout();
                        hlContractNumber.setWidth(100, Unit.PERCENTAGE);
                        hlContractNumber.setHeightUndefined();
                        hlContractNumber.setMargin(false);
                        hlContractNumber.setSpacing(true);
                        //Tf contract number
                        tfContractNumber.addStyleName("lable-tf-caption");
                        tfContractNumber.setPlaceholder("№ договора");
                        tfContractNumber.setRequiredIndicatorVisible(true);
                        //Contract date
                        dfContractDate.setPlaceholder("Дата заключения");
                        dfContractDate.addStyleName("lable-group-caption");
                        dfContractDate.setValue(LocalDate.now());
                        dfContractDate.setDateFormat("dd.MM.yyyy");
                        dfContractDate.setParseErrorMessage("Неверный формат даты");
                        dfContractDate.setRequiredIndicatorVisible(true);
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
                        //Tf insurer fio
                        tfInsurerFio.setWidth(100, Unit.PERCENTAGE);
                        tfInsurerFio.setEnabled(false);
                        tfInsurerFio.addStyleName("lable-tf-caption");
                        //Button insurer edit
                        btnInsurerEdit.setStyleName(ValoTheme.BUTTON_FRIENDLY);
                        btnInsurerEdit.setWidth(100, Unit.PERCENTAGE);
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
                        //Combobox country
                        cbCountry.setWidth(100, Unit.PERCENTAGE);
                        cbCountry.setRequiredIndicatorVisible(true);
                        cbCountry.addStyleName("lable-group-caption");
                        //Tf zip code
                        tfZipCode.setWidth(100, Unit.PERCENTAGE);
                        tfZipCode.addStyleName("lable-tf-caption");
                        //Tf region
                        tfRegion.setWidth(100, Unit.PERCENTAGE);
                        tfRegion.addStyleName("lable-tf-caption");
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
                        //Tf street
                        tfStreet.setWidth(100, Unit.PERCENTAGE);
                        tfStreet.addStyleName("lable-tf-caption");
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
                    //Horisontal layout buttons
                    HorizontalLayout hlButons = new HorizontalLayout();
                        hlButons.setWidthUndefined();
                        hlButons.setHeightUndefined();
                        hlButons.setMargin(false);
                        hlButons.setSpacing(true);
                        //Button save
                        btnSave.setStyleName(ValoTheme.BUTTON_PRIMARY);
                        //Button back
                        btnBack.setStyleName(ValoTheme.BUTTON_FRIENDLY);



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
                        hlAddress.addComponent(cbCountry);
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
                    vlContract.addComponent(hlButons);
                        hlButons.addComponent(btnSave);
                        hlButons.addComponent(btnBack);


        ////////////////////////////////////////////////// Aligment ////////////////////////////////////////////////////
        hlCalculateApply.setComponentAlignment(btnCalculate, Alignment.BOTTOM_LEFT);
        hlContractNumber.setComponentAlignment(dfContractDate, Alignment.MIDDLE_RIGHT);
        hlInsurerFio.setExpandRatio(btnInsurerChoose, 1f);
        hlInsurerFio.setExpandRatio(tfInsurerFio, 8f);
        hlInsurerFio.setExpandRatio(btnInsurerEdit, 1f);
        hlInsurerFio.setComponentAlignment(btnInsurerChoose, Alignment.BOTTOM_LEFT);
        hlInsurerFio.setComponentAlignment(btnInsurerEdit, Alignment.BOTTOM_RIGHT);
        hlInsurerBirthdate.setComponentAlignment(tfInsurerDocumentSeries, Alignment.MIDDLE_CENTER);
        hlInsurerBirthdate.setComponentAlignment(tfInsurerDocumentNumber, Alignment.MIDDLE_RIGHT);
        hlAddress.setExpandRatio(cbCountry, 1.5f);
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
    }

}
