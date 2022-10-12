package com.learn.chapter_one;

import lombok.Data;

import java.io.Serializable;

/**
 * 报表数据
 * Create by liguo on 2022/7/12
 **/
@Data
public class StatementData extends Invoices {
    private Integer amount;
    private Integer totalAmount;
    private Integer totalVolumeCredits;

}
