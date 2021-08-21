DROP PROCEDURE IF EXISTS  getInvoiceByDateRange;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `getInvoiceByDateRange`(IN form_date varchar(20),IN to_date varchar(20))
BEGIN

SELECT I.id,
			   I.invoice_number,
			   I.customer_name,
               I.invoice_date,
               I.total_amount,
               I.total_discount,
               P.fk_type_code
FROM invoices I
INNER JOIN payment_detail P ON I.id =P.fk_invoice_id
WHERE  DATE(I.created_at)>=form_date and date(I.created_at)<to_date
order by I.id DESC;

END //

DELIMITER ;
