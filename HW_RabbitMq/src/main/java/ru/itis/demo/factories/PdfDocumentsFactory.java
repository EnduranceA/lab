package ru.itis.demo.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.demo.dto.GrantUserDto;
import ru.itis.demo.dto.LabUserDto;
import ru.itis.demo.dto.SocialUserDto;
import ru.itis.demo.helpers.PdfGenerator;

import java.util.Random;

@Component
public class PdfDocumentsFactory {

    @Autowired
    private PdfGenerator generator;

    @Autowired
    private Random random;

    public void createDeductionLabPdf(LabUserDto labUserDto) {
        String pathName = "documents/lab/deduction_" + random.nextInt()+ ".pdf";
        String template = "deduction_lab.ftl";
        generator.createPdf(pathName, template, labUserDto);
    }

    public void createEnrollmentLabPdf(LabUserDto labUserDto) {
        String pathname = "documents/lab/enrollment_" + random.nextInt() + ".pdf";
        String template = "enrollment_lab.ftl";
        generator.createPdf(pathname, template, labUserDto);
    }

    public void createMaterialHelpPdf(SocialUserDto socialUserDto) {
        String pathname = "documents/help/material_" + random.nextInt()  + ".pdf";
        String template = "material_help.ftl";
        generator.createPdf(pathname, template, socialUserDto);
    }

    public void createSocialFoodPdf(SocialUserDto socialUserDto) {
        String pathname = "documents/help/social_food_"+ random.nextInt() + ".pdf";
        String template = "social_food.ftl";
        generator.createPdf(pathname, template, socialUserDto);
    }

    public void createTransportPdf(SocialUserDto socialUserDto) {
        String pathname = "documents/help/transport_"+ random.nextInt() + ".pdf";
        String template = "transport.ftl";
        generator.createPdf(pathname, template, socialUserDto);
    }

    public void createGrantAgreementPdf(GrantUserDto grantUserDto) {
        String pathname = "documents/grant/agreement_"+ random.nextInt() + ".pdf";
        String template = "grant_agreement.ftl";
        generator.createPdf(pathname, template, grantUserDto);
    }

    public void createGrantApplicationPdf(GrantUserDto grantUserDto) {
        String pathname = "documents/grant/application_"+ random.nextInt() +".pdf";
        String template = "grant_application.ftl";
        generator.createPdf(pathname, template, grantUserDto);
    }

    public void createLetterOfGuaranteePdf(GrantUserDto grantUserDto) {
        String pathname = "documents/grant/letter_"+ random.nextInt() + ".pdf";
        String template = "grant_letter.ftl";
        generator.createPdf(pathname, template, grantUserDto);
    }

    public void createConsentPdf(GrantUserDto grantUserDto) {
        String pathname = "documents/grant/consent_"+ random.nextInt() + ".pdf";
        String template = "grant_consent.ftl";
        generator.createPdf(pathname, template, grantUserDto);
    }
}
