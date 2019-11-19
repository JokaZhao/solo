package org.b3log.solo.processor;

import org.b3log.latke.Keys;
import org.b3log.latke.Latkes;
import org.b3log.latke.ioc.Inject;
import org.b3log.solo.render.SkinRenderer;
import org.b3log.solo.util.Lang;
import org.b3log.latke.servlet.HttpMethod;
import org.b3log.latke.servlet.RequestContext;
import org.b3log.latke.servlet.annotation.RequestProcessing;
import org.b3log.latke.servlet.annotation.RequestProcessor;
import org.b3log.latke.servlet.renderer.AbstractFreeMarkerRenderer;
import org.b3log.latke.util.Locales;
import org.b3log.solo.SoloServletListener;
import org.b3log.solo.model.Common;
import org.b3log.solo.service.DataModelService;
import org.b3log.solo.service.OptionQueryService;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Map;

/**
 * Created on 2019/11/14 20:32.
 *
 * @author zhaozengjie
 * Description :
 */
@RequestProcessor
public class LoginProcessor {
    @Inject
    private Lang langPropsService;
    @Inject
    private DataModelService dataModelService;

    @Inject
    private Lang lang;

    @Inject
    private OptionQueryService optionQueryService;


    @RequestProcessing(value = "/login", method = HttpMethod.GET)
    public void showLogin(final RequestContext context){
    final AbstractFreeMarkerRenderer renderer = new SkinRenderer(context, "common-template/login.ftl");
        final Map<String, Object> dataModel = renderer.getDataModel();
        final HttpServletRequest request = context.getRequest();
        final Map<String, String> langs = lang.getAll(Locales.getLocale(request));
        dataModel.putAll(langs);
        dataModel.put(Common.STATIC_RESOURCE_VERSION, Latkes.getStaticResourceVersion());
        dataModel.put(Common.YEAR, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        dataModel.put(Common.VERSION, SoloServletListener.VERSION);
        Keys.fillRuntime(dataModel);
        dataModelService.fillFaviconURL(dataModel, optionQueryService.getPreference());
        dataModelService.fillMinified(dataModel);
        dataModelService.fillUsite(dataModel);
    }

    @RequestProcessing(value="/getTicket",method = HttpMethod.POST)
    public void getTicket(final RequestContext context){

        final JSONObject ret = new JSONObject().put(Keys.CODE, 0);
        context.renderJSON(ret);


    }
}
