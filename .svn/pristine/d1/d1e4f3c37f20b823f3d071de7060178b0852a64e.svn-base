package com.dollhouse.admin.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.dollhouse.admin.entity.GoodsDetial;
import com.dollhouse.core.controller.BaseController;
import com.dollhouse.core.dao.query.OrderBy;
import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.core.entity.Page;
import com.dollhouse.core.exception.DaoException;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.core.utils.DataConvertUtil;
import com.dollhouse.core.utils.OptionUtil;
import com.dollhouse.core.utils.StringUtils;
import com.dollhouse.entity.comm.DollName;
import com.dollhouse.entity.comm.DollType;
import com.dollhouse.entity.comm.domain.DollNameDomain;
import com.dollhouse.entity.comm.domain.DollTypeDomain;
import com.dollhouse.service.comm.CommonService;
import com.dollhouse.service.statistical.GoodsInfoService;

@Controller
public class GoodsManagerController  extends BaseController{
	private static final String FILE_PATH = "assets/img/";
	@Autowired
	private BaseService baseService;
	@Autowired
	private CommonService commonService;
	@Autowired
	protected GoodsInfoService goodsInfoService;
	@Autowired
	protected OptionUtil optionUtil;
//	方法一：
	//@RequestMapping(value={"public/admin/goodsInfo"},method=RequestMethod.POST)
	public String addGoods(@RequestParam("file")  CommonsMultipartFile file,  HttpServletRequest request){
		System.out.println("filename"+file.getOriginalFilename());
		
		return null;
		
	}
//	学习网址：http://blog.csdn.net/chenchunlin526/article/details/70945877
	 @RequestMapping("/public/admin/goodsImg")
	 @ResponseBody
	    public DyResponse fileUpload3(  HttpServletRequest request,HttpServletResponse response) throws IOException, NumberFormatException, DaoException{
	    	
	    	 long  startTime=System.currentTimeMillis();
	    	 String name = request.getParameter("goodsName");
	    	 String type = request.getParameter("type");
	    	 String price1 = request.getParameter("price1");
	    	 String price2 = request.getParameter("price2");
	    	 String color = request.getParameter("color");
	    	 String desprtion = request.getParameter("desprtion");
	    	 String goodsType = request.getParameter("goodsTypes");
	    	 String goodsPrice = request.getParameter("goodsPrices");
	    	 System.out.println("request"+name+type+price1+price2+color+desprtion+goodsType+goodsPrice);
	    	// 先实例化一个文件解析器
	         CommonsMultipartResolver coMultiResolver = new CommonsMultipartResolver(request.getSession() .getServletContext());
	         List<String> fileUrlList = new ArrayList<String>();
	         // 判断request请求中是否有文件上传
	         String filePath="";
	         if (coMultiResolver.isMultipart(request)) {
	              //用来保存文件路径，用来jsp页面遍历回显
	             // 转换Request
	             MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	             // 获得文件，方式一
	             List<MultipartFile> files = multiRequest.getFiles("file");
	             
	             for (MultipartFile file : files) { //循环遍历，取出单个文件，下面的操作和单个文件就一样了
	                 if (!file.isEmpty()) { //这个判断必须要加
	                	//根据混合方法
	                     String tmpStr = UUID.randomUUID().toString().replace("-","") ;
	                     // 获得原始文件名
	                     String fileName = file.getOriginalFilename();
	                     String extName=fileName.substring(0, fileName.lastIndexOf("."));
	                     String newfileName = tmpStr+extName;
	                     //获得物理路径webapp所在路径
	                     String pathRoot = request.getSession().getServletContext().getRealPath("");
	                     // 项目下相对路径
	                     String path = FILE_PATH + newfileName;
	                     // 创建文件实例
	                     File tempFile = new File(pathRoot +"/"+ path); //文件保存路径为pathRoot + path
	                     if (!tempFile.getParentFile().exists()) {
	                         tempFile.getParentFile().mkdir();
	                     }
	                     if (!tempFile.exists()) {
	                         tempFile.mkdir();
	                     }
	                     try {
	                         file.transferTo(tempFile);//上传
	                     } catch (IllegalStateException e) {
	                         e.printStackTrace();
	                     } catch (IOException e) {
	                         e.printStackTrace();
	                     }
	                     fileUrlList.add(path);//相对路径
	                     filePath=filePath+path+",";
	                 }
	             }
	         }
	        DollName dollName = new DollName();
	         dollName.setDollGoodsName(name);
	         dollName.setDollType(type);
	         dollName.setDollOrgancode(organcodeCreate(type));
	         dollName.setDollColor(color);
	         dollName.setDollImgUrl(filePath);
	         BigDecimal lowerPrice=new BigDecimal(price1);
	         dollName.setLowerPrice(lowerPrice);
	         BigDecimal heigherPrice=new BigDecimal(price2);
	         dollName.setHeigherPrice(heigherPrice);
	         long result = baseService.insert(dollName);
	        
	         if (result>0) {
				String[] prices = subStrirng(goodsPrice);
				String[] types = subStrirng(goodsPrice);
				List<DollType> dollTypes=new ArrayList<>();
				for (int i = 0; i < prices.length; i++) {
					DollType dollType=new DollType();
					dollType.setDollNameId(result);
					dollType.setDollTypeName(types[i]);
					BigDecimal  price=new BigDecimal(prices[i]);
					dollType.setDollTypePrice(price);
					dollTypes.add(dollType);
				}
				long resulttype = baseService.insert(dollTypes);
				if (resulttype==1) {
					return createSuccessJsonResponse(null);
				}
				
			}
	    	 long  endTime=System.currentTimeMillis();
	    	 /*System.out.println("方法四的运行时间："+String.valueOf(endTime-startTime)+"ms");*/
			return createSuccessJsonResponse(null);
	    }
	 
	 @RequestMapping(value={"/admin/manager/changePage"},method=RequestMethod.GET)
	 public ModelAndView getPage(Integer page,DollName dollName){
//		 调用方法page
		Page dollPage = page(page, dollName);
		return createSuccessModelAndView("goods-manager/change-page",
				new DataConvertUtil(dollPage)
				.setStatus(DollNameDomain.DOLL_TYPE,optionUtil.getGoodsType()).convert());
	 }
	 
	 @RequestMapping(value={"admin/manager/detail"},method=RequestMethod.GET)
	 public ModelAndView goodsDetial(Long id,Model model){
		/* System.out.println("id="+id);*/
//		 调用方法detial
		GoodsDetial detial = detial(id);
		return createSuccessModelAndView("goods-manager/change-detial", detial);
	 }
	 
	 @RequestMapping(value={"/admin/manager/change"},method=RequestMethod.POST)
	 public String goodsChange(HttpServletRequest request){
		 /*System.out.println("请求修改");*/
		 DollName dollName=new DollName();
//		 id
		 String nameId = request.getParameter("nameId");
		 Long id=new Long(nameId);
//		 name
		 String name = request.getParameter("name");
		 dollName.setDollGoodsName(name);
//		 color
    	 String color = request.getParameter("color");
    	 dollName.setDollColor(color);
//		 urls
    	 String urls = request.getParameter("urls");
    	 dollName.setDollImgUrl(urls);
//    	 lowerPrice
    	 String price1 = request.getParameter("lowerPrice");
    	 BigDecimal lowerPrice=new BigDecimal(price1);
    	 dollName.setLowerPrice(lowerPrice);
//    	 heigherPrice
    	 String price2 = request.getParameter("heigherPrice");
    	 BigDecimal heigherPrice=new BigDecimal(price2);
    	 dollName.setHeigherPrice(heigherPrice);
//    	 status
    	 String status = request.getParameter("status");
    	 Long dollStatus=new Long(status);
    	 dollName.setDollStatus(dollStatus);
//    	 desprtion
    	 String desprtion = request.getParameter("types");
    	 dollName.setDespriton(desprtion);
//    	 prices
    	 String goodsPrice = request.getParameter("prices");
    	 try {
			baseService.updateById(dollName, id, false);
		} catch (DaoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	 if(goodsPrice!=null){
    		    	 String[] prices = subStrirng(goodsPrice);
    				String[] types = subStrirng(goodsPrice);
    					for (int i = 0; i < prices.length; i++) {
    						DollType dollType=new DollType();
    						
    						dollType.setDollNameId(id);
    						dollType.setDollTypeName(types[i]);
    						dollType.setDollTypePrice(BigDecimal.valueOf(Long.valueOf(prices[i])));
    						List<Where> whereList = null;
    						whereList.add(Where.eq(DollTypeDomain.DOLL_NAME_ID, id));
    						try {
    							baseService.update(dollType, whereList, false);
    						} catch (DaoException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
    					}
    	 }
			return null;
	 }
//	 获取删除商品列表
	 @RequestMapping(value={"/admin/manager/deletePage"},method=RequestMethod.GET)
	 public ModelAndView deletePage(Integer page,DollName dollName){
//		 调用方法page
		Page dollPage = page(page, dollName);
		return createSuccessModelAndView("goods-manager/delete-page",
				new DataConvertUtil(dollPage)
				.setStatus(DollNameDomain.DOLL_TYPE,optionUtil.getGoodsType()).convert());
	 }
//	 商品删除
	 @RequestMapping(value={"admin/manager/delete"},method=RequestMethod.GET)
	 public ModelAndView  delete(Long id){
		 try {
			baseService.deleteById(DollName.class, id);
			List<Where> whereList=new ArrayList<>();
			 whereList.add(Where.eq(DollTypeDomain.DOLL_NAME_ID, id));
			baseService.delete(DollType.class, whereList);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return createSuccessModelAndView("goods-manager/delete-goods", null);
		 
	 }
	 
//	 查询
	  @RequestMapping(value={"/admin/goodsManager/goodsSearch"},method=RequestMethod.GET)
	    public ModelAndView searchGoods(Integer page,DollName dollName){
//			 调用方法page
			Page dollPage = page(page, dollName);
			return createSuccessModelAndView("goods-manager/search-page",
					new DataConvertUtil(dollPage)
					.setStatus(DollNameDomain.DOLL_TYPE,optionUtil.getGoodsType()).convert());
	    }
	  @RequestMapping(value={"/admin/manager/searchDetial"},method=RequestMethod.GET)
		 public ModelAndView searchDetial(Long id,Model model){
//			 调用方法detial
			GoodsDetial detial = detial(id);
			return createSuccessModelAndView("goods-manager/search-detial", detial);
		 }
	  /**
	   * 提供方法
	   * organcodeCreate  商品编码生成
	   * subStrirng  字符串拆分，根据","拆分
	   * page 获取分页数据列表
	   * detial 获取商品详情
	   * @param type
	   * @return
	   */
	    public Long organcodeCreate(String type) {
	    	List<Where> whereList = new ArrayList<>();
	    	whereList.add(Where.eq(DollNameDomain.DOLL_TYPE, type));
			List<DollName> organcode;
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
			String time = df.format(new Date());
			Long date=Long.valueOf(time);
			long goodOrgancode;
			try {
				organcode = baseService.getList(DollName.class, whereList,Parameter.queryColumn(new String[]{DollNameDomain.DOLL_ORGANCODE}),Parameter.limit(1), Parameter.orderBy(OrderBy.desc("id")));
				goodOrgancode =organcode.get(0).getDollOrgancode()+001 ;
				return goodOrgancode;
			} catch (DaoException e) {
				goodOrgancode =Long.valueOf(type) +date+001;
				return goodOrgancode;
			}
	    }
	    
	    public  String[]  subStrirng(String str){
	    	String[] arr=str.split(",");
			return arr;
	    	
	    }
//	    获取分页列表的信息
	    public Page page(Integer page,DollName dollName){
	    	 Page<DollName> dollPage=null;
			 try {
				 List<Where> whereList=new ArrayList<>();
				 if(!StringUtils.isEmpty(dollName.getDollGoodsName())){
					 whereList.add(Where.like(DollNameDomain.DOLL_GOODS_NAME, dollName.getDollGoodsName()));
				 }
				 String organcode=null;
				 if(dollName.getDollOrgancode()!=null){
					 organcode=String.valueOf(dollName.getDollOrgancode());
					 whereList.add(Where.eq(DollNameDomain.DOLL_ORGANCODE, organcode));
				 }
				if(whereList.size()<=0){
					 whereList.add(Where.ge(DollNameDomain.DOLL_STATUS, 0));
				 }
				dollPage=baseService.getPage(DollName.class,
						whereList,
						page==null?1:page+1 ,
								Parameter.queryColumn(new String[]{DollNameDomain.ID,DollNameDomain.DOLL_GOODS_NAME,DollNameDomain.DOLL_ORGANCODE,DollNameDomain.DOLL_TYPE}),
								Parameter.orderBy(OrderBy.asc(DollNameDomain.ID))
				);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dollPage;
	    }
//	    获取详情
	    public GoodsDetial detial(Long id){
	    	 List<Where> whereList=new ArrayList<>();
			 whereList.add(Where.eq(DollNameDomain.ID, id));
			 DollName goods = new DollName();
			try {
				goods = baseService.getOne(DollName.class, 
						 whereList,
						 Parameter.queryColumn(new String[]{DollNameDomain.ID,DollNameDomain.DOLL_GOODS_NAME,DollNameDomain.DOLL_ORGANCODE,
								 DollNameDomain.DOLL_IMG_URL,DollNameDomain.DOLL_COLOR,DollNameDomain.DOLL_TYPE,DollNameDomain.DOLL_STATUS,
								 DollNameDomain.LOWER_PRICE,DollNameDomain.HEIGHER_PRICE}));
			} catch (DaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 GoodsDetial detial=new GoodsDetial();
//				商品名称
				detial.setName(goods.getDollGoodsName());
//				商品ID
				detial.setId(goods.getId());
//				商品编号
				detial.setOrganCode( goods.getDollOrgancode());
//				颜色部分
				detial.setColor(goods.getDollColor());
//				最低价
				detial.setLowerPrice(goods.getLowerPrice());
//				最高价
				detial.setHeigherPrice(goods.getHeigherPrice());
//				商品状态
				detial.setStatus(goods.getDollStatus());
//				商品描述
				detial.setDespriton(goods.getDespriton());
//				图片部分
				String filePath =  goods.getDollImgUrl();
				String[] paths=filePath.split(",");
				List<GoodsDetial> goodsImgUrl=new ArrayList<>();
				for (String aa : paths) {
					GoodsDetial subdetial=new GoodsDetial();
					subdetial.setURL(aa);
					goodsImgUrl.add(subdetial);
				}
				detial.setImgUrl(goodsImgUrl);
//				型号部分
				List<GoodsDetial> types=new ArrayList<>();
				List<DollType> typeList = new ArrayList<>();
				try {
					List<Where> whereList1=new ArrayList<>();
					whereList1.add(Where.eq(DollTypeDomain.DOLL_NAME_ID, id));
					typeList = baseService.getList(DollType.class,
							whereList1,
							Parameter.queryColumn(new String[]{DollTypeDomain.TYPE_ID,DollTypeDomain.DOLL_NAME_ID,DollTypeDomain.DOLL_TYPE_NAME,
									DollTypeDomain.DOLL_TYPE_NUM,DollTypeDomain.DOLL_TYPE_PRICE}));
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (typeList.size()>0) {
					for (DollType good : typeList) {
						GoodsDetial subdetial=new GoodsDetial();
						subdetial.setId(good.getTypeId());
						subdetial.setPrice(good.getDollTypePrice());
						subdetial.setType(good.getDollTypeName());
						types.add(subdetial);
					}
					detial.setTypes(types);
				}
			return detial;
	    	
	    }
}
