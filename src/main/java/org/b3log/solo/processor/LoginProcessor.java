package org.b3log.solo.processor;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.b3log.latke.Keys;
import org.b3log.latke.Latkes;
import org.b3log.latke.ioc.Inject;
import org.b3log.latke.servlet.renderer.JsonRenderer;
import org.b3log.solo.cache.TokenCache;
import org.b3log.solo.common.Pair;
import org.b3log.solo.model.LoginForm;
import org.b3log.solo.render.SkinRenderer;
import org.b3log.solo.repository.UserLoginInfoRepository;
import org.b3log.solo.service.AuthService;
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
public class LoginProcessor extends BaseProcess {
    @Inject
    private Lang langPropsService;
    @Inject
    private DataModelService dataModelService;

    @Inject
    private Lang lang;

    @Inject
    private OptionQueryService optionQueryService;

    @Inject
    private TokenCache tokenCache;

    @Inject
    private AuthService authService;

    @RequestProcessing(value = "/login", method = HttpMethod.GET)
    public void showLogin(final RequestContext context) {
        final AbstractFreeMarkerRenderer renderer = new SkinRenderer(context, "common-template/login.ftl");
        final Map<String, Object> dataModel = renderer.getDataModel();
        final HttpServletRequest request = context.getRequest();
        final Map<String, String> langs = lang.getAll(Locales.getLocale(request));
        dataModel.putAll(langs);
        dataModel.put(Common.STATIC_RESOURCE_VERSION, Latkes.getStaticResourceVersion());
        dataModel.put(Common.YEAR, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        dataModel.put(Common.VERSION, SoloServletListener.VERSION);
        String tmpUserName = RandomStringUtils.randomAlphanumeric(8);
        dataModel.put(Common.TOKEN, tokenCache.createToken(tmpUserName));
        dataModel.put(Common.TOKEN_NAME, tmpUserName);
        Keys.fillRuntime(dataModel);
        dataModelService.fillFaviconURL(dataModel, optionQueryService.getPreference());
        dataModelService.fillMinified(dataModel);
        dataModelService.fillUsite(dataModel);
    }

    /**
     * 获取获取名称对应的Token
     *
     * @param context
     */
    @RequestProcessing(value = "/getTicket", method = HttpMethod.POST)
    public void getTicket(final RequestContext context) {
        JsonRenderer renderer = new JsonRenderer();
        context.setRenderer(renderer);

        String kid = context.param("kid");

        String token = context.param("tokne");

        String userName = context.param("userName");

        if (StringUtils.isEmpty(kid)) {
            renderer.setJSONObject(err("参数校验失败"));
            return;
        }

        if (StringUtils.isEmpty(token)) {
            renderer.setJSONObject(err("参数校验失败token"));
        }

        String ticket = authService.getUserTicket(userName, kid, token);

        Pair kv = Pair.createInstance()
                .kv("ticket", ticket)
                .kv("resultCode", "000000");

        renderer.setJSONObject(kv.getJSON());
    }

    /**
     * 后台登录验证
     *
     * @param context
     */
    @RequestProcessing(value = "/auth", method = HttpMethod.POST)
    public void auth(final RequestContext context) {

        LoginForm loginForm = new LoginForm(context);

        // 参数校验
        if (!loginForm.verify()) {
            JsonRenderer renderer = new JsonRenderer();
            context.setRenderer(renderer);

            JSONObject err = new JSONObject();

            err.put("resultCode", "000001");
            err.put("resultMsg", "参数缺失");
            renderer.setJSONObject(err);
            return;
        }


        boolean isLogin = authService.authLogin(loginForm);

        if (isLogin) {

        }


    }

}
