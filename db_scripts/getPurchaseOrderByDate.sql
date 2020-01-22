DROP PROCEDURE IF EXISTS  getPurchaseOrderByDate;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `getPurchaseOrderByDate`(IN form_date varchar(20),IN to_date varchar(20))
BEGIN

SELECT  po.id ,
        po.purchase_code,
        sp.first_name AS supplier_name,
        sp.address_1,
        sp.address_2,
        us.name AS user_name,
        DATE(po.estimate_receive_date),
        po.total_amount,
        po.total_discount,
        us.id AS user_id,
        sp.supplier_id AS supplier_is
FROM (SELECT * FROM purchase_order pro
WHERE DATE( pro.created_at)>= form_date AND Date(pro.created_at)<=to_date) AS po
LEFT JOIN supplier sp ON sp.supplier_id =po.fk_supplier_id
LEFT JOIN users us ON po.fk_user_id = us.id;

END //

DELIMITER ;