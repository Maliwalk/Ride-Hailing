package lld.helper;

import lld.exception.ValidationException;
import lld.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserHelper {
    public static void validateUser(User rider){
        StringBuilder errors = new StringBuilder();
        String phoneNumber = rider.getPhoneNumber().toString();
        Pattern ptrn = Pattern.compile("[7-9][0-9]{9}");
        Matcher match = ptrn.matcher(phoneNumber);
        if (!(match.find() && match.group().equals(phoneNumber))){
            errors.append("Validation failed for PhoneNumber "+ phoneNumber);
        }

        if (errors.length()!=0) {
            String errMsg = "Request Validation failed for :  " + errors.toString();
            throw new ValidationException(errMsg);
        }

    }
}
