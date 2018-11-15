
public class Page extends Job{
	
	protected int jobID;
	protected int pageNum;
	protected int pageSize;
	protected boolean placed = false;
	protected boolean ejected = false;
	
	
	/**
	 * @return the ejected
	 */
	public boolean isEjected() {
		return ejected;
	}
	/**
	 * @param ejected the ejected to set
	 */
	public void setEjected(boolean ejected) {
		this.ejected = ejected;
	}
	/**
	 * @return the jobID
	 */
	public int getJobID() {
		return jobID;
	}
	/**
	 * @param jobID the jobID to set
	 */
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	/**
	 * 
	 */
	public Page() {
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Page [pageNum=" + pageNum + ", pageSize=" + pageSize + "]";
	}
	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}
	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
