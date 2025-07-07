package com.oteller.rezervin.gateway.exception.utils;

import jakarta.validation.Path;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.context.i18n.LocaleContextHolder;

import com.oteller.rezervin.gateway.exception.enums.IFriendlyMessageCode;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Slf4j
@UtilityClass
public class FriendlyMessageUtils {
    private static final String RESOURCE_BUNDLE_NAME = "FriendlyMessage";
    private static final String SPECIAL_CHARACTER = "__";

    public static String getFriendlyMessage(IFriendlyMessageCode messageCode){
        String messageKey = null;
        try{
            Locale locale = LocaleContextHolder.getLocale();
            ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
            messageKey = messageCode.getClass().getSimpleName() + SPECIAL_CHARACTER + messageCode;
            return resourceBundle.getString(messageKey);
        }catch (MissingResourceException missingResourceException){
            log.error("Friendly message not found for key {}",messageKey);
            return null;
        }
    }

    public static String getFriendlyMessage(String className, String messageCode){
        String messageKey = null;
        try{
            Locale locale = LocaleContextHolder.getLocale();
            ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
            messageKey = className + SPECIAL_CHARACTER + messageCode;
            return resourceBundle.getString(messageKey);
        }catch (MissingResourceException missingResourceException){
            log.error("Friendly message not found for key {}",messageKey);
            return null;
        }
    }
    public static String getField(Path path){
        PathImpl node = (PathImpl) path;
        return node.getLeafNode().getName();
    }

}
