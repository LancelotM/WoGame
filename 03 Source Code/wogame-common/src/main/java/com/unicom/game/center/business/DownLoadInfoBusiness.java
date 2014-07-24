package com.unicom.game.center.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.ChannelInfoDao;
import com.unicom.game.center.db.dao.DownloadInfoDao;
import com.unicom.game.center.db.domain.DownloadInfoDomain;
import com.unicom.game.center.log.model.DownLoadInfo;
import com.unicom.game.center.log.model.DownloadDiaplayModel;
import com.unicom.game.center.log.model.DownloadInfoModel;
import com.unicom.game.center.utils.Constant;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.Logging;

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

    @Autowired
    private ChannelInfoDao channelInfoDao;
    
    public void typeConversion(Map<String,DownLoadInfo> downLoadInfoHashMap){
        List<DownloadInfoDomain> list = new ArrayList<DownloadInfoDomain>();
        Iterator iterator = downLoadInfoHashMap.entrySet().iterator();
        while (iterator.hasNext()){
            DownloadInfoDomain downloadInfoDomain = new DownloadInfoDomain();
            Map.Entry<Integer, DownLoadInfo> entry = (Map.Entry)iterator.next();
            DownLoadInfo downLoadInfo = entry.getValue();
            downloadInfoDomain.setProductId(downLoadInfo.getProduct_id());
            downloadInfoDomain.setChannelCode(downLoadInfo.getChannel_code());
            downloadInfoDomain.setDownloadCount(downLoadInfo.getDownload_count());
            downloadInfoDomain.setDateCreated(downLoadInfo.getDateCreated());
            list.add(downloadInfoDomain);
        }
        downloadInfoDao.saveDownloadCountDomainList(list, Constant.HIBERNATE_FLUSH_NUM);
    }

    public DownloadInfoModel getDownloadInfos(String channelCode,String startDate,String endDate,int page,Integer rowsPerPage){
        DownloadInfoModel downloadInfoModel = new DownloadInfoModel();;
        try{
            List<DownloadDiaplayModel> downloadInfoDomains = downloadInfoDao.getByProductOrChaOrDate(channelCode,startDate,endDate);
            int totalSize = downloadInfoDomains.size();
            if(downloadInfoDomains != null){
                if(downloadInfoDomains.size()%2 !=0){
                    DownloadDiaplayModel diaplayModel = new DownloadDiaplayModel();
                    diaplayModel.setDownloadCount("");
                    diaplayModel.setProductName("");
                    downloadInfoDomains.add(diaplayModel);
                }
                int start = (page - 1)*rowsPerPage;
                int end = start + rowsPerPage;
                if(end > downloadInfoDomains.size()){
                    end = downloadInfoDomains.size();
                }
                downloadInfoModel.setTotalRecords(totalSize);
                downloadInfoModel.setDownloadInfomodels(downloadInfoDomains.subList(start,end));
            }
        }catch(Exception e){
            Logging.logError("Error occur in getDownloadInfos", e);
        }
        return downloadInfoModel;
    }


    /**
     *
     * @param contentArr
     */
    public DownloadInfoDomain convertDownloadCountFromFile(String[] contentArr){
        DownloadInfoDomain downloadInfoDomain = new DownloadInfoDomain();
        downloadInfoDomain.setProductId(contentArr[0]);
        downloadInfoDomain.setChannelCode(contentArr[1]);
        downloadInfoDomain.setDownloadCount(Integer.parseInt(contentArr[2]));
        downloadInfoDomain.setDateCreated(DateUtils.getDayByInterval(new Date(), -1));
        return downloadInfoDomain;
    }

    /**
     *
     * @param list
     * @param num
     */
    public void saveDownloadCountList(List<DownloadInfoDomain> list, int num){
        try{
            downloadInfoDao.saveDownloadCountDomainList(list, num);
        }catch(Exception ex){
            Logging.logError("Error occur in savePackageReportList.", ex);
        }
    }

}
