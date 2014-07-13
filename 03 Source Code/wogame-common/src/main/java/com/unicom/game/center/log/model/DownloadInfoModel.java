package com.unicom.game.center.log.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wiki_yu
 * Date: 7/12/14
 * Time: 10:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class DownloadInfoModel {
    private int totalPages;
    private List<DownloadDiaplayModel> downloadInfomodels = new ArrayList<DownloadDiaplayModel>();

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<DownloadDiaplayModel> getDownloadInfomodels() {
        return downloadInfomodels;
    }

    public void setDownloadInfomodels(List<DownloadDiaplayModel> downloadInfomodels) {
        this.downloadInfomodels = downloadInfomodels;
    }
}
