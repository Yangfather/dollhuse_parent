package com.dollhouse.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dollhouse.core.dao.query.OrderBy;
import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.BaseEntity;
import com.dollhouse.core.entity.Option;
import com.dollhouse.core.service.BaseService;



@Repository
public class OptionUtil {
	
	protected static Logger logger = Logger.getLogger(OptionUtil.class);

	
	@Autowired
	private BaseService baseService;
	
	/**
	 * 管理员状态
	 * @return
	 */
	public List<Option> getAdminStatus() {
		return generateOption(new Integer[]{1, -1},  new String[]{"正常", "锁定"});
	}
	
	/**
	 * 平台审核状态
	 */
	public List<Option> getPlatformStatus() {
		return generateOption(new Object[]{0, 1, -1}, new String[]{"待审核", "通过", "不通过"});
	}
	/**
	 * 微信关注时是否回复
	 * @return
	 */
	public List<Option> getReplyType(){
		return generateOption(new Object[]{2,1}, new String[]{"是","否"});
	}
	
	/**
	 * 文章类型
	 */
	public <T extends BaseEntity> List<Option> getArticlesCategory(Class<T> clazz){
		return this.generateOption(clazz, null);
	}
	
	/**
	 * 文章状态
	 */
	public List<Option> getArticlesStatus(){
		return generateOption(new Object[]{1,0,-1}, new String[]{"发布","草稿","关闭"});
	}
	
	/**
	 * 托管方式
	 * @return
	 */
	public List<Option> getTrustType() {
		return generateOption(new Object[]{0, 1, 2, 3}, new String[]{"网关", "易宝托管", "汇付托管", "双乾托管"});
	}
	
	/**
	 * 获取省份
	 * @return
	 */
	public List<Option> getProvices() {
		return generateOption(new Object[]{1}, new String[]{"天津"});
	}
	
	/**
	 * 获取城市
	 * @return
	 */
	public List<Option> getCitys() {
		return generateOption(new Object[]{1}, new String[]{"天津市"});
	}
	
	/**
	 * 获取地区
	 * @return
	 */
	public List<Option> getAreas() {
		return generateOption(new Object[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 },
				new String[] { "和平区", "河东区", "河西区", "南开区", "河北区", "红桥区", "塘沽区", "汉沽区", "大港区", "东丽区", "西青区", "津南区", "北辰区", "武清区", "宝坻区", "静海县", "蓟县" });
	}
	
	/**
	 * 广告类型
	 */
	public <T extends BaseEntity> List<Option> getAdvertCategory(Class<T> clazz){
		return this.generateOption(clazz, null);
	}
	
	/**
	 * 友链类型
	 */
	public <T extends BaseEntity> List<Option> getLinksCategory(Class<T> clazz){
		return this.generateOption(clazz, null);
	}
	
	/**
	 * 正常，关闭
	 */
	public List<Option> getOpenClose(){
		return generateOption(new Object[]{1,-1}, new String[]{"正常","关闭"});
	}
	
	/**
	 * 标的类型
	 */
	public List<Option> getLoanCategory(){
		return generateOption(new Object[]{1,2,3,4,9}, new String[]{"信用标","担保标","抵押标","流转标","其他"});
	}
	
	/**
	 * 标的状态
	 */
	public List<Option> getLoanStatus(){
		return generateOption(new Object[]{0,1,2,3,4,5,6,7,8,9,10}, new String[]{"还款中","逾期一个月以内","逾期一个月至三个月","逾期三个月至六个月","逾期六个月至一年","逾期一年以上","已结清","已撤销","展期","坏账","提前还款"});
	}
	
	public List<Option> getRepayStatus(){
		return generateOption(new Object[]{-1,1}, new String[]{"还款中","已还完"});
	}
	
	/**
	 * 担保方式
	 */
	public List<Option> getVouchType(){
		return generateOption(new Object[]{112601,112602,112603,112604,112605}, new String[]{"信用","保证","质押","抵押","联保"});
	}
	
	/**
	 * 平台状态
	 */
	public List<Option> getPlatformBizStatus(){
		return generateOption(new Object[]{0,1,2,3,4}, new String[]{"正常运营","提现困难","跑路","停业","经侦介入"});
	}
	/**
	 * 计息方式
	 */
	public List<Option> getInterestsType(){
		return generateOption(new Object[]{1,3,5,6,7,12,13,99}, new String[]{"按月计息","按季计息","等额还款","等本还款","等本等息","按年计息","先息后本","利随本清"});
	}
	
	/**
	 *绑定状态
	 */
	public List<Option> getBindStatus(){
		return generateOption(new Object[]{-1,1}, new String[]{"未绑定","绑定"});
	}
	
	/**
	 * 标种类型
	 */
	public <T extends BaseEntity> List<Option> getLoanCategory(Class<T> clazz){
		return this.generateOption(clazz, null);
	}
	
	/**
	 * 站内信状态
	 */
	public List<Option> getMessageStatus(){
		return generateOption(new Object[]{-1,1,2}, new String[]{"删除","发送成功(未阅读)","已阅读"});
	}
	
	/**
	 * 站内信多条状态
	 */
	public List<Option> getMessageMulStatus(){
		return generateOption(new Object[]{-2,-1,1}, new String[]{"草稿","删除","发送成功"});
	}
	
	/**
	 * 证件类型
	 */
	public List<Option> getCertificateType(){
		return generateOption(new Object[]{0,1}, new String[]{"身份证","营业执照"});
	}
	
	/**
	 * 抵押物类型
	 */
	public List<Option> getPawnType(){
		return generateOption(new Object[]{0,1,2}, new String[]{"房产","汽车","其他"});
	}

	/**
	 * 还款方式
	 * @return
	 */
	public List<Option> getRepayType(){
		return generateOption(new Object[]{0,1}, new String[]{"一次性还款","分期还款"});
	}
	
	/**
	 * 还款类别
	 */
	public List<Option> getRepyPeriodType(){
		return generateOption(new Object[]{1,2,3,4}, new String[]{"正常","提前","逾期","展期"});
	}
	
	/**
	 * 平台类型
	 */
	public List<Option> getPlatformType(){
		return generateOption(new Object[]{1,2,3,4,5}, new String[]{"民营系","国资系","上市公司系","银行系","风投系"});
	}
	
	/**
	 * 平台类型
	 */
	public List<Option> getProjectType(){
		return generateOption(new String[]{"1","2","3","4","5"}, new String[]{"借贷","众筹","文化艺术品交易","资产交易","小贷"});
	}
	
	/**
	 * 期限单位
	 */
	public List<Option> getLoanSpan(){
		return generateOption(new Object[]{0,1}, new String[]{"天","月"});
	}
	/**
	 * 贷款状态
	 */
	public List<Option> getLoanState(){
		return generateOption(new Object[]{0,1,2,3,4,5,6,7,8,9,10}, new String[]{"正常","逾期1个月以内","逾期1个月－3个月","逾期3个月－6个月","逾期6个月－1年","逾期1年以上","结清","撤销","展期","坏账","提前还款"});
	}
	
	/**
	 * 短信类型
	 */
	public List<Option> getSmsType(){
		return generateOption(new Object[]{1,2,3,4}, new String[]{"注册短信", "重置密码","手机认证","手机解绑"});
	}
	
	/**
	 * 短信状态
	 */
	public List<Option> getSmsStatus(){
		return generateOption(new Object[]{-2,-1,1,2}, new String[]{"待发送", "失败","成功","发送中"});
	}
	
	/**
	 * 邮箱类型
	 */
	public List<Option> getEmailType(){
		return generateOption(new Object[]{1,2,3,4,5,6,7,8,9}, new String[]{"注册认证", "重置密码", "重置邮箱", "邮箱解绑","重新绑定", "找回密码", "绑定邮箱","发送密钥","短信余额"});
	}
	
	/**
	 * 邮箱状态
	 */
	public List<Option> getEmailStatus(){
		return generateOption(new Object[]{-2,1,-1}, new String[]{"未发送", "已发送","发送失败"});
	}
	
	/**
	 * 用户状态
	 */
	public List<Option> getUserStatus(){
		return generateOption(new Object[]{-2,-1,1}, new String[]{"登录锁定", "系统锁定","正常"});
	}
	
	/**
	 * 用户状态修改
	 */
	public List<Option> getUserEditStatus(){
		return generateOption(new Object[]{-1,1}, new String[]{"系统锁定","正常"});
	}
	/**
	 * 生成Option list
	 * @param value
	 * @param text
	 * @return
	 */
	public List<Option> generateOption(Object[] value, String[] text) {
		if(value == null || text == null || value.length != text.length)
			return new ArrayList<Option>();
		
		List<Option> options = new ArrayList<Option>();
		for(int i=0;i<value.length;i++) {
			Option option = new Option();
			option.setValue(value[i]);
			option.setText(text[i]);
			options.add(option);
		}
		return options;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> List<Option> generateOption(Class<T> clazz, List<Where> whereList) {
		List<Option> options = new ArrayList<Option>();
		try {
			if (whereList == null || whereList.size() < 1) {
				whereList = new ArrayList<Where>();
				whereList.add(Where.notEq("id", -1l));
			}
			List<Map> dataList = baseService.getListWithMap(clazz, whereList, Parameter.orderBy(OrderBy.asc("id")));
			if (dataList == null)
				dataList = new ArrayList<Map>();
			for (Map<String, Object> map : dataList) {
				Object value = map.get("id");
				if (value instanceof Double)
					value = ((Double) value).intValue();

				Option option = new Option();
				option.setValue(value);
				option.setName(value.toString());
				option.setText(map.get("name") == null ? "" : map.get("name").toString());

				options.add(option);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return options;
	}
	

	/**
	 * 财务报表类型
	 */
	public List<Option> getAccountReportType(){
		return generateOption(new Object[]{1,2,3,4}, new String[]{"月报","季报","半年报","年报"});
	}
	/**
	 * dollhouse首页图片展示位置
	 */
	public List<Option> getLocation(){
		return generateOption(new Object[]{1,1-1,2,2-1,2-2,3},new String[]{"头部导航条","头部导航栏子菜单","左侧导航栏","左侧导航栏一级菜单","左侧导航栏二级菜单","轮播图片"});
		
	}
	/**
	 * dollhouse商品类别
	 */
	public List<Option> getGoodsType(){
		return generateOption(new Object[]{1,2,3,4,5,6,7,8},new String[]{"棋牌","益智","亲子","动画","布偶","童车","配件","户外"});
		
	}
	/**
	 * dollhouse商品类别
	 */
	public List<Option> getMenuType(){
		return generateOption(new Object[]{20,29,38,47,58,67,68,69,70,71},new String[]{"益智玩具","模型玩具","电子/电动玩具","游艺设备","衍生玩具","搞笑玩具","传统玩具","童车","玩具配件","diy玩具"});
		
	}
	/**
	 * dollhouse订单状态
	 */
	public List<Option> getOrderType(){
		return generateOption(new Object[]{1,2},new String[]{"暂不发货","发货"});
		
	}
}