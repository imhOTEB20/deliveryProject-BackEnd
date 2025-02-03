-- -----------------------------------------------------
-- Table `delivery_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_db`.`users` (
  `id_user` BIGINT NOT NULL AUTO_INCREMENT,
  `auth0_id` VARCHAR(255) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `user_type` ENUM('ADMIN', 'CUSTOMER', 'DELIVERY') NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_db`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_db`.`customers` (
  `id_customer` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(11) NOT NULL,
  `address` VARCHAR(150) NOT NULL,
  `id_user` BIGINT NOT NULL,
  PRIMARY KEY (`id_customer`),
  INDEX `fk_customers_users1_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `fk_customers_users1`
    FOREIGN KEY (`id_user`)
    REFERENCES `delivery_db`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_db`.`administrators`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_db`.`administrators` (
  `id_admin` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(11) NOT NULL,
  `id_user` BIGINT NOT NULL,
  PRIMARY KEY (`id_admin`),
  INDEX `fk_admins_users1_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `fk_admins_users1`
    FOREIGN KEY (`id_user`)
    REFERENCES `delivery_db`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_db`.`dishes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_db`.`dishes` (
  `id_dish` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL,
  `price` DECIMAL(10,2) NULL,
  `available` TINYINT NULL DEFAULT 1,
  `modify_by` BIGINT NULL,
  `img` BLOB NOT NULL,
  PRIMARY KEY (`id_dish`),
  INDEX `fk_dishes_administrators1_idx` (`modify_by` ASC) VISIBLE,
  CONSTRAINT `fk_dishes_administrators1`
    FOREIGN KEY (`modify_by`)
    REFERENCES `delivery_db`.`administrators` (`id_admin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_db`.`promotions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_db`.`promotions` (
  `id_promotion` BIGINT NOT NULL AUTO_INCREMENT,
  `type` ENUM('COMBO', 'DISCOUNT') NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL,
  `minimum_amount` DECIMAL(10,2) NULL DEFAULT NULL,
  `discount_percentage` DECIMAL(5,2) NULL DEFAULT NULL,
  `available` TINYINT NULL DEFAULT 1,
  `price` DECIMAL(10,2) NULL,
  `modify_by` BIGINT NULL,
  `img` BLOB NOT NULL,
  PRIMARY KEY (`id_promotion`),
  INDEX `fk_promotions_admins1_idx` (`modify_by` ASC) VISIBLE,
  CONSTRAINT `fk_promotions_admins1`
    FOREIGN KEY (`modify_by`)
    REFERENCES `delivery_db`.`administrators` (`id_admin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_db`.`combos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_db`.`combos` (
  `id_combo` BIGINT NOT NULL AUTO_INCREMENT,
  `dish` BIGINT NOT NULL,
  `promotion` BIGINT NOT NULL,
  `quantity` SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_combo`),
  INDEX `fk_combos_promotions1_idx` (`promotion` ASC) VISIBLE,
  INDEX `fk_combos_dishes1_idx` (`dish` ASC) VISIBLE,
  CONSTRAINT `fk_combos_promotions1`
    FOREIGN KEY (`promotion`)
    REFERENCES `delivery_db`.`promotions` (`id_promotion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_combos_dishes1`
    FOREIGN KEY (`dish`)
    REFERENCES `delivery_db`.`dishes` (`id_dish`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_db`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_db`.`orders` (
  `id_order` BIGINT NOT NULL AUTO_INCREMENT,
  `customer` BIGINT NOT NULL,
  `delivery_address` VARCHAR(150) NOT NULL,
  `status` ENUM('PENDING', 'ACCEPTED', 'REJECTED', 'CANCELED', 'SUBMITTED', 'COMPLETED') NULL DEFAULT 'PENDING',
  `payment_method` ENUM('TRANSFER', 'CASH') NULL,
  `order_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_order`),
  INDEX `fk_orders_customers1_idx` (`customer` ASC) VISIBLE,
  CONSTRAINT `fk_orders_customers1`
    FOREIGN KEY (`customer`)
    REFERENCES `delivery_db`.`customers` (`id_customer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_db`.`order_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_db`.`order_details` (
  `id_order_detail` BIGINT NOT NULL AUTO_INCREMENT,
  `order` BIGINT NOT NULL,
  `dish` BIGINT NOT NULL,
  `promotion` BIGINT NOT NULL,
  `quantity` SMALLINT NULL DEFAULT 1,
  `details` VARCHAR(150) NULL,
  PRIMARY KEY (`id_order_detail`),
  INDEX `fk_orderdetails_orders1_idx` (`order` ASC) VISIBLE,
  INDEX `fk_orderdetails_dishes1_idx` (`dish` ASC) VISIBLE,
  INDEX `fk_orderdetails_promotions1_idx` (`promotion` ASC) VISIBLE,
  CONSTRAINT `fk_orderdetails_orders1`
    FOREIGN KEY (`order`)
    REFERENCES `delivery_db`.`orders` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderdetails_dishes1`
    FOREIGN KEY (`dish`)
    REFERENCES `delivery_db`.`dishes` (`id_dish`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderdetails_promotions1`
    FOREIGN KEY (`promotion`)
    REFERENCES `delivery_db`.`promotions` (`id_promotion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_db`.`delivery_drivers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_db`.`delivery_drivers` (
  `id_delivery_driver` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(11) NOT NULL,
  `last_activity` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `id_user` BIGINT NOT NULL,
  PRIMARY KEY (`id_delivery_driver`),
  INDEX `fk_deliverydrivers_users1_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `fk_deliverydrivers_users1`
    FOREIGN KEY (`id_user`)
    REFERENCES `delivery_db`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_db`.`assignments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_db`.`assignments` (
  `id_assignment` BIGINT NOT NULL AUTO_INCREMENT,
  `order` BIGINT NOT NULL,
  `assignment_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `delivery_assigned` BIGINT NOT NULL,
  `assigned_by` BIGINT NOT NULL,
  PRIMARY KEY (`id_assignment`),
  INDEX `fk_assignments_orders1_idx` (`order` ASC) VISIBLE,
  INDEX `fk_assignments_deliverydrivers1_idx` (`delivery_assigned` ASC) VISIBLE,
  INDEX `fk_assignments_administrators1_idx` (`assigned_by` ASC) VISIBLE,
  CONSTRAINT `fk_assignments_orders1`
    FOREIGN KEY (`order`)
    REFERENCES `delivery_db`.`orders` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_assignments_deliverydrivers1`
    FOREIGN KEY (`delivery_assigned`)
    REFERENCES `delivery_db`.`delivery_drivers` (`id_delivery_driver`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_assignments_administrators1`
    FOREIGN KEY (`assigned_by`)
    REFERENCES `delivery_db`.`administrators` (`id_admin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
