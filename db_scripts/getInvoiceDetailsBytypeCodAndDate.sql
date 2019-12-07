DROP PROCEDURE IF EXISTS  getInvoiceDetailsByTypeCodeAndDate;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `getInvoiceDetailsByTypeCodeAndDate`(IN type_code varchar(10),IN form_date varchar(20),IN to_date varchar(20))
BEGIN

	DROP TABLE IF EXISTS   Temp_lnRecordTable;

    CREATE  TEMPORARY TABLE Temp_lnRecordTable (
	id int primary key NOT NULL AUTO_INCREMENT,
    invoice_id bigint,
    amount bigint,
    payment_id bigint,
    invoice_number varchar(255),
    customer_id int,
    first_name varchar(255),
    type varchar (10),
    payment_detail_id int
);

INSERT into Temp_lnRecordTable (invoice_id,amount,payment_id,invoice_number,customer_id,first_name,type,payment_detail_id)
(
	SELECT
		i.id AS invoice_id ,
        pd.amount AS amount ,
        pd.id AS payment_id,
        i.invoice_number AS invoice_number,
		i.customer_id AS customer_id,
	    c.first_name AS first_name,
	    pd.fk_type_code AS type,
	    pd.id AS payment_detail_id

		FROM
		invoices i
		INNER JOIN payment_detail pd ON i.id =pd.fk_invoice_id
        LEFT JOIN customer c ON i.customer_id =c.id
		WHERE pd.fk_type_code = type_code
        AND DATE( i.created_at ) >form_date  AND DATE(i.created_at) <= to_date
);
SELECT SUM(IFNULL(pdc.amount,0)) AS paid_amount,
				ipd.payment_id,
				ipd.amount,
                ipd.invoice_id,
                ipd.invoice_number,
                ipd.customer_id,
                ipd.first_name,
                ipd.type,
                ipd.payment_detail_id

FROM  Temp_lnRecordTable AS ipd
LEFT JOIN payment_detail_of_credit pdc ON pdc.fk_payment_detail_id = ipd.payment_id
GROUP BY  ipd.payment_id;


END//

DELIMITER ;