import java.util.Arrays;

/**
 * 
 * @author Matt
 *
 */
/**
 * @author Matt
 *
 */
public class Job {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		return "Job [id=" + id + ", size=" + size + ", executionTime=" + executionTime + ", numOfPages=" + numOfPages
				+ "]";
	}

	protected int id;
	protected int size;
	protected int executionTime;
	protected int numOfPages;
	protected Page[] pageMapTable;
	protected long startTime;
	protected long endTime;
	protected boolean processing = false;

	
	public void setEndTime() {
		endTime = startTime + executionTime;
	}
	
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * Default Constructor
	 */
	public Job() {

	}

	/**
	 * Constructor
	 * @param i -> ID
	 * @param s -> Size
	 * @param e -> Execution Time
	 */
	public Job(int i,int s,int e) {
		setID(i);
		setSize(s);
		setTime(e);
	}

	/**
	 * 
	 * @return ID
	 */
	public int getID() {
		return id;
	}

	/**
	 * 
	 * @return Size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @return Execution Time
	 */
	public int getTime() {
		return executionTime;
	}

	/**
	 * Sets the ID to the given integer.
	 * @param i
	 */
	public void setID(int i) {
		this.id = i;
	}

	/**
	 * Sets the Size to the given integer.
	 * @param s
	 */
	public void setSize(int s) {
		this.size = s;
	}

	/**
	 * Sets the Execution time to the given integer.
	 * @param l
	 */
	public void setTime(int l) {
		this. executionTime = l;
	}

	/**
	 * @return
	 */
	public int getNumOfPages() {
		return numOfPages;
	}

	/**
	 * generates number of pages dependent on size of job
	 */
	public void setNumOfPages() {
		if(this.size%100!=0) {
			this.numOfPages = this.size/100+1;
		}
		else {
			this.numOfPages = this.size/100;
		}
		
		pageMapTable= new Page[numOfPages];

	}
	
	public void setPageMapTable() {
		int x = size;
		for(int y = 0;y<numOfPages;y++) {
			pageMapTable[y] = new Page();
			pageMapTable[y].setPageNum(y+1);
			pageMapTable[y].jobID = id;
			if(x > 99) {
				pageMapTable[y].setPageSize(100);
				x= x - 100;
			}
			else {
				pageMapTable[y].setPageSize(x);
				x = 0;
			}
				
		}
	}
	


}


