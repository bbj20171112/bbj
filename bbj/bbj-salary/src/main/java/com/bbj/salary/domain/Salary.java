package com.bbj.salary.domain;
import com.bbj.base.domain.BBJEntity;

public class Salary extends BBJEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String tableName = "fin_salary";
	
	public static final String baseSalary = "base_salary"; // 基本工资 
	public static final String meritPay = "merit_pay"; // 绩效工资 
	public static final String jobTypeAdd = "job_type_add"; // 职务补贴 
	public static final String serveAge = "serve_age"; // 工龄工资 
	public static final String professorAdd = "professor_add"; // 专家/炊事员费用 
	public static final String addCut = "add_cut"; // 补或扣 
	public static final String foodSite = "food_site"; // 伙补（现场）/月度 
	public static final String foodOffice = "food_office"; // 伙补（机关）/季度 
	public static final String weekendsAdd = "weekends_add"; // 周末加班 
	public static final String festivalAdd = "festival_add"; // 假日加班 
	public static final String siteAdd = "site_add"; // 驻点补助 
	public static final String remoteAdd = "remote_add"; // 远程补助 
	public static final String tiredAdd = "tired_add"; // 艰苦地区补助 
	public static final String addCut2 = "add_cut2"; // 补或扣2 
	public static final String drawPercent = "draw_percent"; // 提成保底奖金 
	public static final String driveAmount = "drive_amount"; // 驾驶员公里费 
	public static final String workAmount = "work_amount"; // 工作量奖金 
	public static final String addCut3 = "add_cut3"; // 补或扣3 
	public static final String bonusSafe = "bonus_safe"; // 安全生产奖 
	public static final String bonusCheck = "bonus_check"; // 月度考核 
	public static final String bonusCertificate = "bonus_certificate"; // 证书津贴/季度 
	public static final String bonusCommunicate = "bonus_communicate"; // 通讯奖励/季度 
	public static final String bonusHot = "bonus_hot"; // 高温费 
	public static final String travelExpense = "travel_expense"; // 差旅费 
	public static final String computer = "computer"; // 电脑自购补助 
	public static final String bonusAnnual = "bonus_annual"; // 年终奖 
	//public static final String meritPay2 = "merit_pay2"; // 绩效工资考核部分 
	public static final String goodStart = "good_start"; // 开门红 
	public static final String springFestival = "spring_festival"; // 春节过节费 
	public static final String companyPeer = "company_peer"; // 公司先进 
	public static final String midAutumn = "mid_autumn"; // 中秋过节费 
	public static final String dragonBoatFestival = "dragon_boat_festival"; // 端午节 
	public static final String bonusDriveClass = "bonus_drive_class"; // 驾驶班补助 
	public static final String doubleBeyondHalf = "double_beyond_half"; // 双过半奖 
	public static final String summerHot = "summer_hot"; // 迎峰度夏奖 
	public static final String special1 = "special1"; // 专1 
	public static final String special2 = "special2"; // 专2 
	public static final String special3 = "special3"; // 专3 
	public static final String special4 = "special4"; // 专4 
	public static final String total = "total"; // 合计 
	public static final String insuranceSelf = "insurance_self"; // 保险个缴 
	public static final String publicReserveFundsSelf = "public_reserve_funds_self"; // 公积金个缴 
	public static final String personIncomeTax = "person_income_tax"; // 个税 
	public static final String withhold = "withhold"; // 扣款 
	public static final String factTotal = "fact_total"; // 实际合计 
	public static final String insuranceCom = "insurance_com"; // 保险企缴 
	public static final String publicReserveFundsCom = "public_reserve_funds_com"; // 公积金企缴 

	
	@Override
	public String initTable() {
		return tableName;
	}
	
	
	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
			baseSalary, 
			meritPay, 
			jobTypeAdd, 
			serveAge, 
			professorAdd, 
			addCut, 
			foodSite, 
			foodOffice, 
			weekendsAdd, 
			festivalAdd, 
			siteAdd, 
			remoteAdd, 
			tiredAdd, 
			addCut2, 
			drawPercent, 
			driveAmount, 
			workAmount, 
			addCut3, 
			bonusSafe, 
			bonusCheck, 
			bonusCertificate, 
			bonusCommunicate, 
			bonusHot, 
			travelExpense, 
			computer, 
			bonusAnnual, 
			meritPay, 
			goodStart, 
			springFestival, 
			companyPeer, 
			midAutumn, 
			dragonBoatFestival, 
			bonusDriveClass, 
			doubleBeyondHalf, 
			summerHot, 
			special1, 
			special2, 
			special3, 
			special4, 
			total, 
			insuranceSelf, 
			publicReserveFundsSelf, 
			personIncomeTax, 
			withhold, 
			factTotal, 
			insuranceCom, 
			publicReserveFundsCom, 
		};
		return attrs;
	}


}