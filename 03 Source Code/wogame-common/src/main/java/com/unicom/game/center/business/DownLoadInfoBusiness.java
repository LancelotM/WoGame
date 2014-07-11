package com.unicom.game.center.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.unicom.game.center.log.model.DownloadDiaplayModel;
import com.unicom.game.center.log.model.DownloadInfoModel;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.DownloadInfoDao;
import com.unicom.game.center.db.domain.DownloadInfoDomain;
import com.unicom.game.center.log.model.DownLoadInfo;

/**
 * Created with IntelliJ IDEA.
 * User: claire_chang
 * Date: 7/4/14
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Transactional
public class DownLoadInfoBusiness {
    @Autowired
    private DownloadInfoDao downloadInfoDao;
    
    public void typeConversion(HashMap<Integer,DownLoadInfo> downLoadInfoHashMap){
        List<DownloadInfoDomain> list = new ArrayList<DownloadInfoDomain>();
        Iterator iterator = downLoadInfoHashMap.entrySet().iterator();
        while (iterator.hasNext()){
            DownloadInfoDomain downloadInfoDomain = new DownloadInfoDomain();
            Map.Entry<Integer, DownLoadInfo> entry = (Map.Entry)iterator.next();
            DownLoadInfo downLoadInfo = entry.getValue();
            downloadInfoDomain.setProductId(downLoadInfo.getProduct_id());
            downloadInfoDomain.setChannelId(downLoadInfo.getChannel_id());
            downloadInfoDomain.setDownloadCount(downLoadInfo.getDownload_count());
            downloadInfoDomain.setDateCreated(downLoadInfo.getDateCreated());
            list.add(downloadInfoDomain);
        }
        downloadInfoDao.saveUserCountDomainList(list,100);
    }

    public DownloadInfoModel getDownloadInfos(String productId,Integer channelID,String dateStr,int page){
        String startDate = null;
        String endDate = null;
        if(!Utility.isEmpty(dateStr)){
            String[] date = dateStr.split("-");
            startDate = DateUtils.formatDateToString(DateUtils.stringToDate(date[0],"yyyy.MM.dd"),"yyyy-MM-dd");
            endDate = DateUtils.formatDateToString(DateUtils.stringToDate(date[1],"yyyy.MM.dd"),"yyyy-MM-dd");
        }
        List<DownloadDiaplayModel> downloadInfoDomains = downloadInfoDao.getByProductOrChaOrDate(productId,channelID,startDate,endDate,page);
        int perRowsPage = 10;
        int totalPage = 0;
        if(downloadInfoDomains.size()%perRowsPage == 0){
            totalPage = downloadInfoDomains.size()/perRowsPage;
        }else {
            totalPage = downloadInfoDomains.size()/perRowsPage + 1;
        }
        DownloadInfoModel downloadInfoModel = new DownloadInfoModel();
        downloadInfoModel.setTotalPage(totalPage);
        downloadInfoModel.setDownloadInfoDomains(downloadInfoDomains);
        return downloadInfoModel;
    }


}
