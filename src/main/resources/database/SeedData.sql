INSERT INTO PRO_CATEGORIES(ID, CATEGORY_ID, CATEGORY_NAME, SUB_CATEGORY_ID, SUB_CATEGORY_NAME) 
values(1, 1, "Incense Stick", 0, null);
INSERT INTO PRO_CATEGORIES(ID, CATEGORY_ID, CATEGORY_NAME, SUB_CATEGORY_ID, SUB_CATEGORY_NAME)
values(2, 1, "Incense Stick", 1, "Raw Material");
INSERT INTO PRO_CATEGORIES(ID, CATEGORY_ID, CATEGORY_NAME, SUB_CATEGORY_ID, SUB_CATEGORY_NAME) 
values(3, 1, "Incense Stick", 2, "Unscented Incense Stick");
INSERT INTO PRO_CATEGORIES(ID, CATEGORY_ID, CATEGORY_NAME, SUB_CATEGORY_ID, SUB_CATEGORY_NAME) 
values(4, 1, "Incense Stick", 3, "Scented Incense Stick");
INSERT INTO PRO_CATEGORIES(ID, CATEGORY_ID, CATEGORY_NAME, SUB_CATEGORY_ID, SUB_CATEGORY_NAME)
values(5, 1, "Incense Stick", 4, "Packed Incense Stick");

INSERT INTO PRO_METRIC_UNITS(METRIC_ID, UNIT_TYPE, UNIT_NAME, UNIT_SYMBOL)
values(1, "LENGTH", "Millimeter", "mm");
INSERT INTO PRO_METRIC_UNITS(METRIC_ID, UNIT_TYPE, UNIT_NAME, UNIT_SYMBOL)
values(2, "LENGTH", "Centimeter", "cm");
INSERT INTO PRO_METRIC_UNITS(METRIC_ID, UNIT_TYPE, UNIT_NAME, UNIT_SYMBOL)
values(3, "LENGTH", "Meter", "m");
INSERT INTO PRO_METRIC_UNITS(METRIC_ID, UNIT_TYPE, UNIT_NAME, UNIT_SYMBOL)
values(4, "LENGTH", "Kilometer", "km");
INSERT INTO PRO_METRIC_UNITS(METRIC_ID, UNIT_TYPE, UNIT_NAME, UNIT_SYMBOL)
values(5, "MASS", "Kilogram", "kg");
INSERT INTO PRO_METRIC_UNITS(METRIC_ID, UNIT_TYPE, UNIT_NAME, UNIT_SYMBOL)
values(6, "MASS", "Gram", "g");
INSERT INTO PRO_METRIC_UNITS(METRIC_ID, UNIT_TYPE, UNIT_NAME, UNIT_SYMBOL)
values(7, "PACKET", "Packet", "packet");

INSERT INTO pro_products(product_id, product_code, product_name, category_id, sub_category_id)
values(1, "BAMBOO_INCENSE_STICK", "Bamboo Incense Stick", 1, 1);
INSERT INTO pro_products(product_id, product_code, product_name, category_id, sub_category_id)
values(2, "AGARBATTI_PREMIX_POWDER", "Agarbatti Premix Powder", 1, 1);
INSERT INTO pro_products(product_id, product_code, product_name, category_id, sub_category_id)
values(3, "BROWN_JOSH_POWDER", "Brown Josh Powder", 1, 1);
INSERT INTO pro_products(product_id, product_code, product_name, category_id, sub_category_id)
values(4, "PREMIX_AND_JOSH_POWDER", "Premix and Josh Powder", 1, 1);
INSERT INTO pro_products(product_id, product_code, product_name, category_id, sub_category_id)
values(5, "UNSCENTED_INCENSE_STICK", "Unscented Incense Stick", 1, 2);
INSERT INTO pro_products(product_id, product_code, product_name, category_id, sub_category_id)
values(6, "SCENTED_INCENSE_STICK", "Scented Incense Stick", 1, 3);

INSERT INTO PERSON(name,message) values("Varun","Hey this is Varun");
INSERT INTO PERSON(name,message) values("Joe","Hey this is Joe");