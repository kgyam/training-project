package org.example.user.web.validator.bean.validation;

import org.example.user.web.domain.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.logging.Logger;

/**
 * @author kg yam
 * @date 2021-03-10 10:29
 * @since
 */
public class UserValidAnnotationValidator implements ConstraintValidator<UserValid, User> {

    private static final Logger logger = Logger.getLogger (UserValidAnnotationValidator.class.getName ());


    @Override
    public void initialize(UserValid idValid) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        logger.info (constraintValidatorContext.getDefaultConstraintMessageTemplate ());
        long id = user.getId ();
        if (id <= 0) {
            return false;
        }

        if (user.getPhoneNumber ().trim ().length () != 11) {
            return false;
        }

        return true;
    }
}
