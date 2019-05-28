package com.dollhouse.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.dollhouse.core.dao.query.OrderBy;
import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.core.entity.Page;
import com.dollhouse.core.utils.PropertiesUtil;
import com.dollhouse.core.utils.RequestUtil;
import com.dollhouse.core.utils.StringUtils;
import com.dollhouse.entity.comm.SysSystemConfig;
import com.dollhouse.entity.comm.domain.SysSystemConfigDomain;

/**
 * 网站内容
 * @author Administrator
 *
 */
@Controller
public class WebContentController extends AdminBaseController {

	@RequestMapping(value = { "/system/web/contentList" }, method = RequestMethod.GET)
	public ModelAndView contentList(HttpServletRequest request) throws Exception {
		ModelAndView view = createSuccessModelAndView("system/webContent/content-list", null);
		return view;
	}

	@RequestMapping(value = { "/system/web/contentPage" }, method = RequestMethod.GET)
	public ModelAndView contentPage(HttpServletRequest request, Integer page) throws Exception {
		List<Where> whereList = new ArrayList<Where>();
		String keywords = RequestUtil.getString(request, "keywords", null);
		if (StringUtils.isNotEmpty(keywords)) {
			whereList.add(Where.likeAll(SysSystemConfigDomain.NAME, keywords));
		}
		String[] nids = new String[]{"site_name","site_logo","site_discription","site_keywords","site_license_number","site_copyright"
		,"service_tel","service_qq","service_qq_name","service_qq_group","service_qq_group_name","service_weixin_imgcode"
		,"service_weibo_url","service_hours","benchmark_rate","warnning_key"};
		whereList.add(Where.in(SysSystemConfigDomain.NID,nids));
		Page<SysSystemConfig> pages = this.baseService.getPage(SysSystemConfig.class, whereList, page == null ? 1 : page+1,
				Parameter.pageSize(10),
				Parameter.orderBy(OrderBy.asc("id")));
		ModelAndView mview = createSuccessModelAndView("system-management/webContent/content-page",pages);
		mview.addObject("imgPath", PropertiesUtil.getImageHost());
		return mview;
	}

	@RequestMapping(value = { "/system/web/edit" }, method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request, Long id) throws Exception {
		SysSystemConfig config = baseService.getById(SysSystemConfig.class, id, null);
		if("service_weixin_imgcode".equals(config.getNid()) || "site_logo".equals(config.getNid())){
			config.setValue(PropertiesUtil.getImageHost() + config.getValue());
		}
		ModelAndView view = createSuccessModelAndView("system-management/webContent/content-edit", config);
		//提示信息
		String description = "";
		String imgDes = "";
		if("site_name".equals(config.getNid())){//网站名称
			description = "配置网站名称,Title显示";
		}else if("site_logo".equals(config.getNid())){//网站logo
			imgDes = "请上传网站logo,在网站前台显示,宽*高：310px*70px";
		}else if("site_discription".equals(config.getNid())){//配置平台关键字，用于用户搜索平台时使用
			description = "配置平台关键字，用于用户搜索平台时使用";
		}else if("site_license_number".equals(config.getNid())){//在网站底部显示
			description = "在网站底部显示";
		}else if("site_copyright".equals(config.getNid())){//在网站底部显示
			description = "在网站底部显示";
		}else if("service_tel".equals(config.getNid())){//在前台‘联系我们’中的财富热线和头部左上角的服务热线显示
			description = "在前台‘联系我们’中的财富热线和头部左上角的服务热线显示";
		}else if("service_qq".equals(config.getNid())){//多个QQ用' | '隔开
			description = "多个QQ用' | '隔开";
		}else if("service_qq_name".equals(config.getNid())){//名称对应服务QQ,多个用' | '隔开
			description = "名称对应服务QQ,多个用' | '隔开";
		}else if("service_qq_group".equals(config.getNid())){//请登录“QQ一键加群”官网http://qun.qq.com/join.html ，选择您创建客服群后，拷贝kdkey部分粘贴到此；多个客服群idkey使用“|”隔开。 参考图：
			description = "请登录“QQ一键加群”官网http://qun.qq.com/join.html ，选择您创建客服群后，拷贝kdkey部分粘贴到此；多个客服群idkey使用“|”隔开。";
		}else if("service_qq_name".equals(config.getNid())){//名称对应服务QQ群名称,多个用' | '隔开
			description = "名称对应服务QQ群名称,多个用' | '隔开";
		}else if("service_weixin_imgcode".equals(config.getNid())){//请上传网站二维码,在网站前台显示
			imgDes = "请上传网站二维码,在网站前台显示";
		}else if("service_weibo_url".equals(config.getNid())){//请填写http://为开头的新浪微博网址
			description = "请填写http://为开头的新浪微博网址";
		}
		view.addObject("description", description);
		view.addObject("imgDes", imgDes);
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/system/web/save", method = RequestMethod.POST)
	public DyResponse saveArticles(HttpServletRequest request) throws Exception {
		String name=request.getParameter("webName");
		if(StringUtils.isBlank(name)){
			return createErrorJsonResponse("名称不能为空");
		}
		Long id = RequestUtil.getLong(request, "id", null);
		SysSystemConfig config = baseService.getById(SysSystemConfig.class, id, null);
		if("service_weixin_imgcode".equals(config.getNid()) || "site_logo".equals(config.getNid())){
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Map<String, String> imgMap = fileUpload(multiRequest, "system", "config");
			if(imgMap != null && imgMap.get("imgValue") != null){
				config.setValue(imgMap.get("imgValue"));
			}
		}else{
			String value = RequestUtil.getString(request, "value", null);
			config.setValue(value);
			config.setName(name);
		}
		baseService.updateById(config, config.getId());
		DyResponse response = createSuccessJsonResponse(null);
		//添加后台日志
		this.setAdminLogAtrribute(response,new String[]{"name"},new Object[]{config.getName()});
		return response;
	}
}