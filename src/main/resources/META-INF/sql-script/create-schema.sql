-- --------------------------------------------------------
-- Host:                         C:\WorkDirectory\minikhata-spring-sqlite\sqlitesample.db
-- Server version:               3.34.0
-- Server OS:                    
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table sqlitesample.hibernate_sequence
DROP TABLE IF EXISTS "hibernate_sequence";
CREATE TABLE IF NOT EXISTS hibernate_sequence (next_val bigint);
INSERT INTO hibernate_sequence VALUES(0);

-- Data exporting was unselected.

-- Dumping structure for table sqlitesample.pro_categories
DROP TABLE IF EXISTS "pro_categories";
CREATE TABLE IF NOT EXISTS pro_categories (id integer not null primary key autoincrement, category_id bigint not null, category_name varchar not null, sub_category_id bigint not null, sub_category_name varchar, version_id integer);

-- Data exporting was unselected.

-- Dumping structure for table sqlitesample.pro_customers
DROP TABLE IF EXISTS "pro_customers";
CREATE TABLE IF NOT EXISTS pro_customers (customer_id integer not null primary key autoincrement, address1 VARCHAR(100) DEFAULT NULL, address2 VARCHAR(100) DEFAULT NULL, city INT(15) DEFAULT NULL, country INT(15) DEFAULT NULL, created_by VARCHAR(60) NOT NULL DEFAULT 'SEED_DATA_FROM_APPLICATION' not null, creation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP not null, customer_email VARCHAR(40) DEFAULT NULL, customer_gstin varchar, customer_mobile VARCHAR(40) NOT NULL not null, customer_name VARCHAR(40) NOT NULL not null, customer_phone_home VARCHAR(40) DEFAULT NULL, isdeleted VARCHAR(1) DEFAULT 'Y' not null, last_update_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP not null, last_updated_by VARCHAR(60) NOT NULL DEFAULT 'SEED_DATA_FROM_APPLICATION' not null, postcode VARCHAR(40) DEFAULT NULL, ship_address1 VARCHAR(40) DEFAULT NULL, ship_address2 VARCHAR(40) DEFAULT NULL, ship_city INT(15) DEFAULT NULL, ship_country INT(15) DEFAULT NULL, ship_postcode VARCHAR(40) DEFAULT NULL, ship_state INT(15) DEFAULT NULL, state INT(15) DEFAULT NULL, version_id integer);

-- Data exporting was unselected.

-- Dumping structure for table sqlitesample.pro_file_execution_entries
DROP TABLE IF EXISTS "pro_file_execution_entries";
CREATE TABLE IF NOT EXISTS pro_file_execution_entries (id integer not null primary key autoincrement, created_by varchar not null, creation_date date not null, execution_id bigint not null, file_path varchar not null, last_update_date date not null, last_updated_by varchar not null, version_id bigint);

-- Data exporting was unselected.

-- Dumping structure for table sqlitesample.pro_manufactures
DROP TABLE IF EXISTS "pro_manufactures";
CREATE TABLE IF NOT EXISTS pro_manufactures (manufacture_id integer not null primary key autoincrement, is_delete_allowed varchar(3) default 'Y' not null, manufacture_cost double, manufacture_date date, manufacture_notes varchar, manufacture_quantity double, product_id bigint not null, related_purchase_id bigint, version_id integer);

-- Data exporting was unselected.

-- Dumping structure for table sqlitesample.pro_manufacture_product_map
DROP TABLE IF EXISTS "pro_manufacture_product_map";
CREATE TABLE IF NOT EXISTS pro_manufacture_product_map (id integer not null primary key autoincrement, manufacture_id bigint not null, product_id bigint not null, product_quantity double not null, version_id integer);

-- Data exporting was unselected.

-- Dumping structure for table sqlitesample.pro_metric_units
DROP TABLE IF EXISTS "pro_metric_units";
CREATE TABLE IF NOT EXISTS pro_metric_units (metric_id integer not null primary key autoincrement, unit_name varchar not null, unit_symbol varchar not null, unit_type varchar not null, version_id integer);

-- Data exporting was unselected.

-- Dumping structure for table sqlitesample.pro_products
DROP TABLE IF EXISTS "pro_products";
CREATE TABLE IF NOT EXISTS pro_products (product_id integer not null primary key autoincrement, category_id bigint not null, description varchar, product_barcode varchar, product_code varchar not null, product_image varchar, product_link varchar, product_name varchar not null, sub_category_id bigint not null, version_id integer default 0);

-- Data exporting was unselected.

-- Dumping structure for table sqlitesample.pro_purchases
DROP TABLE IF EXISTS "pro_purchases";
CREATE TABLE IF NOT EXISTS pro_purchases (purchase_id integer not null primary key autoincrement, amount_before_tax double, bill_number varchar not null, discount_amount double, gst_amount double, is_amount_settled varchar not null, is_consumed varchar not null, payable_amount double, product_id bigint not null, purchase_date date, purchase_notes varchar, purchase_quantity double, purchase_type_id bigint default 1 not null, version_id integer);

-- Data exporting was unselected.

-- Dumping structure for table sqlitesample.pro_purchase_manufacture_map
DROP TABLE IF EXISTS "pro_purchase_manufacture_map";
CREATE TABLE IF NOT EXISTS pro_purchase_manufacture_map (id integer not null primary key autoincrement, in_quantity double, manufacture_id bigint, out_quantity double, product_id bigint not null, purchase_id bigint, version_id integer);

-- Data exporting was unselected.

-- Dumping structure for table sqlitesample.pro_purchase_types
DROP TABLE IF EXISTS "pro_purchase_types";
CREATE TABLE IF NOT EXISTS pro_purchase_types (purchase_type_id integer not null primary key autoincrement, description varchar, purchase_type_code varchar not null, version_id integer);

-- Data exporting was unselected.

-- Dumping structure for table sqlitesample.pro_sells
DROP TABLE IF EXISTS "pro_sells";
CREATE TABLE IF NOT EXISTS pro_sells (
	sell_id integer not null primary key autoincrement, 
	customer_id bigint not null,   
	sell_date date, 
	sell_notes varchar, 
	is_amount_settled varchar(3) default 'N' not null,
	is_delete_allowed varchar(3) default 'Y' not null,
	created_by VARCHAR(60) NOT NULL DEFAULT 'SEED_DATA_FROM_APPLICATION' not null, 
	creation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP not null, 
	last_update_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP not null, 
	last_updated_by VARCHAR(60) NOT NULL DEFAULT 'SEED_DATA_FROM_APPLICATION' not null,
	version_id integer);

-- Data exporting was unselected.

-- Dumping structure for table sqlitesample.pro_sell_product_maps
DROP TABLE IF EXISTS "pro_sell_product_maps";
CREATE TABLE IF NOT EXISTS pro_sell_product_maps (id integer not null primary key autoincrement, amount_before_tax double, discount_amount double, gst_amount double, payable_amount double, product_id bigint not null, sell_id bigint not null, sell_quantity double, version_id integer);

-- Data exporting was unselected.

-- Dumping structure for table sqlitesample.pro_stocks
DROP TABLE IF EXISTS "pro_stocks";
CREATE TABLE IF NOT EXISTS pro_stocks (stock_id integer not null primary key autoincrement, manufacture_id bigint, manufacture_quantity double, product_id bigint not null, purchase_id bigint, purchase_quantity double, sell_id bigint, sell_quantity double, version_id integer);

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
