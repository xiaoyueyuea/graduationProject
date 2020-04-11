package com.yuelei.controller.financialManagerController;

import com.yuelei.dao.FinancialDao;
import com.yuelei.entity.FinancialManagerEntity;
import com.yuelei.entity.RoomEntity;
import com.yuelei.entity.bean.DataGridResult;
import com.yuelei.entity.bean.FinancialManagerBean;
import com.yuelei.service.RoomBookService;
import com.yuelei.util.SimpleExcelView;
import com.yuelei.util.WebJsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/financialManager")
public class FinancialManagerController {

    @Autowired(required = false)
    private RoomBookService roomBookService;

    @Autowired(required = false)
    private FinancialDao financialDao;

    @RequestMapping(path = "/financialManagerPage")
    public String financialManagerPage(Model model){
        List<RoomEntity> roomEntityList = roomBookService.getAllTypeRoomInfo();
        List<String> types=new ArrayList<>();
        for(RoomEntity roomEntity : roomEntityList){
            types.add(roomEntity.getType());
        }
        model.asMap().put("types_getFinancialIncome",types);
        return "financialManager/financialIncome";
    }

    @RequestMapping(path = "/getAllFinancialIncome")
    public @ResponseBody
    WebJsonResult getAllFinancialIncome(){
        List<FinancialManagerEntity> financialManagerEntityList = financialDao.getAllFinancialManagerEntity();
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(financialManagerEntityList);
        dataGridResult.setTotal(financialManagerEntityList.size());
        if(dataGridResult.getTotal()!=0){
            return WebJsonResult.newSuccess(dataGridResult);
        }
        return WebJsonResult.newFailure("未查询到相关收益");
    }

    @RequestMapping(path = "/getAllFinancialIncomeByCondition_{startDate}_{endDate}_{roomType}")
    public @ResponseBody
    WebJsonResult getAllFinancialIncomeByCondition(@PathVariable String startDate,@PathVariable String endDate,@PathVariable String roomType){
        List<FinancialManagerEntity> financialManagerEntityList = financialDao.getFinancialManagerEntityListByCondition(startDate,endDate,roomType);
        List<FinancialManagerBean> financialManagerBeanList = new ArrayList<>();
        for(FinancialManagerEntity financialManagerEntity : financialManagerEntityList){
            FinancialManagerBean bean = new FinancialManagerBean();
            bean.setOrderId(financialManagerEntity.getOrderId());
            bean.setRoomType(financialManagerEntity.getRoomType());
            bean.setRoomPrice(financialManagerEntity.getRoomPrice());
            bean.setStartDate(financialManagerEntity.getStartDate());
            bean.setEndDate(financialManagerEntity.getEndDate());
            bean.setDays(financialManagerEntity.getDays());
            bean.setRoomCharge(financialManagerEntity.getRoomCharge());
            bean.setPriceDifference(financialManagerEntity.getPriceDifference());
            bean.setOtherCost(financialManagerEntity.getOtherCost());
            String totalCost = String.valueOf(Float.parseFloat(financialManagerEntity.getRoomCharge())+Float.parseFloat(financialManagerEntity.getPriceDifference())+Float.parseFloat(financialManagerEntity.getOtherCost()));
            bean.setTotalCost(totalCost);
            financialManagerBeanList.add(bean);
        }
        DataGridResult dataGridResult =new DataGridResult();
        dataGridResult.setRows(financialManagerBeanList);
        dataGridResult.setTotal(financialManagerBeanList.size());
        if(dataGridResult.getTotal()!=0){
            return WebJsonResult.newSuccess(dataGridResult);
        }
        return WebJsonResult.newFailure("没有查询到相关账单");
    }

    @RequestMapping(path = "/exportExcel")
    public ModelAndView exportFinancialIncomeExcel(@RequestParam("startDate") String startDate,
                                                   @RequestParam("endDate") String endDate,
                                                   @RequestParam("roomType") String roomType){
        final WebJsonResult result = getAllFinancialIncomeByCondition(startDate,endDate,roomType);
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data", ((DataGridResult) result.getData()).getRows());
        modelAndView.setView(new SimpleExcelView());
        return modelAndView;
    }
}
