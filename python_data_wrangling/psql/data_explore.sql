-- Show table schema 
\d+ retail;

-- Show first 10 rows
SELECT * FROM retail limit 10;

-- Check # of records
 SELECT count(*) FROM retail;

-- number of clients (e.g. unique client ID)
SELECT count(DISTINCT customer_id) FROM retail;

--  invoice date range (e.g. max/min dates)
SELECT MAX(invoice_date), MIN(invoice_date) FROM retail;

-- number of SKU/merchants (e.g. unique stock code)
SELECT count(DISTINCT stock_code) FROM retail;

-- Q6: Calculate average invoice amount excluding invoices with a negative amount (e.g. canceled orders have negative amount)
SELECT avg(unit_price * quantity) FROM retail
WHERE quantity >= 0
GROUP BY invoice_no;

-- Calculate total revenue (e.g. sum of unit_price * quantity)
SELECT sum(unit_price * quantity) FROM retail;

-- Calculate total revenue by YYYYMM 
SELECT (CAST(EXTRACT(YEAR FROM invoice_date) AS INTEGER) * 100 + CAST(EXTRACT(MONTH FROM invoice_date) AS INTEGER)) AS yyyymm ,sum(unit_price * quantity) FROM retail
GROUP BY yyyymm;



