package org.b3log.solo.util;

import org.apache.commons.lang.StringUtils;
import org.b3log.latke.Latkes;
import org.b3log.latke.ioc.Singleton;
import org.b3log.latke.logging.Level;
import org.b3log.latke.logging.Logger;
import org.b3log.latke.util.Locales;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created on 2019/11/15 17:44.
 *
 * @author zhaozengjie
 * Description : 解析Bundle
 */
@Singleton
public class Lang {
    private static final Logger LOGGER = Logger.getLogger(Lang.class);
    private static final Map<Locale, Map<String, String>> LANGS = new HashMap();

    public Map<String, String> getAll(Locale locale) {
        Map<String, String> ret = (Map) LANGS.get(locale);
        if (null == ret) {
            ret = new HashMap();
            ResourceBundle defaultLangBundle = ResourceBundle.getBundle("lang", Latkes.getLocale());
            Enumeration defaultLangKeys = defaultLangBundle.getKeys();

            while (defaultLangKeys.hasMoreElements()) {
                String key = (String) defaultLangKeys.nextElement();
                try {
                    String val = new String(defaultLangBundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
                    String value = this.replaceVars(val);
                    ((Map) ret).put(key, value);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            ResourceBundle langBundle = ResourceBundle.getBundle("lang", locale);
            Enumeration langKeys = langBundle.getKeys();

            while (langKeys.hasMoreElements()) {
                String key = (String) langKeys.nextElement();
                try {
                    String val = new String(defaultLangBundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
                    String value = this.replaceVars(val);
                    ((Map) ret).put(key, value);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            LANGS.put(locale, ret);
        }

        return (Map) ret;
    }

    public String get(String key) {
        return this.get("lang", key, Locales.getLocale());
    }

    public String get(String key, Locale locale) {
        return this.get("lang", key, locale);
    }

    private String get(String baseName, String key, Locale locale) {
        if (!"lang".equals(baseName)) {
            RuntimeException e = new RuntimeException("i18n resource [baseName=" + baseName + "] not found");
            LOGGER.log(Level.ERROR, e.getMessage(), e);
            throw e;
        } else {
            try {
                return this.replaceVars(ResourceBundle.getBundle(baseName, locale).getString(key));
            } catch (MissingResourceException var5) {
                LOGGER.log(Level.WARN, "{0}, get it from default locale [{1}]", new Object[]{var5.getMessage(), Latkes.getLocale()});
                return this.replaceVars(ResourceBundle.getBundle(baseName, Latkes.getLocale()).getString(key));
            }
        }
    }


    private String replaceVars(String langValue) {
        String ret = StringUtils.replace(langValue, "${servePath}", Latkes.getServePath());
        ret = StringUtils.replace(ret, "${staticServePath}", Latkes.getStaticServePath());
        return ret;
    }

}
