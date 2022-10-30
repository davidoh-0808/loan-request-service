package com.neo.common.view;


public class PagingViewFront {

	private int totalRows = 0;
	private int currentPage = 1;
	private int pageSize;	
	private int blockSize;	
	private int totalPages;
	private int totalBlocks;
	private int startPageNum;
	private int endPageNum;
	private int currentBlock;

	private String amp = "";
	
	// for design
//	pageNum.preBlockLink = "<img src=\"/first.gif\">";
	public String firstLink = "처음";
	public String preBlockLink = "이전";
	public String firstOffLink = "";
//	public String prevLink = "<";
	public String prevOffLink = "";
//	public String nextLink = ">";
	public String nextOffLink = "";
	public String nxtBlockLink = "다음";
	public String lastLink = "마지막";
	public String lastOffLink = "";
	
	public String delimiter = "|";
	
	// current Page Wrapper
	public String preWrap = "<b>";
	public String postWrap = "</b>";
	
	public String linkPage = "";
	public String queryString = "";
	
	// result temp object
	public StringBuffer pageString = new StringBuffer();
	
	public PagingViewFront(int currentPage , int pageSize , int blockSize , int totalRows)
	{
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.blockSize = blockSize;
		this.totalRows = totalRows;
		
		initialize();
	} 
	
	public void initialize()
	{	
		this.totalPages = (int)Math.ceil((double)this.totalRows / this.pageSize);
		this.totalBlocks = (int)Math.ceil((double)this.totalPages / this.blockSize);
		this.currentBlock = (int)Math.ceil((double)((this.currentPage - 1) / this.blockSize)) + 1;		
		this.startPageNum = ((this.currentBlock - 1) * this.blockSize) + 1;
		this.endPageNum = this.startPageNum + this.blockSize;
//		this.endPageNum = this.startPageNum + this.pageSize;
	}
	
	public void prePrint()
	{
		// set first block link
		if(this.currentBlock > 1){
//			pageString.append("<a href=\"javascript:goPage(" +  + ")\">" + firstLink + "</a> ");
			pageString.append("<li><a href=\"javascript:goPage(1)\" class=\"paging_first\">" + this.firstLink + "</a></li>");
			pageString.append("<li><a href=\"javascript:goPage("+ ((this.currentBlock - 1) * this.blockSize) +")\" class=\"paging_prev\">" + this.preBlockLink + "</a></li>");
		}else{
			pageString.append(this.firstOffLink + " ");
		}	
		
		// set prev page link
		if(this.currentPage > 1) {
//			pageString.append("<a href=\"javascript:goPage(" + (this.currentPage  - 1) + ")\">" + this.prevLink + "</a> ");
		}else {
			pageString.append(this.prevOffLink + " ");
		}
	}
	
	public void postPrint()
	{
		// set next page link
		if(this.currentPage < this.totalPages ) {
//			pageString.append("<a href=\"javascript:goPage(" + (this.currentPage + 1) + ")\">" + this.nextLink + "</a> ");
		}else {
			pageString.append(this.nextOffLink + " ");
		}
		
		// set last page link
		if(this.currentBlock < this.totalBlocks){
			pageString.append("<li><a href=\"javascript:goPage(" + ((this.currentBlock * this.blockSize) + 1) + ")\" class=\"paging_next\">" + this.nxtBlockLink + "</a></li>");
			pageString.append("<li><a href=\"javascript:goPage(" + this.totalPages + ")\" class=\"paging_last\">" + this.lastLink + "</a></li>");
		}else{
			pageString.append(this.lastOffLink);
		}
	}
	
	public void printList()
	{	
		for(int i = startPageNum ; i <= endPageNum ; i++)
		{
			if(i > this.totalPages || i == endPageNum){
				break;
			}else if(i > startPageNum){
//				pageString.append(this.delimiter);
			}
			
			if(i == this.currentPage){
//				pageString.append(" " + this.preWrap + i +  this.postWrap + " ");
				pageString.append("<li><a href=\"javascript:goPage(" + i + ")\" class=\"active\" >" + i + "</a></li>");
			}else{
				pageString.append("<li><a href=\"javascript:goPage(" + i + ")\">" + i + "</a></li>");
			}
			
//			//System.out.println("==============================================");
//			//System.out.println("[startPageNum]="+startPageNum);
//			//System.out.println("[endPageNum]="+endPageNum);
//			//System.out.println("[this.totalPages]="+this.totalPages);
//			//System.out.println("[this.preWrap]="+this.preWrap);
//			//System.out.println("[this.postWrap]="+this.postWrap);
//			//System.out.println("[this.linkPage]="+this.linkPage);
//			//System.out.println("[this.queryString]="+this.queryString);
//			//System.out.println("[this.currentPage]="+this.currentPage);
//			//System.out.println("[this.pageSize]="+this.pageSize);
//			//System.out.println("[this.blockSize]="+this.blockSize);
//			//System.out.println("[this.totalRows]="+this.totalRows);
//			//System.out.println("[this.totalBlocks]="+this.totalBlocks);
//			//System.out.println("[this.currentBlock]="+this.currentBlock);
//			//System.out.println("[this.currentPage]="+this.currentPage);
			
			
		}
	}
	
	public String print()
	{
		// set amp if already to set up queryString property
		if(!this.queryString.equals(""))
			this.amp = "&";
		
		if(this.totalPages > 1)
		{
			this.prePrint();
			this.printList();
			this.postPrint();
		}
		return(pageString.toString());
	}

}

