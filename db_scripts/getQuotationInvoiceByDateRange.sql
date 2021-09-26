DROP PROCEDURE IF EXISTS  getQuotationInvoiceByDateRange;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `getQuotationInvoiceByDateRange`(IN form_date varchar(20),IN to_date varchar(20))
BEGIN

SELECT q.id,
       q.invoice_number,
       q.customer_name,
       q.invoice_date,
       q.total_amount,
       q.total_discount
FROM quotation q
WHERE  DATE(q.created_at)>=form_date and date(q.created_at)<to_date
order by q.id DESC;

END //

DELIMITER ;
