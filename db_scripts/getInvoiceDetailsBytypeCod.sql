DROP PROCEDURE IF EXISTS  getInvoiceDetailsBytypeCode;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `getInvoiceDetailsBytypeCode`(IN type_code varchar(10))
BEGIN

	DROP TABLE IF EXISTS   Temp_lnRecordTable;

    CREATE  TEMPORARY TABLE Temp_lnRecordTable (
	id int primary key NOT NULL AUTO_INCREMENT,
    invoice_id bigint,
    amount bigint,
    payment_id bigint,
    invoice_number varchar(255)

);

INSERT into Temp_lnRecordTable (invoice_id,amount,payment_id,invoice_number)
(
	SELECT
		i.id AS invoice_id ,
        pd.amount AS amount ,
        pd.id AS payment_id,
        i.invoice_number AS invoice_number

		FROM
		invoices i
		INNER JOIN payment_detail pd ON i.id =pd.fk_invoice_id
		WHERE pd.fk_type_code = type_code
);
SELECT SUM(IFNULL(pdc.amount,0)) AS paid_amount,
				ipd.payment_id,
				ipd.amount,
                ipd.invoice_id,
                ipd.invoice_number

FROM  Temp_lnRecordTable AS ipd
LEFT JOIN payment_detail_of_credit pdc ON pdc.fk_payment_detail_id = ipd.payment_id
GROUP BY  ipd.payment_id;


END//

DELIMITER ;