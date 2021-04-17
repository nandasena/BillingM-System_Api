
DROP PROCEDURE IF EXISTS getInvoiceDetailsByDateRange;

DELIMITER //

CREATE
DEFINER=`root`@`localhost` PROCEDURE `getInvoiceDetailsByDateRange`(IN form_date varchar(20),IN to_date varchar(20))
BEGIN

select iid.*, sum(iid.total_cost) as total_invoice_cost
from (select i.id as invoice_id,
             i.invoice_number,
             i.total_amount,
             i.invoice_discount,
             i.total_discount,
          date (i.invoice_date),
     id.item_price,
     id.selling_quantity,
     id.total_item_discount,
     IFNULL(id.item_cost, 0) as item_cost,
     id.discount_type,
     id.item_id,

     TRUNCATE(
             (IFNULL(id.item_cost, 0) * IFNULL(id.selling_quantity, 0)), 2)
         as total_cost from (select * from invoices  where date (invoice_date) >=form_date and date (invoice_date) <=to_date) i
        inner join invoice_item_Details  id
on i.id =id.invoice_id
    ) as iid
group by iid.invoice_id
    -- ,iid.invoice_number, iid.total_amount, iid.invoice_discount, iid.total_discount, iid.item_price,
    -- iid.selling_quantity, iid.total_item_discount, iid.item_cost, iid.discount_type, iid.item_id, iid.total_cost
order by iid.invoice_id;

END
//

DELIMITER ;
