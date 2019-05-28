package com.dollhouse.admin.schedule;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.exception.DaoException;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.core.utils.DateUtil;
import com.dollhouse.entity.comm.DollOrder;
import com.dollhouse.entity.comm.DollStar;
import com.dollhouse.entity.comm.domain.DollOrderDomain;
import com.dollhouse.service.statistical.GoodsInfoService;

@Component
public class UserGoodsShowSchedule extends BaseSchedule {
	@Autowired
	protected BaseService baseService; 
	@Autowired
	private GoodsInfoService goodsInfoService;
	@Scheduled(cron="0 30 9 * * ?")
	public void getStarGoods(){
		
		List<Map<String, Object>> starList = goodsInfoService.starGoods();
		for (Map<String, Object> map : starList) {
			DollStar dollStar=new DollStar();
			if(map.get("doll_img")!=null){
				dollStar.setDollImgurl(String.valueOf(map.get("doll_img")));
			}else {
				dollStar.setDollImgurl("#");
			}
//			价格
			String string = String.valueOf(map.get("doll_price"));
			BigDecimal price=new BigDecimal(string);
			dollStar.setDollLowerprice(price);
//			名字
			dollStar.setDollName(String.valueOf(map.get("doll_name")));
//			编号
			dollStar.setDollOrgancode(String.valueOf(map.get("doll_organcode")));
//			时间
			dollStar.setAddTime(String.valueOf(DateUtil.getCurrentTime()));
			try {
				baseService.insert(dollStar);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Scheduled(cron="0 46 17 * * ?")
	public void saleStatistics() throws Exception{
		Long time = DateUtil.getCurrentTime();
		Date date = new Date();//获取当前时间    
		Calendar calendar = Calendar.getInstance();    
		calendar.setTime(date);    
//		calendar.add(Calendar.YEAR, -1);//当前时间减去一年，即一年前的时间    
//		calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间    
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式 
		String dataTime = df.format(calendar.getTime());
		
		List<Where> whereList=new ArrayList<>();
		addAndWhereCondition(whereList,DollOrderDomain.BUY_TIME, DateUtil.convert(dataTime+" 00:00:00"), DateUtil.convert(dataTime+" 23:59:59"));
		try {
			baseService.getList(DollOrder.class, whereList, Parameter.queryColumn(new String[]{}));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
