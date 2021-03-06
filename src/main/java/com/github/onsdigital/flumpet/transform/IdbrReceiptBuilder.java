package com.github.onsdigital.flumpet.transform;

//import org.springframework.util.Assert;

import com.github.onsdigital.flumpet.json.IdbrReceipt;
import com.github.onsdigital.flumpet.json.Survey;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An IDBR receipt records that a respondent unit (RU) has completed a survey (id)
 * on a particular date.
 *
 * An IDBR batch receipt file can contain multiple receipts.
 */
public class IdbrReceiptBuilder {

    private static final String DELIMITER = ":";

    private static final String IDBR_PREFIX = "REC";
    private static final String SEPARATOR = "_";
    private static final String IDBR_FILE_TYPE = ".DAT";

    private static final String NEW_LINE = System.getProperty("line.separator");

    private DateTimeFormatter surveyFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private DateTimeFormatter idbrReceiptFormatter = DateTimeFormatter.ofPattern("yyyyMM");
    private DateTimeFormatter idbrFilenameFormatter = DateTimeFormatter.ofPattern("ddMM");

    public IdbrReceipt createIdbrReceipt(final Survey survey, final long batchId) {
        return createIdbrReceipt(survey, LocalDate.now(), batchId);
    }

    public IdbrReceipt createIdbrReceipt(final Survey survey, final LocalDate date, final long batchId) {
        //TODO: import an assert library? this is spring below
//        Assert.notNull(survey, "survey should not be null");
//        Assert.notNull(survey.getDate(), "survey date should not be null");
//        Assert.notNull(date, "date should not be null");

        return IdbrReceipt.builder()
                .receipt(createReceipt(survey))
                .filename(createFilename(date, batchId))
                .build();
    }

    /**
     * Create IDBR receipt data for a survey.
     *
     * Format is respondent:checkletter:surveyId:date
     *
     * e.g. 99999994188:F:244:201410
     *
     * @param survey
     * @return the IDBR receipt data
     * @throws java.text.ParseException if problem parsing the survey date e.g. 01 Oct 2014
     */
    private String createReceipt(final Survey survey) {
        final StringBuilder receipt = new StringBuilder()
            .append(survey.getRespondentId())
            .append(DELIMITER)
            .append(survey.getRespondentCheckLetter())
            .append(DELIMITER)
            .append(survey.getId())
            .append(DELIMITER)
            .append(formatIdbrDate(survey.getDate()))
            .append(NEW_LINE);

        return receipt.toString();
    }

    /**
     * Create IDBR filename for a batch.
     *
     * Format is RECddMM_batchId.DAT
     *
     * e.g. REC1001_30000.DAT
     * for 10th January, batchId 30000
     *
     * @param date the date to use in the filename
     * @param batchId the batchId to use in the filename
     * @return the IDBR filename
     */
    private String createFilename(final LocalDate date, final long batchId) {
        final StringBuilder filename = new StringBuilder()
                .append(IDBR_PREFIX)
                .append(idbrFilenameFormatter.format(date))
                .append(SEPARATOR)
                .append(batchId)
                .append(IDBR_FILE_TYPE);

        return filename.toString();
    }

    private String formatIdbrDate(final String surveyDate) {
        LocalDate parsedSurveyDate = LocalDate.parse(surveyDate, surveyFormatter);
        return idbrReceiptFormatter.format(parsedSurveyDate);
    }
}
