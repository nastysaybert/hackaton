package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.details.DetailsVariant;
import ru.tele2.autoct.services.TestCaseService;

public class TestCasesAccordion extends Accordion {
    public TestCasesAccordion(TestCaseService testCaseService){
        this.setSizeFull();
        testCaseService.getAll().forEach( testCaseDto -> {
            AccordionPanel panel = new AccordionPanel(testCaseDto.getName(), new TestCaseGrid(testCaseDto));
            panel.addThemeVariants(DetailsVariant.FILLED);
            panel.setOpened(false);
            this.add(panel);
        });
    }
}
