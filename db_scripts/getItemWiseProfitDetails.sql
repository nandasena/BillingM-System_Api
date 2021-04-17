DROP PROCEDURE IF EXISTS getItemWiseDetailsByDateRange;

DELIMITER
//

CREATE
DEFINER=`root`@`localhost` PROCEDURE `getItemWiseDetailsByDateRange`(IN form_date varchar(20),IN to_date varchar(20))
BEGIN

select id.*, i.name
from (select date (created_at),
     sum(selling_quantity) as selling_quantity,
     IFNULL(sum(total_amount), 0) as total_amount,
     IFNULL(sum(total_item_discount), 0) as total_item_discount,
     IFNULL(sum(item_cost*selling_quantity), 0) as item_cost,
     item_id from sivilima.invoice_item_Details
where created_at >= form_date
  and created_at <= to_date
group by item_id
    --     ,item_cost, total_item_discount, total_amount, selling_quantity, created_at
    ) id
    inner join items i
on i.id = id.item_id;

END
//

DELIMITER ;
