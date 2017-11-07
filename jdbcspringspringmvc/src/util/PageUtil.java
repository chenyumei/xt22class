package util;

public class PageUtil {
	private Integer  StrartNum=0;//开始显示数量
	private Integer pageSize=5;
	private Integer currentPageNo=0;
	public Integer getStrartNum() {
		return StrartNum;
	}
	public void setStrartNum(Integer strartNum) {
		StrartNum = strartNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}


}
