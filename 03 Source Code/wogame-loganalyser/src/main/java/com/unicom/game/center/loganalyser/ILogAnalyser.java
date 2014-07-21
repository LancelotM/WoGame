package com.unicom.game.center.loganalyser;

public interface ILogAnalyser {

	public void doLogAnalyse() throws Exception;

    public void doPackageInfoDomainsSave() throws Exception;

    public void doPackageReportDomainsSave() throws Exception;

    public void doExtractReportDomainsSave() throws Exception;

    public void doDownloadCountDomainsSave() throws Exception;

}
