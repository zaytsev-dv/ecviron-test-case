/*
 * Copyright (zaytsev_dv)
 *  *|
 *  *|
 *  *|(•.•). i'm watching for you.....
 *  *|⊂ﾉ
 *  *|
 *  *|
 */


package com.ecviron.api.rest;

import com.ecviron.api.exception.UnvalidException;
import ecviron.generated.swaggerCodegen.model.PostEventRequest;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ecviron.api.rest.EventValidator.PostEventRequestError.*;


public class EventValidator {

    public static final int LENGTH_COUNT_FOR_CUSTOMER_AND_SELLER = 9;
    public static final int LENGTH_COUNT_FOR_PRODUCT_CODE = 13;

    private void checkOnNull(final List<String> errors, final String value, final PostEventRequestError errorEnum) {
        if (!StringUtils.hasText(value)) {
            errors.add(errorEnum.errorMsg);
        }
    }

    private void checkOnLength(
            final List<String> errors,
            final String value,
            final int lengthCount,
            final PostEventRequestError errorEnum
    ) {
        if (value.length() != lengthCount) {
            errors.add(errorEnum.errorMsg);
        }
    }

    private void checkOnLength(
            final List<String> errors,
            final String value,
            final int lengthCount,
            final PostEventRequestError errorEnum,
            final String name
    ) {
        if (value.length() != lengthCount) {
            errors.add(String.format(errorEnum.errorMsg, value, name));
        }
    }


    private void validateCustomerSeller(final List<String> errors, final PostEventRequest toValidate) {
        checkOnNull(errors, toValidate.getCustomer(), CUSTOMER_NULL);
        checkOnNull(errors, toValidate.getSeller(), SELLER_NULL);

        checkOnLength(errors, toValidate.getCustomer(), LENGTH_COUNT_FOR_CUSTOMER_AND_SELLER, CUSTOMER_ID_UNVALID_LENGHT);
        checkOnLength(errors, toValidate.getSeller(), LENGTH_COUNT_FOR_CUSTOMER_AND_SELLER, SELLER_ID_UNVALID_LENGHT);
    }

    private void validateProducts(final List<String> errors, final PostEventRequest toValidate) {
        toValidate.getProductsList().forEach(pr-> {
            checkOnNull(errors, pr.getCode(), PRODUCT_CODE_NULL);
            checkOnNull(errors, pr.getName(), PRODUCT_NAME_NULL);

            checkOnLength(errors, pr.getCode(), LENGTH_COUNT_FOR_PRODUCT_CODE, PRODUCT_ID_UNVALID_LENGHT, pr.getName());
        });
    }

    @SneakyThrows
    protected void validate(final PostEventRequest toValidate) {
        List<String> errors = new ArrayList<>();

        this.validateCustomerSeller(errors, toValidate);
        this.validateProducts(errors, toValidate);

        if (!errors.isEmpty()) {
            throw new UnvalidException(String.join("; ", errors));
        }
    }

    protected enum PostEventRequestError {
        CUSTOMER_NULL("field \"Customer\" is NULL"),
        SELLER_NULL("field \"Seller\" is NULL"),
        PRODUCT_NAME_NULL("field \"Name\" is NULL"),
        PRODUCT_CODE_NULL("field \"Code\" is NULL"),
        CUSTOMER_ID_UNVALID_LENGHT("field \"Customer\" length not equal to 9 characters"),
        SELLER_ID_UNVALID_LENGHT("field \"Seller\" length not equal to 9 characters"),
        PRODUCT_ID_UNVALID_LENGHT("code: \"%s\" length not equal to 13 characters. Product: \"%s\" ");

        private final String errorMsg;

        PostEventRequestError(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }
}
