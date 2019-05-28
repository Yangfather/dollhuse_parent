package com.dollhouse.api.entity;

/**
 * 报表基本类
 * 
 * @author zbs
 *
 */
public abstract class BaseReport {

    private int report_type; // 类型 （1:月报 2:季度报表 3:半年报,4:年报）

    private int report_year; // 报表年份

    private int report_month; // 报表月份(report_type=1值为1-12，report_type=2值为1-4,report_type=3值为1-2,report_type=4值为1)

    public int getReport_type() {
        return report_type;
    }

    public void setReport_type(int report_type) {
        this.report_type = report_type;
    }

    public int getReport_year() {
        return report_year;
    }

    public void setReport_year(int report_year) {
        this.report_year = report_year;
    }

    public int getReport_month() {
        return report_month;
    }

    public void setReport_month(int report_month) {
        this.report_month = report_month;
    }

}

