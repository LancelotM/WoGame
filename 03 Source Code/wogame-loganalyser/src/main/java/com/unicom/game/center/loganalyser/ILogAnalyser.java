package com.unicom.game.center.loganalyser;

public interface ILogAnalyser {

	public void doLogAnalyse() throws Exception;

    public void doPackageInfoDomainsSave() throws Exception;

    public void doReportDomainsSave() throws Exception;

    public void doDownloadCountDomainsSave() throws Exception;
}
