```mysql


 USE [PorscheHoldingTemp20200422]  
 declare @ItemNo nvarchar(20) 
 declare @ToPostingDate datetime
 declare @MaxLoop int 
 declare @CurrLoop int 
 declare @Qty decimal(38,20) 
 declare @TransQty decimal(38,20) 
 declare @PostingDate datetime 
 declare @DocumentNo nvarchar(20)
 declare @EntryNo int 
 declare @InEntryNo int 
 declare @ItemLedgEntryNo int 
 declare @ReturnPostingDate datetime 
 declare @EntryType int 
 declare @FinalReturnPostingDate datetime 
 declare @InitQty decimal(38,20) 
 declare @PeriodQtyA decimal(38,20) 
 declare @PeriodQtyB decimal(38,20) 
 declare @PeriodQtyC decimal(38,20) 
 declare @PeriodQtyD decimal(38,20) 
 SET @MaxLoop = 20 
 SELECT @ToPostingDate = CAST('20200331' As datetime) 
 IF Object_Id('tempdb..#PartsStockValue') Is NOT NULL BEGIN   
 DROP TABLE #PartsStockValue 
 END 
 CREATE TABLE #PartsStockValue
  ([Item No_] nvarchar(20) collate DATABASE_DEFAULT, 
  [Posting Date] datetime,
  [Quantity] decimal(38,20) 
  )

  DECLARE C_Entry CURSOR FAST_FORWARD LOCAL FOR 
  SELECT [Posting Date],[Document No_],[Item No_],TILE.[Quantity] 
  FROM [PCNBHJ$Item Ledger Entry] As TILE WITH(NOLOCK) 
  INNER JOIN [PCNBHJ$Item] AS TItem WITH(NOLOCK) 
  ON TItem.[Item Type] =1 AND [Item No_] = [No_] AND [No_] <> ''
  WHERE [Posting Date] <= @ToPostingDate AND TILE.[Quantity] >0 AND [Entry Type] = 4 
  --AND [Location Code] ='CITY_SR' 
  
  OPEN C_Entry FETCH NEXT FROM C_Entry INTO @PostingDate,@DocumentNo,@ItemNo,@Qty 
  WHILE @@FETCH_STATUS=0 BEGIN 
  SET @CurrLoop = 0 
  SET @InitQty= @Qty 
  SET @FinalReturnPostingDate = @PostingDate 
  WHILE @CurrLoop < @MaxLoop BEGIN  
  SET @CurrLoop = @CurrLoop +1  
  SET @EntryNo = NULL  
  SELECT TOP 1 @EntryNo= [Entry No_]  FROM [PCNBHJ$Item Ledger Entry] WITH(NOLOCK)  
  WHERE [Document No_] = @DocumentNo  AND [Posting Date] =@PostingDate  
  AND [Item No_] = @ItemNo  AND [Positive] = 0  AND [Quantity] = - @InitQty 
  --AND [Location Code] ='CITY_SR' 
  ORDER BY [Document No_]DESC,[Posting Date] DESC  
  
  IF ISNULL(@EntryNo,-1) = -1 BEGIN   
  Break  
  END  
  SET @InEntryNo = NULL  
  SELECT TOP 1 @InEntryNo= [Inbound Item Entry No_],@ItemLedgEntryNo=[Item Ledger Entry No_]  
  FROM [PCNBHJ$Item Application Entry] WITH(NOLOCK)  
  WHERE [Item Ledger Entry No_] = @EntryNo  AND [Outbound Item Entry No_] = @EntryNo  
  ORDER BY [Entry No_] DESC  
  
  
  
  IF ISNULL(@InEntryNo,-1) = -1 BEGIN   
  Break  
  END  

  SET @ItemLedgEntryNo = NULL  
  SELECT TOP 1 @ItemLedgEntryNo=[Item Ledger Entry No_], @ReturnPostingDate= [Posting Date]  
  FROM [PCNBHJ$Item Application Entry] WITH(NOLOCK)  
  WHERE [Inbound Item Entry No_] = @InEntryNo  
  ORDER BY [Entry No_] ASC  
  
  
  IF ISNULL(@ItemLedgEntryNo,-1) =-1 
  BEGIN   
  Break  
  END  
  
  SET @EntryType = NULL  
  SELECT @EntryType = [Entry Type],@PostingDate = [Posting Date],@DocumentNo=[Document No_],@InitQty=[Quantity]  
  FROM [PCNBHJ$Item Ledger Entry] WITH(NOLOCK)  
  WHERE [Entry No_] = @ItemLedgEntryNo  
  
  IF ISNULL(@EntryType,-1) = -1 
  BEGIN   
  Break  
  END  
  
  IF @EntryType = 4 BEGIN   
  SET @EntryNo =NULL   
  SET @InEntryNo = NULL   
  SET @ItemLedgEntryNo = NULL   
  SET @ReturnPostingDate = NULL   
  SET @EntryType = NULL  
  END ELSE BEGIN   
  SET @FinalReturnPostingDate = @ReturnPostingDate   
  Break  
  END 
  END 
  
  
  IF @FinalReturnPostingDate is not null BEGIN  
  INSERT INTO #PartsStockValue([Item No_],[Posting Date],[Quantity])  VALUES(@ItemNo,@FinalReturnPostingDate,@Qty) 
  END   
  
  FETCH NEXT FROM C_Entry INTO @PostingDate,@DocumentNo,@ItemNo,@Qty 
  END 
  CLOSE C_Entry;
  DEALLOCATE C_Entry; 
  
  SET @Qty = NULL 
  SET @PostingDate = NULL 
  SET @DocumentNo = NULL 
  SET @EntryNo = NULL 
  SET @InEntryNo = NULL 
  SET @ItemLedgEntryNo = NULL 
  SET @ReturnPostingDate = NULL
  SET @EntryType = NULL 
  SET @FinalReturnPostingDate = NULL 
  DECLARE C_Entry_Negtive CURSOR FAST_FORWARD LOCAL FOR 
  SELECT [Posting Date],[Document No_],[Item No_],T2.[Quantity],T1.[Quantity] 
  FROM [PCNBHJ$Item Ledger Entry] AS T1 WITH(NOLOCK) 
  INNER JOIN ( SELECT TIAE.[Quantity],TIAE.[Inbound Item Entry No_] FROM [PCNBHJ$Item Ledger Entry] AS TILE WITH(NOLOCK) 
			INNER JOIN [PCNBHJ$Item] AS TItem WITH(NOLOCK) ON TItem.[Item Type] =1 AND [Item No_] = [No_] AND [No_] <> '' 
			INNER JOIN [PCNBHJ$Item Application Entry] AS TIAE WITH(NOLOCK) 
			ON TIAE.[Item Ledger Entry No_] = TILE.[Entry No_] AND TIAE.[Outbound Item Entry No_] = TILE.[Entry No_] 
			WHERE TILE.[Quantity] <= 0 AND TILE.[Posting Date] <= @ToPostingDate AND TILE.[Item Type] = 1 
			) AS T2 
  ON T1.[Entry No_] = T2.[Inbound Item Entry No_] 
  WHERE T1.[Entry Type] = 4 
  --AND [Location Code] ='CITY_SR' 

  OPEN C_Entry_Negtive FETCH NEXT FROM C_Entry_Negtive INTO @PostingDate,@DocumentNo,@ItemNo,@Qty,@TransQty 
  WHILE @@FETCH_STATUS=0 BEGIN 
  SET @CurrLoop = 0
  SET @FinalReturnPostingDate = @PostingDate 
  WHILE @CurrLoop < @MaxLoop BEGIN  
	  SET @CurrLoop = @CurrLoop +1  
	  SET @EntryNo = NULL  
	  SELECT TOP 1 @EntryNo= [Entry No_]   
	  FROM [PCNBHJ$Item Ledger Entry] WITH(NOLOCK)  
	  WHERE [Document No_] = @DocumentNo  AND [Posting Date] =@PostingDate  AND [Item No_] = @ItemNo  
	  AND [Positive] = 0  AND [Quantity] = - @TransQty 
	  --AND [Location Code] ='CITY_SR'  
	  ORDER BY [Document No_]DESC,[Posting Date] DESC  
	  IF ISNULL(@EntryNo,-1) = -1 BEGIN   
		Break 
	  END  
	  SET @InEntryNo = NULL 
	  SELECT TOP 1 @InEntryNo= [Inbound Item Entry No_],@ItemLedgEntryNo=[Item Ledger Entry No_]  
	  FROM [PCNBHJ$Item Application Entry] WITH(NOLOCK)  
	  WHERE [Item Ledger Entry No_] = @EntryNo  
	  AND [Outbound Item Entry No_] = @EntryNo  
	  ORDER BY [Entry No_] DESC  
	  IF ISNULL(@InEntryNo,-1) = -1 
	  BEGIN   
		Break  
	  END  
  
	  SET @ItemLedgEntryNo = NULL  
	  SELECT TOP 1 @ItemLedgEntryNo=[Item Ledger Entry No_], @ReturnPostingDate= [Posting Date]  
	  FROM [PCNBHJ$Item Application Entry] WITH(NOLOCK)  
	  WHERE [Inbound Item Entry No_] = @InEntryNo  
	  ORDER BY [Entry No_] ASC  
	  IF ISNULL(@ItemLedgEntryNo,-1) =-1 
	  BEGIN  
		Break 
	  END  
  
	  SET @EntryType = NULL  
	  SELECT TOP 1 @EntryType = [Entry Type],@PostingDate = [Posting Date],@DocumentNo=[Document No_],@TransQty=[Quantity]   
	  FROM [PCNBHJ$Item Ledger Entry] WITH(NOLOCK)  
	  WHERE [Entry No_] = @ItemLedgEntryNo 
	  --AND [Location Code] ='CITY_SR'
	  IF ISNULL(@EntryType,-1) = -1 
	  BEGIN   
		 Break  
	  END 
  
	  IF @EntryType = 4 BEGIN   
		  SET @EntryNo =NULL   
		  SET @InEntryNo = NULL   
		  SET @ItemLedgEntryNo = NULL   
		  SET @ReturnPostingDate = NULL  
		  SET @EntryType = NULL 
		  END ELSE BEGIN   
		  SET @FinalReturnPostingDate = @ReturnPostingDate   
			Break  
		  END 
	  END 
	  IF @FinalReturnPostingDate is not null BEGIN  
		INSERT INTO #PartsStockValue([Item No_],[Posting Date],[Quantity])  VALUES(@ItemNo,@FinalReturnPostingDate,@Qty) 
	  END  
	  FETCH NEXT FROM C_Entry_Negtive INTO @PostingDate,@DocumentNo,@ItemNo,@Qty,@TransQty 
  END
  CLOSE C_Entry_Negtive; 
  DEALLOCATE C_Entry_Negtive; 
   
  WITH FINAL_CTE AS
  ( SELECT [Item No_],[Period],SUM([Quantity]) AS [Quantity] 
  FROM (  SELECT [Item No_] COLLATE DATABASE_DEFAULT AS [Item No_],  
	  CASE WHEN [Posting Date] <= @ToPostingDate AND [Posting Date] > DATEADD(MONTH,-6,@ToPostingDate) THEN 1  
	  WHEN [Posting Date] <= DATEADD(MONTH,-6,@ToPostingDate) AND [Posting Date] > DATEADD(MONTH,-12,@ToPostingDate) THEN 2 
	  WHEN [Posting Date] <= DATEADD(MONTH,-12,@ToPostingDate) AND [Posting Date] > DATEADD(MONTH,-24,@ToPostingDate) THEN 3  
	  WHEN [Posting Date] <= DATEADD(MONTH,-24,@ToPostingDate) THEN 4 
	  ELSE 0  
	  END AS [Period],
	  [Quantity]  
	  FROM [PCNBHJ$Item Ledger Entry] As T1 WITH(NOLOCK)  
	  WHERE [Quantity] >0 AND [Entry Type] <> 4 AND [Posting Date] <= @ToPostingDate  
	  AND EXISTS(SELECT 1 FROM [PCNBHJ$Item] WITH(NOLOCK) WHERE [No_] = T1.[Item No_])
	  --AND [Location Code] ='CITY_SR' 
	  UNION ALL 
	  SELECT [Item No_], 
	  CASE WHEN [Posting Date] <= @ToPostingDate AND [Posting Date] > DATEADD(MONTH,-6,@ToPostingDate) THEN 1 
	  WHEN [Posting Date] <= DATEADD(MONTH,-6,@ToPostingDate) AND [Posting Date] > DATEADD(MONTH,-12,@ToPostingDate) THEN 2 
	  WHEN [Posting Date] <= DATEADD(MONTH,-12,@ToPostingDate) AND [Posting Date] > DATEADD(MONTH,-24,@ToPostingDate) THEN 3 
	  WHEN [Posting Date] <= DATEADD(MONTH,-24,@ToPostingDate) THEN 4 
	  ELSE 0 END AS [Period],
	  [Quantity] FROM  #PartsStockValue 
	  UNION ALL 
	  SELECT [Item No_] COLLATE DATABASE_DEFAULT AS [Item No_], 
	  CASE WHEN [Posting Date] <= @ToPostingDate AND [Posting Date] > DATEADD(MONTH,-6,@ToPostingDate) THEN 1
	  WHEN [Posting Date] <= DATEADD(MONTH,-6,@ToPostingDate) AND [Posting Date] > DATEADD(MONTH,-12,@ToPostingDate) THEN 2 
	  WHEN [Posting Date] <= DATEADD(MONTH,-12,@ToPostingDate) AND [Posting Date] > DATEADD(MONTH,-24,@ToPostingDate) THEN 3 
	  WHEN [Posting Date] <= DATEADD(MONTH,-24,@ToPostingDate) THEN 4 
	  ELSE 0 END AS [Period],
	  T2.[Quantity] 
	  FROM [PCNBHJ$Item Ledger Entry] AS T1 WITH(NOLOCK)
	  INNER JOIN ( SELECT TIAE.[Quantity],TIAE.[Inbound Item Entry No_] 
		   FROM [PCNBHJ$Item Ledger Entry] AS TILE WITH(NOLOCK) 
		   INNER JOIN [PCNBHJ$Item] AS TItem WITH(NOLOCK) ON TItem.[Item Type] =1 AND [Item No_] = [No_] AND [No_] <> '' 
		   INNER JOIN [PCNBHJ$Item Application Entry] AS TIAE WITH(NOLOCK) 
		   ON TIAE.[Item Ledger Entry No_] = TILE.[Entry No_] AND TIAE.[Outbound Item Entry No_] = TILE.[Entry No_] 
		   WHERE TILE.[Quantity] <= 0 AND TILE.[Posting Date] <= @ToPostingDate AND TILE.[Item Type] = 1 
		   ) AS T2 
		   ON T1.[Entry No_] = T2.[Inbound Item Entry No_] 
		   WHERE T1.[Entry Type] <> 4 
		   --AND T1.[Location Code] ='CITY_SR' --
		   ) AS Temp 
	  GROUP BY [Item No_],[Period] ) ,
			   FINAL_QTY_CTE AS 
			   (SELECT [Item No_],[Period],[Quantity], 
			   (SELECT SUM([Quantity]) FROM FINAL_CTE AS T2 WHERE T1.[Item No_]= T2.[Item No_] AND [Period] <> 0 ) As [Total Quantity] FROM FINAL_CTE AS T1 ) ,
	           FINAL_COST_CTE AS 
	           (SELECT [Item No_], SUM([Total Cost]) As [Total Cost]
	            FROM  ( SELECT [Entry No_],[Item No_], 
	               CASE WHEN SUM([Reverse Expected Cost]) <> 0 THEN SUM([Adjusted Cost]) ELSE SUM([Expected Adjusted Cost]) END AS [Total Cost]   
	               FROM (SELECT T1.[Entry No_],T1.[Item No_],CASE WHEN [Expected Cost] = 0 THEN 1 ELSE 0 END AS [Reverse Expected Cost],    
	                     CASE WHEN [Expected Cost] = 0  THEN SUM([Adjusted Cost]) ELSE 0  END AS [Adjusted Cost],  
	                     CASE WHEN [Expected Cost] = 1  THEN SUM([Adjusted Cost]) ELSE 0  END AS [Expected Adjusted Cost]    
	                     FROM [PCNBHJ$Item Ledger Entry] AS T1 WITH(NOLOCK)   
	                     INNER JOIN [PCNBHJ$Value Entry] AS T2 WITH(NOLOCK) 
		                 ON T2.[Posting Date] <= @ToPostingDate AND [Item Ledger Entry No_] = T1.[Entry No_] AND [Inventoriable] =1   
		                 WHERE T1.[Posting Date] <= @ToPostingDate AND T1.[Item Type] = 1   
		                 AND EXISTS(SELECT 1 FROM [PCNBHJ$Item] WITH(NOLOCK) WHERE [No_] = T1.[Item No_])   
		                 --AND T1.[Location Code] ='CITY_SR'    
		                 GROUP BY T1.[Entry No_],T1.[Item No_],[Expected Cost]   
		                )AS Temp1   
		                Group by [Entry No_],[Item No_]  ) AS Temp2 
		                GROUP BY [Item No_] 
		        ) 
		  
		SELECT [Period],SUM([Period Cost]) AS [Period Cost]
		FROM (SELECT CASE WHEN [Total Quantity] IS NULL THEN 1 ELSE [Period] END AS [Period], 
		    CASE WHEN ISNULL([Total Quantity],0) <> 0 THEN ([Total Cost]/[Total Quantity]) * [Quantity] ELSE [Total Cost] END AS [Period Cost]  
		    FROM FINAL_COST_CTE AS T2  LEFT JOIN FINAL_QTY_CTE AS T1 
		    ON T1.[Item No_] = T2.[Item No_] AND T1.[Total Quantity] <> 0
		    ) AS Temp3 
		GROUP BY [Period] 
DROP TABLE #PartsStockValue


```