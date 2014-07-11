package com.unicom.game.center.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
}
