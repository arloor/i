package datahelper.Impl;

import datahelper.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by moontell on 2017/5/15.
 */
@Service
public class StockDataHelperImpl{
    @Autowired
    private DataHelper dao;


//    public StockDailyInfo getStockDailyInfo(String code, String date) {
//        StockDailyInfo stockDailyInfo=null;
//        Connection connection =dao.getConnection();
//        Statement statement=dao.getStatement(connection);
//        //获取两天的数据
//        String sql="SELECT * FROM `stock_d_data` WHERE `code` = '"+code+"' AND `date` <= '"+date+"%'  ORDER BY `date` DESC LIMIT 0,2";
//
//        //String sql="SELECT * FROM `stock_d_data` WHERE `code` = '"+code+"' AND `date` LIKE '"+dateString+"%'";
//        System.out.println(sql);
//        try {
//            ResultSet resultSet=statement.executeQuery(sql);
//            if(resultSet.next()){
//                if(!resultSet.getString(2).startsWith(date))
//                    return null;
//                Double open=resultSet.getDouble(3);
//                Double close=resultSet.getDouble(4);
//                Double hign=resultSet.getDouble(5);
//                Double low=resultSet.getDouble(6);
//                Double volume=resultSet.getDouble(7);
//                //System.out.println(code+" "+dateString+" "+open+" "+close+" "+hign+" "+low+" "+volume);
//                if(resultSet.next()){
//                    Double yestodayClose=resultSet.getDouble(4);
//                    Double changePercent=(close-yestodayClose)/yestodayClose;
//                    DecimalFormat df = new DecimalFormat("#.####");
//                    String changePercentString =df.format(changePercent);
//                    changePercent=Double.parseDouble(changePercentString);
//                    stockDailyInfo=new StockDailyInfo(code,date,open,close,hign,low,volume,changePercent,0,0,0,0,0);
//
//                }else{
//                    stockDailyInfo=new StockDailyInfo(code,date,open,close,hign,low,volume,0,0,0,0,0,0);
//
//                }
//            }
//
//            dao.close(resultSet,statement,connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return stockDailyInfo;
//    }

//    @Override
//    public List<StockDailyInfo> getStockDailyInfoList(String date) {
//        List<StockDailyInfo> stockDailyInfoList=new ArrayList<>();
//        String sql="SELECT\n" +
//                "\ta.*, (\n" +
//                "\t\t(\n" +
//                "\t\t\tSELECT\n" +
//                "\t\t\t\t`close`\n" +
//                "\t\t\tFROM\n" +
//                "\t\t\t\t`stock_d_data`\n" +
//                "\t\t\tWHERE\n" +
//                "\t\t\t\t`code` = a.`code`\n" +
//                "\t\t\tAND `date` < a.`date`\n" +
//                "\t\t\tORDER BY\n" +
//                "\t\t\t\t`date` DESC\n" +
//                "\t\t\tLIMIT 0,\n" +
//                "\t\t\t1\n" +
//                "\t\t) - a.`close`\n" +
//                "\t) / (\n" +
//                "\t\tSELECT\n" +
//                "\t\t\t`close`\n" +
//                "\t\tFROM\n" +
//                "\t\t\t`stock_d_data`\n" +
//                "\t\tWHERE\n" +
//                "\t\t\t`code` = a.`code`\n" +
//                "\t\tAND `date` < a.`date`\n" +
//                "\t\tORDER BY\n" +
//                "\t\t\t`date` DESC\n" +
//                "\t\tLIMIT 0,\n" +
//                "\t\t1\n" +
//                "\t) changePercent\n" +
//                "FROM\n" +
//                "\t`stock_d_data` a\n" +
//                "WHERE\n" +
//                "\ta.`code` IN (\n" +
//                "\t\tSELECT\n" +
//                "\t\t\t`code`\n" +
//                "\t\tFROM\n" +
//                "\t\t\t`stock_basics`\n" +
//                "\t)\n" +
//                "AND a.`date` = '"+date+"'\n" +
//                "ORDER BY\n" +
//                "\ta.`date` DESC";
//        StockDailyInfo stockDailyInfo=null;
//        Connection connection =dao.getConnection();
//        Statement statement=dao.getStatement(connection);
//
//        try {
//            ResultSet resultSet=statement.executeQuery(sql);
//            while(resultSet.next()){
//                String code =resultSet.getString(1);
//                Double open =resultSet.getDouble(3);
//                Double close=resultSet.getDouble(4);
//                Double hign=resultSet.getDouble(5);
//                Double low=resultSet.getDouble(6);
//                Double volume=resultSet.getDouble(7);
//
//                Double changePercent=resultSet.getDouble(8);
//                DecimalFormat df = new DecimalFormat("#.####");
//                String changePercentString =df.format(changePercent);
//                changePercent=Double.parseDouble(changePercentString);
//                stockDailyInfo =new StockDailyInfo(code,date,open,close,hign,low,volume,changePercent,0,0,0,0,0);
//                stockDailyInfoList.add(stockDailyInfo);
//            }
//            dao.close(resultSet,statement,connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return stockDailyInfoList;
//    }

//    @Override
//    //两步查询比上面注释掉的部分快了接近20倍
//    public List<StockDailyInfo> getStockDailyInfoList(String date){
//        List<StockDailyInfo> stockDailyInfoList=new ArrayList<>();
//        StockDailyInfo stockDailyInfo;
//        String sql4TodayInfo="SELECT\n" +
//                "\t* \n" +
//                "FROM\n" +
//                "\t`stock_d_data`\n" +
//                "WHERE\n" +
//                "\t`date` = '"+date+"'\n" +
//                "ORDER BY\n" +
//                "\t`code` ASC";
//        String sql4YestodayInfo="SELECT\n" +
//                "\t`code`,`close`\n" +
//                "FROM\n" +
//                "\t`stock_d_data`\n" +
//                "WHERE\n" +
//                "\t`date` = (SELECT\n" +
//                "`date`\n" +
//                "FROM\n" +
//                "`stock_d_data`\n" +
//                "WHERE \n" +
//                "`date` <'"+date+"'\n" +
//                "and `code`='SH000001'\n" +
//                "ORDER BY `date` DESC\n" +
//                "LIMIT 0,1)\n" +
//                "ORDER BY\n" +
//                "\t`code` ASC";
//
//        Connection connection =dao.getConnection();
//        Statement statement=dao.getStatement(connection);
//
//        //获取前一天的收盘价的map
//        Map<String,Double> yestodayCloseMap=new HashMap<>();
//        try {
//            ResultSet resultSet=statement.executeQuery(sql4YestodayInfo);
//            //System.out.println(sqlafter2);
//            while (resultSet.next()){
//                String code=resultSet.getString(1);
//                Double close=resultSet.getDouble(2);
//                yestodayCloseMap.put(code,close);
//            }
//            resultSet.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            ResultSet resultSet=statement.executeQuery(sql4TodayInfo);
//            while(resultSet.next()){
//                String code =resultSet.getString(1);
//                Double open =resultSet.getDouble(3);
//                Double close=resultSet.getDouble(4);
//                Double hign=resultSet.getDouble(5);
//                Double low=resultSet.getDouble(6);
//                Double volume=resultSet.getDouble(7);
//
//                Double yestodayClose=yestodayCloseMap.get(code);
//                double changePercent=0;
//                if(yestodayClose!=null){
//                    changePercent=(close-yestodayClose)/yestodayClose;
//                    DecimalFormat df = new DecimalFormat("#.####");
//                    String changePercentString =df.format(changePercent);
//                    changePercent=Double.parseDouble(changePercentString);
//                }
//                stockDailyInfo =new StockDailyInfo(code,date,open,close,hign,low,volume,changePercent,0,0,0,0,0);
//                stockDailyInfoList.add(stockDailyInfo);
//            }
//            dao.close(resultSet,statement,connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return stockDailyInfoList;
//    }
//
//    @Override
//    public List<StockDailyInfo> getLongTimeSingleStockDailyInfoList(String code,String startDate, int numOfDays) {
//        Connection connection =dao.getConnection();
//        Statement statement=dao.getStatement(connection);
//
//        //通过数据库查到倒叙排列的信息
//        List<StockDailyInfo> stockDailyInfoList=new ArrayList<>();
//        String sqlafter2="SELECT * from (SELECT\n" +
//                "\ta.*" +
//                ", (\n" +
//                "\t\t(\n" +
//                "\t\t\tSELECT\n" +
//                "\t\t\t\t`close`\n" +
//                "\t\t\tFROM\n" +
//                "\t\t\t\t`stock_d_data`\n" +
//                "\t\t\tWHERE\n" +
//                "\t\t\t\t`code` = a.`code`\n" +
//                "\t\t\tAND `date` < a.`date`\n" +
//                "\t\t\tORDER BY\n" +
//                "\t\t\t\t`date` DESC\n" +
//                "\t\t\tLIMIT 0,\n" +
//                "\t\t\t1\n" +
//                "\t\t) - a.`close`\n" +
//                "\t) / (\n" +
//                "\t\tSELECT\n" +
//                "\t\t\t`close`\n" +
//                "\t\tFROM\n" +
//                "\t\t\t`stock_d_data`\n" +
//                "\t\tWHERE\n" +
//                "\t\t\t`code` = a.`code`\n" +
//                "\t\tAND `date` < a.`date`\n" +
//                "\t\tORDER BY\n" +
//                "\t\t\t`date` DESC\n" +
//                "\t\tLIMIT 0,\n" +
//                "\t\t1\n" +
//                "\t) changePercent\n" +
//                "FROM\n" +
//                "\t`stock_d_data` a\n" +
//                "WHERE\n" +
//                "\ta.`date` >='"+startDate+"%'\n" +
//                "AND a.`code` = '"+code+"'\n" +
//                "ORDER BY a.`date` ASC\n" +
//                "LIMIT 0,"+numOfDays+") a\n" +
//                "ORDER BY `date` DESC\n";
//        try {
//            ResultSet resultSet=statement.executeQuery(sqlafter2);
//            //System.out.println(sqlafter2);
//            StockDailyInfo stockDailyInfo=null;
//            while (resultSet.next()){
//                String date=resultSet.getString(2);
//                Double open =resultSet.getDouble(3);
//                Double close=resultSet.getDouble(4);
//                Double hign=resultSet.getDouble(5);
//                Double low=resultSet.getDouble(6);
//                Double volume=resultSet.getDouble(7);
//
//                stockDailyInfo =new StockDailyInfo(code,date,open,close,hign,low,volume,0,0,0,0,0,0);
//                stockDailyInfoList.add(stockDailyInfo);
//            }
//            resultSet.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        String sql60before="SELECT\n" +
//                "\ta.*" +
//                ", (\n" +
//                "\t\t(\n" +
//                "\t\t\tSELECT\n" +
//                "\t\t\t\t`close`\n" +
//                "\t\t\tFROM\n" +
//                "\t\t\t\t`stock_d_data`\n" +
//                "\t\t\tWHERE\n" +
//                "\t\t\t\t`code` = a.`code`\n" +
//                "\t\t\tAND `date` < a.`date`\n" +
//                "\t\t\tORDER BY\n" +
//                "\t\t\t\t`date` DESC\n" +
//                "\t\t\tLIMIT 0,\n" +
//                "\t\t\t1\n" +
//                "\t\t) - a.`close`\n" +
//                "\t) / (\n" +
//                "\t\tSELECT\n" +
//                "\t\t\t`close`\n" +
//                "\t\tFROM\n" +
//                "\t\t\t`stock_d_data`\n" +
//                "\t\tWHERE\n" +
//                "\t\t\t`code` = a.`code`\n" +
//                "\t\tAND `date` < a.`date`\n" +
//                "\t\tORDER BY\n" +
//                "\t\t\t`date` DESC\n" +
//                "\t\tLIMIT 0,\n" +
//                "\t\t1\n" +
//                "\t) changePercent\n" +
//                "FROM\n" +
//                "\t`stock_d_data` a\n" +
//                "WHERE\n" +
//                "\ta.`date` < '"+startDate+"%'\n" +
//                "AND a.`code` = '"+code+"'\n" + //SH600009
//                "ORDER BY a.`date` DESC\n" +
//                "LIMIT 0,60";



//        try {
//            ResultSet resultSet=statement.executeQuery(sql60before);
//            StockDailyInfo stockDailyInfo=null;
//            while (resultSet.next()){
//                String date=resultSet.getString(2);
//                Double open =resultSet.getDouble(3);
//                Double close=resultSet.getDouble(4);
//                Double hign=resultSet.getDouble(5);
//                Double low=resultSet.getDouble(6);
//                Double volume=resultSet.getDouble(7);
//                stockDailyInfo =new StockDailyInfo(code,date,open,close,hign,low,volume,0,0,0,0,0,0);
//                stockDailyInfoList.add(stockDailyInfo);
//            }
//            dao.close(resultSet,statement,connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i <=stockDailyInfoList.size()-61; i++) {
//            double yestodayClose=stockDailyInfoList.get(i+1).getClose();
//            double todayClose=stockDailyInfoList.get(i).getClose();
//            double changePercent=(todayClose-yestodayClose)/yestodayClose;
//            DecimalFormat df = new DecimalFormat("#.####");
//            String changePercentString =df.format(changePercent);
//            changePercent=Double.parseDouble(changePercentString);
//            //计算x日收盘价的和
//            double sum5=0;
//            for(int j=i+5;j>i;j--){
//                sum5+=stockDailyInfoList.get(j).getClose();
//            }
//            double sum10=sum5;
//            for (int j = i+10; j >i+5 ; j--) {
//                sum10+=stockDailyInfoList.get(j).getClose();
//            }
//            double sum20=sum10;
//            for (int j = i+20; j >i+10 ; j--) {
//                sum20+=stockDailyInfoList.get(j).getClose();
//            }
//            double sum30=sum20;
//            for (int j = i+30; j >i+20 ; j--) {
//                sum30+=stockDailyInfoList.get(j).getClose();
//            }
//            double sum60=sum30;
//            for (int j = i+60; j >i+30 ; j--) {
//                sum60+=stockDailyInfoList.get(j).getClose();
//            }
//            double avg5d=sum5/5;
//            double avg10d=sum10/10;
//            double avg20d=sum20/20;
//            double avg30d=sum30/30;
//            double avg60d=sum60/60;
//            //todo:格式化均价
//            df = new DecimalFormat("#.##");
//            String temp =df.format(avg5d);
//            avg5d=Double.parseDouble(temp);
//            temp =df.format(avg10d);
//            avg10d=Double.parseDouble(temp);
//            temp =df.format(avg20d);
//            avg20d=Double.parseDouble(temp);
//            temp =df.format(avg30d);
//            avg30d=Double.parseDouble(temp);
//            temp =df.format(avg60d);
//            avg60d=Double.parseDouble(temp);
//
//
//
//            StockDailyInfo stockDailyInfo=stockDailyInfoList.get(i);
//            stockDailyInfo.setAvg5d(avg5d);
//            stockDailyInfo.setAvg10d(avg10d);
//            stockDailyInfo.setAvg20d(avg20d);
//            stockDailyInfo.setAvg30d(avg30d);
//            stockDailyInfo.setAvg60d(avg60d);
//            stockDailyInfo.setChangePercent(changePercent);
//        }
//        Collections.reverse(stockDailyInfoList);
//        stockDailyInfoList=new ArrayList<>(stockDailyInfoList.subList(60,stockDailyInfoList.size()));
//        return stockDailyInfoList;
//    }
}
