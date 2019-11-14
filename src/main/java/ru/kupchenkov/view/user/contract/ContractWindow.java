package ru.kupchenkov.view.user.contract;

import com.vaadin.ui.*;
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

    public ContractWindow() {

        setWidth(98, Unit.PERCENTAGE);
        setHeightUndefined();
        setModal(true);
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
                    panelCalculate.addStyleName("arm-title");
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
                                // Start date
                                dfStartDate.setWidth(100, Unit.PERCENTAGE);
                                dfStartDate.setPlaceholder("Действует с");
                                dfStartDate.addStyleName("lable-group-caption");
                                dfStartDate.setValue(LocalDate.now());
                                dfStartDate.setDateFormat("dd.MM.yyyy");
                                dfStartDate.setParseErrorMessage("Неверный формат даты");
                                dfStartDate.setRequiredIndicatorVisible(true);
                                // End date
                                dfEndDate.setWidth(100, Unit.PERCENTAGE);
                                dfEndDate.setPlaceholder("Действует по");
                                dfEndDate.addStyleName("lable-group-caption");
                                dfEndDate.setValue(LocalDate.now());
                                dfEndDate.setDateFormat("dd.MM.yyyy");
                                dfEndDate.setParseErrorMessage("Неверный формат даты");
                                dfEndDate.setRequiredIndicatorVisible(true);

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

    }

}
